package o1.adventure

/** The class `Action` represents actions that a player may take in a text adventure game.
  * `Action` objects are constructed on the basis of textual commands and are, in effect,
  * parsers for such commands. An action object is immutable after creation.
  * @param input  a textual in-game command such as “go east” or “rest” */
class Action(input: String):

  private val commandText = input.trim
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim

  def execute(actor: Player) = this.verb match
    case "go"              => Some(actor.go(this.modifiers))
    case "checkwallet"     => Some(actor.checkWallet)
    case "checkinventory"  => Some(actor.printInventory)
    case "quit"            => Some(actor.quit())
    case "eat"             => Some(actor.eat(this.modifiers))
    case "eaten"           => Some(actor.eaten)
    case "gohome"          => Some(actor.goHome())
    case "enterQuocTuGiam" => Some(actor.enterQuocTuGiam())
    case "exitQuocTuGiam"  => Some(actor.exitQuocTuGiam())
    case "enterCircleK"    => Some(actor.enterCircleK())
    case "exitCircleK"     => Some(actor.exitCircleK())
    case "buy"             => Some(actor.buyDrink(this.modifiers.toInt))
    case "use"             => Some(actor.use(this.modifiers))
    case "A"               => Some(actor.answerQuestion("A"))
    case "B"               => Some(actor.answerQuestion("B"))
    case "C"               => Some(actor.answerQuestion("C"))
    case "D"               => Some(actor.answerQuestion("D"))
    case "help"            => Some(actor.help())

    case other             => None


  override def toString = s"$verb (modifiers: $modifiers)"
  

end Action

