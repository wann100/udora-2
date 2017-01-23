package edu.udel.cisc275_15S.UDoraTheExplorer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import edu.udel.cisc275_15S.UDoraTheExplorer.AdvisementArea.AdvisementScreen;
import edu.udel.cisc275_15S.UDoraTheExplorer.UDoraTheExplorer.GameState;

public class IntroScreen {
	public UDoraTheExplorer game;
	public AdvisementArea area;
	public BitmapFont font;
	public BitmapFont small;
	public BitmapFont text;
	public SpriteBatch batch;
	public Stage stage;
	public int textLine;
	public TextButton button;
	public ShapeRenderer shape;
	Sprite image = new Sprite(new Texture("person.png"));
	Sprite textbar = new Sprite(new Texture("bar.png"));
	Sprite background = new Sprite(new Texture("introBG.png"));
	Sprite headshot = new Sprite(new Texture("udee.png"));
	Music music = Gdx.audio.newMusic(Gdx.files.internal("introSong.mp3"));
	
	
	public IntroScreen(UDoraTheExplorer game){
		this.game = game;
		shape = new ShapeRenderer();
		shape.setColor(Color.YELLOW);
		font = new BitmapFont(Gdx.files.internal("arial.fnt"));
		batch = new SpriteBatch();
		textLine = 0;
		stage = new Stage();
		small = new BitmapFont(Gdx.files.internal("arial.fnt"));
		small.setScale(0.4f);
		
		text = new BitmapFont(Gdx.files.internal("arial.fnt"));
		text.setScale(0.3f);
		//small.setColor(Color.BLUE);
	       Skin skin = new Skin();
	       
	       //Uiskinatlas maps the uiskins that i have downloaded
	       //if you download a new uiskin you need a new map file
	       
	       TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
	       skin.addRegions(buttonAtlas);
	       skin.add("textbar", new Texture("clear.png"));
	       
	       //button style setup
	       //to change skin please look at uiskin.atlas
	        TextButtonStyle ButtonStyle = new TextButtonStyle();
	        ButtonStyle.font = text;
	       

	        
	        ButtonStyle.up = skin.getDrawable("textbar");
	        ButtonStyle.down = skin.getDrawable("textbar");
	        ButtonStyle.checked = skin.getDrawable("textbar");
	        ButtonStyle.fontColor = Color.BLUE;
	        
	        button = new TextButton("", ButtonStyle);
	        button.setBounds(-30, -125,  Gdx.graphics.getWidth(),400);
	        stage.addActor(button);	
	        

	        
	}
	public void create(){
		
	}
	
	public void dispose(){
		batch.dispose();
	}
	
	public void render(){
		//long id = music.play();
		//music.setPitch(id,0.5f);
		//music.setLooping(true);
		Gdx.gl.glClearColor(0, 38/255f,99/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		create_dialog_box(stage,InteractWithFiles.insertNewLineSpaces(InteractWithFiles.readIntroScreen(textLine),10));
		stage.draw();
		batch.begin();
		
		batch.draw(background, -10, 0);
		batch.end();
		shape.begin(ShapeType.Filled);
		shape.rect(0, 0, ((textLine+1)*Gdx.graphics.getWidth()/InteractWithFiles.getSizeOfIntro()), 50);
		shape.end();
		batch.begin();
		batch.draw(textbar, -40, -170);
		batch.draw(headshot, 50, 110);	
		batch.end();
		
		create_dialog_box(stage,InteractWithFiles.insertNewLineSpaces(InteractWithFiles.readIntroScreen(textLine),40));
		stage.draw();
		if (Gdx.input.justTouched()){
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			if(x > 340 && x < 470 && y > 436 && y < 472){
				if (textLine<InteractWithFiles.getSizeOfIntro()-1){
					textLine++;
				}
				else{
					textLine = 0;
					//area.screenState = AdvisementScreen.minigame;
					game.mode = GameState.overworld;
					music.dispose();
				}
			}	
//			dispose();
		}
	}
	/**
	 * Render Dialog box right
	 * -Renders dialog box facing right for scenes
	 * @param stage
	 * @param input
	 */
	public void create_dialog_box(Stage stage,String input) {
	       //Gdx.input.setInputProcessor(stage);
		button.setText(input);
	}
	/**
	 * RENDERS LITTLE PERSON DUDE
	 * @param batch
	 */
	public void render_person_icon(SpriteBatch batch){
		
		
		
	}
	
}


