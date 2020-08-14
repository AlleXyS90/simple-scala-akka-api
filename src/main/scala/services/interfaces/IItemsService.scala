package services.interfaces

import main.scala.dtos.Item

import scala.concurrent.Future

trait IItemsService {
  def getAll: Future[List[Item]]
  def getById(id: Int): Future[Option[Item]]
  def getRandomAge: Int
}
