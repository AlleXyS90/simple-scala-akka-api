package main.scala.helpers

import main.scala.dtos.Item
import spray.json.DefaultJsonProtocol

object JsonProtocols {
  trait ItemJsonProtocol extends DefaultJsonProtocol {
    implicit val itemFormat = jsonFormat3(Item)
  }
}
