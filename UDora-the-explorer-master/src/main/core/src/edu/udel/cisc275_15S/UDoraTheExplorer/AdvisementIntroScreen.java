package edu.udel.cisc275_15S.UDoraTheExplorer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import edu.udel.cisc275_15S.UDoraTheExplorer.AdvisementArea.AdvisementScreen;

public class AdvisementIntroScreen {
	public UDoraTheExplorer game;
	public AdvisementArea area;
	public BitmapFont font;
	public SpriteBatch batch;
	
	public AdvisementIntroScreen(UDoraTheExplorer game, AdvisementArea area){
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
		font.draw(batch, InteractWithFiles.readAdvisementIntroScreen(0), 100, 400);
		batch.end();
		if (Gdx.input.justTouched()){
			area.screenState = AdvisementScreen.teaching;
//			dispose();
		}
	}
}
