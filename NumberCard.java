package UNO;

public class NumberCard extends Card {

	// Constructor: Initializes a NumberCard with a given color and value
	public NumberCard(Color color, int value) {
		super(color, Type.NUMBER, value);
	}

	// Implementation of the abstract method canPlayOn
	@Override
	public boolean canPlayOn(Card topCard) {
		// A NumberCard can be played if its color or value matches the top card
		return this.getColor() == topCard.getColor() || this.getValue() == topCard.getValue();
	}
}