package edu.udel.cisc275_15S.UDoraTheExplorer;

import junit.framework.TestCase;

public class GameTests extends TestCase {
	private UDoraTheExplorer game = new UDoraTheExplorer();
	public void test_method(){
		//These only test to see if the variables are initialized to java defaults
		//Need to run game.create() to initialize to the values needed by the game,
		//however cannot run this method from this class.
		assertEquals(0, game.numPieces);
		assertEquals(0, game.totalTime);
		assertFalse(game.f);
		assertFalse(game.showEnd);
	}
}
