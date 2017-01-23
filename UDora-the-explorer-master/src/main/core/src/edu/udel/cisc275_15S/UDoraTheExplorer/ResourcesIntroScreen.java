package edu.udel.cisc275_15S.UDoraTheExplorer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.UDoraTheExplorer.CalendarArea.CalendarScreen;
import edu.udel.cisc275_15S.UDoraTheExplorer.ResourcesArea.ResourcesScreen;

public class ResourcesIntroScreen {
	public UDoraTheExplorer game;
	public ResourcesArea area;
	public BitmapFont font;
	public SpriteBatch batch;
	
	public ResourcesIntroScreen(UDoraTheExplorer game, ResourcesArea area){
		this.area = area;
		this.game = game;
		font = new BitmapFont(Gdx.files.internal("arial.fnt"));
		batch = new SpriteBatch();
	}
	
	public void render(){
		Gdx.gl.glClearColor(0, 38/255f,99/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, InteractWithFiles.readResourcesIntroScreen(0), 100, 400);
		batch.end();
		if (Gdx.input.justTouched()){
			area.screenState = ResourcesScreen.teaching;
//			dispose();
		}
	}
}
