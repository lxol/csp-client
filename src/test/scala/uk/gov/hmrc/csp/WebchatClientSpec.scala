/*
 * Copyright 2019 HM Revenue & Customs
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

import org.scalatest.mockito.MockitoSugar
import org.scalatest.{Matchers, WordSpec}
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.ws.WSClient

class WebchatClientSpec extends WordSpec with MockitoSugar with Matchers {

  "WebchatClient" should {
    "retrieve timeouts as values set in config when config is available" in {

      val builder = new GuiceApplicationBuilder()
        .configure(
          "csp-partials.refreshAfter" -> 66,
          "csp-partials.expireAfter" -> 666
        )

      val configuration = builder.configuration

      val provider = new CachedStaticHtmlPartialProvider(mock[WSClient], configuration)
      val refreshAfter = provider.refreshSeconds
      val expireAfter = provider.expireSeconds

      refreshAfter shouldBe 66
      expireAfter shouldBe 666
    }

    "retrieve default timeouts as values set in config when config is unavailable" in {

      val builder = new GuiceApplicationBuilder()
      val configuration = builder.configuration

      val provider = new CachedStaticHtmlPartialProvider(mock[WSClient], configuration)
      val refreshAfter = provider.refreshSeconds
      val expireAfter = provider.expireSeconds

      refreshAfter shouldBe 60
      expireAfter shouldBe 3600
    }
  }
}
