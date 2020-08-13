package controllers

import services.interfaces.ItemsService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object ApiController {
  val itemsService = new ItemsService

  def getItems(customerId: Int) = Future {
    itemsService.getItems
  }
}
