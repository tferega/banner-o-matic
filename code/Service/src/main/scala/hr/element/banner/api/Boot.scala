package hr.element.banner
package api

import net.liftweb.http.{ Bootable, LiftRules }
import net.liftweb.http.LiftRulesMocker.toLiftRules

class Boot extends Bootable {
  def boot {
    LiftRules.addToPackages("hr.element.banner")

    LiftRules.dispatch.append(rest.ActionListener)
  }
}
