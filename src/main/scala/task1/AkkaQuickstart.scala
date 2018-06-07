package task1

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

object Card {
  def props(cardId: String, terminalActor: ActorRef): Props = Props(new Card(cardId, terminalActor))

  final case class Payment(amount: Double)
  case object Transfer
}

class Card(cardId: String, terminalActor: ActorRef) extends Actor {
  import Card._
  import Terminal._

  var amount = 0.0

  def receive = {
    case Payment(amount) =>
      this.amount = amount
    case Transfer           =>
      terminalActor ! Transaction(cardId, amount)
  }
}

object Terminal {
  def props(terminalId: String): Props = Props(new Terminal(terminalId))

  final case class Transaction(cardId: String, amount: Double)
}

class Terminal(terminalId: String) extends Actor with ActorLogging {
  import Terminal._

  def receive = {
    case Transaction(cardId, amount) =>
      log.info("Payment received (from " + sender() + "): " + cardId + ";" + terminalId + ";" + amount)
  }
}
