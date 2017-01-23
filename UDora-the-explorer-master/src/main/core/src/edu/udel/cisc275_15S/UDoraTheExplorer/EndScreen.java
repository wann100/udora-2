package edu.udel.cisc275_15S.UDoraTheExplorer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.udel.cisc275_15S.UDoraTheExplorer.CalendarArea.CalendarScreen;
import edu.udel.cisc275_15S.UDoraTheExplorer.UDoraTheExplorer.GameState;

public class EndScreen {
	public UDoraTheExplorer game;
	public BitmapFont font;
	public SpriteBatch batch;
	public TextButtonStyle ButtonStyle;
	public Stage stage;
	public Button button;
	public Button end;
	public Skin skin;
	public TextureAtlas buttonAtlas;
	
	public EndScreen(final UDoraTheExplorer game){
		this.game = game;
		font = new BitmapFont(Gdx.files.internal("arial.fnt"));
		batch = new SpriteBatch();
		font.scale(-0.5f);
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
	        button = new TextButton("Map", ButtonStyle);
	        button.setBounds(400, 50, 150, 60);
	        button.addListener(new ClickListener() {
				@Override
				public void touchUp(InputEvent e, float x, float y, int point, int btn) {
					super.touchUp(e, x, y, point, btn);
					game.mode = GameState.overworld;
//					dispose();
				}
			});
	        stage.addActor(button);	
	        
	        end = new TextButton("Exit", ButtonStyle);
	        end.setBounds(150, 50, 150, 60);
	        end.addListener(new ClickListener() {
				@Override
				public void touchUp(InputEvent e, float x, float y, int point, int btn) {
					game.exit();
//					dispose();
				}
			});
	        stage.addActor(end);	
	}
	
	public void render(){
		Gdx.gl.glClearColor(0, 38/255f,99/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.input.setInputProcessor(stage);
		stage.draw();
		batch.begin();
		font.draw(batch, "Congratulations!", 200, 400);
		font.draw(batch, "You have completed the game!", 150, 300);
		batch.end();
	}
}
