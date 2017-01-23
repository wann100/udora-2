package edu.udel.cisc275_15S.UDoraTheExplorer;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.udel.cisc275_15S.UDoraTheExplorer.UDoraTheExplorer.GameState;

public class RewardScreen {
	public UDoraTheExplorer game;
	public Area area;
	private BitmapFont text;
	public SpriteBatch batch;
	public Texture personTexture0;
	public TextureRegion personRegion0;
	public Texture personTexture1;
	public TextureRegion personRegion1;
	public Texture personTexture2;
	public TextureRegion personRegion2;
	public Texture personTexture3;
	public TextureRegion personRegion3;
	public Texture personTexture4;
	public TextureRegion personRegion4;
	public Image person0;
	public Image person1;
	public Image person2;
	public Image person3;
	public Image person4;
	public ArrayList<Image> people;
	public Stage stage;
	public TextButtonStyle ButtonStyle;
	public Skin skin;
	public TextureAtlas buttonAtlas;
	public Button button;
	
	public RewardScreen(final UDoraTheExplorer game, final Area area){
		this.game = game;
		this.area = area;
		text = new BitmapFont(Gdx.files.internal("arial.fnt"));
		text.scale(-0.45f);
		batch = new SpriteBatch();
		personTexture0 = new Texture(Gdx.files.internal("alien/firststick.png"));
		personRegion0 = new TextureRegion(personTexture0);
		person0 = new Image(personRegion0);
		person0.setPosition(200, 100);
		personTexture1 = new Texture(Gdx.files.internal("alien/stickwithhead.png"));
		personRegion1 = new TextureRegion(personTexture1);
		person1 = new Image(personRegion1);
		person1.setPosition(200, 100);
		personTexture2 = new Texture(Gdx.files.internal("alien/stickwithtorso.png"));
		personRegion2 = new TextureRegion(personTexture2);
		person2 = new Image(personRegion2);
		person2.setPosition(200, 100);
		personTexture3 = new Texture(Gdx.files.internal("alien/stickwithtarms.png"));
		personRegion3 = new TextureRegion(personTexture3);
		person3 = new Image(personRegion3);
		person3.setPosition(200, 100);
		personTexture4 = new Texture(Gdx.files.internal("alien/fullalien.png"));
		personRegion4 = new TextureRegion(personTexture4);
		person4 = new Image(personRegion4);
		person4.setPosition(200, 100);
		people = new ArrayList<Image>();
		people.add(person0);
		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);
		stage = new Stage();
		skin = new Skin();
		
		buttonAtlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
	    skin.addRegions(buttonAtlas);
	    ButtonStyle = new TextButtonStyle();
	    ButtonStyle.font = text;
	    ButtonStyle.up = skin.getDrawable("default-rect");
	    ButtonStyle.down = skin.getDrawable("default-rect-down");
	    ButtonStyle.checked = skin.getDrawable("default-rect");
	    button = new TextButton("Continue", ButtonStyle);
	    button.setBounds(400, 50, 150, 60);
	    button.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int btn) {
				super.touchUp(e, x, y, point, btn);
				if (game.mode == UDoraTheExplorer.GameState.advisement) {
					area.screenState = AdvisementArea.AdvisementScreen.end;
				}
				else if (game.mode == UDoraTheExplorer.GameState.online) {
					area.screenState = OnlineArea.SakaiScreen.end;
				}
				else if (game.mode == UDoraTheExplorer.GameState.calendar_dates) {
					area.screenState = CalendarArea.CalendarScreen.end;
				}
				else if (game.mode == UDoraTheExplorer.GameState.resources) {
					area.screenState = ResourcesArea.ResourcesScreen.end;
				}
			}
		});
	    stage.addActor(button);	
	}
	
	public void render(){
		Gdx.gl.glClearColor(0, 38/255f,99/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.input.setInputProcessor(stage);
		stage.clear();
		stage.addActor(people.get(game.numPieces));
		stage.addActor(button);
		stage.draw();
		batch.begin();
		if(game.numPieces == 1){
			text.draw(batch, "You found the first piece!", 150, 450);
		}
		else if (game.numPieces == 2){
			text.draw(batch, "You found the second piece!", 150, 450);
		}
		else if (game.numPieces == 3){
			text.draw(batch, "You found the third piece!", 150, 450);
		}
		else {
			text.draw(batch, "You found the last piece!", 150, 450);
		}
		batch.end();
	}
}
