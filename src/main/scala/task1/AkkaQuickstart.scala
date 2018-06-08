package task1

import java.text.SimpleDateFormat
import java.util.Calendar

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
    case Transfer =>
      terminalActor ! Transaction(cardId, amount)
  }
}

object Terminal {
  def props(terminalId: String): Props = Props(new Terminal(terminalId))

  final case class Transaction(cardId: String, amount: Double)
}

class Terminal(terminalId: String) extends Actor with ActorLogging {
  import Terminal._

  val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm")

  def receive = {
    case Transaction(cardId, amount) =>
      // Get transaction time
      val now = Calendar.getInstance().getTime()
      val transactionTime = dateFormat.format(now)

      // Print transaction log
      log.info(transactionTime + ";" + cardId + ";" + terminalId + ";" + amount)
  }
}
