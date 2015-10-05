package MainPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;


import javax.xml.bind.annotation.XmlElement;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

public class Hand {
	private UUID playerID;
	@XmlElement
	private ArrayList<Card> CardsInHand;
	private ArrayList<Card> BestCardsInHand;

	@XmlElement
	private int HandStrength;
	@XmlElement
	private int HiHand;
	@XmlElement
	private int LoHand;
	@XmlElement
	private int Kicker;

	private boolean bScored = false;

	private boolean Flush;
	private boolean Straight;
	private boolean FourKind;
	private boolean TOAK;
	private boolean twopair;
	private boolean onepair;
	private boolean FullHouse;
	private boolean Ace;
	private static Deck dJoker = new Deck();

	public Hand()
	{
		
	}
	public void  AddCardToHand(Card c)
	{
		if (this.CardsInHand == null)
		{
			CardsInHand = new ArrayList<Card>();
		}
		this.CardsInHand.add(c);
	}
	
	public Card  GetCardFromHand(int location)
	{
		return CardsInHand.get(location);
	}
	
	public Hand(Deck d) {
		ArrayList<Card> Import = new ArrayList<Card>();
		for (int x = 0; x < 5; x++) {
			Import.add(d.drawFromDeck());
		}
		CardsInHand = Import;


	}


	public Hand(ArrayList<Card> setCards) {
		this.CardsInHand = setCards;
	}

	public ArrayList<Card> getCards() {
		return CardsInHand;
	}

	public ArrayList<Card> getBestHand() {
		return BestCardsInHand;
	}

	public void setPlayerID(UUID playerID)
	{
		this.playerID = playerID;
	}
	public UUID getPlayerID()
	{
		return playerID;
	}
	public void setBestHand(ArrayList<Card> BestHand) {
		this.BestCardsInHand = BestHand;
	}

	public int getHandStrength() {
		return HandStrength;
	}


	public int getKicker() {
		return Kicker;
	}

	public int getHighPairStrength() {
		return HiHand;
	}

	public int getLowPairStrength() {
		return LoHand;
	}

	public boolean getAce() {
		return Ace;
	}

	public static Hand EvalHand(ArrayList<Card> SeededHand) {
		
		Deck d = new Deck();
		Hand h = new Hand(d);
		h.CardsInHand = SeededHand;

		return h;
	}

