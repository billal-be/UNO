package UNO;

import java.util.Random;

public class Deck {
    private Card[] cards;
    private int top; // Pointer to the next card to draw

    public Deck() {
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        cards = new Card[108]; // 108 cards including wild cards
        int index = 0;

        // Manually define valid colors (RED, BLUE, GREEN, YELLOW)
        Card.Color[] validColors = {Card.Color.RED, Card.Color.BLUE, Card.Color.GREEN, Card.Color.YELLOW};

        // Add cards for each valid color
        for (Card.Color color : validColors) {
            // Add one "0" card
            cards[index++] = new NumberCard(color, 0);

            // Add two copies of "1-9" cards
            for (int i = 1; i <= 9; i++) {
                cards[index++] = new NumberCard(color, i);
                cards[index++] = new NumberCard(color, i);
            }

            // Add two SKIP, REVERSE, and DRAW_TWO cards per color
            for (int j = 0; j < 2; j++) {
                cards[index++] = new SpecialCard(color, Card.Type.SKIP);
                cards[index++] = new SpecialCard(color, Card.Type.REVERSE);
                cards[index++] = new SpecialCard(color, Card.Type.DRAW_TWO);
            }
        }

        // Add wild cards (4 WILD, 4 WILD_DRAW_FOUR)
        for (int i = 0; i < 4; i++) {
            cards[index++] = new WildCard(Card.Color.NONE, Card.Type.WILD);
            cards[index++] = new WildCard(Card.Color.NONE, Card.Type.WILD_DRAW_FOUR);
        }

        top = 0; // Initialize the deck pointer
    }

    // Shuffle the deck using random swaps
    private void shuffle() {
        Random rand = new Random();
        for (int i = cards.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    public Card drawCard() {
        if (top < cards.length) {
            return cards[top++];
        }
        System.out.println("The deck is empty!");
        return null; // Return null if the deck is empty
    }

    public void showDeck() {
        for (int j = 0; j < cards.length; j++) {
            System.out.println("Card " + j + ": " + cards[j]);
        }
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.showDeck();
    }
}
