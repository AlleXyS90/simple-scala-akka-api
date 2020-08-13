package services.interfaces

import dtos.Item

trait IItemsService {
  def getItems: List[Item]
  def getRandomAge: Int
}
