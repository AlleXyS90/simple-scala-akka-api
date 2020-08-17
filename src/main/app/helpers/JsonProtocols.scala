package app.helpers

import app.dtos.Item
import spray.json.DefaultJsonProtocol

object JsonProtocols {
  trait ItemJsonProtocol extends DefaultJsonProtocol {
    implicit val itemFormat = jsonFormat3(Item)
  }
}
