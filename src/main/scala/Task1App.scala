import Task1App.system.dispatcher
import akka.actor.{ActorRef, ActorSystem}
import task1.Card._
import task1.{Card, CardGenerator, Terminal, TerminalGenerator}

import scala.concurrent.duration._

object Task1App extends App {
  val system: ActorSystem = ActorSystem("AkkaSystemTask1")

  // Normal transaction scheduler
  system.scheduler.schedule(100 millis, 500 millis) {
    // Create terminal actor
    val terminalId: String = TerminalGenerator.getTerminal()
    val terminal: ActorRef = system.actorOf(Terminal.props(terminalId))

    // Create card actor
    val cardId: String = CardGenerator.getCard()
    val card: ActorRef = system.actorOf(Card.props(cardId, terminal))

    // Get random amount of transaction
    val amount: Int = CardGenerator.getAmount(cardId.charAt(1))

    // Transfer money
    card ! Payment(amount)
    card ! Transfer
  }

  // Scheduler for fraud transaction because of stolen card
  system.scheduler.schedule(1 second, 20 seconds) {
    // Create terminal actor
    val kind: Int = 0 // ATM
    val terminalId: String = TerminalGenerator.getTerminal(kind)
    val terminal: ActorRef = system.actorOf(Terminal.props(terminalId))

    // Create card actor
    val cardId: String = CardGenerator.getCard()
    val card: ActorRef = system.actorOf(Card.props(cardId, terminal))

    // Get random amount of fraud transaction
    val amount: Int = CardGenerator.getFraudAmount(cardId.charAt(1))

    // Transfer money
    card ! Payment(amount)
    card ! FraudTransfer
  }

  // Scheduler for online fraud transaction
  system.scheduler.schedule(1 second, 10 seconds) {
    // Create terminal actor
    val kind: Int = 1 // POS
    val location: Int = 99 // Internet
    val terminalId: String = TerminalGenerator.getTerminal(kind, location)
    val terminal: ActorRef = system.actorOf(Terminal.props(terminalId))

    // Create card actor
    val cardId: String = CardGenerator.getCard()
    val card: ActorRef = system.actorOf(Card.props(cardId, terminal))

    // Get random amount of fraud transaction
    val amount: Int = CardGenerator.getFraudAmount(cardId.charAt(1))

    // Transfer money
    card ! Payment(amount)
    card ! FraudTransfer
  }

  // Scheduler for international fraud transaction
  system.scheduler.schedule(1 second, 1 minutes) {
    // Create terminal actor
    val kind: Int = 1 // POS
    val location: Int = 0 // International
    val terminalId: String = TerminalGenerator.getTerminal(kind, location)
    val terminal: ActorRef = system.actorOf(Terminal.props(terminalId))

    // Create card actor
    val cardId: String = CardGenerator.getCard()
    val card: ActorRef = system.actorOf(Card.props(cardId, terminal))

    // Get random amount of fraud transaction
    val amount: Int = CardGenerator.getFraudAmount(cardId.charAt(1))

    // Transfer money
    card ! Payment(amount)
    card ! FraudTransfer
  }

  // Scheduler for fraud transaction
  system.scheduler.schedule(1 second, 1 minute) {
    // Create terminal actor
    val terminalId: String = TerminalGenerator.getTerminal()
    val terminal: ActorRef = system.actorOf(Terminal.props(terminalId))

    // Create card actor
    val cardId: String = CardGenerator.getCard()
    val card: ActorRef = system.actorOf(Card.props(cardId, terminal))

    // Get random amount of fraud transaction
    val amount: Int = CardGenerator.getFraudAmount(cardId.charAt(1))

    // Transfer money
    card ! Payment(amount)
    card ! FraudTransfer
  }
}
