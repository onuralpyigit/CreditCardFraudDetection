package task1

import scala.util.Random

object CardGenerator {

  val limitDistributionList: List[Int] = List(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 4)

  def generateRandomNumber(start: Int, end: Int): Int = {
    val n = start + Random.nextInt((end - start) + 1)
    return n
  }

  def generateKind(): Int = {
    val kind = generateRandomNumber(0, 1)
    return kind
  }

  def generateLimit(kind: Int): Int = {
    var limit = 0
    if (kind == 1) {
      limit = limitDistributionList(Random.nextInt(limitDistributionList.size))
    }
    return limit
  }

  def generateHome(): Int = {
    val home = generateRandomNumber(0, 80)
    return home
  }

  def generateCustomerNo(): Int = {
    val customerNo = generateRandomNumber(0, 99999)
    return customerNo
  }

  def generateCardId(kind: Int, limit: Int, home: Int, customerNo: Int): String = {
    val cardId = "" + kind + limit + "%02d".format(home) + "%05d".format(customerNo)
    return cardId
  }

  def getCard(): String = {
    // Generate kind
    val kind = generateKind()

    // Generate limit
    val limit = generateLimit(kind)

    // Generate home
    val home = generateHome()

    // Generate customer no
    val customerNo = generateCustomerNo()

    // Generate card id
    val cardId = generateCardId(kind, limit, home, customerNo)

    return cardId
  }

  def getCard(home: Int): String = {
    // Generate kind
    val kind = generateKind()

    // Generate limit
    val limit = generateLimit(kind)

    // Generate customer no
    val customerNo = generateCustomerNo()

    // Generate card id
    val cardId = generateCardId(kind, limit, home, customerNo)

    return cardId
  }

  def getCardFromDifferentLocation(location: String): String = {
    // Generate kind
    val kind = generateKind()

    // Generate limit
    val limit = generateLimit(kind)

    // Find home location from given terminal location
    var home = 0
    do {
      // Generate home
      home = generateHome()
    } while (location.toInt == home)

    // Generate customer no
    val customerNo = generateCustomerNo()

    // Generate card id
    val cardId = generateCardId(kind, limit, home, customerNo)

    return cardId
  }

  def getAmount(limit: Char): Int = {
    var maxAmount = 0
    limit match {
      case '0' => maxAmount = 1000
      case '1' => maxAmount = 5000
      case '2' => maxAmount = 10000
      case '3' => maxAmount = 20000
      case '4' => maxAmount = 30000
      case _ => maxAmount = 0
    }
    val amount = generateRandomNumber(0, maxAmount)
    return amount
  }

  def getFraudAmount(limit: Char): Int = {
    var maxAmount = 0
    limit match {
      case '0' => maxAmount = 1000
      case '1' => maxAmount = 5000
      case '2' => maxAmount = 10000
      case '3' => maxAmount = 20000
      case '4' => maxAmount = 30000
      case _ => maxAmount = 10
    }
    val startLimit: Int = (maxAmount * 0.9).toInt
    val amount = generateRandomNumber(startLimit, maxAmount)
    return amount
  }
}
