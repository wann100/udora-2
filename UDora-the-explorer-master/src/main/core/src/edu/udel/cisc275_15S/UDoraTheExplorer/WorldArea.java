package edu.udel.cisc275_15S.UDoraTheExplorer;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.udel.cisc275_15S.UDoraTheExplorer.AdvisementArea.AdvisementScreen;
import edu.udel.cisc275_15S.UDoraTheExplorer.UDoraTheExplorer.GameState;

public class WorldArea{
	public UDoraTheExplorer game;
	public Texture background;
	private BitmapFont text;
	public boolean done;
	public SpriteBatch batch;
	public ShapeRenderer shape;
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
	public int current;
	public Texture checktexture;
	public TextureRegion checkregion;
	public Image check1;
	public Image check2;
	public Image check3;
	public Image check4;
	public Texture redxtexture;
	public TextureRegion redxregion;
	public Image redx1;
	public Image redx2;
	public Image redx3;
	public Image redx4;
	public Texture redtexture;
	public TextureRegion redregion;
	public Texture greentexture;
	public TextureRegion greenregion;
	public Image green1;
	public Image green2;
	public Image green3;
	public Image green4;
	public Image red1;
	public Image red2;
	public Image red3;
	public Image red4;
	public Stage stage2;
	public TextButton exit;
	//the world area will only have one screen and will not have a completion value since it is the map
	
