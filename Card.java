package UNO;

public abstract class Card {
    // Enum for Card Colors
    public enum Color {
        RED, GREEN, BLUE, YELLOW, NONE // NONE is for wild cards
    }

    // Enum for Card Types
    public enum Type {
        NUMBER, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR
    }

    private Color color; // Color of the card
    private final Type type;   // Type of the card
    private final int value;   // Value of the card (used for NUMBER cards, -1 for others)

    // Constructor
    public Card(Color color, Type type, int value) {
        this.color = color;
        this.type = type;
        this.value = value;
    }

    // Getter for the card's color
    public Color getColor() {
        return color;
    }

//    // use to set the chosen color of wild card
//	public void setColor(Color color) {
//		this.color = color;
//	}S

	// Getter for the card's type
    public Type getType() {
        return type;
    }

    // Getter for the card's value
    public int getValue() {
        return value;
    }

    // Abstract method to determine if the card can be played on top of another card
    public abstract boolean canPlayOn(Card topCard);

    // String representation of the card
    @Override
    public String toString() {
        if (type == Type.NUMBER) {
            return color + " " + value;
        } else if((type == Type.WILD || type == Type.WILD_DRAW_FOUR) && color == Color.NONE) {
        		return type.toString();
			} else {
				return color + " " + type;
        }
    }
    
}