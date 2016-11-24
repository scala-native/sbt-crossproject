// COPIED FROM sbt-cross-test/src/sbt-test/plugins.sbt
val pluginVersion = sys.props.get("plugin.version").getOrElse("0.1.0-SNAPSHOT")

addSbtPlugin("org.scala-js"     % "sbt-scalajs"       % "0.6.13")
addSbtPlugin("org.scala-native" % "sbt-scalajs-cross" % pluginVersion)
addSbtPlugin("org.scala-native" % "sbt-cross"         % pluginVersion)
addSbtPlugin("org.scala-native" % "sbt-native"        % "0.1-SNAPSHOT")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature",
  "-encoding",
  "utf8"
)
