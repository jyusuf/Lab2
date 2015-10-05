package MainPackage;

import java.util.ArrayList;



public class Rule {

	private int MaxNumberOfPlayers;
	private int NumberOfCards;
	private int NumberOfJokers;
	private int CommunityCards;
	private ArrayList<Card> RuleCards = new ArrayList<Card>();

	public Rule(enumGame gme) {
		switch (gme) {
		case FiveStud: {
			this.MaxNumberOfPlayers = 4;
			this.NumberOfCards = 5;
			this.NumberOfJokers = 0;
			this.CommunityCards = 0;
			break;
		}
		case FiveStudOneJoker: {
			this.MaxNumberOfPlayers = 4;
			this.NumberOfCards = 5;
			this.NumberOfJokers = 1;
			this.CommunityCards = 0;
			break;
		}
		case FiveStudTwoJoker: {
			this.MaxNumberOfPlayers = 4;
			this.NumberOfCards = 5;
			this.NumberOfJokers = 2;
			this.CommunityCards = 0;
			break;
		}
		case TexasHoldEm: {
			this.MaxNumberOfPlayers = 8;
			this.NumberOfCards = 2;
			this.NumberOfJokers = 0;
			this.CommunityCards = 5;
			break;
		}
		case Omaha: {
			this.MaxNumberOfPlayers = 6;
			this.NumberOfCards = 4;
			this.NumberOfJokers = 0;
			this.CommunityCards = 5;
			break;
		}
		case SevenDraw: {
			this.MaxNumberOfPlayers = 4;
			this.NumberOfCards = 7;
			this.NumberOfJokers = 0;
			this.CommunityCards = 0;
			break;
		}		
		case DeucesWild: {
			this.MaxNumberOfPlayers = 4;
			this.NumberOfCards = 5;
			this.NumberOfJokers = 0;
			this.RuleCards.add(new Card(enumSuit.DIAMONDS, enumRank.TWO, 40));
			this.RuleCards.add(new Card(enumSuit.HEARTS, enumRank.TWO, 1));
			this.RuleCards.add(new Card(enumSuit.SPADES, enumRank.TWO, 14));
			this.RuleCards.add(new Card(enumSuit.CLUBS, enumRank.TWO, 27));
			this.CommunityCards = 0;
			break;
		}
		case AcesAndEights: {
			this.MaxNumberOfPlayers = 4;
			this.NumberOfCards = 2;
			this.NumberOfJokers = 0;
			this.RuleCards.add(new Card(enumSuit.DIAMONDS, enumRank.ACE, 52));
			this.RuleCards.add(new Card(enumSuit.HEARTS, enumRank.ACE, 13));
			this.RuleCards.add(new Card(enumSuit.SPADES, enumRank.ACE, 26));
			this.RuleCards.add(new Card(enumSuit.CLUBS, enumRank.ACE, 39));
			this.RuleCards.add(new Card(enumSuit.DIAMONDS, enumRank.EIGHT, 46));
			this.RuleCards.add(new Card(enumSuit.HEARTS, enumRank.EIGHT, 7));
			this.RuleCards.add(new Card(enumSuit.SPADES, enumRank.EIGHT, 20));
			this.RuleCards.add(new Card(enumSuit.CLUBS, enumRank.EIGHT, 33));
			this.CommunityCards = 0;
			break;
		}
		}
	}

	public int GetMaxNumberOfPlayers() {
		return this.MaxNumberOfPlayers;
	}

	public int GetNumberOfCards() {
		return this.NumberOfCards;
	}

	public int GetNumberOfJokers() {
		return this.NumberOfJokers;
	}
	
	public int GetCommunityCardsCount()
	{
		return this.CommunityCards;
	}
	
	public ArrayList<Card> GetRuleCards()
	{
		return this.RuleCards;
	}
}
