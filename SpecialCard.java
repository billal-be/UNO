package UNO;

public class SpecialCard extends Card {
    // Constructor: Initializes a SpecialCard with a specific color and type
    public SpecialCard(Color color, Type type) {
        super(color, type, -1); // Special cards don't have a numeric value
    }

    // Implementation of the abstract method canPlayOn
    @Override
    public boolean canPlayOn(Card topCard) {
        // A SpecialCard can be played if:
        // 1. Its color matches the top card's color.
        // 2. Its type matches the top card's type.
        return this.color == topCard.getColor() || this.type == topCard.getType();
    }
}
