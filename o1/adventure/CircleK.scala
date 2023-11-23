package o1.adventure

import scala.collection.mutable.*

object CircleK extends Area("Circle K", "Circle K is a popular chain of convenience stores in Hanoi, known for offering a wide range of snacks, beverages, and daily necessities."):

  val item1  = Consumable("Vietnamese white coffee", 15000)
  val item2 = Consumable("Sting energy drink", 10000)
  val item3 = Consumable("Peach flavored C2 tea", 10000)
  val item4 = Consumable("Thai Milk Tea", 20000)
  val item5 = Consumable("Bubble Milk Tea", 25000)

  val drinkList = Vector(item1, item2, item3, item4, item5)

  override def fullDescription =
    var result = Buffer[String]()
    val welcome = "Circle K is a popular chain of convenience stores in Hanoi, known for offering a wide range of snacks, beverages, and daily necessities."
    val menu =
      for i <- drinkList.indices do
        result += "\n" + "item" + "|" + (i.toInt+1) + "  " + drinkList(i)
      result.mkString
    val guidance = "\nPlease type \"buy ?\" to buy drinks. For example, \"buy 1\", \"buy 2\"."
    welcome + menu + guidance