package main.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{complete, get, parameter, pathPrefix, _}
import com.typesafe.config.ConfigFactory
import main.scala.controllers.ItemsController
import main.scala.helpers.JsonProtocols.ItemJsonProtocol
import main.scala.helpers.{RoutesExceptionHandler, RoutesRejectionHandler}
import spray.json._

import scala.concurrent.ExecutionContext.Implicits.global

class ItemRoutes extends ItemJsonProtocol with RoutesExceptionHandler with RoutesRejectionHandler {
  val apiToken = ConfigFactory.load.getString("api.token")

  val getItemRoute = parameter(Symbol("id").as[Int]) { id =>
    complete {
      ItemsController.getById(id) map {
        result =>
          result match {
            case Some(value) => toHttpEntity(value.toJson.prettyPrint)
            case None => throw new NoSuchElementException(s"Item with id $id not found")
          }
      }
    }
  }

  val concatRoutes = (pathPrefix("api" / "items") & get & extractRequest) { request =>
    // items route
    // get item by id (with parameter), getAll as default route (pathEndOrSingleSlash directive)
    val tokenResult = request.headers.find(x => x.name() == "Token")
    tokenResult match {
      case None => throw new IllegalArgumentException("Unauthorized. Token not found.")
      case Some(token) => token.value match {
        case this.apiToken => getItemRoute ~
          pathEndOrSingleSlash {
            complete(
              ItemsController.getAll
                .map(_.toJson.prettyPrint)
                .map(toHttpEntity)
            )
          }
        case _ => throw new IllegalArgumentException("Unauthorized. Invalid token.")
      }
    }
  }

  val itemRoutes = (
      handleExceptions(noSuchElementExceptionHandler) &
      handleExceptions(tokenNotFoundExceptionHandler)) {
    concatRoutes
  }

  private def toHttpEntity(payload: String) = HttpEntity(ContentTypes.`application/json`, payload)
}
