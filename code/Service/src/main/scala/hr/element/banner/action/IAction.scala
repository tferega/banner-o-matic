package hr.element.banner
package action

trait IAction[A, R] {
  def act: (A) => R
}
