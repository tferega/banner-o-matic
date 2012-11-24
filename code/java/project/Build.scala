import sbt._
import Keys._

object Build extends Build {
  import com.typesafe.sbteclipse.plugin.EclipsePlugin.{settings => eclipseSettings, _}

  lazy val defaults =
    Defaults.defaultSettings ++
    eclipseSettings ++ Seq(
      organization     := "hr.element"
    , version          := "0.0.0-SNAPSHOT"
    , scalaVersion     := "2.9.2"

    , javacOptions     := Seq("-deprecation", "-Xlint:unchecked", "-encoding", "UTF-8", "-source", "1.6", "-target", "1.6")
    , unmanagedSourceDirectories in Compile <<= (javaSource in Compile)(_ :: Nil)
    , unmanagedSourceDirectories in Test := Nil
    , autoScalaLibrary := false
    , crossPaths       := false

    , resolvers := Seq(
        "Element Nexus"     at "http://repo.element.hr/nexus/content/groups/public/"
      , "Element Releases"  at "http://repo.element.hr/nexus/content/repositories/releases/"
      , "Element Snapshots" at "http://repo.element.hr/nexus/content/repositories/snapshots/"
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
    , publishTo <<= (version, resolvers) { (version, rS) => Some(
        if (version endsWith "SNAPSHOT") rS(2) else rS(1)
      )}
    , publishArtifact in (Compile, packageDoc) := false

    )
  )
}
