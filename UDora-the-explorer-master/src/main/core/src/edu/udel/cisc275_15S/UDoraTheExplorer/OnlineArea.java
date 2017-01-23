package edu.udel.cisc275_15S.UDoraTheExplorer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import edu.udel.cisc275_15S.UDoraTheExplorer.AdvisementArea.AdvisementScreen;
import edu.udel.cisc275_15S.UDoraTheExplorer.UDoraTheExplorer.GameState;

public class OnlineArea extends Area{
	public UDoraTheExplorer game;
	public OnlineIntroScreen introScreen;
	public OnlineTeachingScreen teachingScreen;
	public OnlineEndScreen endScreen;
	public MiniGame mGame;
	public ArrayList<String> list1;
	public ArrayList<String> list2;
	public HashMap<String,String> answers;
	public RewardScreen reward;
	Random r = new Random();
	//all screens needed for this area will be held as fields
	
	public OnlineArea(UDoraTheExplorer game){
		this.game = game;
		completed = false;
		questions = new int[4];
		screenState = SakaiScreen.teaching;
		introScreen = new OnlineIntroScreen(game, this);
		teachingScreen = new OnlineTeachingScreen(game, this);
		endScreen = new OnlineEndScreen(game, this);
		reward = new RewardScreen(game, this);
		mGame = new MiniGame(game, this, InteractWithFiles.readOnlineQuestions());
	}
	
	public void render(){
		if (screenState == SakaiScreen.intro){
			introScreen.render();
		}
		else if(screenState == SakaiScreen.teaching){
			teachingScreen.render();
		}
		else if(screenState == SakaiScreen.minigame){
			mGame.render();
		}
		else if(screenState == SakaiScreen.reward){
			if(!completed){
				game.numPieces++;
			}
			if(mGame.success == true){
				completed = true;
			}
			reward.render();
		}
		else {
			if(game.advisementArea.completed && game.sakaiArea.completed && game.calendarArea.completed && game.resourceArea.completed){
				game.mode = GameState.overworld;
				screenState = SakaiScreen.teaching;
			}
			else {
				endScreen.render();
			}
		}
	}
	
	static enum SakaiScreen {
		intro, teaching, minigame, reward, end
	}
}
