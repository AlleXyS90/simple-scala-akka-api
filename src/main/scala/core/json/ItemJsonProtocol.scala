package core.json

import dtos.Item
import spray.json.DefaultJsonProtocol

trait ItemJsonProtocol extends DefaultJsonProtocol {
  implicit val itemFormat = jsonFormat3(Item)
}
