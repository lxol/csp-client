import play.core.PlayVersion
import sbt.Keys._
import sbt._
import uk.gov.hmrc.DefaultBuildSettings._
import uk.gov.hmrc.PublishingSettings._
import uk.gov.hmrc.{SbtAutoBuildPlugin, ShellPrompt}
import uk.gov.hmrc.versioning.SbtGitVersioning
import uk.gov.hmrc.SbtArtifactory

object HmrcBuild extends Build {


  import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion
  import uk.gov.hmrc.SbtArtifactory.autoImport.makePublicallyAvailableOnBintray

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
        Resolver.jcenterRepo
      )
    )

  val compile = Seq(
    "uk.gov.hmrc" %% "play-config" % "5.0.0",
    "uk.gov.hmrc" %% "logback-json-logger" % "3.1.0",
    "uk.gov.hmrc" %% "play-partials" % "6.1.0",
    "uk.gov.hmrc" %% "http-verbs" % "7.2.0"
  )

  val testCompile = Seq(
    "org.scalatest"          %% "scalatest"            % "2.2.6"             % "test",
    "com.typesafe.play"      %% "play-test"            % PlayVersion.current % "test",
    "org.mockito"            % "mockito-all"           % "1.10.19"             % "test",
    "uk.gov.hmrc"            %% "hmrctest"             % "3.0.0"             % "test"
  )


}

object Developers {
  def apply() = developers := List[Developer]()
}
