package UNO;



public class Player {
    private final String name; // Player's name
    private Card[] hand = new Card[50]; // Player's hand
    private int numberOfCards = 0;      // Current number of cards in the player's hand

    public Player(String name, Deck deck) {
        this.name = name;
        for (int i = 0; i < 7; i++) {
			hand[i] = deck.drawCard(); // Draw 7 initial cards from the deck
			numberOfCards++;
        }
    }
    public int getcardCount() {
    	return numberOfCards;
    }
    public Card getlastdrawncard(){
    	return hand[numberOfCards-1];
    }
    public String getName() {
        return name;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    // Check if the player has won (no cards left)
    public boolean hasWon() {
        return numberOfCards == 0;
    }

    // Draw a card from the deck and add it to the player's hand
    public void drawCardForPlayer(Deck deck) {
        Card newCard = deck.drawCard();
        if (newCard != null) {
            hand[numberOfCards++] = newCard;
        } else {
        	System.out.println(name + " cannot draw a card because the deck is empty!");
            hand[numberOfCards++] = newCard; // Add new card to hand
            
        }
    }
  

    // Choose a card to play based on matching with the top card
    public Card chooseCardToPlay(int index, Card topCard) {
        if (index >= 0 && index < numberOfCards) {
            if (hand[index] != null && hand[index].canPlayOn(topCard)) {
                Card chosenCard = hand[index];
                removeCard(index);
                return chosenCard;
            }
        }
        return null; // No matching card found
    }

    // Search for playable cards
    public boolean search(Card topCard) {
        for (int i = 0; i < numberOfCards; i++) {
            if (hand[i].canPlayOn(topCard)) {
                return true;
            }
        }
        return false;
    }

    // Remove a card from the player's hand
    private void removeCard(int index) {
        for (int i = index; i < numberOfCards - 1; i++) {
            hand[i] = hand[i + 1];
        }
        hand[--numberOfCards] = null;
    }

    // Display the player's hand
    public void showHand() {
        System.out.print(name + "'s hand: ");
        for (int i = 0; i < numberOfCards; i++) {
            System.out.print("  " + (i + 1) + "| " + hand[i] + " | ");
        }
        System.out.println();
    }
}
