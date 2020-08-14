package main.scala.helpers

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.{Rejection, RejectionHandler}

trait RoutesRejectionHandler {
  val badRequestHandler: RejectionHandler = { _: Seq[Rejection] =>
    Some(complete(StatusCodes.BadRequest))
  }
  val forbiddenHandler: RejectionHandler = { _: Seq[Rejection] =>
    Some(complete(StatusCodes.Forbidden))
  }
}
