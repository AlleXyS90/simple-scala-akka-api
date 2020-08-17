package app.controllers

import app.services.ItemsService

object ItemsController {
  val itemsService = new ItemsService

  def getAll() = {
    itemsService.getAll
  }

  def getById(id: Int) = {
    itemsService.getById(id)
  }
}
