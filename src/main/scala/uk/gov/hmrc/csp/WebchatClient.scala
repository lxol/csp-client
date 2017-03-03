/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.csp

import java.util.concurrent.TimeUnit

import play.api.mvc.Request
import play.twirl.api.Html
import uk.gov.hmrc.play.http.hooks.HttpHook
import uk.gov.hmrc.play.http.ws.WSGet
import uk.gov.hmrc.play.partials._
import uk.gov.hmrc.play.config.ServicesConfig
import TimeUnit._

import play.Logger

import scala.concurrent.duration.Duration


object WebchatClient extends ServicesConfig {

  lazy val serviceUrl = baseUrl("csp-partials") + "/csp-partials"


  def webchatOfferPartial()(implicit request: Request[_]): Html = {
    getPartialContent(serviceUrl + "/webchat-offers")
  }

  def webchatClickToChatScriptPartial(entryPoint: String, template: String)(implicit request: Request[_]): Html = {
    getPartialContent(serviceUrl + s"/webchat-click-to-chat/$entryPoint/$template")
  }

  private def getPartialContent(url: String)(implicit request: Request[_]) = {
    val partialContent = CachedStaticHtmlPartialProvider.getPartialContent(url)

    partialContent.body match {
      case b if b.isEmpty => {
        Logger.error(s"No content found for $url")
        Html(s"<!-- $url returned no content! -->")
      }
      case _ => partialContent
    }
  }

  object CachedStaticHtmlPartialProvider extends CachedStaticHtmlPartialRetriever with ServicesConfig {
    override val httpGet = new WSGet {
      override val hooks: Seq[HttpHook] = NoneRequired
    }

    val refreshSeconds = getConfInt("csp-partials.refreshAfter", 60)
    val expireSeconds = getConfInt("csp-partials.expireAfter", 3600)

    Logger.info(s"Setting webchat partial refresh to $refreshSeconds seconds.")
    Logger.info(s"Setting webchat partial expiration to $expireSeconds seconds.")

    override def refreshAfter: Duration = Duration(refreshSeconds, SECONDS)
    override def expireAfter: Duration = Duration(expireSeconds, SECONDS)
  }

}
