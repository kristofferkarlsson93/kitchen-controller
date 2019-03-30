package kitchencontroller.routes

import java.time.LocalDateTime
import java.util.UUID

import akka.http.scaladsl.model.{DateTime, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}
import kitchencontroller.timer.{AllTimersRespone, Timer, TimerApiModel, TimerDraft, TimerRepository}
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport
import io.circe.generic.auto._
import kitchencontroller.formatters._
import org.scalatest.concurrent.ScalaFutures

import scala.concurrent.Future
import scala.util.Try

class TimerRoutesSpec extends WordSpec with Matchers with ScalatestRouteTest with PlayJsonSupport with ScalaFutures {


  "POST /timers" should {
    "create and return a timer" in {
      val repository = new TimerRepository()
      val routes = new TimerRoutes(repository).getRoutes
      val startTime = LocalDateTime.of(2019, 1, 1, 0, 0, 0)
      val endTime = LocalDateTime.of(2019, 1, 1, 1, 0, 0)
      val timerDraft = TimerDraft("my timer", startTime, endTime)

      Post("/timers", timerDraft) ~> routes ~> check {
        status shouldBe StatusCodes.Created
        val response = responseAs[Timer]
        response.title shouldBe "my timer"
        response.endsAt shouldBe endTime
        response.startedAt shouldBe startTime
      }
    }
  }

  "Get /timers" should {
    "return all the timers" in {
      val repository = new TimerRepository()
      val routes = new TimerRoutes(repository).getRoutes

      val startTime = LocalDateTime.of(2019, 1, 1, 0, 0, 0)
      val endTime = LocalDateTime.of(2019, 1, 1, 1, 0, 0)
      val secondStartTime = LocalDateTime.of(2019, 1, 2, 0, 0, 0)
      val secondEndTime = LocalDateTime.of(2019, 1, 2, 1, 0, 0)


      val timerDraft1 = TimerDraft("T1A", startTime, endTime)
      val timerDraft2 = TimerDraft("T2A", secondStartTime, secondEndTime)

      repository.create(timerDraft1)
      repository.create(timerDraft2)

      Get("/timers") ~> routes ~> check {
        status shouldBe StatusCodes.OK
        //        println(response)
        val response = responseAs[AllTimersRespone]
        response.timers(0).title shouldBe "T1A"
        response.timers(1).title shouldBe "T2A"
      }
    }
  }

  "Get /timers/:timerId" should {
    "return one timer" in {
      val repository = new TimerRepository()
      val routes = new TimerRoutes(repository).getRoutes

      val startTime = LocalDateTime.of(2019, 1, 1, 0, 0, 0)
      val endTime = LocalDateTime.of(2019, 1, 1, 1, 0, 0)
      val timerDraft = TimerDraft("T1A", startTime, endTime)

      val timer: Timer = repository.create(timerDraft).futureValue

      Get(s"/timers/${timer.id}") ~> routes ~> check {
        status shouldBe StatusCodes.OK
        val response = responseAs[TimerApiModel]
        response.title shouldBe "T1A"
      }
    }
    "return error when no timer is found" in {
      val repository = new TimerRepository()
      val routes = new TimerRoutes(repository).getRoutes

      Get(s"/timers/${UUID.randomUUID}") ~> routes ~> check {
        status shouldBe StatusCodes.NotFound
        val response = responseAs[TimerErrorResponse]
        response.error.errorType shouldBe "TIMER_NOT_FOUND"
      }
    }
  }

}
