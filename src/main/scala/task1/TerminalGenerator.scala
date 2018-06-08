package task1

object TerminalGenerator {

  def generateRandomNumber(start: Int, end: Int): Int = {
    val r = new scala.util.Random
    val n = start + r.nextInt((end - start) + 1)
    return  n
  }

  def generateKind(): Int = {
    val kind = generateRandomNumber(0, 1)
    return kind
  }

  def generateMerchant(kind: Int): Int = {
    var merchant = 0
    if (kind == 1) {
      merchant = generateRandomNumber(1, 5)
    }
    return merchant
  }

  def generateLocation(kind: Int): Int = {
    var location = 99
    if (kind == 1) {
      location = generateRandomNumber(0, 81)
    }
    else {
      location = generateRandomNumber(0, 80)
    }
    return location
  }

  def generateTerminalNo(): Int = {
    val terminalNo = generateRandomNumber(0, 9)
    return terminalNo
  }

  def generateTerminalId(kind: Int, merchant: Int, location: Int, terminalNo: Int): String = {
    val cardId = "" + kind + merchant + "%02d".format(location) + terminalNo
    return cardId
  }

  def getTerminal(): String = {
    // Generate kind
    val kind = generateKind()

    // Generate merchant
    val merchant = generateMerchant(kind)

    // Generate location
    val location = generateLocation(kind)

    // Generate terminal no
    val terminalNo = generateTerminalNo()

    // Generate terminal id
    val terminalId = generateTerminalId(kind, merchant, location, terminalNo)

    return terminalId
  }
}
