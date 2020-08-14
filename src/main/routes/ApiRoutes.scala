package main.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Directives.{complete, path, pathEndOrSingleSlash, _}
import akka.http.scaladsl.server.Route
import com.typesafe.config.ConfigFactory

trait ApiRoutes {

  val config = ConfigFactory.load
  val apiToken = config.getString("api.token")

  lazy val routes: Route = allRoutes

  // entity routes
  val itemRoutes = new ItemRoutes().itemRoutes

  protected val getDefaultRoute: Route = {
    path("api") {
      val msg = "Rest API FIRST PAGE. Nothing to be seen here"
      complete(
        HttpEntity(
          ContentTypes.`text/html(UTF-8)`,
          msg
        )
      )
    }
  }

  protected val allRoutes =
    itemRoutes ~
      getDefaultRoute ~
      pathEndOrSingleSlash {
        redirect("/api", StatusCodes.PermanentRedirect)
      }
}

