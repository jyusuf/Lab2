package MainPackage;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class Deck_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestFullDeck() {
	
		Deck myDeck = new Deck();
		int numOfCards = myDeck.getTotalCards();
		assertTrue(numOfCards==52);
	}
	
	@Test
	public void FiveOfAKind() {
		Deck d = new Deck();
		Hand h = new Hand();
		h.AddCardToHand(new Card(enumSuit.CLUBS,enumRank.TEN,0));
		h.AddCardToHand(new Card(enumSuit.CLUBS,enumRank.TEN,0));
		h.AddCardToHand(new Card(enumSuit.CLUBS,enumRank.TEN,0));
		h.AddCardToHand(new Card(enumSuit.CLUBS,enumRank.TEN,0));
		h.AddCardToHand(new Card(enumSuit.CLUBS,enumRank.TEN,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == enumHandStrength.FiveOfAKind.getHandStrength());

	}	
	
}
