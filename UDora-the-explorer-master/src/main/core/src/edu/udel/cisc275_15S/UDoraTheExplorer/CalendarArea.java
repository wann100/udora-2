package edu.udel.cisc275_15S.UDoraTheExplorer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import edu.udel.cisc275_15S.UDoraTheExplorer.AdvisementArea.AdvisementScreen;
import edu.udel.cisc275_15S.UDoraTheExplorer.UDoraTheExplorer.GameState;


public class CalendarArea extends Area{
	public UDoraTheExplorer game;
	public CalendarIntroScreen introScreen;
	public CalendarTeachingScreen teachingScreen;
	public CalendarEndScreen endScreen;
	public MiniGame mGame;
	public ArrayList<String> list1;
	public ArrayList<String> list2;
	public HashMap<String,String> answers;
	public RewardScreen reward;
	Random r = new Random();
	//all screens needed for this area will be held as fields
	
	public CalendarArea(UDoraTheExplorer game){
		this.game = game;
		completed = false;
		questions = new int[4];
		screenState = CalendarScreen.teaching;
		introScreen = new CalendarIntroScreen(game, this);
		teachingScreen = new CalendarTeachingScreen(game, this);
		endScreen = new CalendarEndScreen(game, this);
		reward = new RewardScreen(game, this);
		mGame = new MiniGame(game, this, InteractWithFiles.readCalendarQuestions());
	}
	
	public void render(){
		if (screenState == CalendarScreen.intro){
			introScreen.render();
		}
		else if(screenState == CalendarScreen.teaching){
			teachingScreen.render();
		}
		else if(screenState == CalendarScreen.minigame){
			mGame.render();
		}
		else if(screenState == CalendarScreen.reward){
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
				screenState = CalendarScreen.teaching;
			}
			else {
				endScreen.render();
			}
		}
	}
	
	static enum CalendarScreen {
		intro, teaching, minigame, reward, end
	}
}