	public WorldArea(final UDoraTheExplorer game){
		this.game = game;
		stage2 = new Stage();
		stage = new Stage();
		background = new Texture("map.jpg");
		text = new BitmapFont(Gdx.files.internal("arial.fnt"));
		text.scale(-0.45f);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		
		checktexture = new Texture(Gdx.files.internal("checkmark.png"));
		checkregion = new TextureRegion(checktexture);
		check1 = new Image(checkregion);
		check2 = new Image(checkregion);
		check3 = new Image(checkregion);
		check4 = new Image(checkregion);
		check1.setPosition(140, 110);
		check1.scaleBy(-0.5f);
		check2.setPosition(235, 220);
		check2.scaleBy(-0.5f);
		check3.setPosition(320, 380);
		check3.scaleBy(-0.5f);
		check4.setPosition(400, 245);
		check4.scaleBy(-0.5f);
		done = false;
		
		redxtexture = new Texture(Gdx.files.internal("redx.png"));
		redxregion = new TextureRegion(redxtexture);
		redx1 = new Image(redxregion);
		redx1.scaleBy(-0.5f);
		redx2 = new Image(redxregion);
		redx2.scaleBy(-0.5f);
		redx3 = new Image(redxregion);
		redx3.scaleBy(-0.5f);
		redx4 = new Image(redxregion);
		redx4.scaleBy(-0.5f);
		redx1.setPosition(140, 110);
		redx2.setPosition(235, 220);
		redx3.setPosition(320, 380);
		redx4.setPosition(400, 245);
		
		redtexture = new Texture(Gdx.files.internal("redrectangle.png"));
		redregion = new TextureRegion(redtexture);
		greentexture = new Texture(Gdx.files.internal("greenrectangle.png"));
		greenregion = new TextureRegion(greentexture);
		red1 = new Image(redregion);
		red1.setPosition(40, 110);
		red1.scaleBy(-0.5f);
		red1.scaleBy(-0.1f, 0f);
		red2 = new Image(redregion);
		red2.setPosition(80, 220);
		red2.scaleBy(-0.5f);
		red2.scaleBy(0.12f, 0f);
		red3 = new Image(redregion);
		red3.setPosition(370, 390);
		red3.scaleBy(-0.5f);
		red4 = new Image(redregion);
		red4.setPosition(450, 250);
		red4.scaleBy(-0.5f);
		red4.scaleBy(0.1f, 0f);
		green1 = new Image(greenregion);
		green1.setPosition(40, 110);
		green1.scaleBy(-0.5f);
		green1.scaleBy(-0.1f, 0f);
		green2 = new Image(greenregion);
		green2.setPosition(80, 220);
		green2.scaleBy(-0.5f);
		green2.scaleBy(0.12f, 0f);
		green3 = new Image(greenregion);
		green3.setPosition(370, 390);
		green3.scaleBy(-0.5f);
		green4 = new Image(greenregion);
		green4.setPosition(450, 250);
		green4.scaleBy(-0.5f);
		green4.scaleBy(0.1f, 0f);
		red1.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int btn) {
				super.touchUp(e, x, y, point, btn);
				current = 1;
			}
		});
		red2.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int btn) {
				super.touchUp(e, x, y, point, btn);
				current = 2;
			}
		});
		red3.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int btn) {
				super.touchUp(e, x, y, point, btn);
				current = 3;
			}
		});
		red4.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int btn) {
				super.touchUp(e, x, y, point, btn);
				current = 4;
			}
		});
		green1.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int btn) {
				super.touchUp(e, x, y, point, btn);
				current = 1;
			}
		});
		green2.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int btn) {
				super.touchUp(e, x, y, point, btn);
				current = 2;
			}
		});
		green3.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int btn) {
				super.touchUp(e, x, y, point, btn);
				current = 3;
			}
		});
		green4.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point, int btn) {
				super.touchUp(e, x, y, point, btn);
				current = 4;
			}
		});
		
		stage2.addActor(redx1);
		stage2.addActor(redx2);
		stage2.addActor(redx3);
		stage2.addActor(redx4);
		stage2.addActor(red1);
		stage2.addActor(red2);
		stage2.addActor(red3);
		stage2.addActor(red4);
		
		personTexture0 = new Texture(Gdx.files.internal("alien/firststick.png"));
		personRegion0 = new TextureRegion(personTexture0);
		person0 = new Image(personRegion0);
		person0.setPosition(300, 200);
		person0.scaleBy(-0.75f);
		personTexture1 = new Texture(Gdx.files.internal("alien/stickwithhead.png"));
		personRegion1 = new TextureRegion(personTexture1);
		person1 = new Image(personRegion1);
		person1.setPosition(200, 200);
		person1.scaleBy(-0.75f);
		personTexture2 = new Texture(Gdx.files.internal("alien/stickwithtorso.png"));
		personRegion2 = new TextureRegion(personTexture2);
		person2 = new Image(personRegion2);
		person2.setPosition(200, 200);
		person2.scaleBy(-0.75f);
		personTexture3 = new Texture(Gdx.files.internal("alien/stickwithtarms.png"));
		personRegion3 = new TextureRegion(personTexture3);
		person3 = new Image(personRegion3);
		person3.setPosition(200, 200);
		person3.scaleBy(-0.75f);
		personTexture4 = new Texture(Gdx.files.internal("alien/fullalien.png"));
		personRegion4 = new TextureRegion(personTexture4);
		person4 = new Image(personRegion4);
		person4.setPosition(200, 200);
		person4.scaleBy(-0.75f);
		people = new ArrayList<Image>();
		people.add(person0);
		people.add(person1);
		people.add(person2);
		people.add(person3);
		people.add(person4);
		
		stage.addActor(person0);
		skin = new Skin();
		current = 0;
		
		buttonAtlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
	    skin.addRegions(buttonAtlas);
	    ButtonStyle = new TextButtonStyle();
	    ButtonStyle.font = text;
	    ButtonStyle.up = skin.getDrawable("default-rect");
	    ButtonStyle.down = skin.getDrawable("default-rect-down");
	    ButtonStyle.checked = skin.getDrawable("default-rect");
	    button = new TextButton("Continue", ButtonStyle);
	    button.setBounds(450, 20, 150, 60);
	    button.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent e, float x, float y, int point, int btn) {
				//super.touchUp(e, x, y, point, btn);
				if(current == 1){
					game.mode = GameState.online;
					Student s = InteractWithFiles.removeStudentFromStudentStorage(CurrentPlayer.getUsername());
					s.setOnlineTries(s.getOnlineTries()+1);
					InteractWithFiles.addStudentToStudentStorage(s);
				}
				else if (current == 2){
					game.mode = GameState.advisement;
					Student s = InteractWithFiles.removeStudentFromStudentStorage(CurrentPlayer.getUsername());
					s.setAdvisementTries(s.getAdvisementTries()+1);
					InteractWithFiles.addStudentToStudentStorage(s);
				}
				else if (current == 3){
					game.mode = GameState.calendar_dates;
					Student s = InteractWithFiles.removeStudentFromStudentStorage(CurrentPlayer.getUsername());
					s.setCalendarTries(s.getCalendarTries()+1);
					InteractWithFiles.addStudentToStudentStorage(s);
				}
				else if (current == 4){
					game.mode = GameState.resources;
					Student s = InteractWithFiles.removeStudentFromStudentStorage(CurrentPlayer.getUsername());
					s.setResourcesTries(s.getResourcesTries()+1);
					InteractWithFiles.addStudentToStudentStorage(s);
				}
				return true;
			}
		});
	    stage.addActor(button);	
	    exit = new TextButton("Exit", ButtonStyle);
	    exit.setBounds(200, 20, 150, 60);
	    exit.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent e, float x, float y, int point, int btn) {
				game.exit();
				return true;
			}
		});
	}
	
	public void render(){
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		Gdx.input.setInputProcessor(stage2);
		stage.clear();
		stage2.clear();
		if(done){
			stage2.addActor(exit);
		}
		if(current == 1){
			people.get(game.numPieces).setPosition(70, 105);
		}
		else if(current == 2){
			people.get(game.numPieces).setPosition(140, 215);
		}
		else if(current == 3){
			people.get(game.numPieces).setPosition(400, 385);
		}
		else if(current == 4){
			people.get(game.numPieces).setPosition(480, 245);
		}
		stage.addActor(people.get(game.numPieces));
		if(current != 0){
			stage2.addActor(button);
		}
		if(!game.advisementArea.completed){
			stage2.addActor(redx2);
			stage2.addActor(red2);
		}
		else {
			stage2.addActor(check2);
			stage2.addActor(green2);
		}
		
		if(!game.calendarArea.completed){
			stage2.addActor(redx3);
			stage2.addActor(red3);
		}
		else {
			stage2.addActor(check3);
			stage2.addActor(green3);
		}
		
		if(!game.sakaiArea.completed){
			stage2.addActor(redx1);
			stage2.addActor(red1);
		}
		else {
			stage2.addActor(check1);
			stage2.addActor(green1);
		}
		
		if(!game.resourceArea.completed){
			stage2.addActor(redx4);
			stage2.addActor(red4);
		}
		else {
			stage2.addActor(check4);
			stage2.addActor(green4);
		}
		batch.begin();
		batch.draw(background, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch.end();
		stage2.draw();
		batch.begin();
		text.draw(batch, "UDSIS", 50, 140);
		text.draw(batch, "Advisement", 85, 250);
		text.draw(batch, "Calendar", 378, (float)(height*(7.0/8.0)));
		text.draw(batch, "Resources", 460, 280);
		batch.end();
		stage.draw();
	}
}
