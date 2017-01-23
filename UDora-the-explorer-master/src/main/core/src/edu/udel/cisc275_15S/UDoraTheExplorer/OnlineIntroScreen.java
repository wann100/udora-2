package edu.udel.cisc275_15S.UDoraTheExplorer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.udel.cisc275_15S.UDoraTheExplorer.OnlineArea.SakaiScreen;

public class OnlineIntroScreen {
	public UDoraTheExplorer game;
	public OnlineArea area;
	public BitmapFont font;
	public SpriteBatch batch;
	public Stage stage;
	
	public OnlineIntroScreen(UDoraTheExplorer game, OnlineArea area){
		this.area = area;
		this.game = game;
		font = new BitmapFont(Gdx.files.internal("arial.fnt"));
		batch = new SpriteBatch();
	}
	
	public void dispose(){
		batch.dispose();
	}
	
	public void render(){
		Gdx.gl.glClearColor(0, 38/255f,99/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, InteractWithFiles.readOnlineIntroScreen(0), 100, 400);
		font.draw(batch, InteractWithFiles.readOnlineIntroScreen(1), 100, 350);
		batch.end();
		if (Gdx.input.justTouched()){
			area.screenState = SakaiScreen.teaching;
//			dispose();
		}
	}
}
