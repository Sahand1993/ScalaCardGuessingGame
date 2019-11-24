abstract class Card {

}

object Card {
  def redCard(): RedCard = new RedCard
  def blackCard(): BlackCard = new BlackCard
  def jokerCard(): JokerCard = new JokerCard
}

class RedCard extends Card
class BlackCard extends Card
class JokerCard extends Card