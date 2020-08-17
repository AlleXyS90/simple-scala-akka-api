package app.helpers

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.ExceptionHandler

trait RoutesExceptionHandler {
  val tokenNotFoundExceptionHandler: ExceptionHandler = ExceptionHandler {
    case e: IllegalArgumentException => complete(StatusCodes.Forbidden, e.getMessage)
  }
  val noSuchElementExceptionHandler: ExceptionHandler = ExceptionHandler {
    case e: NoSuchElementException => complete(StatusCodes.NotFound, e.getMessage)
  }
}
