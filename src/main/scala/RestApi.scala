package main.scala

import akka.actor.ActorSystem
import akka.util.Timeout
import main.routes.ApiRoutes

import scala.concurrent.ExecutionContextExecutor

class RestApi(timeout: Timeout)(implicit system: ActorSystem) extends ApiRoutes {

  implicit val requestTimeout: Timeout = timeout

  implicit def executionContext: ExecutionContextExecutor = system.dispatcher
}
