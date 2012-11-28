package hr.element.banner
package api
package utils

import action.IAction

import serialization.JavaSerializator.deserialize

object AsDeserializable {
  def unapply(obj: Array[Byte]) =
    try {
      val ser = deserialize(obj)
      Some(ser)
    } catch {
      case e: Exception => None
    }
}
