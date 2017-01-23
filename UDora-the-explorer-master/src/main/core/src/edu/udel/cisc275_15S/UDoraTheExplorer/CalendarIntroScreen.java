package edu.udel.cisc275_15S.UDoraTheExplorer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.udel.cisc275_15S.UDoraTheExplorer.CalendarArea.CalendarScreen;

public class CalendarIntroScreen {
	public UDoraTheExplorer game;
	public CalendarArea area;
	public BitmapFont font;
	public SpriteBatch batch;
	
	public CalendarIntroScreen(UDoraTheExplorer game, CalendarArea area){
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
		font.draw(batch, InteractWithFiles.readCalendarIntroScreen(0), 100, 400);
		batch.end();
		if (Gdx.input.justTouched()){
			area.screenState = CalendarScreen.teaching;
//			dispose();
		}
	}
}
