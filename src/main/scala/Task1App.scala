import Task1App.system.dispatcher
import akka.actor.{ActorRef, ActorSystem}
import akka.event.Logging
import task1.Card._
import task1.{Card, CardGenerator, Terminal, TerminalGenerator}

import scala.concurrent.duration._

object Task1App extends App {
  val system: ActorSystem = ActorSystem("AkkaSystemTask1")

  system.scheduler.schedule(500 millis, 1000 millis) {
    val terminalId: String = TerminalGenerator.getTerminal()
    val terminal: ActorRef = system.actorOf(Terminal.props(terminalId))

    val cardId: String = CardGenerator.getCard()
    val card: ActorRef = system.actorOf(Card.props(cardId, terminal))

    val amount: Int = CardGenerator.getAmount(cardId.charAt(1))

    card ! Payment(amount)
    card ! Transfer
  }
}
