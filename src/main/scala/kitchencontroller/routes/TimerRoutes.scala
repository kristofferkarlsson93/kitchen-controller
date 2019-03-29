package kitchencontroller.routes

import java.time.format
import java.util.UUID

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.{ActorMaterializer, Materializer}
import kitchencontroller.timer.{TimerDraft, TimerRepository}
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport
import kitchencontroller.formatters._

import scala.concurrent.ExecutionContext

class TimerRoutes(timerRepository: TimerRepository)(implicit mat: Materializer, system: ActorSystem, executor: ExecutionContext) extends PlayJsonSupport {


  def getRoutes: Route = {
    pathPrefix("timers") {
      pathEndOrSingleSlash {
        post {
          entity(as[TimerDraft]) { timerDraft =>
            onSuccess(timerRepository.create(timerDraft)) { timer =>
              complete(timer)
            }
          }
        } ~ get {
          complete(timerRepository.all)
        }
      } ~ path(Segment) { id: String =>
        delete {
          onComplete(timerRepository.delete(UUID.fromString(id))) { _ =>
            complete(StatusCodes.NoContent)
          }
        }
      }
    }

  }

}
