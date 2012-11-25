import sbt._
import Keys._

import Helpers._

object ASCIIBannerBuild extends Build {
  lazy val root = top(Seq(
    client,
    common,
    service
  ))

  lazy val client = project(
    "Client",
    Seq(),
    Seq(common)
  ) settings ((
    Publishing.settings ++
    Default.javaProject): _*
  )

  lazy val common = project("Common").
  settings (
    Default.javaProject: _*
  )

  lazy val service = project(
    "Service",
    Seq(),
    Seq(common)
  ) settings (
    Default.scalaProject: _*
  )
}
