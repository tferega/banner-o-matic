import sbt._
import Keys._

// =============  PACKS THE REPOSITORIES INTO A SETTINGS VARIABLE  =============
object Resolvers {
  import Repositories._

  lazy val settings = Seq(
    resolvers := Seq(ElementNexus, ElementReleases, ElementSnapshots),
    externalResolvers <<= resolvers map { rs =>
      Resolver.withDefaultResolvers(rs, mavenCentral = false)
    }
  )
}



// ==============  PUBLISHING SETTINGS  ==============
object Publishing {
  import Repositories._

  lazy val settings = Seq(
    publishTo <<= (version) { version => Some(
      if (version.endsWith("SNAPSHOT")) ElementSnapshots else ElementReleases)
    },
    credentials += Credentials(Path.userHome / ".config" / "ascii-banner" / "nexus.config"),
    publishArtifact in (Compile, packageDoc) := false,
    crossScalaVersions := Seq("2.9.2", "2.9.1-1", "2.9.1")
  )
}



// ==============  LIFT SETTINGS  ==============
object Lift {
  import com.github.siasia.PluginKeys._
  import com.github.siasia.WebPlugin._

  def settings(portNumber: Int) = 
    webSettings ++ Seq(
      port in container.Configuration := portNumber,
      scanDirectories in Compile      := Nil
    )
}



// ==============  DEFINES DEFAULT SETTINGS USED BY ALL PROJECTS  ==============
object Default {
  import com.typesafe.sbteclipse.plugin.EclipsePlugin
  import EclipsePlugin.{ settings => eclipseSettings, EclipseProjectFlavor }
  import EclipsePlugin.EclipseKeys.projectFlavor

  val Name = "ASCIIBanner"

  lazy val javaProject =
    eclipseSettings ++ Seq(
      projectFlavor    := EclipseProjectFlavor.Java,
      javacOptions     := Seq("-deprecation", "-Xlint:unchecked", "-encoding", "UTF-8", "-source", "1.6", "-target", "1.6"),
      autoScalaLibrary := false,
      crossPaths       := false,
      unmanagedSourceDirectories in Compile <<= (javaSource in Compile)(_ :: Nil)
  )

  lazy val scalaProject =
    eclipseSettings ++ Seq(
      //projectFlavor := EclipseProjectFlavor.Scala,
      scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "UTF-8", "-optimise"),
      unmanagedSourceDirectories in Compile <<= (scalaSource in Compile)(_ :: Nil)
  )

  lazy val settings =
    Defaults.defaultSettings ++
    Resolvers.settings ++ Seq(
      name         := Name,
      organization := "hr.element",
      version      := "0.0.0-SNAPSHOT",
      scalaVersion := "2.9.2",
      unmanagedSourceDirectories in Test := Nil
  )
}



// ==================  HELPER FUNCTIONS FOR PROJECT CREATION  ==================
object Helpers {
  // Implicit magic that makes project definitions really simple to write.
  implicit def depToFunSeq(m: ModuleID) = Seq((_: String) => m)
  implicit def depFunToSeq(fm: String => ModuleID) = Seq(fm)
  implicit def depSeqToFun(mA: Seq[ModuleID]) = mA.map(m => ((_: String) => m))
  implicit def warName2SMA(name: String) = (_: String, _: ModuleID, _: Artifact) => name

  sealed trait ProjectFlavor
  object ProjectFlavor {
    case object Java  extends ProjectFlavor
    case object Scala extends ProjectFlavor
  }

  private def flavorSettings(flavor: ProjectFlavor) =
    flavor match {
      case ProjectFlavor.Java  => Default.javaProject
      case ProjectFlavor.Scala => Default.scalaProject
    }


  // Creates a main container project (one that contains all other projects, and
  // is otherwise empty).
  def top(projectAggs: Seq[sbt.ProjectReference] = Seq()) =
    Project(
      Default.Name,
      file("."),
      settings = Default.settings
    ) aggregate(projectAggs: _*)


  // Creates a container project (one that contains sub-projects, and is
  // otherwise empty).
  def parent(
      title:       String,
      projectAggs: Seq[sbt.ProjectReference] = Seq()) =
    Project(
      title,
      file(title.replace('-', '/')),
      settings = Default.settings ++ Seq(
        name := Default.Name +"-"+ title
      )
    ) aggregate(projectAggs: _*)


  // Creates a proper project.
  def project(
      title:       String,
      flavor:      ProjectFlavor,
      deps:        Seq[Seq[String => ModuleID]] = Seq(),
      projectDeps: Seq[ClasspathDep[ProjectReference]] = Seq()) =
    Project(
      title,
      file(title.replace('-', '/')),
      settings = Default.settings ++ flavorSettings(flavor) ++ Seq(
        name := Default.Name +"-"+ title
      ) :+ (libraryDependencies <++= scalaVersion( sV =>
        for (depSeq <- deps; dep <- depSeq) yield dep(sV))
      )
    ) dependsOn(projectDeps: _*)
}
