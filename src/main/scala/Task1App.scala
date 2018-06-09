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

  // Scheduler for online fraud transaction
  system.scheduler.schedule(1 second, 10 seconds) {
    // Create terminal actor
    val pos: Int = 1 // POS
    val internet: Int = 99 // Internet
    val terminalId: String = TerminalGenerator.getTerminal(pos, internet)
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

  // Scheduler for fraud transaction because of drawing cash via ATM
  system.scheduler.schedule(1 second, 20 seconds) {
    // Create terminal actor
    val atm: Int = 0 // ATM
    val location: Int = TerminalGenerator.generateLocation(atm)
    val terminalId: String = TerminalGenerator.getTerminal(atm, location)
    val terminal: ActorRef = system.actorOf(Terminal.props(terminalId))

    // Create card actor
    val cardId: String = CardGenerator.getCard(location)
    val card: ActorRef = system.actorOf(Card.props(cardId, terminal))

    // Get random amount of fraud transaction
    val amount: Int = CardGenerator.getFraudAmount(cardId.charAt(1))

    // Transfer money
    card ! Payment(amount)
    card ! FraudTransfer
  }

  // Scheduler for fraud transaction over different locations
  system.scheduler.schedule(1 second, 30 seconds) {
    // Create terminal actor
    val terminalId: String = TerminalGenerator.getTerminal()
    val terminal: ActorRef = system.actorOf(Terminal.props(terminalId))

    // Create card actor
    val terminalLocation = terminalId.slice(2,4)
    val cardId: String = CardGenerator.getCard(terminalLocation)
    val card: ActorRef = system.actorOf(Card.props(cardId, terminal))

    // Get random amount of fraud transaction
    val amount: Int = CardGenerator.getFraudAmount(cardId.charAt(1))

    // Transfer money
    card ! Payment(amount)
    card ! FraudTransfer
  }

  // Scheduler for international fraud transaction
  system.scheduler.schedule(1 second, 30 seconds) {
    // Create terminal actor
    val pos: Int = 1 // POS
    val international: Int = 0 // International
    val terminalId: String = TerminalGenerator.getTerminal(pos, international)
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

  // Scheduler for fraud transaction over specific merchant and location
  system.scheduler.schedule(1 second, 45 seconds) {
    // Create terminal actor
    val pos: Int = 1 // POS
    val tooRiskyMerchant: Int = 3
    val tooRiskyLocation: Int = 1
    val terminalId: String = TerminalGenerator.getTerminal(pos, tooRiskyMerchant, tooRiskyLocation)
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
