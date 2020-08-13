package services.interfaces

import dtos.Item

import scala.util.Random

class ItemsService extends IItemsService {
  override def getItems: List[Item] = {
    List(
      Item(1, "Alex", getRandomAge),
      Item(2, "Andrei", getRandomAge),
      Item(3, "Vasi", getRandomAge)
    )
  }

  override def getRandomAge: Int = 10 + Random.nextInt(30)
}
