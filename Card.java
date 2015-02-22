public class Card {
	private int value;
	private String suit;
	private String type;

	public Card(String type, int value, String suit) {
		this.type = type;
		this.value = value;
		this.suit = suit;	
	}

	public String type() {
		return type;
	}

	public int value() {
		return value;
	}

	public String suit() {
		return suit;
	}

	public boolean isRoyal() {
		if (type != "number" && type != "ace") {
			return true;
		}
		return false;
	}

	public boolean isAce() {
		if (type == "ace") {
			return true;
		}
		return false;
	}


}