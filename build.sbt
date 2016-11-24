import Extra._

val sbtPluginSettings = ScriptedPlugin.scriptedSettings ++ Seq(
    organization := "org.scala-native",
    version := "0.1.0-SNAPSHOT",
    sbtPlugin := true,
    scalaVersion := "2.10.6",
    scriptedLaunchOpts += "-Dplugin.version=" + version.value,
    scalacOptions ++= Seq(
      "-deprecation",
      "-unchecked",
      "-feature",
      "-encoding",
      "utf8"
    )
  )

lazy val `sbt-cross-project` =
  project
    .in(file("."))
    .aggregate(sbtScalaJSCross, sbtCross)
    .dependsOn(sbtScalaJSCross, sbtCross)

lazy val sbtScalaJSCross =
  project
    .in(file("sbt-scalajs-cross"))
    .settings(sbtPluginSettings)
    .settings(
      moduleName := "sbt-scalajs-cross",
      addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.13")
    )
    .dependsOn(sbtCross)

lazy val sbtCross =
  project
    .in(file("sbt-cross"))
    .settings(moduleName := "sbt-cross")
    .settings(sbtPluginSettings)
    .settings(scaladocFromReadme)

lazy val sbtCrossTest =
  project
    .in(file("sbt-cross-test"))
    .settings(sbtPluginSettings)
    .settings(
      scripted := scripted
        .dependsOn(
          publishLocal in sbtCross,
          publishLocal in sbtScalaJSCross
        )
        .evaluated
    )
    .settings(duplicateProjectFolders)
