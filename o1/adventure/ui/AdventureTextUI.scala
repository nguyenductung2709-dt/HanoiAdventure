package o1.adventure.ui

import o1.adventure.*
import scala.io.StdIn.*

/** The singleton object `AdventureTextUI` represents a fully text-based version of the
  * Adventure game application. The object serves as an entry point for the game, and
  * it can be run to start up a user interface that operates in the text console.
  * @see [[AdventureGUI]] */
object AdventureTextUI extends App:
  private val game = Adventure()
  private val player = game.player
  this.run()

  private def run() =
    println(this.game.welcomeMessage)
    while !this.game.isOver do
      this.printAreaInfo()
      this.printQuestion()
      this.playTurn()
    println("\n" + this.game.goodbyeMessage)

  private def printAreaInfo() =
    val area = this.player.location
    println("\n\n" + area.name)
    println("-" * area.name.length)
    println(area.fullDescription + "\n")
    println("Your stamina is: " + this.player.currentStamina +"/100.0")

  private def printQuestion() =
    if player.location == QuocTuGiam then
      println(QuocTuGiam.currentQuestion)

  private def playTurn() =
    if player.location != QuocTuGiam then
      println()
      val command = readLine("Command: ")
      val turnReport = this.game.playTurn(command)
      if turnReport.nonEmpty then
        println(turnReport)
    else
      println()
      val command = readLine("Command: ")
      val answers = Vector("A", "B", "C", "D", "exitQuocTuGiam")
      if answers.contains(command) then
        val turnReport = this.game.playTurn(command)
        if turnReport.nonEmpty then
          println(turnReport)
      else println("We don't do that here")
