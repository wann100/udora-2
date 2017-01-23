package edu.udel.cisc275_15S.UDoraTheExplorer;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.Date;

public class UDoraTheExplorer extends ApplicationAdapter{
	
	 /* Just so I don't have to keep switching back to a text file...
	 * 
	 * First Priority
	 * 	Students know: Critical dates on academic calendar
	 * 	Students know: their roles and responsibilities as advisees and for the advisors
	 * 	Students know: where to obtain resources to help them be academically successful: math tutoring, writing center
	 * 	Students know: where to obtain resources to help them be successful (wut?)
	 * 	Students know: where to go to change their major or to research changing their major
	 * 
	 * Second Priority:
	 * 	Students know: how their choice of major connects to their career goals
	 * 	Students know: about the Career Services Center
	 * 	Students know: / are aware of the career development process
	 * 	Students know: to begin to assess their own interests and abilities
	 * 	Students know: about on and off campus activities that help them grow as individuals (Undergrad research, study abroad, service learning etc)
	 * 
	 * Important data to monitor:
	 * 		Student ID
	 * 		Date attempted
	 * 		# of Attempts
	 * 		Last Date Attempted
	 * 		**see the above priorities for everything else**
	 */
	// Element 0: Number of tries, Element 1: Number of successes, Element 2: % of questions correct
	public ArrayList<Integer> onlineData;
	public ArrayList<Integer> resourcesData;
	public ArrayList<Integer> advisementData;
	public ArrayList<Integer> calendarData;
	public long startTime;
	public HTTPrequest database;
	public int totalTime;
	public GameState mode;
	public WorldArea worldArea;
	public OnlineArea sakaiArea;
	public AdvisementArea advisementArea;
	public ResourcesArea resourceArea;
	public CalendarArea calendarArea;
	public MainScreen mainScreen;
	public LogInScreen loginScreen;
	public IntroScreen introscreen;
	public Basketballgame basketballgame;
	public EndScreen endScreen;
	public int numPieces;
	private int first;
	public boolean f;
	public boolean showEnd;
	private Date currDate;
	
	public void resetGame () {
		//sets all variables used in the game back to their base values.
	}

	public void create(){
		mode = GameState.titlescreen;
		first = 0;
		f = true;
		showEnd = true;
		totalTime = 0;
		startTime = TimeUtils.millis();
		worldArea = new WorldArea(this);
		sakaiArea = new OnlineArea(this);
		advisementArea = new AdvisementArea(this);
		resourceArea = new ResourcesArea(this);
		calendarArea = new CalendarArea(this);
		mainScreen = new MainScreen(this);
		introscreen = new IntroScreen(this);
		loginScreen= new LogInScreen(this);
		basketballgame = new Basketballgame(this);
		loginScreen = new LogInScreen(this);
		endScreen = new EndScreen(this);
		onlineData = new ArrayList<Integer>();
		advisementData = new ArrayList<Integer>();
		resourcesData = new ArrayList<Integer>();
		calendarData = new ArrayList<Integer>();
		database = new HTTPrequest();
		numPieces = 0;
	}
	
	public void render(){
		if(mode == GameState.titlescreen){
			mainScreen.render();
		}
		else if(mode == GameState.intro){
		//	database.login("http://localhost:10080/login",loginScreen.username);

			introscreen.render();
		}
		else if (mode == GameState.overworld){
			if (showEnd && sakaiArea.completed == true && advisementArea.completed == true && resourceArea.completed == true && calendarArea.completed == true){
				mode = GameState.end;
				showEnd = false;
				worldArea.done = true;
			}
			else {
				worldArea.render();
			}
		}
		else if (mode == GameState.online) {
			//mainScreen.disposer();
			//Stage stage = new Stage();
			//mainScreen.batch.dispose();
			sakaiArea.render();
			//S
		}
		else if (mode == GameState.advisement) {
			advisementArea.render();
		}
		else if (mode == GameState.resources) {
			resourceArea.render();
		}
		else if (mode == GameState.calendar_dates) {
			calendarArea.render();
		}
		else if(mode == GameState.login){
			loginScreen.render();
		}
		if(mode == GameState.end){
			endScreen.render();
			if(f){
				totalTime = (int) ((TimeUtils.millis() - startTime)/1000);
				database.post("http://cisc275udora.appspot.com/addData",loginScreen.username , 
						"online_overallpercent="+sakaiArea.mGame.percentCorrect+"&"
						+"online_amountofplays="+sakaiArea.mGame.numTries
						+"&"
						+"online_numofsuccess="+sakaiArea.mGame.numSuccess+"&"+
						///////////////////////////////////////////////////
						"advisement_overallpercent="+advisementArea.mGame.percentCorrect+"&"
						+"advisement_amountofplays="+advisementArea.mGame.numTries
						+"&"
						+"advsuccess="+advisementArea.mGame.numSuccess+"&"+
						////////////////////////////////////////////////////////
						"resources_overallpercent="+resourceArea.mGame.percentCorrect+"&"
						+"resources_amountofplays="+resourceArea.mGame.numTries
						+"&"
						+"resources_numofsuccess="+resourceArea.mGame.numSuccess +"&"+
						//////////////////////////////////////////////////////
						"calendar_overallpercent="+calendarArea.mGame.percentCorrect+"&"
						+"calendar_amountofplays="+calendarArea.mGame.numTries+"&"
						+"calendar_numofsuccess="+calendarArea.mGame.numSuccess);
				
				onlineData.add(sakaiArea.mGame.numTries);
				onlineData.add(sakaiArea.mGame.numSuccess);
				onlineData.add((int)sakaiArea.mGame.percentCorrect);
				advisementData.add(advisementArea.mGame.numTries);
				advisementData.add(advisementArea.mGame.numSuccess);
				advisementData.add((int)advisementArea.mGame.percentCorrect);
				resourcesData.add(resourceArea.mGame.numTries);
				resourcesData.add(resourceArea.mGame.numSuccess);
				resourcesData.add((int)resourceArea.mGame.percentCorrect);
				calendarData.add(calendarArea.mGame.numTries);
				calendarData.add(calendarArea.mGame.numSuccess);
				calendarData.add((int)calendarArea.mGame.percentCorrect);
				f = false;
				//System.out.println(totalTime);
				CurrentPlayer.getStudent().setTotalTime(totalTime);
				InteractWithFiles.addStudentToStudentStorage(CurrentPlayer.getStudent());
			}
		}
	}
	
	public void exit(){
		Gdx.app.exit();
	}
	
	static enum GameState {
		titlescreen, overworld, online, advisement, resources, scheduling, calendar_dates, end, login, intro
	}
}
