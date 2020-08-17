package app

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.util.Timeout
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.Future

object Module extends App with RequestTimeout {

  implicit val system = ActorSystem("SimpleScalaApi")

  val config = ConfigFactory.load
  val host = config.getString("http.host")
  val port = config.getInt("http.port")

  val api = new RestApi(requestTimeout(config)).routes

  val bindingFuture: Future[ServerBinding] = Http().bindAndHandle(api, host, port)

  val log = Logging(system.eventStream, "Scala Akka Api")

  import scala.concurrent.ExecutionContext.Implicits.global

  try {
    // Here we start the HTTP server and log the info
    bindingFuture.map { serverBinding =>
      log.info(s"Scala Akka Api bound to ${serverBinding.localAddress}")
    }
  }
  catch {
    // If the HTTP server fails to start, we throw an Exception and log the error and close the system
    case ex: Exception =>
      log.error(ex, "Failed to bind to {}:{}!", host, port)
      system.terminate()
  }
}

trait RequestTimeout {

  import scala.concurrent.duration._

  def requestTimeout(config: Config): Timeout = {
    val t = config.getString("akka.http.server.request-timeout")
    val d = Duration(t)
    FiniteDuration(d.length, d.unit)
  }
}
