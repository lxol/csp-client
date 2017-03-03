credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

val hmrcRepoHost = java.lang.System.getProperty("hmrc.repo.host", "https://nexus-preview.tax.service.gov.uk")


resolvers += Resolver.url("hmrc-sbt-plugin-releases",
  url("https://dl.bintray.com/hmrc/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

resolvers ++= Seq("hmrc-snapshots" at hmrcRepoHost + "/content/repositories/hmrc-snapshots",
  "hmrc-releases" at hmrcRepoHost + "/content/repositories/hmrc-releases",
  "typesafe-releases" at hmrcRepoHost + "/content/repositories/typesafe-releases")

addSbtPlugin("uk.gov.hmrc" % "sbt-auto-build" % "0.10.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-git-versioning" % "0.8.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-settings" % "3.3.0")

addSbtPlugin("uk.gov.hmrc" % "hmrc-resolvers" % "0.4.0")

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.8")

