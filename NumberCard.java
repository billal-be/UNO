package UNO;

public class NumberCard extends Card {
    // Constructor: Initializes a NumberCard with a specific color and value
    public NumberCard(Color color, int value) {
        super(color, Type.NUMBER, value); // Call the parent class (Card) constructor
    }

    // Implementation of the abstract method canPlayOn
    @Override
    public boolean canPlayOn(Card topCard) {
        // A NumberCard can be played if:
        // 1. Its color matches the top card's color.
        // 2. Its value matches the top card's value.
        return this.color == topCard.getColor() || this.value == topCard.getValue();
    }
}