package edu.udel.cisc275_15S.UDoraTheExplorer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import edu.udel.cisc275_15S.UDoraTheExplorer.CalendarArea.CalendarScreen;
import edu.udel.cisc275_15S.UDoraTheExplorer.UDoraTheExplorer.GameState;

public class ResourcesArea extends Area{
	public UDoraTheExplorer game;
	public MiniGame mGame;
	public ResourcesIntroScreen introScreen;
	public ResourcesTeachingScreen teachingScreen;
	public ResourcesEndScreen endScreen;
	public ArrayList<String> list1;
	public ArrayList<String> list2;
	public HashMap<String,String> answers;
	public RewardScreen reward;
	Random r = new Random();
	//all screens needed for this area will be held as fields
	
	public ResourcesArea(UDoraTheExplorer game){
		this.game = game;
		completed = false;
		questions = new int[4];
		introScreen = new ResourcesIntroScreen(game, this);
		teachingScreen = new ResourcesTeachingScreen(game, this);
		endScreen = new ResourcesEndScreen(game, this);
		reward = new RewardScreen(game, this);
		screenState = ResourcesScreen.teaching;
		mGame = new MiniGame(game, this,InteractWithFiles.readResourcesQuestions());
	}
	
	public void render(){
		if (screenState == ResourcesScreen.intro){
			introScreen.render();
		}
		else if(screenState == ResourcesScreen.teaching){
			teachingScreen.render();
		}
		else if(screenState == ResourcesScreen.minigame){
			mGame.render();
		}
		else if(screenState == ResourcesScreen.reward){
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
				screenState = ResourcesScreen.teaching;
			}
			else {
				endScreen.render();
			}
		}
	}
	
	static enum ResourcesScreen {
		intro, teaching, minigame, reward, end
	}
}
