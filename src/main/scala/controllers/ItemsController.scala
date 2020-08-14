package main.scala.controllers

import main.scala.services.ItemsService

object ItemsController {
  val itemsService = new ItemsService

  def getAll() = {
    itemsService.getAll
  }

  def getById(id: Int) = {
    itemsService.getById(id)
  }
}
