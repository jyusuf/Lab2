package MainPackage;

import java.util.Comparator;


public final class Card {
	
	private enumSuit Suit;	
	private enumRank Rank;	
	private boolean Wild = false;	
	private String CardImg;
	
	
	/**
	 * Keep the no-arg constructor private.  I don't want 'Card' created without attributes.
	 */
	private Card()
	{
	}
	
	/**
	 * Create a new card of a given rank and suit.
	 * @param suit
	 * @param rank
	 */
	public Card(enumSuit suit, enumRank rank, int CardNbr ) {
		Suit = suit; 
		Rank = rank; 
		this.Wild = false;
		this.CardImg = CardNbr + ".png";
		
	}

	public Card(enumSuit suit, enumRank rank, boolean Wild) {
		Suit = suit; 
		Rank = rank; 
		this.Wild = Wild;
	}
	
	/**
	 * Getter for Rank
	 * @return
	 */
	public enumRank getRank() {
		return this.Rank;
	}

	/**
	 * Getter for Suit
	 * @return
	 */
	public enumSuit getSuit() {
		return this.Suit;
	}
	
	public boolean getWild()
	{
		return this.Wild;
	}
	
	public void setWild()
	{
		this.Wild = true;
	}
	
	public String getCardImg()
	{
		return this.CardImg;
	}

	/**
	 * CardRank Comparator is used for sorting the collection by rank
	 */
	public static Comparator<Card> CardRank = new Comparator<Card>() {

		public int compare(Card c1, Card c2) {

		   int Cno1 = c1.getRank().getRank();
		   int Cno2 = c2.getRank().getRank();

		   /*For descending order*/
		   return Cno2 - Cno1;

	   }};

}