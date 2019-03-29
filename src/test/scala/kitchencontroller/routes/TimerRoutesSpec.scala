package kitchencontroller.routes

import akka.http.scaladsl.model.{DateTime, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}
import kitchencontroller.timer.{Timer, TimerDraft, TimerRepository}
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.auto._

class TimerRoutesSpec extends WordSpec with Matchers with ScalatestRouteTest {


//  "POST /timers" should {
//    "create and return a kitchencontroller.timer" in {
//      val repository = new TimerRepository()
//      val kitchencontroller.routes = new TimerRoutes(repository).getRoutes
//      val timerDraft = TimerDraft("my kitchencontroller.timer", DateTime(2019, 1, 1, 0, 0, 0), DateTime(2019, 1, 1, 1, 0, 0))
//
//      Post("/timers", timerDraft) ~> kitchencontroller.routes ~> check {
//        status shouldBe StatusCodes.Created
//        val response = responseAs[Timer]
//        response.title shouldBe "my kitchencontroller.timer"
//      }
//    }
//  }
//
//  "Get /timers" should {
//    "return all the timers" in {
//      val repository = new TimerRepository()
//      val kitchencontroller.routes = new TimerRoutes(repository).getRoutes
//      val timerDraft1 = TimerDraft("T1A", 100)
//      val timerDraft2 = TimerDraft("T2A", 100)
//      repository.create(timerDraft1)
//      repository.create(timerDraft2)
//      Get("/timers") ~> kitchencontroller.routes ~> check {
//        status shouldBe StatusCodes.OK
//        val response = responseAs[Seq[Timer]]
//        response(0).title shouldBe "T1A"
//        response(1).title shouldBe "T2A"
//      }
//    }
//  }

}
