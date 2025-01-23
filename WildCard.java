package UNO;

public class WildCard extends Card {
	private Color chosenColor; // The color selected after playing the WildCard

	// Constructor: Initializes a WildCard with a given color and type
	public WildCard(Color color, Type type) {
		super(color, type, -1);
	}

	// Implementation of the abstract method canPlayOn
	@Override
	public boolean canPlayOn(Card topCard) {
		return true;
	}

	// Sets the chosen color for the wild card
	public void setchoosenColor(Color color) {
		this.chosenColor = color;
	}

	// Gets the chosen color of the wild card
	public Color getChosenColor() {
		return chosenColor;
	}
}
