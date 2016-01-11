name := "okta-saml-auth"

organization := "es.develex"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

crossPaths := false

libraryDependencies ++= Seq(
  cache,
  javaWs
)

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <url>https://github.com/ismail2ov/okta-saml-auth</url>
    <licenses>
      <license>
        <name>Apache 2</name>
        <url>http://opensource.org/licenses/Apache-2.0</url>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:ismail2ov/okta-saml-auth.git</url>
      <connection>scm:git:git@github.com:ismail2ov/okta-saml-auth.git</connection>
    </scm>
    <developers>
      <developer>
        <id>ismail2ov</id>
        <name>Ismail Ismailov</name>
        <url>http://develex.es</url>
      </developer>
    </developers>
  )

credentials += Credentials("Sonatype Nexus Repository Manager",
                            "oss.sonatype.org",
                            "your-sonatype-username",
                            "your-sonatype-password")