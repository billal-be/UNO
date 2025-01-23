package UNO;

import java.util.Random;

public class Deck {
	private Card[] cards; // Array to store all the cards
	private int top; // Pointer to track the next card to draw

	// Constructor: Initializes the deck and shuffles it
	public Deck() {
		initializeDeck();
		shuffle();
	}

	// Initializes the deck with all 108 UNO cards
	private void initializeDeck() {
		cards = new Card[108];
		int index = 0;

		// Define valid colors for cards (RED, BLUE, GREEN, YELLOW)
		Card.Color[] validColors = { Card.Color.RED, Card.Color.BLUE, Card.Color.GREEN, Card.Color.YELLOW };

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
			for (int i = 0; i < 2; i++) {
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

	// Shuffles the deck using the Fisher-Yates algorithm
	private void shuffle() {
		Random rand = new Random();
		for (int i = cards.length - 1; i > 0; i--) {
			// Generate a random index between 0 and i
			int j = rand.nextInt(i + 1);

			// Swap the cards at positions i and j
			Card temp = cards[i];
			cards[i] = cards[j];
			cards[j] = temp;
		}
	}

	// Draws the top card from the deck
	public Card drawCard() {
		if (top < cards.length) {
			return cards[top++]; // Return the card and increment the pointer
		}
		System.out.println("The deck is empty!");
		return null; // Return null if the deck is empty
	}

	/*
	 * these method are for testing
		public void showDeck() {
			for (int i = 0; i < cards.length; i++) {
				System.out.println("Card " + i + ": " + cards[i]);
			}
		}

		public static void main(String[] args) {
			Deck deck = new Deck();
			deck.showDeck();
		}
	*/
}
