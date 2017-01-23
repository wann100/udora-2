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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Json;

public class Testing extends ApplicationAdapter {
	UDoraTheExplorer game = null;
	Screens screener = new Screens(game);
	SpriteBatch batch;
	Texture img;
	Stage stage;
	TextButton button;
	TextButton button_s;
	TextButtonStyle ButtonStyle;
	Skin skin;
	TextureAtlas buttonAtlas;
	//parameter.size= 12;
	private BitmapFont font;
	//Testing to make sure reading from json works
	MainScreen screen;
	
	@Override
	public void create () {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("italic.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		batch = new SpriteBatch();
		img = new Texture("background.jpg");
		font = generator.generateFont(22);
		Color White = new Color();
		font.setColor(White.WHITE);
		screen = new MainScreen(game);
	/**
	 * TESTING MY SCREENS CLASS BELOW
	 */
		
	       stage = new Stage();
	       Gdx.input.setInputProcessor(stage);
	       screener.create_dialog_box(stage, "MY TEXT BOX CODE WORKS");
	}
	public float resizeText(float width, float currentSize, float currentWidth){
	   return screen.resizeText(width, currentSize, currentWidth);
	   
	}
	@Override
	public void render () {
//		/**
//		 * Trying to make it not resize and distort the graphics when
//		 * window is stretched but still acting funky
//		 */
//		Matrix4 matrix = new Matrix4();
//		matrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		//uncomment line below to see what i mean
//		//batch.setProjectionMatrix(matrix);
//		times++;
//		Gdx.gl.glClearColor(0, 38/255f,99/255f, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		stage.draw();;
//		batch.begin();
//		
//		batch.draw(img, 100, 400);
//		
//		font.setScale(2,2);
//		font.draw(batch,"Welcome New Students",150,350);
//		
//		batch.end();
//		//Testing to make sure reading from json works
//		if (times==2){
//			Dialog d = InteractWithFiles.readWelcomeDialog();
//			System.out.println(d.toString());
//		}
		screen.render();
	}
	
}

