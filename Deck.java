import java.util.*;
public class Deck {

	private Card[] cards;
	private String[] suits = {"diamonds", "clubs", "hearts", "spades"};
	private String[] royals = {"jack", "queen", "king"};
	private Random random = new Random();

	public Deck(String deck) {
		if (deck == "no royals") {
			this.cards = new Card[40];
			this.addAces(0);
			this.addNormalCards(4);
		}
		else {
			this.cards = new Card[52];
			this.standardDeck();
		}
	}

	// Deal an amount of cards, does not remove the cards from the deck
	public Card[] deal(int amount) {
		Card[] proxy = new Card[amount];
		for (int i = 0; i < amount; i+= 1) {
			int proxy2 = random.nextInt(cards.length);
			System.out.println(proxy2);
			proxy[i] = cards[proxy2];
		}
		return proxy;
	}

	// Add all the cards in a standard jokerless deck
	private void standardDeck() {
		this.addAces(0);
		this.addNormalCards(4);
		this.addRoyals(40, 10);
	}


	// Add aces to a deck from start
	private void addAces(int start) {
		for (String suit : suits) {
			cards[start] = new Card("ace", 1, suit);
			start += 1;
		}
	}

	// Add normal number cards to a deck from start
	private void addNormalCards(int start) {
		for (int i = 2; i < 11; i += 1) {
			for(String suit : suits) {
				cards[start] = new Card("", i, suit);
				start += 1;
			}
		}
	}

	// Add royal cards worth face_value to a deck from start
	private void addRoyals(int start, int face_value) {
		for(String royal : royals) {
			for(String suit : suits) {
				cards[start] = new Card(royal, face_value, suit);
				start += 1;
			}
		}
	}

}