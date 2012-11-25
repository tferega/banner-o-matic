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
    ProjectFlavor.Java,
    Seq(),
    Seq(common)
  ) settings (
    Publishing.settings: _*
  )

  lazy val common = project("Common", ProjectFlavor.Java)

  lazy val service = project(
    "Service",
    ProjectFlavor.Scala,
    Seq(),
    Seq(common)
  )
}
