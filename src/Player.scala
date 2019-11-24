class Player {
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
