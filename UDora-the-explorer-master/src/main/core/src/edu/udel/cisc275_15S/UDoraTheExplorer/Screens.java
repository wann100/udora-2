package edu.udel.cisc275_15S.UDoraTheExplorer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

/**
 * 
 * @author wann100
 * Screens is going to be an abstract class for all screens
 * its going to setup renders for different objects
 * if function starts with render please call those in render method
 * 
 *
 */
public class Screens{
	
	UDoraTheExplorer game;
	public Screens(UDoraTheExplorer game){
		this.game = game;
	}
	/**
	 * Render Dialog box right
	 * -Renders dialog box facing right for scenes
	 * @param stage
	 * @param input
	 */
	public void create_dialog_box(Stage stage,String input) {
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("italic.ttf"));
			FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	       //Gdx.input.setInputProcessor(stage);
	       BitmapFont font = generator.generateFont(18);
	       Skin skin = new Skin();
	       
	       //Uiskinatlas maps the uiskins that i have downloaded
	       //if you download a new uiskin you need a new map file
	       
	       TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
	       skin.addRegions(buttonAtlas);
	       
	       //button style setup
	       //to change skin please look at uiskin.atlas
	        TextButtonStyle ButtonStyle = new TextButtonStyle();
	        ButtonStyle.font = font;
	        ButtonStyle.up = skin.getDrawable("textfield");
	        ButtonStyle.down = skin.getDrawable("textfield");
	        ButtonStyle.checked = skin.getDrawable("textfield");
	        
	        TextButton button = new TextButton(input, ButtonStyle);
	        button.setBounds(0, 0,  Gdx.graphics.getWidth(),100);
	        stage.addActor(button);	
	}
	/**
	 * RENDERS LITTLE PERSON DUDE
	 * @param batch
	 */
	public void render_person_icon(SpriteBatch batch){
		Sprite image = new Sprite(new Texture("person.png"));
		batch.draw(image, 0, 110);
		
	}
	public void render_back_button(SpriteBatch batch,Texture img){
		
	}
	

}
