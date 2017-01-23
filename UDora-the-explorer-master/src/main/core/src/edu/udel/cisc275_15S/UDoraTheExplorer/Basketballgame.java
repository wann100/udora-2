package edu.udel.cisc275_15S.UDoraTheExplorer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sun.org.apache.xerces.internal.util.Status;

import edu.udel.cisc275_15S.UDoraTheExplorer.UDoraTheExplorer.GameState;


public class Basketballgame extends ApplicationAdapter {
//		public UDoraTheExplorer game;
		public String status  = "";
		public float _time=0f;
		SpriteBatch batch;
		Texture img;
		Texture background;
		Stage stage;
		TextButtonStyle ButtonStyle;
		Skin skin;
		TextureAtlas buttonAtlas;
		BitmapFont font;
		Label statusLabel;
		public float positionx;
		public int positiony;
	    Animation   ballAnimation;       
	    Texture  spritesheet;
	    Texture  udbird;
	    Texture  basketballnet;
	    private static final int        FRAME_COLS = 34 ;         
	    private static final int        FRAME_ROWS = 16;             
	    TextureRegion[]ballframes;                    
	    TextureRegion currentFrame;          
		private State state = State.RUN;
	    float stateTime;
		private UDoraTheExplorer game;
		private TextButton MapButton;   
		public Music music = Gdx.audio.newMusic(Gdx.files.internal("swish.mp3"));
		public Music music2 = Gdx.audio.newMusic(Gdx.files.internal("miss.mp3"));
		
		public Basketballgame(final UDoraTheExplorer game) {
			
			this.game = game;
			batch = new SpriteBatch();
			img = new Texture(Gdx.files.internal("background.jpg"));
			background = new Texture(Gdx.files.internal("backgrounds/minigame.jpg"));
			font = new BitmapFont(Gdx.files.internal("arial.fnt"));
			positionx = Gdx.graphics.getWidth()-50;
			positiony = 10;
			
		       skin = new Skin();

		     //  Gdx.input.setInputProcessor(stage);
		       buttonAtlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		       skin.addRegions(buttonAtlas);

		        ButtonStyle = new TextButtonStyle();
		        ButtonStyle.font = font;
		        ButtonStyle.up = skin.getDrawable("default-rect");
		        ButtonStyle.down = skin.getDrawable("default-rect-down");
		        ButtonStyle.checked = skin.getDrawable("default-rect");
		    
		        stage = new Stage();
		        
		        Gdx.input.setInputProcessor(stage);
		        
		        MapButton = new TextButton("Map", ButtonStyle);
		        MapButton.setBounds(250, 150, 150, 60);
		        MapButton.addListener(new ClickListener() {
					@Override
					public void touchUp(InputEvent e, float x, float y, int point, int btn) {
						super.touchUp(e, x, y, point, btn);
						status ="true";
						run();
					}
				});
		        
		     //   stage.addActor(MapButton);
		        this.createanimation();
		}
		        

		public void createanimation(){
			Gdx.input.setInputProcessor(stage);
			spritesheet = new Texture(Gdx.files.internal("basketballsprite.png"));
			basketballnet = new Texture(Gdx.files.internal("net1.png"));
	        TextureRegion[][] tmp = TextureRegion.split(spritesheet, spritesheet.getWidth()/FRAME_COLS, spritesheet.getHeight()/FRAME_ROWS);              // #10
	        ballframes = new TextureRegion[FRAME_COLS * FRAME_ROWS];
	        int index = 0;
	        for (int i = 0; i < FRAME_ROWS; i++) {
	            for (int j = 0; j < FRAME_COLS; j++) {
	                ballframes[index++] = tmp[i][j];
	            }
	        }
	        ballAnimation = new Animation(0.025f, ballframes); 

	        stateTime = 0f;      
		}

