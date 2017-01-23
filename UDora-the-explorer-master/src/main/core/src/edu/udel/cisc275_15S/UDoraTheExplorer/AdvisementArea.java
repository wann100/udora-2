package edu.udel.cisc275_15S.UDoraTheExplorer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import edu.udel.cisc275_15S.UDoraTheExplorer.UDoraTheExplorer.GameState;

public class AdvisementArea extends Area{
	public UDoraTheExplorer game;
	public AdvisementIntroScreen introScreen;
	public AdvisementTeachingScreen teachingScreen;
	public AdvisementEndScreen endScreen;
	public MiniGame mGame;
	public ArrayList<String> list1;
	public ArrayList<String> list2;
	public HashMap<String,String> answers;
	public RewardScreen reward;
	Random r = new Random();
	//all screens needed for this area will be held as fields
	
	public AdvisementArea(UDoraTheExplorer game){
		this.game = game;
		screenState = AdvisementScreen.teaching;
		questions = new int[4];
		introScreen = new AdvisementIntroScreen(game, this);
		teachingScreen = new AdvisementTeachingScreen(game, this);
		endScreen = new AdvisementEndScreen(game, this);
		reward = new RewardScreen(game, this);
		completed = false;
		list1 = new ArrayList<String>();
		mGame = new MiniGame(game, this, InteractWithFiles.readAdvisementQuestions());
	}
	
	public void render(){
		if (screenState == AdvisementScreen.intro){
			introScreen.render();
		}
		else if(screenState == AdvisementScreen.teaching){
			teachingScreen.render();
		}
		else if(screenState == AdvisementScreen.minigame){
			mGame.render();
		}
		else if(screenState == AdvisementScreen.reward){
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
				screenState = AdvisementScreen.teaching;
			}
			else {
				endScreen.render();
			}
		}
		
	}
	
	static enum AdvisementScreen {
		intro, teaching, minigame, reward, end
	}
}
