package UNO;

public class WildCard extends Card {
    private Color chosenColor; // The color chosen after playing this card

    // Constructor: Initializes a WildCard with a specific type
    public WildCard(Color color, Type type){
        super(color, type, -1);
    }
    
    // Implementation of the abstract method canPlayOn
    @Override
    public boolean canPlayOn(Card topCard) {
        // A WildCard can always be played
        return true;
    }

    // Sets the chosen color for the wild card
    public void chooseColor(Color color) {
        this.chosenColor = color;
    }

    // Gets the chosen color of the wild card
    public Color getChosenColor() {
        return chosenColor;
    }
}

