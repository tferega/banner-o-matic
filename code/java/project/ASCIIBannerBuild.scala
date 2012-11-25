import sbt._
import Keys._

import Helpers._

object ASCIIBannerBuild extends Build {
  lazy val root = top(Seq(
    service,
    client
  ))

  lazy val service = project("Service").
  settings (
    Default.scalaProject: _*
  )

  lazy val client = project("Client").
  settings ((
    Publishing.settings ++
    Default.javaProject): _*
  )
}
