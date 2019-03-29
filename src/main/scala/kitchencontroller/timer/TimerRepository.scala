package kitchencontroller.timer

import java.time._
import java.util.UUID

import akka.http.scaladsl.model.DateTime
import akka.stream.Materializer
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport
import play.api.libs.json._
import format._
import java.time.temporal.ChronoUnit

import kitchencontroller.formatters._

import scala.concurrent.Future

class TimerRepository()(implicit mat: Materializer) extends PlayJsonSupport {
  private var timers: Vector[Timer] = Vector()

  def create(timerDraft: TimerDraft): Future[Timer] = {
    val timer = Timer.fromDraft(timerDraft)
    timers = timers :+ timer
    Future.successful(timer)
  }

  def all: Future[Seq[TimerApiModel]] = {
    timers.map(timer => timerToApiModel(timer))
    Future.successful(timers.map(timer => timerToApiModel(timer)))
  }

  def delete(id: UUID): Future[Unit] = {
    timers = timers.filterNot(timer => timer.id == id)
    Future.unit
  }

  private def timerToApiModel(timer: Timer): TimerApiModel = {
    val millis = LocalDateTime.now().until(timer.endsAt, ChronoUnit.MILLIS)
    TimerApiModel(timer.id, timer.title, millis)
  }
}