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

  def generateCustomerId(): Int = {
    val customerId = generateRandomNumber(1, 1000)
    return customerId
  }

  def generateCardId(kind: Int, limit: Int, home: Int, customerId: Int) : String = {
    val cardId = "" + kind + limit + "%02d".format(home) + "%04d".format(customerId)
    return cardId
  }

  def getCard(): String = {
    // Generate kind
    val kind = generateKind()

    // Generate limit
    val limit = generateLimit(kind)

    // Generate home
    val home = generateHome()

    // Generate customer id
    val customerId = generateCustomerId()

    // Generate card id
    val cardId = generateCardId(kind, limit, home, customerId)

    return cardId
  }
}
