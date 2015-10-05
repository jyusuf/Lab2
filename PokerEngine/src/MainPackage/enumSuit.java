package MainPackage;


public enum enumSuit {

	HEARTS(1), SPADES(2), CLUBS(3), DIAMONDS(4), JOKER(99);
	
	private enumSuit(final int suit){
		this.suit = suit;
	}

	private int suit;
	
	public int getSuit(){
		return suit;
	}
	
	
}