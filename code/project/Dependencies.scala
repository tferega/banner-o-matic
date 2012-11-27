import sbt._
import Keys._

object Dependencies {
  private val liftVersion = "2.5-M3"
  private val liftCommon  = "net.liftweb" %% "lift-common" % liftVersion
  private val liftJson    = "net.liftweb" %% "lift-json"   % liftVersion
  private val liftUtil    = "net.liftweb" %% "lift-util"   % liftVersion
  private val liftWebkit  = "net.liftweb" %% "lift-webkit" % liftVersion

  private val jettyWebapp = "org.eclipse.jetty"       % "jetty-webapp"  % "7.6.5.v20120716"     % "container"
  private val jettyOrbit  = "org.eclipse.jetty.orbit" % "javax.servlet" % "2.5.0.v201103041518" % "container" artifacts Artifact("javax.servlet", "jar", "jar")

  val jetty = Seq(jettyWebapp, jettyOrbit)
  val lift  = Seq(liftJson, liftCommon, liftUtil, liftWebkit)
}
