package kitchencontroller.timer

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

import akka.http.scaladsl.unmarshalling.Unmarshaller
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._
import play.api.libs.json._
import de.heikoseeberger.akkahttpplayjson
import kitchencontroller.formatters._

// 2019-03-29T22:39:19.944377

case class TimerDraft(title: String, startedAt: LocalDateTime, endsAt: LocalDateTime)

case class AllTimersRespone(timers: Seq[TimerApiModel])

case class TimerApiModel(id: UUID, title: String, timeToLive: Long)

case class Timer(id: UUID, title: String, startedAt: LocalDateTime, endsAt: LocalDateTime)

object Timer {
  def fromDraft(timerDraft: TimerDraft): Timer = {
    Timer(UUID.randomUUID(), timerDraft.title, timerDraft.startedAt, timerDraft.endsAt)
  }
}
