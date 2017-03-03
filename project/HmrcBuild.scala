import play.core.PlayVersion
import sbt.Keys._
import sbt._
import uk.gov.hmrc.DefaultBuildSettings._
import uk.gov.hmrc.PublishingSettings._
import uk.gov.hmrc.{SbtAutoBuildPlugin, ShellPrompt}
import uk.gov.hmrc.versioning.SbtGitVersioning

object HmrcBuild extends Build {

  val appName = "csp-client"
  lazy val plugins : Seq[Plugins] = Seq(play.sbt.PlayScala)

  lazy val CspClient = Project(appName,  file("."))
    .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning)
    .settings(scalaSettings: _*)
    .settings(defaultSettings(): _*)
    .settings(
      targetJvm := "jvm-1.8",
      shellPrompt := ShellPrompt(appName),
      libraryDependencies ++= compile ++ testCompile,
      resolvers := Seq(
        Resolver.bintrayRepo("hmrc", "releases"), Resolver.typesafeRepo("typesafe-releases"), Resolver.jcenterRepo
      )
    )
    .settings(scalaVersion := "2.11.7")
    .settings(publishAllArtefacts: _*)
    .settings(resolvers += Resolver.bintrayRepo("hmrc", "releases"))
    .disablePlugins(sbt.plugins.JUnitXmlReportPlugin)


  val compile = Seq(
    "uk.gov.hmrc" %% "play-config" % "3.0.0",
    "uk.gov.hmrc" %% "logback-json-logger" % "3.1.0",
    "uk.gov.hmrc" %% "play-partials" % "5.2.0"
  )

  val testCompile = Seq(
    "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    "com.typesafe.play" %% "play-test" % PlayVersion.current % "test",
    "org.mockito" % "mockito-all" % "1.9.5" % "test",
    "org.pegdown" % "pegdown" % "1.5.0" % "test",
    "org.hamcrest" % "hamcrest-all" % "1.3" % "test"
  )


}

object Developers {
  def apply() = developers := List[Developer]()
}
