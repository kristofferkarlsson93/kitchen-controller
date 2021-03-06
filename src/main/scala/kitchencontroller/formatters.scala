package kitchencontroller

import kitchencontroller.routes.{ErrorResponse, TimerErrorResponse}
import kitchencontroller.timer.{AllTimersRespone, Timer, TimerApiModel, TimerDraft}
import play.api.libs.json.{Json, OFormat}

object formatters {

  implicit val timerDraftFormat: OFormat[TimerDraft] = Json.format[TimerDraft]
  implicit val timerFormat: OFormat[Timer] = Json.format[Timer]
  implicit val timerApiModelFormat: OFormat[TimerApiModel] = Json.format[TimerApiModel]

  implicit val allTimersResponseFormat: OFormat[AllTimersRespone] = Json.format[AllTimersRespone]

  implicit val errorResponseFormatter: OFormat[ErrorResponse] = Json.format[ErrorResponse]
  implicit val timerApiExceptionFormatter: OFormat[TimerErrorResponse] = Json.format[TimerErrorResponse]

}
