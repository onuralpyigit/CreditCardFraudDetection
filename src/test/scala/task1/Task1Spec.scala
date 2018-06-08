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
      testProbe.expectMsg(500 millis, Transaction(cardId, amount))
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
      testProbe.expectMsg(500 millis, Transaction(cardId, amount))
    }
  }

  "A debit card id" should {
    "consist of kind, limit, and home properties" in {
      val kind = 0
      val limit = 0
      val home = 1
      val customerId = 1
      val cardId = CardGenerator.generateCardId(kind, limit, home, customerId)
      val expectedCardId = "00010001"
      cardId shouldEqual(expectedCardId)
    }
  }

  "A credit card id" should {
    "consist of kind, limit, and home properties" in {
      val kind = 1
      val limit = 3
      val home = 0
      val customerId = 999
      val cardId = CardGenerator.generateCardId(kind, limit, home, customerId)
      val expectedCardId = "13000999"
      cardId shouldEqual(expectedCardId)
    }
  }

}
