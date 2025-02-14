package UNO;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;



public class Game {
    private final Deck deck;                // The deck of cards
    private final Player[] players;         // Array of players
    private Card topCard;                   // The top card of the discard pile
    private int currentPlayerIndex;         // The current player's index
    private boolean direction;              // True for clockwise, false for counter-clockwise
    private boolean decision;               // True if there is a winner
    private boolean draw;
    private final Scanner scanner;
    private Card playedCard;
    private int ai;
    private int numPlayers;
    private String playerName;
    private Player currentPlayer;
    
    public boolean getDraw() {
		return draw;
	}

	public void setDraw(boolean draw) {
		this.draw = draw;
	}
	public boolean playerexist(String name,int r) {
		for (int i = 0; i < r; i++) {
			if(players[i].getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
    // Constructor to initialize the game
    public Game() {
    	displayTitleScreen();
        deck = new Deck(); // Initialize the deck
        scanner = new Scanner(System.in);
        setDraw(false);
        do {
			System.out.println("Choose an option:");
			System.out.println("1. Play against your friend");
			System.out.println("2. Play against the bot");
			System.out.print("Enter your choice: ");
			ai = scanner.nextInt();
		} while (ai > 2 || ai < 1);
        switch (ai) {
		case 2:
			players = new Player[2];
	            System.out.print("Enter the Player name: ");
	            playerName = scanner.next();
	            players[0] = new Player(playerName, deck);
	            players[1] = new BotPlayer("Bot", deck);
			break;
		default:
	        // Initialize players (max of 4 players)
	        
	        System.out.print("Enter the number of players (2-4): ");
	        numPlayers = scanner.nextInt();
	        scanner.nextLine();

	        while (numPlayers < 2 || numPlayers > 4) {
	            System.out.print("Enter a valid number of players (2-4): ");
	            numPlayers = scanner.nextInt();
	            scanner.nextLine();
	        }

	        players = new Player[numPlayers];
	        for (int i = 0; i < numPlayers; i++) {
	            System.out.print("Enter Player " + (i + 1) + "'s name: ");
	            playerName = scanner.next();
	            if(i>=1 && playerexist(playerName,i)) {
	            	while(playerexist(playerName,i)) {
	            	System.out.print("player allready exist choose enother name for player " + (i + 1) + " ");
	            	playerName = scanner.next();
	            	
	            	}
	            }
	            scanner.nextLine();
	            players[i] = new Player(playerName, deck);
	        	}
			break;
		}


        // Initialize the game
        currentPlayerIndex = 0;
        direction = true; // Default direction is clockwise
        do {
            topCard = deck.drawCard();
        } while (topCard == null || !(topCard instanceof NumberCard)); // Ensure the starting card is a NumberCard
        System.out.println("Game starts with top card: " + topCard);
    }
	private void displayTitleScreen() {
		System.out.println("=================================");
		System.out.println("      WELCOME TO THE GAME OF     ");
		System.out.println("  _   _  _   _   ___            ");
		System.out.println(" | | | || \\ | | / _ \\           ");
		System.out.println(" | | | ||  \\| || | | |          ");
		System.out.println(" | |_| || |\\  || |_| |          ");
		System.out.println("  \\___/ |_| \\_| \\___/           ");
		System.out.println("=================================");
		System.out.println("     Let's Start the Game! 🎮    ");
		System.out.println();
	}

    // Method to start the game
    public void startGame() {
        while (!decision) {
            currentPlayer = players[currentPlayerIndex];
            System.out.println("\nTop card: " + topCard);
            currentPlayer.showHand();
            
            
            if (currentPlayer.search(topCard)) {
            	if(currentPlayer instanceof BotPlayer) {
                	// Bot's turn
                    playedCard = currentPlayer.chooseCardToPlay(0, topCard);
                    if (playedCard != null) {
                        topCard = playedCard;
                        handleCardEffect(playedCard);
                        System.out.println(currentPlayer.getName() + " played: " + topCard);
                        if (currentPlayer.hasWon()) {
		                	System.out.println("\n\n");
		                	System.out.println("╔════════════════════════════════════════════════╗");
		                	System.out.println("║                🎉 CONGRATULATIONS! 🎉          ║");
		                	System.out.println("║                                                ║");
		                	System.out.printf("║           %s has won the game! 🏆           ║\n", currentPlayer.getName());
		                	System.out.println("║                                                ║");
		                	System.out.println("╚════════════════════════════════════════════════╝");
		                	System.out.println("\n\n");
		                    decision = true;
		                    break;
		                }
                    }}else {
		            	currentPlayer.validindex(topCard);
		                System.out.println("Select the card you want to play (1-" + currentPlayer.getNumberOfCards() + "):");
		                int cardIndex = scanner.nextInt() - 1;
		
		                while (cardIndex < 0 || cardIndex >= currentPlayer.getNumberOfCards()) {
		                    System.out.println("Invalid choice. Try again:");
		                    cardIndex = scanner.nextInt() - 1;
		                }
		
		                playedCard = currentPlayer.chooseCardToPlay(cardIndex, topCard);
		                while (playedCard == null) {
		                    System.out.println("Card cannot be played. Choose another card:");
		                    cardIndex = scanner.nextInt() - 1;
		                    playedCard = currentPlayer.chooseCardToPlay(cardIndex, topCard);
		                }
		
		                topCard = playedCard;
		                // Handle special and wild card effects
		                handleCardEffect(playedCard);
		                System.out.println(currentPlayer.getName() + " played: " + topCard);
		
		                // Check if the player has won
		                if (currentPlayer.hasWon()) {
		                	System.out.println("\n\n");
		                	System.out.println("╔════════════════════════════════════════════════╗");
		                	System.out.println("║                🎉 CONGRATULATIONS! 🎉          ║");
		                	System.out.println("║                                                ║");
		                	System.out.printf("║           %s has won the game! 🏆           ║\n", currentPlayer.getName());
		                	System.out.println("║                                                ║");
		                	System.out.println("╚════════════════════════════════════════════════╝");
		                	System.out.println("\n\n");
		                    decision = true;
		                    break;
		                }

                    }
            	} else {
				         System.out.println(currentPlayer.getName() + " has no playable cards and must draw.");
				         currentPlayer.drawCardForPlayer(deck);
				         if(players[currentPlayerIndex].getlastdrawncard()==null) {
				        	 decision=true;
				        	 setDraw(true);
				             	break;
				            }
				          Card drawnCard = currentPlayer.chooseCardToPlay((currentPlayer.getNumberOfCards())-1,topCard);
				          if(drawnCard != null && drawnCard.canPlayOn(topCard)) {
				        	  topCard = drawnCard; // Update top card with the played card
				               // Handle special and wild card effects
				               handleCardEffect(drawnCard);
				               System.out.println(currentPlayer.getName() + " played:the drawn card " + topCard);
				             }
            		}
            currentPlayerIndex = nextPlayerIndex();
        }
        if(getDraw()==true) {
        	System.out.println("the game end with a draw");        	
        }
    }

    private void handleCardEffect(Card card) {
        switch (card.getType()) {
            case REVERSE:
               direction = !direction; // Reverse the game direction
                if(players.length==2) {
                	currentPlayerIndex = nextPlayerIndex();
                }
                break;

            case SKIP:
                currentPlayerIndex = nextPlayerIndex(); // Skip the next player's turn
                break;

            case DRAW_TWO:
                currentPlayerIndex = nextPlayerIndex(); // Move to the next player
                players[currentPlayerIndex].drawCardForPlayer(deck);
                if(players[currentPlayerIndex].getlastdrawncard()==null) {
                	decision=true;
                	setDraw(true);
                	break;
                }
                players[currentPlayerIndex].drawCardForPlayer(deck);
                if(players[currentPlayerIndex].getlastdrawncard()==null) {
                	decision=true;
                	setDraw(true);
                	break;
                }
                break;

            case WILD:
            	int chosenColor;
            	if(currentPlayer instanceof BotPlayer) {
            		Random random = new Random();
                     chosenColor = random.nextInt(4) + 1;
            	}else {
            		System.out.println("Choose a color (1 RED, 2 GREEN, 3 BLUE, 4 YELLOW):");
	                 chosenColor=scanner.nextInt();
	             while (chosenColor<1||chosenColor>4) {	
	            	 System.out.println("Choose a color (1 RED, 2 GREEN, 3 BLUE, 4 YELLOW):");
	            	 chosenColor=scanner.nextInt();
	             		}
            		}
	                
	             switch (chosenColor) {
						case 1:
							((WildCard) card).setchoosenColor(Card.Color.RED); // Update the card's color
			                System.out.println("Chosen color: " + ((WildCard) card).getChosenColor());
							break;
						case 2:
							((WildCard) card).setchoosenColor(Card.Color.GREEN); // Update the card's color
			                System.out.println("Chosen color: " + ((WildCard) card).getChosenColor());
							break;
						case 3:
							((WildCard) card).setchoosenColor(Card.Color.BLUE); // Update the card's color
			                System.out.println("Chosen color: " + ((WildCard) card).getChosenColor());
							break;
						case 4:
							((WildCard) card).setchoosenColor(Card.Color.YELLOW); // Update the card's color
			                System.out.println("Chosen color: " + ((WildCard) card).getChosenColor());
							break;
						default:
							break;
	             }
                // Update the topCard to reflect the chosen color
                topCard = new WildCard(((WildCard) card).getChosenColor(), card.getType());
                break;

            case WILD_DRAW_FOUR:
            	if(currentPlayer instanceof BotPlayer) {
            		Random random = new Random();
                     chosenColor = random.nextInt(4) + 1;
            	}else {
            		System.out.println("Choose a color (1 RED, 2 GREEN, 3 BLUE, 4 YELLOW):");
	                 chosenColor=scanner.nextInt();
	             while (chosenColor<1||chosenColor>4) {	
	            	 System.out.println("Choose a color (1 RED, 2 GREEN, 3 BLUE, 4 YELLOW):");
	            	 chosenColor=scanner.nextInt();
	             		}
            		}
	             switch (chosenColor) {
						case 1:
							((WildCard) card).setchoosenColor(Card.Color.RED); // Update the card's color
			                System.out.println("Chosen color: " + ((WildCard) card).getChosenColor());
							break;
						case 2:
							((WildCard) card).setchoosenColor(Card.Color.GREEN); // Update the card's color
			                System.out.println("Chosen color: " + ((WildCard) card).getChosenColor());
							break;
						case 3:
							((WildCard) card).setchoosenColor(Card.Color.BLUE); // Update the card's color
			                System.out.println("Chosen color: " + ((WildCard) card).getChosenColor());
							break;
						case 4:
							((WildCard) card).setchoosenColor(Card.Color.YELLOW); // Update the card's color
			                System.out.println("Chosen color: " + ((WildCard) card).getChosenColor());
							break;
						default:
							break;
	             }
	             	topCard = new WildCard(((WildCard) card).getChosenColor(), card.getType());
                currentPlayerIndex = nextPlayerIndex();
                for (int i = 0; i < 4; i++) {
                    players[currentPlayerIndex].drawCardForPlayer(deck);
                    if(players[currentPlayerIndex].getlastdrawncard()==null) {
                    	decision=true;
                    	setDraw(true);
                    	break;
                    }
                }
                break;

            default:
                break;
        }
    }

   private int nextPlayerIndex() {
        if (direction) {
            return (currentPlayerIndex + 1) % (players.length);
        } else {
            return (currentPlayerIndex - 1 + (players.length)) % (players.length);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
