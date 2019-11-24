import scala.util.Random

class Board(
             val redsFlipped: Int,
             val blacksFlipped: Int,
             val jokerFlipped: Boolean,
             val deckSize: Int,
             val noOfRedCards: Int,
             val cardList: List[Card],
             val lastCard: Card
           ) {

  def cardsFlipped: Int = redsFlipped + blacksFlipped + (if (jokerFlipped) 1 else 0)

  def cardsLeft: Int = deckSize - cardsFlipped

  def this(redsFlipped: Int, blacksFlipped: Int, jokerFlipped: Boolean, deckSize: Int, noOfRedCards: Int) {
    this(redsFlipped, blacksFlipped, jokerFlipped, deckSize, noOfRedCards, List(), null)
  }

  def flipCard(): Board = {
    val nextCard: Card = cardList.head
    new Board(
      if (nextCard.isInstanceOf[RedCard]) redsFlipped + 1 else redsFlipped,
      if (nextCard.isInstanceOf[BlackCard]) blacksFlipped + 1 else blacksFlipped,
      if (nextCard.isInstanceOf[JokerCard]) !jokerFlipped else jokerFlipped,
      deckSize,
      noOfRedCards,
      cardList.tail,
      nextCard
    )
  }

  def initializeDeck(): Board = {
    val withRedsCardList: List[Card] = Board.addNColorCards(List(), noOfRedCards, "red")
    val withBlacksCardList: List[Card] = Board.addNColorCards(withRedsCardList, deckSize - noOfRedCards - 1, "black")
    val completeCardList: List[Card] = withBlacksCardList :+ Card.jokerCard()
    withNewCardList(Random.shuffle(completeCardList))
  }

  def withNewCardList(newCardList: List[Card]): Board = {
    new Board(redsFlipped, blacksFlipped, jokerFlipped, deckSize, noOfRedCards, newCardList, lastCard)
  }
}

object Board {

  /**
    * Adds cards of a certain color, red or black
    * @param list
    * @param N
    * @param color
    * @return
    */
  def addNColorCards(list: List[Card], N: Int, color: String): List[Card] = {
    val newCard = if (color == "red") Card.redCard() else Card.blackCard()
    if (N == 1) list :+ newCard
    else {
      addNColorCards(list :+ newCard, N - 1, color)
    }
  }
}