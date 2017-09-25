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

  lazy val microservice = Project(appName, file("."))
    .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning)
    .settings(scalaSettings: _*)
    .settings(defaultSettings(): _*)
    .settings(
      targetJvm := "jvm-1.8",
      libraryDependencies ++= compile ++ testCompile,
      crossScalaVersions := Seq("2.11.7", "2.10.4")
    )
    .settings(
      resolvers := Seq(
        Resolver.bintrayRepo("hmrc", "releases"),
        Resolver.typesafeRepo("releases"),
        Resolver.jcenterRepo
      )
    )

  val compile = Seq(
    "uk.gov.hmrc" %% "play-config" % "4.3.0",
    "uk.gov.hmrc" %% "logback-json-logger" % "3.1.0",
    "uk.gov.hmrc" %% "play-partials" % "6.0.0",
    "uk.gov.hmrc" %% "http-verbs" % "7.2.0"
  )

  val testCompile = Seq(
    "org.scalatest"          %% "scalatest"            % "2.2.6"             % "test",
    "com.typesafe.play"      %% "play-test"            % PlayVersion.current % "test",
    "org.mockito"            % "mockito-all"           % "1.10.19"             % "test",
    "uk.gov.hmrc"            %% "hmrctest"             % "2.3.0"             % "test"
  )


}

object Developers {
  def apply() = developers := List[Developer]()
}
