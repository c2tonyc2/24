public class Launcher24 {
	private static Deck current_deck;
	private static Card[][] table_cards;

		// Simulate a game of 24
	public static void main(String[] args) {
		if (args.length == 0) {
			current_deck = new Deck("royals");
		}
		else if (args[0].equals("no royals")) {
			current_deck = new Deck("no royals");
		}
		int N = 2;
	    StdDrawPlus.setXscale(0, N);
	    StdDrawPlus.setYscale(0, N);
	    while(true) { 
	    	drawTable(N);
	        if (StdDrawPlus.isSpacePressed()) { // Any way to change sample rate?
	        	reDeal(4);
	        }
	        StdDrawPlus.show(100);
	    }
	}

	private static void drawTable(int N) {
		for (int i = 0; i < N; ++i) {
	        for (int j = 0; j < N; ++j) {
	        	if (table_cards != null) { // Special thanks to Diana Ly for producing all images in the img directory. 
			    	StdDrawPlus.picture(i + .5, j + .5, "img/" + String.valueOf(table_cards[i][j].type()) 
			    	+ String.valueOf(table_cards[i][j].value()) + String.valueOf(table_cards[i][j].suit()) + ".png", .75, .9);
	       		}
	       	}
	    }
	}


	// Places a Card into the table_cards array at x and y
	public void place(Card c, int x, int y) {
		table_cards[x][y] = c;
	}


	// Redeals a new hand of 4 cards
	private static void reDeal(int x) {
		wipe();  //Wipes current table_cards
		Card[] cards = current_deck.deal(x);   /// BUG HERE
		int[] card_values = new int[x];
		for (int i = 0; i < x; ++i) {
			card_values[i] = cards[i].value();
		}
		// if (tester.isPossible(card_values)) {
			int counter = 0;
			for (int i = 0; i < 2; ++i) {
				for (int j = 0; j < 2; ++j) {
					table_cards[i][j] = cards[counter];
					counter += 1;
				}
			}
		// }
		
	}

	private static void wipe() {
		table_cards = new Card[2][2];
	}


}
