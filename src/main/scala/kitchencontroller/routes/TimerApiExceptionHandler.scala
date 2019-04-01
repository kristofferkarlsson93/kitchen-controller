package kitchencontroller.routes

import akka.http.scaladsl.server._
import Directives._
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport
import kitchencontroller.formatters._

case class ErrorResponse(errorType: String, message: String)

case class TimerErrorResponse(error: ErrorResponse)

object TimerApiExceptionHandler extends PlayJsonSupport {

  val handler: ExceptionHandler = ExceptionHandler {
    case error: TimerNotFound =>
      val errorResponse: ErrorResponse = ErrorResponse(error.errorType, error.getMessage)
      complete(error.statusCode, TimerErrorResponse(errorResponse))
  }

}
