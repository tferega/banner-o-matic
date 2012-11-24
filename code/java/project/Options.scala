import sbt._
import Keys._

object Options {
  val Name            = "ASCIIBanner"
  val Organisation    = "hr.element.banner"
  val Version         = "0.0.1-SNAPSHOT"

  object Repositories {
  val Repositories    = Seq(
    val ElementNexus     = "Element Nexus"     at "http://repo.element.hr/nexus/content/groups/public/"
    val ElementReleases  = "Element Releases"  at "http://repo.element.hr/nexus/content/repositories/releases/"
    val ElementSnapshots = "Element Snapshots" at "http://repo.element.hr/nexus/content/repositories/snapshots/"
  )
  }
}