		public float resizeText(float width, float currentSize, float currentWidth){
		    //currentWidth/currentSize = width/x
		   float a = width * currentSize;//450 * 3 in example above
		    float b = a/currentWidth;
		    return b;//returns the x or the new size that your text should be
		}

		
		@Override
		public void render()
		
		{	//status ="false";
			//Gdx.input.setInputProcessor(stage);
		    switch (state)
		    {
		    case RUN:
		    	batch.begin();
		    	drawbasketball(batch);
        		//positionx = Gdx.graphics.getWidth()/2;
    		//	positiony = 0;
		    	batch.end();
		    //	stage.draw();
		        break;
		    case PAUSE:
		    	Gdx.gl.glClearColor(0, 38/255f,99/255f, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				
				positionx = Gdx.graphics.getWidth()-50;
				positiony = 10;
				stateTime += Gdx.graphics.getDeltaTime();           // #15
		        currentFrame = ballAnimation.getKeyFrame(stateTime, true); 
				font.setScale(0.8f);
				batch.begin();
				batch.draw(background, 0, 0,Gdx.graphics.getWidth(),350); 
				batch.draw(basketballnet, (Gdx.graphics.getWidth()/2)-80,50);
		        batch.draw(currentFrame, positionx, positiony);  
				batch.end();
				font.setScale(0.5f);
				stage.draw();
				
    			//status ="reset";
		//do stuff here

		        break;
		    case RESUME:
		    	batch.begin();
		    	drawbasketball(batch);
        		//positionx = Gdx.graphics.getWidth()/2;
    		//	positiony = 0;
		    	batch.end();


		        break;

		    default:
		        break;
		    }
		}

		public void winorlose(String status){
		this.status = status;
			
		}
		public void drawbasketball(Batch batch){
			
			Gdx.gl.glClearColor(0, 38/255f,99/255f, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.draw(background, 0, 0,Gdx.graphics.getWidth(),350);
			stateTime += Gdx.graphics.getDeltaTime();           // #15
	        currentFrame = ballAnimation.getKeyFrame(stateTime, true); 
	        if(status == "true"){
		        if (positiony <=82 ){      
		        positiony = positiony+3;
		        positionx = (float) (positionx-14);// #16
		        //System.out.println(_time);
		        }
		        font.setColor(Color.YELLOW);
		        font.draw(batch, "Swish",(Gdx.graphics.getWidth()/2)-80, 20);
		     //  System.out.println(positiony);
		        if(positiony ==82){
		        	music.play();
		        	float time = 0 ;
		        	time+=  Gdx.graphics.getDeltaTime();
		        	///System.out.println(time);
		        	if(time >0.012){
		        	pause();
		        	}
		        }
		       

		        
	        }
	        if(status == "false"){
	        	if (positiony <=120){
	        	positiony = positiony+3;
	        	 positionx = (float) (positionx-6);
	        	}
	        	float time = 0 ;
	        	font.setColor(Color.RED);
	        	font.draw(batch, "Miss",(Gdx.graphics.getWidth()/2)-80, 20);
	        	 if(positiony ==121){
			        	time+=  Gdx.graphics.getDeltaTime();
			        	music2.play();
			        	//System.out.println(time);
			        	//time+=  Gdx.graphics.getDeltaTime();
			        	//System.out.println(time);
			        	if(time >0.014){
				        	pause();
				        	}
			        }
	        }
	       
			font.setScale(0.8f);
			
			
	        batch.draw(basketballnet, (Gdx.graphics.getWidth()/2)-80,50);// #17
	        batch.draw(currentFrame, positionx, positiony);  
			
			font.setScale(0.5f);


			
		}
		@Override
		public void pause()
		{
		    this.state = State.PAUSE;
		}

		@Override
		public void resume()
		{
		    this.state = State.RESUME;
		}
		public void run(){
			this.state = State.RUN;
		}
		public enum State
		{
		    PAUSE,
		    RUN,
		    RESUME,
		    STOPPED
		}
	}



