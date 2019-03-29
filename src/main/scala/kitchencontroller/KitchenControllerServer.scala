package kitchencontroller

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import kitchencontroller.routes.TimerRoutes
import kitchencontroller.timer.TimerRepository

import scala.concurrent.ExecutionContext

object KitchenControllerServer {

  implicit val system: ActorSystem = ActorSystem("kitchenController")
  implicit val executor: ExecutionContext = system.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val host = "0.0.0.0"
  val port = 9000

  private val timerRoutes = new TimerRoutes(new TimerRepository)

  def start(): Unit = {
    Http().bindAndHandle(timerRoutes.getRoutes, host, port)
  }
}
