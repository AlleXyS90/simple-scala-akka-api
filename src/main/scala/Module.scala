package main.scala

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import controllers.ApiController
import core.json.ItemJsonProtocol
import spray.json._

object Module extends App with ItemJsonProtocol {

  implicit val system = ActorSystem("SimpleScalaApi")
  implicit val materialier = ActorMaterializer

  import system.dispatcher

  val apiServerRoute =
    (pathPrefix("api") & get) {
      path("items") {
        parameter(Symbol("customerId").as[Int]) { customerId =>
          complete(
            ApiController
              .getItems(customerId)
              .map(x => x.toJson.prettyPrint)
              .map(toHttpEntity)
          )
        }
      } ~ pathEndOrSingleSlash {
        val msg = "FIRST PAGE. Nothing to be seen here"
        complete(
          HttpEntity(
            ContentTypes.`text/html(UTF-8)`,
            msg
          )
        )
      }
    }

  def toHttpEntity(payload: String) = HttpEntity(ContentTypes.`application/json`, payload)

  Http().bindAndHandle(apiServerRoute, "localhost", 9003)
}
