package task1

object CardGenerator {

  def generateRandomNumber(start: Int, end: Int): Int = {
    val r = new scala.util.Random
    val n = start + r.nextInt((end - start) + 1)
    return  n
  }

  def generateKind(): Int = {
    val kind = generateRandomNumber(0, 1)
    return kind
  }

  def generateLimit(kind: Int): Int = {
    var limit = 0
    if (kind == 1) {
      limit = generateRandomNumber(1, 4)
    }
    return limit
  }

  def generateHome(): Int = {
    val home = generateRandomNumber(0, 80)
    return home
  }

  def generateCustomerNo(): Int = {
    val customerNo = generateRandomNumber(0, 9999)
    return customerNo
  }

  def generateCardId(kind: Int, limit: Int, home: Int, customerNo: Int) : String = {
    val cardId = "" + kind + limit + "%02d".format(home) + "%04d".format(customerNo)
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
}