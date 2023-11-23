package o1.adventure

import scala.collection.mutable.Map

/** A `Player` object represents a player character controlled by the real-life user
  * of the program.
  *
  * A player object’s state is mutable: the player’s location and possessions can change,
  * for instance.
  *
  * @param startingArea  the player’s initial location */
class Player(startingArea: Area):

  /**ATTRIBUTES*/
  private var currentLocation = startingArea
  def location = this.currentLocation
  
  private var quitCommandGiven = false    
  def hasQuit = this.quitCommandGiven
  
  private var eatenCuisine = Map[String, Cuisine]()
  def hadEaten = eatenCuisine
  
  private var money: Int = 0
  def currentMoney = money

  private var stamina: Double = 100
  def currentStamina = this.stamina

  private var inventory = Map[String, Consumable]()
  def checkInventory = this.inventory

  def printInventory =
    if checkInventory.nonEmpty then s"You have:\n" + checkInventory.keys.mkString(", ") + " in your bag."
    else "You don't have any drinks in your bag."
  
  /**QUOC TU GIAM METHODS*/
  var locationBeforeQuocTuGiam: Area = QuocTuGiam
  
  def enterQuocTuGiam() =
    var result = ""
    locationBeforeQuocTuGiam = this.currentLocation
    if this.currentLocation.canEnterQuocTuGiam then
      this.currentLocation = QuocTuGiam
      this.stamina -= 7
      result = "You have entered Quoc Tu Giam, the first university of Vietnam"
    else result = "You must go to Ba Dinh to enter Quoc Tu Giam"
    result

  def exitQuocTuGiam() =
    var result = ""
    if this.currentLocation == QuocTuGiam then
      this.currentLocation = locationBeforeQuocTuGiam
      result = "You have returned to Dong Da."
    else result = "You are not in Quoc Tu Giam."
    result

  /**CIRCLE K METHODS*/
  var locationBeforeCircleK: Area = CircleK

  def enterCircleK() =
    var result = ""
    locationBeforeCircleK = this.currentLocation
    if this.currentLocation.canEnterCircleK then
      this.currentLocation = CircleK
      result = "You have entered Circle K"
    else result = "You cannot enter Circle K from here."
    result

  def exitCircleK() =
    var result = ""
    if this.currentLocation == CircleK then
      this.currentLocation = locationBeforeCircleK
      result = s"You have returned to ${locationBeforeCircleK.name}."
    else result = "You are not in Circle K."
    result

  /**CHECK METHODS*/
  def checkWallet = s"You have $money VND"
  
  def eaten: String =
    if eatenCuisine.nonEmpty then s"You have eaten:\n" + eatenCuisine.keys.mkString(", ")
    else "You are hungry."

  /**ACTION METHODS*/
  def buyDrink(itemNumber: Int): String =
    var result = ""
    if this.location == CircleK then
      if this.money >= CircleK.drinkList(itemNumber-1).price then
        this.inventory += CircleK.drinkList(itemNumber-1).name -> CircleK.drinkList(itemNumber-1)
        this.money -= CircleK.drinkList(itemNumber-1).price
        result = s"You bought ${CircleK.drinkList(itemNumber-1).name}."
      else
        result = "You don't have enough money, please go to Quoc Tu Giam to earn more money."
    else
      result = "You are not in Circle K."
    result

  def use(drinkName: String): String =
    var result = ""
    if this.inventory.contains(drinkName) then
      this.stamina += this.inventory(drinkName).staminaIncreased
      inventory.remove(drinkName)
      result = s"You have drunk ${drinkName}. Your stamina now is ${this.stamina}"
    else
      result = s"There is no $drinkName in your bag to drink."
    result

  def eat(cuisineName: String): String =
    var result = ""
    if this.location.cuisineCollection.contains(cuisineName) then
      val cuisinePrice = this.location.cuisineCollection(cuisineName).price
      if this.location.contains(cuisineName) && this.money >= cuisinePrice then
        money -= cuisinePrice
        this.location.cuisineCollection(cuisineName).sold = true
        val cuisine = this.location.removeCuisine(cuisineName).get
        this.eatenCuisine += cuisineName -> cuisine
        result = s"You ate the $cuisineName."
      else if this.location.contains(cuisineName) && this.money < cuisinePrice then
        result = s"You don't have enough money to buy $cuisineName. Go to Quoc Tu Giam in Dong Da to earn some money"
    else
      result = s"There is no $cuisineName here to eat."
    result

  def go(direction: String) =
    val destination = this.location.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation)
    this.stamina -= 5
    if destination.isDefined then "You go " + direction + "." else "You can't go " + direction + "."

  def answerQuestion (answer: String) =
    var result = ""
    if currentLocation == QuocTuGiam then
      this.stamina -= 4
      if answer == QuocTuGiam.questionToAnswer(QuocTuGiam.QIndex)(QuocTuGiam.currentQuestion) then
        money += 20000
        result = "Congratulations! Your answer is correct. You received 20000 VND"
      else result = "You get it wrong, better luck next time"
    else result = "Go to Quoc Tu Giam to answer questions"
    QuocTuGiam.nextQuestion()
    result

  def quit() =
    this.quitCommandGiven = true
    ""

  def goHome() =
    var result = ""
    if this.currentLocation.canGoHome && this.eatenCuisine.size == 9 then
      this.currentLocation = Home
      result = "Congratulations! You make it home"
    else if this.currentLocation.canGoHome && this.eatenCuisine.size != 9 then
      result = "You haven't full yet. GO EAT MORE!"
    else
      result = "You cannot go home from here. Go to Hoan Kiem!"
    result

  def help() =
    val availableCommands = "Available commands: " +
    "go [direction], checkwallet, checkinventory, quit, eat [food], " +
    "eaten, gohome, enterQuocTuGiam, exitQuocTuGiam, enterCircleK, exitCircleK, " +
    "buy [number of drink], use [item], A, B, C, D"

    availableCommands

  override def toString = "Now at: " + this.location.name

end Player

