package hr.element.banner
package api
package rest

import action.ActionExecutor

import net.liftweb.common.Full
import net.liftweb.http.{ NotFoundResponse, InternalServerErrorResponse, PlainTextResponse, PostRequest, Req }
import net.liftweb.http.rest.RestHelper

object ActionListener extends RestHelper {
  serve{
    case req @ Req("api" :: Nil, _, PostRequest) =>
      req.body match {
        case Full(utils.AsDeserializable(ser)) =>
          try {
            ActionExecutor(ser)
            PlainTextResponse("OK")
          } catch {
            case e: Exception =>
              e.printStackTrace
              InternalServerErrorResponse()
          }
        case _ =>
          NotFoundResponse("Invalid request!")
      }
  }
}
