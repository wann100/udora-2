package edu.udel.cisc275_15S.UDoraTheExplorer;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.udel.cisc275_15S.UDoraTheExplorer.AdvisementArea.AdvisementScreen;
import edu.udel.cisc275_15S.UDoraTheExplorer.OnlineArea.SakaiScreen;
import edu.udel.cisc275_15S.UDoraTheExplorer.UDoraTheExplorer.GameState;

public class OnlineTeachingScreen {
	public UDoraTheExplorer game;
	public OnlineArea area;
	public BitmapFont font;
	public BitmapFont small;
	public BitmapFont text;
	public SpriteBatch batch;
	public ArrayList<Texture> screens;
	Sprite textbar = new Sprite(new Texture("bar.png"));
	Sprite headshot = new Sprite(new Texture("moustacheyouDee.png"));
	public Stage stage;
	public TextButton button;
	public int textLine;
	Sprite image = new Sprite(new Texture("person.png"));
	public ShapeRenderer shape;
	public TextButton back;
	public Stage stage2;
	public TextButtonStyle ButtonStyle2;
	public Texture maptexture;
	public TextureRegion mapregion;
	public Image map;
	
	public OnlineTeachingScreen(final UDoraTheExplorer game, OnlineArea area){
		this.area = area;
		this.game = game;
		stage2 = new Stage();
		shape = new ShapeRenderer();
		shape.setColor(Color.YELLOW);
		font = new BitmapFont(Gdx.files.internal("arial.fnt"));
		batch = new SpriteBatch();
		stage = new Stage();
		small = new BitmapFont(Gdx.files.internal("arial.fnt"));
		small.setColor(1, 0, 0, 1);
		
		//Creating the background screens
		screens = new ArrayList<Texture>();
		for (int i = 0; i < InteractWithFiles.getSizeOfOnlineTeachingImages(); i++){
			screens.add(new Texture(InteractWithFiles.readOnlineTeachingImages(i)));
		}
		if (InteractWithFiles.getSizeOfOnlineTeachingImages()<InteractWithFiles.getSizeOfOnlineTeaching()){
			for (int i = InteractWithFiles.getSizeOfOnlineTeachingImages();i<InteractWithFiles.getSizeOfOnlineTeaching();i++){
				screens.add(new Texture(InteractWithFiles.readOnlineTeachingImages(InteractWithFiles.getSizeOfOnlineTeachingImages()-1)));
			}
		}
		
		small.setScale(0.4f);
		maptexture = new Texture(Gdx.files.internal("map.jpg"));
		mapregion = new TextureRegion(maptexture);
		map = new Image(mapregion);
		map.setScale(0.2f);
		map.setPosition(490, 390);
		map.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int btn) {
				super.touchUp(e, x, y, point, btn);
				game.mode = GameState.overworld;
			}
		});
		stage2.addActor(map);
		text = new BitmapFont(Gdx.files.internal("arial.fnt"));
		text.setScale(0.3f);
		textLine = 0;
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
	    ButtonStyle2 = new TextButtonStyle();
		font.setScale(0.5f);
        ButtonStyle2.font = font;
        ButtonStyle2.up = skin.getDrawable("default-rect");
        ButtonStyle2.down = skin.getDrawable("default-rect-down");
        ButtonStyle2.checked = skin.getDrawable("default-rect");
        back = new TextButton("Back", ButtonStyle2);
        back.setBounds(20, 420, 100, 50);
        back.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int btn) {
				super.touchUp(e, x, y, point, btn);
				if(textLine>0){
					textLine--;
				}
			}
		});
        stage2.addActor(back);
	}
	
	public void dispose(){
		batch.dispose();
	}
	
	public void render(){
		Gdx.gl.glClearColor(0, 38/255f,99/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.input.setInputProcessor(stage2);
		batch.begin();
		batch.draw(screens.get(textLine), 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch.end();
		shape.begin(ShapeType.Filled);
		shape.rect(0, 0, ((textLine+1)*Gdx.graphics.getWidth()/InteractWithFiles.getSizeOfOnlineTeaching()), 50);
		shape.end();
		batch.begin();
		batch.draw(textbar, -40, -170);
		batch.draw(headshot, 52, 108);
	//	batch.begin();
		//font.draw(batch, InteractWithFiles.readSakaiTeachingScreen(1), 100, 400);
		//font.draw(batch, InteractWithFiles.readSakaiTeachingScreen(2), 100, 300);
		batch.end();
		create_dialog_box(stage,InteractWithFiles.insertNewLineSpaces(InteractWithFiles.readOnlineTeachingScreen(textLine),40));
		stage.draw();
		if(textLine < 1){
			back.setVisible(false);
		}
		else {
			back.setVisible(true);
		}
		
		stage2.draw();
		if (Gdx.input.justTouched()){
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			if(x > 340 && x < 470 && y > 436 && y < 472){
				if (textLine<InteractWithFiles.getSizeOfOnlineTeaching()-1){
					textLine++;
				}
				else{
					textLine = 0;
					area.screenState = SakaiScreen.minigame;
				}
			}
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
