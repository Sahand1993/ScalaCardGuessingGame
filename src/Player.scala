import scala.util.Random

abstract class Player {
  def guessRed(board: Board): Boolean
}

class StrategicPlayer extends Player {
  /**
    * Clicks on top card to make a guess that it is red.
    * @param board
    * @return
    */
  def guessRed(board: Board): Boolean = {
    val fracRedLeft: Float = (board.noOfRedCards - board.redsFlipped) / (board.deckSize - board.cardsFlipped)
    fracRedLeft > 0.5
  }
}

class NaivePlayer extends Player {

  def guessRed(board: Board): Boolean = Random.nextBoolean()

}

class AlwaysRedPlayer extends Player {
  def guessRed(board: Board): Boolean = true
}
