object RedVibes extends App {

  /**
    * Play a round. Return true if player won.
    * @param board
    * @param player
    * @return true if player won
    */
  def winsRound(board: Board, player: Player): Boolean = {
    if (board.cardsLeft == 1) return board.flipCard().lastCard.isInstanceOf[RedCard] // If player hasn't guessed by last card, the last card will be his guess. If it's red, he wins.
    if (player.guessRed(board)) {
      board.flipCard().lastCard match {
        case card: RedCard => true
        case _ => false
      }
    } else winsRound(board.flipCard(), player)
  }

  /**
    * Play nGames games. Return outcomes as list of booleans where true means player won.
    * @param nGames
    * @return
    */
  def playGames(nGames: Int, player: Player, won: List[Boolean]): List[Boolean] = {
    if (nGames == 0) return won
    if (nGames % 10000 == 0) println(s"${nGames} games left")
    val board: Board =
      new Board(0, 0, false, 53, 26)
        .initializeDeck()
    playGames(nGames - 1, player, winsRound(board, player) +: won)
  }

  println(playGames(100000, new StrategicPlayer, List()).count(outcome => outcome))

}
