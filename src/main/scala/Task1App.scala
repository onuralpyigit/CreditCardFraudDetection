import akka.actor.{ActorRef, ActorSystem}
import task1.{Card, CardGenerator, Terminal, TerminalGenerator}

object Task1App extends App {
  import Card._

  val system: ActorSystem = ActorSystem("AkkaSystemTask1")

  val atmId: String = "00001"
  val atm: ActorRef = system.actorOf(Terminal.props(atmId), "atmActor")

  val debitCardId: String = "00000001"
  val debitCard: ActorRef =
    system.actorOf(Card.props(debitCardId, atm), "debitCardActor")

  debitCard ! Payment(500)
  debitCard ! Transfer

  val posId: String = "11011"
  val pos: ActorRef = system.actorOf(Terminal.props(posId), "posActor")

  val creditCardId: String = "11010002"
  val creditCard: ActorRef =
    system.actorOf(Card.props(creditCardId, pos), "creditCardActor")

  creditCard ! Payment(200)
  creditCard ! Transfer

  val terminalId: String = TerminalGenerator.getTerminal()
  val terminal: ActorRef = system.actorOf(Terminal.props(posId), "terminalActor")

  val cardId: String = CardGenerator.getCard()
  val card: ActorRef =
    system.actorOf(Card.props(cardId, terminal), "cardActor")

  var amount: Int = CardGenerator.getAmount(cardId.charAt(1))

  card ! Payment(amount)
  card ! Transfer
}