package kitchencontroller.timer

import java.time._
import java.util.UUID

import akka.stream.Materializer
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport
import play.api.libs.json._
import java.time.temporal.ChronoUnit

import kitchencontroller.formatters._
import kitchencontroller.routes.TimerNotFound

import scala.concurrent.Future

class TimerRepository()(implicit mat: Materializer) extends PlayJsonSupport {
  private var timers: Vector[Timer] = Vector()

  def create(timerDraft: TimerDraft): Future[Timer] = {
    val timer = Timer.fromDraft(timerDraft)
    timers = timers :+ timer
    Future.successful(timer)
  }

  def all: Future[AllTimersRespone] = {
    val timerApiModels = timers.map(timer => timerToApiModel(timer))
    Future.successful(AllTimersRespone(timerApiModels))
  }

  def getById(id: UUID): TimerApiModel = {
    timers.find(timer => timer.id == id) match {
      case Some(timer) => timerToApiModel(timer)
      case _ => throw new TimerNotFound(s"Could not find timer with id ${id.toString}")
    }
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