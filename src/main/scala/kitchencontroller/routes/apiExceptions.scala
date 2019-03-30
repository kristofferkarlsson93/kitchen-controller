package kitchencontroller.routes

import akka.http.scaladsl.model.{StatusCode, StatusCodes}

case class SomeError()

class TimerNotFound(message: String) extends RuntimeException(message) {
  val statusCode: StatusCode = StatusCodes.NotFound
  val errorType: String = "TIMER_NOT_FOUND"
}


