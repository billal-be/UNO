package UNO;

public abstract class Card {
	// Possible colors for cards
	public enum Color {
		RED, GREEN, BLUE, YELLOW, NONE // NONE is for wild cards
	}

	// Types of cards in UNO
	public enum Type {
		NUMBER, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR
	}

	private Color color; // Card's color
	private final Type type; // Card's type
	private final int value; // Card's value (-1 for non-number cards)

	// Constructor
	public Card(Color color, Type type, int value) {
		this.color = color;
		this.type = type;
		this.value = value;
	}

	public Color getColor() {
		return color;
	}

	public Type getType() {
		return type;
	}

	public int getValue() {
		return value;
	}

	// Checks if this card can be played on top of another card.
	public abstract boolean canPlayOn(Card topCard);

	// Returns the card as a string
	@Override
	public String toString() {
		if (type == Type.NUMBER) {
			return color + " " + value;
		} else if ((type == Type.WILD || type == Type.WILD_DRAW_FOUR) && color == Color.NONE) {
			return type.toString();
		} else {
			return color + " " + type;
		}
	}
}