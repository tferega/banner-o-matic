package hr.element.banner
package action

import comms.req._

import java.io.{ Serializable => JSer }

object ActionExecutor {
  private def banner = BannerAction.act
  private def fonts   = FontsAction.act

  def apply(ser: JSer): JSer = {
    ser match {
      case arg: ReqBanner => banner(arg)
      case arg: ReqFonts  => fonts(arg)
      case _ =>
        throw new IllegalArgumentException("Action [%s] not found!" format ser.getClass.toString)
    }
  }
}
