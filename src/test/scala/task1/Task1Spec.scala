package task1

import org.scalatest.{BeforeAndAfterAll, WordSpecLike, Matchers}
import akka.actor.ActorSystem
import akka.testkit.{TestKit, TestProbe}
import scala.concurrent.duration._
import scala.language.postfixOps
import Card._
import Terminal._

class Task1Spec(_system: ActorSystem)
  extends TestKit(_system)
    with Matchers
    with WordSpecLike
    with BeforeAndAfterAll {

  def this() = this(ActorSystem("AkkaQuickstartSpec"))

  override def afterAll: Unit = {
    shutdown(system)
  }

  "A debit card actor" should {
    "pass on amount when instructed to" in {
      val testProbe = TestProbe()
      val cardId = "00000001"
      val card = system.actorOf(Card.props(cardId, testProbe.ref))
      val amount = 100
      card ! Payment(amount)
      card ! Transfer
      testProbe.expectMsg(500 millis, Transaction(cardId, amount, 0))
    }
  }

  "A credit card actor" should {
    "pass on amount when instructed to" in {
      val testProbe = TestProbe()
      val cardId = "11010002"
      val card = system.actorOf(Card.props(cardId, testProbe.ref))
      val amount = 100
      card ! Payment(amount)
      card ! Transfer
      testProbe.expectMsg(500 millis, Transaction(cardId, amount, 0))
    }
  }

  "A debit card id" should {
    "consist of kind, limit, and home properties" in {
      val kind = 0
      val limit = 0
      val home = 1
      val customerNo = 1
      val cardId = CardGenerator.generateCardId(kind, limit, home, customerNo)
      val expectedCardId = "00010001"
      cardId shouldEqual(expectedCardId)
    }
  }

  "A credit card id" should {
    "consist of kind, limit, and home properties" in {
      val kind = 1
      val limit = 3
      val home = 0
      val customerNo = 999
      val cardId = CardGenerator.generateCardId(kind, limit, home, customerNo)
      val expectedCardId = "13000999"
      cardId shouldEqual(expectedCardId)
    }
  }

  "A pos id" should {
    "consist of kind, merchant, and location properties" in {
      val kind = 1
      val merchant = 0
      val location = 46
      val terminalNo = 1
      val terminalId = TerminalGenerator.generateTerminalId(kind, merchant, location, terminalNo)
      val expectedTerminalId = "10461"
      terminalId shouldEqual(expectedTerminalId)
    }
  }

  "A atm id" should {
    "consist of kind, merchant, and location properties" in {
      val kind = 0
      val merchant = 0
      val location = 0
      val terminalNo = 9
      val terminalId = TerminalGenerator.generateTerminalId(kind, merchant, location, terminalNo)
      val expectedTerminalId = "00009"
      terminalId shouldEqual(expectedTerminalId)
    }
  }

}
