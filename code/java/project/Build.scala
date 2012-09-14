import sbt._
import Keys._

object Build extends Build {
  import com.typesafe.sbteclipse.plugin.EclipsePlugin.{settings => eclipseSettings, _}

  lazy val defaults =
    Defaults.defaultSettings ++
    eclipseSettings ++ Seq(
      organization     := "hr.element"
    , version          := "0.1.0-SNAPSHOT"
    , scalaVersion     := "2.9.2"

    , javacOptions     := Seq("-deprecation", "-Xlint:unchecked", "-encoding", "UTF-8", "-source", "1.6", "-target", "1.6")
    , unmanagedSourceDirectories in Compile <<= (javaSource in Compile)(_ :: Nil)
    , unmanagedSourceDirectories in Test := Nil
    , autoScalaLibrary := false
    , crossPaths       := false

    , resolvers := Seq(
      )
    , externalResolvers <<= resolvers map { rS =>
        Resolver.withDefaultResolvers(rS, mavenCentral = false)
      }
    , credentials += Credentials(Path.userHome / ".config" / "element" / "publishing" / "element.credentials")

    , EclipseKeys.projectFlavor :=  EclipseProjectFlavor.Java
    )

  lazy val root = Project(
    "ASCIIBanner",
    file("project/strap/root"),
    settings = defaults ++ Seq(
      autoScalaLibrary := true
    )
  ) dependsOn(core)

  lazy val core = Project(
    "ASCIIBanner-Core",
    file("core"),
    settings = defaults ++ Seq(
      libraryDependencies ++= Seq(
      )
    )
  )
}
