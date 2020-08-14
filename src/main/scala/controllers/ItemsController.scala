package main.scala.controllers

import main.scala.services.ItemsService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object ItemsController {
  val itemsService = new ItemsService

  def getAll() = {
    itemsService.getAll
  }

  def getById(id: Int) = {
    itemsService.getById(id)
  }
}
