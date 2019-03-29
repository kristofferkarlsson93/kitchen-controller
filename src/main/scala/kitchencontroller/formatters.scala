package kitchencontroller

import kitchencontroller.timer.{Timer, TimerApiModel, TimerDraft}
import play.api.libs.json.{Json, OFormat}

object formatters {
  
  implicit val timerDraftFormat: OFormat[TimerDraft] = Json.format[TimerDraft]
  implicit val timerFormat: OFormat[Timer] = Json.format[Timer]
  implicit val timerApiModelFormat: OFormat[TimerApiModel] = Json.format[TimerApiModel]

}
