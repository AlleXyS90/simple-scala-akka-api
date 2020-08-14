package main.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Directives.{complete, get, parameter, pathPrefix, _}
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import core.json.ItemJsonProtocol
import main.scala.controllers.ItemsController
import spray.json._

import scala.concurrent.ExecutionContext.Implicits.global

class ItemRoutes extends ItemJsonProtocol {
  protected val getItemsRoute: Route = {
    (pathPrefix("api" / "items") & get) {
      complete(
        ItemsController
          .getAll
          .map(x => x.toJson.prettyPrint)
          .map(toHttpEntity)
      )
    }
  }

  protected val getItemRoute: Route = rejectEmptyResponse {
    (pathPrefix("api" / "items") & get) {
      parameter(Symbol("id").as[Int]) { id =>
        complete {
          ItemsController.getById(id) map { result =>
            result match {
              case Some(value) => toHttpEntity(value.toJson.prettyPrint)
              case None => throw new NoSuchElementException(s"Item with id $id not found")
            }
          }
        }
      }
    }
  }

  val noSuchElementExceptionHandler: ExceptionHandler = ExceptionHandler {
    case e: NoSuchElementException => complete(StatusCodes.NotFound, e.getMessage)
  }

  val itemRoutes = handleExceptions(noSuchElementExceptionHandler) {
    getItemRoute ~
      getItemRoute
  }

  private def toHttpEntity(payload: String) = HttpEntity(ContentTypes.`application/json`, payload)
}
