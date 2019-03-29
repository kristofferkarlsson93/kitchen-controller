package kitchencontroller

import kitchencontroller.timer.{Timer, TimerApiModel, TimerDraft}
import play.api.libs.json.{Json, OFormat}

object formatters {

  //  implicit val localDateTimeFormatter: Unmarshaller[String, LocalDateTime] =
  //    Unmarshaller.strict[String, LocalDateTime] { string =>
  //      import java.time.LocalDateTime
  //      import java.time.format.DateTimeFormatter
  //      var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
  //      implicit val dateEncoder: Encoder[LocalDateTime] = Encoder.encodeString.contramap[LocalDateTime](_.format(formatter))
  //      val date = LocalDateTime.parse(string, formatter)
  //      date
  //    }

  implicit val timerDraftFormat: OFormat[TimerDraft] = Json.format[TimerDraft]
  implicit val timerFormat: OFormat[Timer] = Json.format[Timer]
  implicit val timerApiModelFormat: OFormat[TimerApiModel] = Json.format[TimerApiModel]

}
