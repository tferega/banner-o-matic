import sbt._
import Keys._

import Helpers._

object ASCIIBannerBuild extends Build {
  lazy val root = top(
    Seq(
      service,
      client
    )
  )

  lazy val service = project(
    "Service",
    Seq(),
    Seq()
  ) settings (
    Publishing.settings: _*
  )

  lazy val client = project("Client")
}
