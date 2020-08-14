package main.scala.services

import dtos.Item
import services.interfaces.IItemsService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Random

class ItemsService extends IItemsService {
  override def getAll: Future[List[Item]] = {
    Future(List(
      Item(1, "Alex", getRandomAge),
      Item(2, "Andrei", getRandomAge),
      Item(3, "Vasi", getRandomAge)
    ))
  }

  override def getById(id: Int): Future[Option[Item]] = {
    getAll map {list => list.find(x => x.id == id)}
  }

  override def getRandomAge: Int = 10 + Random.nextInt(30)
}
