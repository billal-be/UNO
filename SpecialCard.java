package UNO;

public class SpecialCard extends Card {
	// Constructor: Initializes a SpecialCard with a specific color and type
	public SpecialCard(Color color, Type type) {
		super(color, type, -1);
	}

	// Implementation of the abstract method canPlayOn
	@Override
	public boolean canPlayOn(Card topCard) {
		// A SpecialCard can be played if its color or type matches the top card
		return this.getColor() == topCard.getColor() || this.getType() == topCard.getType();
	}
}