package kitchencontroller.routes

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import StatusCodes._
import akka.http.scaladsl.server._
import Directives._
import akka.stream.ActorMaterializer
import akka.stream.Materializer
import kitchencontroller.formatters._
import play.api.libs.json._
import akka.actor.ActorSystem
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport

import scala.concurrent.ExecutionContext

case class TimerErrorResponse(error: ErrorResponse)

case class ErrorResponse(errorType: String, message: Option[String])

object TimerApiExceptionHandler extends PlayJsonSupport {

  val handler: ExceptionHandler = ExceptionHandler {
    case error: TimerNotFound =>

      println("\nEtt error \n")
      println(error.getMessage)
      println(error.errorType)
      val errorResponse: ErrorResponse = ErrorResponse(error.errorType, Some(error.getMessage))
      complete(error.statusCode, TimerErrorResponse(errorResponse))
    //    case e => throw new RuntimeException("An unknown error: " + e.getMessage)
  }

}
