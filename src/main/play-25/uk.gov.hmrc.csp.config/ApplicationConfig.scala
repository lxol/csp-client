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

package uk.gov.hmrc.csp.config

import javax.inject.{Inject, Singleton}
import play.api.{Configuration, Mode}
import uk.gov.hmrc.play.config.ServicesConfig

@Singleton
class ApplicationConfig @Inject()(val runmode: Mode.Mode, configuration: Configuration) extends ServicesConfig {

  override protected def mode = runmode

  override protected def runModeConfiguration: play.api.Configuration = configuration

  lazy val serviceUrl: String = baseUrl("csp-partials") + "/csp-partials"
}
