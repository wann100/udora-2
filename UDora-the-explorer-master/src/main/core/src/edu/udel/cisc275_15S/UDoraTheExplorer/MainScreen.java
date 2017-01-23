package edu.udel.cisc275_15S.UDoraTheExplorer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;

import edu.udel.cisc275_15S.UDoraTheExplorer.UDoraTheExplorer.GameState;
public class MainScreen extends ApplicationAdapter {
	public UDoraTheExplorer game;
	SpriteBatch batch;
	Texture img;
	Texture background;
	Stage stage;
	TextButton button;
	TextButton button_s;
	TextButton CalendarButton;
	TextButton MapButton;
	TextButtonStyle ButtonStyle;
	Skin skin;
	TextureAtlas buttonAtlas;
	BitmapFont font;
	Label statusLabel;
	HTTPrequest test = new HTTPrequest();
	
	
	public MainScreen(final UDoraTheExplorer game) {
		this.game = game;
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("background.jpg"));
		background = new Texture(Gdx.files.internal("backgrounds/First.jpg"));
		font = new BitmapFont(Gdx.files.internal("arial.fnt"));
//		font.setScale();
	/**
	 * Stage starts here  for buttons	
	 * Stage has two buttons or two actors
	 * button and button_s
	 */
		
	       
	       skin = new Skin();
	       
	       //Uiskinatlas maps the uiskins that i have downloaded
	       //if you download a new uiskin you need a new map file
	       
	       buttonAtlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
	       skin.addRegions(buttonAtlas);
	       
	       //button style setup
	       //to change skin please look at uiskin.atlas
	        ButtonStyle = new TextButtonStyle();
	        ButtonStyle.font = font;
	        ButtonStyle.up = skin.getDrawable("default-rect");
	        ButtonStyle.down = skin.getDrawable("default-rect-down");
	        ButtonStyle.checked = skin.getDrawable("default-rect");
	        
	        stage = new Stage();
		     Gdx.input.setInputProcessor(stage);
	        
	        //button 
//	        button = new TextButton("Advisement", ButtonStyle);
//	        button.setBounds(240, 200, 150, 60);
//	        button.addListener(new ClickListener() {
//				@Override
//				public void touchUp(InputEvent e, float x, float y, int point, int btn) {
//					super.touchUp(e, x, y, point, btn);
//					if(game.mode == GameState.titlescreen){
//		
//						game.mode = GameState.advisement;
//					}
//				}
//			});
//	        stage.addActor(button);	
//	        
//	        button_s = new TextButton("Online", ButtonStyle);
//	        button_s.setBounds(240, 120, 150, 60);
//	        button_s.addListener(new ClickListener() {
//				@Override
//				public void touchUp(InputEvent e, float x, float y, int point, int btn) {
//					super.touchUp(e, x, y, point, btn);
//					if(game.mode == GameState.titlescreen) {
//						game.mode = GameState.sakai;
//					}
//				}
//			});
//	        stage.addActor(button_s);	
//	        
//	        CalendarButton = new TextButton("Calendar", ButtonStyle);
//	        CalendarButton.setBounds(240, 40, 150, 60);
//	        CalendarButton.addListener(new ClickListener() {
//				@Override
//				public void touchUp(InputEvent e, float x, float y, int point, int btn) {
//					super.touchUp(e, x, y, point, btn);
//					if(game.mode == GameState.titlescreen){
//						game.mode = GameState.calendar_dates;
//					}
//				}
//			});
//	        stage.addActor(CalendarButton);	
	        
	        MapButton = new TextButton("Begin!", ButtonStyle);
	        MapButton.setBounds(250, 175, 150, 60);
	        MapButton.addListener(new ClickListener() {
				@Override
				public void touchUp(InputEvent e, float x, float y, int point, int btn) {
					super.touchUp(e, x, y, point, btn);
					if(game.mode == GameState.titlescreen){
						
						game.mode= GameState.login;
						//IntroScreen.render();
						//game.mode = GameState.overworld;
						
					}
				}
			});
	        
	        stage.addActor(MapButton);	
	        /**
	         * this is wher i test the login function
	         * check render to see how you use boolean logedin
	         * uncomment to use and put it on login screen
	         * replace beginning of url with below url for live 
	         * cisc275udora.appspot.com
	         */
					//  test.login("http://localhost:10080/login","username=mamadouwann@gmail.com&password=password");
	        //here I am changing or adding an email to database
	        /**
	         * this is where i post the new data into database
	         *  replace beginning of url with below url for live 
	         * cisc275udora.appspot.com
	         */
	        //test.post("http://localhost:10080/add","mamadouwann@gmail.com","password","email=worddng@working.com");
			
	}

	public float resizeText(float width, float currentSize, float currentWidth){
	    //currentWidth/currentSize = width/x
	   float a = width * currentSize;//450 * 3 in example above
	    float b = a/currentWidth;
	    return b;//returns the x or the new size that your text should be
	}
	
	public void dispose(){
		batch.dispose();
		stage.clear();
	}
	
	@Override
	public void render () {
		/**
		 * Trying to make it not resize and distort the graphics when
		 * window is stretched but still acting funky
		 */
		Gdx.input.setInputProcessor(stage);
		
//		if(game.advisementArea.completed){
//			button.setText("Advisement: Done");
//		}
//		
//		if(game.sakaiArea.completed){
//			button_s.setText("Online: Done");
//		}
//		
//		if(game.calendarArea.completed){
//			CalendarButton.setText("Calendar: Done");
//		}
		
		Gdx.gl.glClearColor(0, 38/255f,99/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(background, 0, 0,Gdx.graphics.getWidth(),350);
		batch.draw(img, 100, 410);

		font.setScale(0.8f);
		
		font.draw(batch,"Welcome New Students",100,400);
		font.setScale(0.5f);
		batch.end();
	/**
	 * here i check if loged in then draw the stage button
	 * uncomment to test out and switch the url to
	 * cisc275udora.appspot.com/login 
	 * i have it set as local because local serveris easier
	 */
       // if(test.logedin == true){
       // 	stage.draw();
      //  }
        stage.draw();
		 
	}
	public void disposer(){
		stage.dispose();
	}
}