	public void EvalHand() {
		// Evaluates if the hand is a flush and/or straight then figures out
		// the hand's strength attributes

		// Sort the cards!
		Collections.sort(CardsInHand, Card.CardRank);

		// Ace Evaluation
		if (CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank() == enumRank.ACE) {
			Ace = true;
		}


		// Flush Evaluation
		if (CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
				.get(enumCardNo.SecondCard.getCardNo()).getSuit()
				&& CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(enumCardNo.ThirdCard.getCardNo()).getSuit()
				&& CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(enumCardNo.FourthCard.getCardNo()).getSuit()
				&& CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(enumCardNo.FifthCard.getCardNo()).getSuit()) {
			Flush = true;
		} else {
			Flush = false;
		}

		// five of a Kind

		 if (CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(enumCardNo.FifthCard.getCardNo()).getRank()) {
			ScoreHand(enumHandStrength.FiveOfAKind,
					CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
									.getRank(), 0, 0);
		}

		// Straight Evaluation
		else if (Ace) {
			// Looks for Ace, King, Queen, Jack, 10
			if (CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank() == enumRank.KING
					&& CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank() == enumRank.QUEEN
					&& CardsInHand.get(enumCardNo.FourthCard.getCardNo())
							.getRank() == enumRank.JACK
					&& CardsInHand.get(enumCardNo.FifthCard.getCardNo()).getRank() == enumRank.TEN) {
				Straight = true;
				// Looks for Ace, 2, 3, 4, 5
			} else if (CardsInHand.get(enumCardNo.FifthCard.getCardNo()).getRank() == enumRank.TWO
					&& CardsInHand.get(enumCardNo.FourthCard.getCardNo())
							.getRank() == enumRank.THREE
					&& CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank() == enumRank.FOUR
					&& CardsInHand.get(enumCardNo.SecondCard.getCardNo())
							.getRank() == enumRank.FIVE) {
				Straight = true;
			} else {
				Straight = false;
			}
			// Looks for straight without Ace
		} else if (CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
				.getRank() == CardsInHand.get(enumCardNo.SecondCard.getCardNo())
				.getRank().getRank() + 1
				&& CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
				.getRank() == CardsInHand
						.get(enumCardNo.ThirdCard.getCardNo()).getRank().getRank() + 2
				&& CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(enumCardNo.FourthCard.getCardNo()).getRank()
						.getRank() + 3
				&& CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(enumCardNo.FifthCard.getCardNo()).getRank().getRank() + 4) {
			Straight = true;
		} else {
			Straight = false;
		}

		// Evaluate Royal Flush
		if (Straight == true
				&& Flush == true
				&& CardsInHand.get(enumCardNo.FifthCard.getCardNo()).getRank() == enumRank.TEN
				&& Ace) {
			ScoreHand(enumHandStrength.RoyalFlush, 0, 0, 0);
		}

		// Straight Flush
		else if (Straight == true && Flush == true) {
			ScoreHand(enumHandStrength.StraightFlush,
					CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0, 0);
		}
		
		
		// Four of a Kind
		if((CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
				.getRank()==CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank()
				.getRank() && CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
				.getRank()==CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank()
				.getRank() && CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
				.getRank()==CardsInHand.get(enumCardNo.FourthCard.getCardNo()).getRank()
				.getRank()) 
				|| 
			(CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank()
				.getRank()==CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank()
						.getRank() && CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank()
				.getRank()==CardsInHand.get(enumCardNo.FourthCard.getCardNo()).getRank()
						.getRank()	&& CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank()
				.getRank()==CardsInHand.get(enumCardNo.FifthCard.getCardNo()).getRank()
						.getRank())){
			FourKind = true;
			
		}
		else {
			FourKind = false;
		}

		

		// Full House
		if((CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
				.getRank()==CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank()
					.getRank() && CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
				.getRank()==CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank()
					.getRank() && CardsInHand.get(enumCardNo.FourthCard.getCardNo()).getRank()
				.getRank()==CardsInHand.get(enumCardNo.FifthCard.getCardNo()).getRank()
					.getRank())
				|| 
			(CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
				 .getRank()==CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank()
					 .getRank() && CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank()
				 .getRank()==CardsInHand.get(enumCardNo.FourthCard.getCardNo()).getRank()
					 .getRank() && CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank()
				 .getRank()==CardsInHand.get(enumCardNo.FifthCard.getCardNo()).getRank()
					.getRank())) {
				FullHouse = true;
			
			}
			else {
				FullHouse = false;
			}

			
		// Three of a Kind
		 if((CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
					.getRank()==CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank()
						.getRank() && CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
					.getRank()==CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank()
						.getRank())
					||
			(CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank()
					.getRank()==CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank()
						.getRank() && CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank()
					.getRank()==CardsInHand.get(enumCardNo.FourthCard.getCardNo()).getRank()
						.getRank())
					||
			(CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank()
					.getRank()==CardsInHand.get(enumCardNo.FourthCard.getCardNo()).getRank()
							.getRank() && CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank()
					.getRank()==CardsInHand.get(enumCardNo.FifthCard.getCardNo()).getRank()
							.getRank())){
				TOAK = true;
				
			}
			else {
				TOAK = false;
			}
		

			
			// Two Pair
				if((CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
						.getRank()==CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank()
							.getRank() && CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank()
						.getRank()==CardsInHand.get(enumCardNo.FourthCard.getCardNo()).getRank()
							.getRank())
						||
					(CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
						.getRank()==CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank()
							.getRank() && CardsInHand.get(enumCardNo.FourthCard.getCardNo()).getRank()
						.getRank()==CardsInHand.get(enumCardNo.FifthCard.getCardNo()).getRank()
							.getRank())
						||
					(CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank()
						.getRank()==CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank()
							.getRank() && CardsInHand.get(enumCardNo.FourthCard.getCardNo()).getRank()
						.getRank()==CardsInHand.get(enumCardNo.FifthCard.getCardNo()).getRank()
							.getRank())){
				twopair = true;
							
			}
			else {
				twopair = false;
			}
			

			// One Pair
				if((CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
						.getRank()==CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank()
								.getRank() || CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
						.getRank()==CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank()
								.getRank() || CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
						.getRank()==CardsInHand.get(enumCardNo.FourthCard.getCardNo()).getRank()
								.getRank() || CardsInHand.get(enumCardNo.FirstCard.getCardNo()).getRank()
						.getRank()==CardsInHand.get(enumCardNo.FifthCard.getCardNo()).getRank()
							.getRank()) 
							|| 
					(CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank()
							.getRank()==CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank()
								.getRank() || CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank()
							.getRank()==CardsInHand.get(enumCardNo.FourthCard.getCardNo()).getRank()
									.getRank() || CardsInHand.get(enumCardNo.SecondCard.getCardNo()).getRank()
							.getRank()==CardsInHand.get(enumCardNo.FifthCard.getCardNo()).getRank()
								.getRank())
							||
					(CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank()
							.getRank()==CardsInHand.get(enumCardNo.FourthCard.getCardNo()).getRank()
								.getRank() || CardsInHand.get(enumCardNo.ThirdCard.getCardNo()).getRank()
							.getRank()==CardsInHand.get(enumCardNo.FifthCard.getCardNo()).getRank()
								.getRank())
							|| 
					(CardsInHand.get(enumCardNo.FourthCard.getCardNo()).getRank()
								.getRank()==CardsInHand.get(enumCardNo.FifthCard.getCardNo()).getRank()			
									.getRank())){
							onepair = true;
							}
							else {
							onepair = false;
							}
	
	}
		// High Card
		//	I'll give you this one :)	
	
		
	private void ScoreHand(enumHandStrength hST, int HiHand, int LoHand, int Kicker) {
		this.HandStrength = hST.getHandStrength();
		this.HiHand = HiHand;
		this.LoHand = LoHand;
		this.Kicker = Kicker;
		this.bScored = true;

	}

	/**
	 * Custom sort to figure the best hand in an array of hands
	 */
	public static Comparator<Hand> HandRank = new Comparator<Hand>() {

		public int compare(Hand h1, Hand h2) {

			int result = 0;

			result = h2.getHandStrength() - h1.getHandStrength();

			if (result != 0) {
				return result;
			}

			result = h2.getHighPairStrength() - h1.getHighPairStrength();
			if (result != 0) {
				return result;
			}

			result = h2.getLowPairStrength() - h1.getLowPairStrength();
			if (result != 0) {
				return result;
			}

			result = h2.getKicker() - h1.getKicker();
			if (result != 0) {
				return result;
			}

			return 0;
		}
	};
}
