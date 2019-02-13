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

import play.core.PlayVersion
import sbt.Keys._
import sbt._
import uk.gov.hmrc.DefaultBuildSettings._
import uk.gov.hmrc.{SbtArtifactory, SbtAutoBuildPlugin}
import uk.gov.hmrc.versioning.SbtGitVersioning

object HmrcBuild extends Build {


  import uk.gov.hmrc.SbtArtifactory.autoImport.makePublicallyAvailableOnBintray
  import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion

  val appName = "csp-client"
  lazy val plugins : Seq[Plugins] = Seq(play.sbt.PlayScala)

  lazy val microservice = Project(appName, file("."))
    .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
    .settings(majorVersion := 3)
    .settings(makePublicallyAvailableOnBintray := true)
    .settings(scalaSettings: _*)
    .settings(defaultSettings(): _*)
    .settings(
      scalaVersion := "2.11.11",
      targetJvm := "jvm-1.8",
      libraryDependencies ++= compile ++ testCompile
    )
    .settings(
      resolvers := Seq(
        Resolver.jcenterRepo,
        Resolver.bintrayRepo("hmrc","releases")
      )
    )

  val compile = Seq(
    "uk.gov.hmrc" %% "play-config" % "7.2.0",
    "uk.gov.hmrc" %% "logback-json-logger" % "3.1.0",
    "uk.gov.hmrc" %% "play-partials" % "6.5.0",
    "uk.gov.hmrc" %% "http-verbs" % "9.1.0-play-25"
  )

  val testCompile = Seq(
    "org.scalatest" %% "scalatest" % "2.2.6" % "test",
    "com.typesafe.play" %% "play-test" % PlayVersion.current % "test",
    "org.mockito" % "mockito-all" % "1.10.19" % "test",
    "uk.gov.hmrc" %% "hmrctest" % "3.4.0-play-25" % "test"
  )

}

object Developers {
  def apply() = developers := List[Developer]()
}
