package UNO;

public class BotPlayer extends Player {

    public BotPlayer(String name, Deck deck) {
        super(name, deck);
    }

    @Override
    public Card chooseCardToPlay(int index, Card topCard) {
        // Bot logic: Play the first playable card found in the hand
        for (int i = 0; i < getNumberOfCards(); i++) {
            if (gethand(i) != null && gethand(i).canPlayOn(topCard)) {
                Card chosenCard = gethand(i);
                removeCard(i);
                return chosenCard;
            }
        }
        return null; // No matching card found
    }

    @Override
    public void showHand() {
        // Override to hide the bot's hand from the human player
        System.out.println(getName() + "'s hand: [Hidden]");
    }
}
