package edu.udel.cisc275_15S.UDoraTheExplorer;
import java.io.PrintStream;
import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.udel.cisc275_15S.UDoraTheExplorer.UDoraTheExplorer.GameState;
/*
 * @author: Pat
 * Special thanks to andrew and mamadou for pushing this file several different times while I worked out issues with my computer!
 */

public class LogInScreen {
	//This is the class that will be used to store information about the student in a a student object when they log into the game
	
	public UDoraTheExplorer game;
	public Stage stage;
	public Skin skin;
	public BitmapFont font;
	public Texture background;
	public TextureAtlas buttonAtlas;
	public ShapeRenderer shape;
  	public SpriteBatch batch;
  	public String username;
  	public boolean first = true;
  	/*
  	 * The button and text-field that will be used to gather data and advance to the next page
  	 */
	public TextButton button;
	public TextButtonStyle ButtonStyle;
	public TextField userInput;
	public TextFieldListener textListener;
	
	Date currDate;
	/*
	 * Objects that will be used for the storage of student data, as well as writing it to a file / server
	 */
	public FileHandle userDataFile;
	public PrintStream output;
	public Student newStudent;
	
	/*
	 * constructor for the LogInScreen object
	 */
	public LogInScreen(final UDoraTheExplorer game){
		currDate = new Date();
		this.game = game;
		stage = new Stage();
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		background = new Texture("background.jpg");
		font = new BitmapFont(Gdx.files.internal("arial.fnt"));
		font.setColor(Color.WHITE);
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin(Gdx.files.internal("uiskin.json"));

        buttonAtlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
        TextFieldStyle style = new TextFieldStyle();
        style.fontColor = Color.WHITE;
        style.font = new BitmapFont();
        userInput = new TextField("Enter your UD Username here", skin);
	 	
        userInput.setTextFieldListener(new TextFieldListener() {

        	
            @Override
            public void keyTyped(TextField textField, char key) {
            		if(first){
            			char[] arr = {key};
            			userInput.setText(new String(arr));
            			userInput.setCursorPosition(1);
            			first = false;
            		}
                   username = textField.getText();
            }
        });
        
        
		userInput.setPosition(190, 185);
		userInput.setSize(250, 50);
//		userInput.setCursorPosition(userInput.getCursorPosition());
		
		stage.addActor(userInput);
		
		buttonAtlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
	    skin.addRegions(buttonAtlas);
		
	    //button style setup
	    //to change skin please look at uiskin.atlas
        ButtonStyle = new TextButtonStyle();
        ButtonStyle.font = font;
        ButtonStyle.up = skin.getDrawable("default-rect");
        ButtonStyle.down = skin.getDrawable("default-rect-down");
        ButtonStyle.checked = skin.getDrawable("default-rect");
        
        TextButton	button = new TextButton("Submit", ButtonStyle);
	    button.setBounds(240, 50, 150, 45);
	    button.addListener(new ClickListener() {
			public HTTPrequest database = new HTTPrequest();

			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int btn) {
				super.touchUp(e, x, y, point, btn);
				if(game.mode == GameState.login){
					username=userInput.getText();
					//System.out.println(username);
					//System.out.println(currDate);
					/**
					database.post("http://localhost:10080/addData",game.loginScreen.username , 
							"online_overallpercent="+game.sakaiArea.mGame.percentCorrect+"&"
							+"online_amountofplays="+game.sakaiArea.mGame.numTries
							+"&"
							+"online_numofsuccess="+game.sakaiArea.mGame.numSuccess+"&"+
							///////////////////////////////////////////////////
							"advisement_overallpercent="+game.advisementArea.mGame.percentCorrect+"&"
							+"advisement_amountofplays="+game.advisementArea.mGame.numTries
							+"&"
							+"advsuccess="+game.advisementArea.mGame.numSuccess+"&"+
							////////////////////////////////////////////////////////
							"resources_overallpercent="+game.resourceArea.mGame.percentCorrect+"&"
							+"resources_amountofplays="+game.resourceArea.mGame.numTries
							+"&"
							+"resources_numofsuccess="+game.resourceArea.mGame.numSuccess +"&"+
							//////////////////////////////////////////////////////
							"calendar_overallpercent="+game.calendarArea.mGame.percentCorrect+"&"
							+"calendar_amountofplays="+game.calendarArea.mGame.numTries+"&"
							+"calendar_numofsuccess="+game.calendarArea.mGame.numSuccess);
							**/
					Student s = InteractWithFiles.removeStudentFromStudentStorage(userInput.getText());
					CurrentPlayer.setUsername(userInput.getText());
					CurrentPlayer.setStudent(s);
					CurrentPlayer.getStudent().setLastDateTried(currDate.toString());
					game.mode = GameState.intro;
				}
			}
		});
	    
	   stage.addActor(button);	
	}
	
	public boolean setStudentData(int id){
		return false;
	}
	
	/*
	 * My stream of conscious for how I want this screen to work.
	 * 	User inputs text in the text box. 
	 * 	textbox listener listens and shows the characters in the box UNTIL the button is pressed
	 * 	If no text in box, don't move on
	 * 	Otherwise, go to main screen
	 */
	
	
	public void render(){
		Gdx.input.setInputProcessor(stage);
		
		Gdx.gl.glClearColor(0, 38/255f,99/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		font.setScale(0.8f);
		font.draw(batch,"Hello there!",215,400);
		font.setScale(0.5f);
		
		batch.end();
		stage.draw();
		
	}
	
}
