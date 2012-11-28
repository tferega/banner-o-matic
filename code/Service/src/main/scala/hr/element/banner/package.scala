package hr.element

import java.io.{ PrintWriter, StringWriter }

package object banner {
  def ??? = throw new Exception("???: Not implemented")

  implicit def exceptionImpaler(e: Exception): ImpaleException = new ImpaleException(e)
  class ImpaleException(e: Exception) {
    def stackTrace: String = {
      val sw = new StringWriter
      val pw = new PrintWriter(sw)
      e.printStackTrace(pw)
      val s = sw.toString
      pw.close
      sw.close
      s
    }
  }
}
