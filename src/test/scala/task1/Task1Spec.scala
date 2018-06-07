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

  "A Card Actor" should {
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
}
