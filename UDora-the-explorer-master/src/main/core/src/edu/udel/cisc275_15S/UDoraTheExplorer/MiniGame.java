package edu.udel.cisc275_15S.UDoraTheExplorer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.udel.cisc275_15S.UDoraTheExplorer.UDoraTheExplorer.GameState;

public class MiniGame {
	UDoraTheExplorer game;
	Area area;
	int numTries;
	int numSuccess;
	float percentCorrect;
	GameState mini = GameState.start;
	ShapeRenderer shapeRenderer;
	SpriteBatch batch;
	TextureAtlas buttonAtlas;
	ListOfQuestions questions;
	ArrayList<String> questionsOrdered;
	ArrayList<Integer> questionsOrderedByIndex;
	HashMap<String, ArrayList<String>> answersForQuestions;
	ArrayList<String> needToReview;
	BitmapFont font;
	BitmapFont font1; // this is used for the buttons
	TextButton a1;
	TextButton a2;
	TextButton a3;
	TextButton a4;
	TextButton next;
	TextButtonStyle ButtonStyle;
	Skin skin;
	Basketballgame basketball = new Basketballgame(game);
	boolean success;
	Random rand;

	/*
	 * These are for managing the game
	 */
	int Qclicks = 0; // used for switching from questions to score state
	int clicks = 0;
	int[] clickOrder = new int[4];
	int[] userClicks = new int[4];
	int score = 0;
	boolean first = true;
	int[] heights = { 10, 165, 320, 475 };
	boolean[] heightUsed = { false, false, false, false };
	Random r = new Random();
	ArrayList<Integer> review2 = new ArrayList<Integer>();
	int reviewNum = 0;
	boolean nextscore = false;
	ArrayList<String> text;

	/*
	 * These are the variables needed for the click listener The answ's are the
	 * answers the users can choose. For the prototype, the order that the
	 * answers are chosen in will determine which is correct.
	 */

	private Stage stage;
	private BitmapFont questionfont;

	public MiniGame(UDoraTheExplorer game, Area area, ListOfQuestions questions) {
		needToReview = new ArrayList<String>();
		this.rand = new Random();
		this.area = area;
		this.game = game;
		success = false;
		percentCorrect = 0f;
		batch = new SpriteBatch();
		stage = new Stage();
		skin = new Skin();
		numTries = 0;
		numSuccess = 0;

		questionsOrdered = new ArrayList<String>();
		questionsOrderedByIndex = new ArrayList<Integer>();
		answersForQuestions = new HashMap<String, ArrayList<String>>();

		font1 = new BitmapFont(Gdx.files.internal("arial.fnt"));
		font = new BitmapFont(Gdx.files.internal("arial.fnt"));
		questionfont = new BitmapFont(Gdx.files.internal("arial.fnt"));

		this.questions = questions;

		// Randomize Order of Questions
		LinkedHashSet<Integer> orderOfQuestions = new LinkedHashSet<Integer>();
		while (orderOfQuestions.size() < 4) {
			orderOfQuestions
					.add(rand
							.nextInt((questions.getQuestions().size() - 1 - 0) + 1) + 0);
		}
		Iterator<Integer> iter = orderOfQuestions.iterator();
		while (iter.hasNext()) {
			int current = iter.next();
			questionsOrdered.add(questions.getQuestionAtIndex(current)
					.getQuestion());
			questionsOrderedByIndex.add(current);
		}

		// Randomize Order of Answers for Each Question Q1
		ArrayList<String> answersForQ1 = new ArrayList<String>();
		LinkedHashSet<Integer> orderOfAnswersQ1 = new LinkedHashSet<Integer>();
		while (orderOfAnswersQ1.size() < 3) {
			orderOfAnswersQ1.add(rand.nextInt((questions
					.getQuestionAtIndex(questionsOrderedByIndex.get(0))
					.getWrongAnswers().size() - 1 - 0) + 1) + 0);
		}
		iter = orderOfAnswersQ1.iterator();
		while (iter.hasNext()) {
			int current = iter.next();
			answersForQ1.add(questions.getQuestionAtIndex(
					questionsOrderedByIndex.get(0)).getWrongAnswerByIndex(
					current));
		}
		answersForQ1.add(rand.nextInt((3 - 0) + 1) + 0, questions
				.getQuestionAtIndex(questionsOrderedByIndex.get(0))
				.getCorrectAnswer());
		answersForQuestions.put(questionsOrdered.get(0), answersForQ1);

		// Q2
		ArrayList<String> answersForQ2 = new ArrayList<String>();
		LinkedHashSet<Integer> orderOfAnswersQ2 = new LinkedHashSet<Integer>();
		while (orderOfAnswersQ2.size() < 3) {
			orderOfAnswersQ2.add(rand.nextInt((questions
					.getQuestionAtIndex(questionsOrderedByIndex.get(1))
					.getWrongAnswers().size() - 1 - 0) + 1) + 0);
		}
		iter = orderOfAnswersQ2.iterator();
		while (iter.hasNext()) {
			int current = iter.next();
			answersForQ2.add(questions.getQuestionAtIndex(
					questionsOrderedByIndex.get(1)).getWrongAnswerByIndex(
					current));
		}
		answersForQ2.add(rand.nextInt((3 - 0) + 1) + 0, questions
				.getQuestionAtIndex(questionsOrderedByIndex.get(1))
				.getCorrectAnswer());
		answersForQuestions.put(questionsOrdered.get(1), answersForQ2);

		// Q3
		ArrayList<String> answersForQ3 = new ArrayList<String>();
		LinkedHashSet<Integer> orderOfAnswersQ3 = new LinkedHashSet<Integer>();
		while (orderOfAnswersQ3.size() < 3) {
			orderOfAnswersQ3.add(rand.nextInt((questions
					.getQuestionAtIndex(questionsOrderedByIndex.get(2))
					.getWrongAnswers().size() - 1 - 0) + 1) + 0);
		}
		iter = orderOfAnswersQ3.iterator();
		while (iter.hasNext()) {
			int current = iter.next();
			answersForQ3.add(questions.getQuestionAtIndex(
					questionsOrderedByIndex.get(2)).getWrongAnswerByIndex(
					current));
		}
		answersForQ3.add(rand.nextInt((3 - 0) + 1) + 0, questions
				.getQuestionAtIndex(questionsOrderedByIndex.get(2))
				.getCorrectAnswer());
		answersForQuestions.put(questionsOrdered.get(2), answersForQ3);

		// Q4
		ArrayList<String> answersForQ4 = new ArrayList<String>();
		LinkedHashSet<Integer> orderOfAnswersQ4 = new LinkedHashSet<Integer>();
		while (orderOfAnswersQ4.size() < 3) {
			orderOfAnswersQ4.add(rand.nextInt((questions
					.getQuestionAtIndex(questionsOrderedByIndex.get(3))
					.getWrongAnswers().size() - 1 - 0) + 1) + 0);
		}
		iter = orderOfAnswersQ4.iterator();
		while (iter.hasNext()) {
			int current = iter.next();
			answersForQ4.add(questions.getQuestionAtIndex(
					questionsOrderedByIndex.get(3)).getWrongAnswerByIndex(
					current));
		}
		answersForQ4.add(rand.nextInt((3 - 0) + 1) + 0, questions
				.getQuestionAtIndex(questionsOrderedByIndex.get(3))
				.getCorrectAnswer());
		answersForQuestions.put(questionsOrdered.get(3), answersForQ4);

		font.setScale(0.4f);
		font1.setScale(0.3f);
		questionfont.setScale(0.6f);
		buttonAtlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
		skin.addRegions(buttonAtlas);
		ButtonStyle = new TextButtonStyle();
		ButtonStyle.font = font1;
		ButtonStyle.up = skin.getDrawable("default-rect");
		ButtonStyle.down = skin.getDrawable("default-rect-down");
		ButtonStyle.checked = skin.getDrawable("default-rect");

		/*
		 * These are the scenarios. They will be on the left side and will be
		 * matched up with the answers on the right side.
		 */
		/*
		 * These are the "answers" to the scenarios.
		 */

		/*
		 * I am using Images as my actors. Hopefully it works out fine in the
		 * long run but it's an easy change if not.
		 */

		/*
		 * The listeners for the answer choices.
		 */
		a1 = new TextButton(answersForQuestions.get(
				questionsOrdered.get(Qclicks)).get(0), ButtonStyle);
		a1.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point,
					int btn) {
				super.touchUp(e, x, y, point, btn);
				if (mini == GameState.questions && Qclicks < 4) {
					if (checkAnswerStatus(0)) {
						basketball.status = "true";
						basketball.run();
						score++;

					} else {
						basketball.status = "false";
						basketball.run();
						needToReview.add(getReview());
					}
					Qclicks++;
					if (Qclicks < 4) {
						text = InteractWithFiles.drawQuestionsDifferentLines(
								questionsOrdered.get(Qclicks), 30);
					}
				}
			}
		});

		a2 = new TextButton(answersForQuestions.get(
				questionsOrdered.get(Qclicks)).get(1), ButtonStyle);
		a2.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point,
					int btn) {
				super.touchUp(e, x, y, point, btn);
				if (mini == GameState.questions && Qclicks < 4) {
					if (checkAnswerStatus(1)) {
						basketball.status = "true";
						basketball.run();
						score++;

					} else {
						basketball.status = "false";
						basketball.run();
						needToReview.add(getReview());
					}
					Qclicks++;
					if (Qclicks < 4) {
						text = InteractWithFiles.drawQuestionsDifferentLines(
								questionsOrdered.get(Qclicks), 30);
					}
				}
			}
		});

		a3 = new TextButton(answersForQuestions.get(
				questionsOrdered.get(Qclicks)).get(2), ButtonStyle);
		a3.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point,
					int btn) {
				super.touchUp(e, x, y, point, btn);
				if (mini == GameState.questions && Qclicks < 4) {
					if (checkAnswerStatus(2)) {
						basketball.status = "true";
						basketball.run();
						score++;

					} else {
						basketball.status = "false";
						basketball.run();
						needToReview.add(getReview());
					}
					Qclicks++;
					if (Qclicks < 4) {
						text = InteractWithFiles.drawQuestionsDifferentLines(
								questionsOrdered.get(Qclicks), 30);
					}
				}
			}
		});

		a4 = new TextButton(answersForQuestions.get(
				questionsOrdered.get(Qclicks)).get(3), ButtonStyle);
		a4.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point,
					int btn) {
				super.touchUp(e, x, y, point, btn);
				if (mini == GameState.questions && Qclicks < 4) {
					if (checkAnswerStatus(3)) {
						basketball.status = "true";
						basketball.run();
						score++;

					} else {
						basketball.status = "false";
						basketball.run();
						needToReview.add(getReview());
					}
					Qclicks++;
					if (Qclicks < 4) {
						text = InteractWithFiles.drawQuestionsDifferentLines(
								questionsOrdered.get(Qclicks), 30);
					}
				}
			}
		});
		next = new TextButton("Continue", ButtonStyle);
		next.setBounds(200, 350, 250, 60);
		next.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent e, float x, float y, int point,
					int btn) {
				super.touchUp(e, x, y, point, btn);
				if (mini == GameState.questions && Qclicks > 3) {
					font.setScale(0.4f);
					nextscore = true;
				}
			}
		});

		/*
		 * This is the part that randomizes which answer goes where
		 */
		text = InteractWithFiles.drawQuestionsDifferentLines(
				questionsOrdered.get(Qclicks), 30);
	}

	public Area getArea() {
		return area;
	}

	public void endGame() {
		needToReview = new ArrayList<String>();
		if (game.mode == UDoraTheExplorer.GameState.advisement) {
			//System.out.println("Advisement Area Score: " + score);
			CurrentPlayer.getStudent().increaseAdvisementTries();
		} else if (game.mode == UDoraTheExplorer.GameState.online) {
			//System.out.println("Online Area Score: " + score);
			CurrentPlayer.getStudent().increaseOnlineTries();
		} else if (game.mode == UDoraTheExplorer.GameState.resources) {
			//System.out.println("Resources Area Score: " + score);
			CurrentPlayer.getStudent().increaseResourcesTries();
		} else if (game.mode == UDoraTheExplorer.GameState.calendar_dates) {
			//System.out.println("Calendar Area Score: " + score);
			CurrentPlayer.getStudent().increaseCalendarTries();
		}
		first = true;
		nextscore = false;

		// REINITIALIZING QUESTION STUFF
		questionsOrdered = new ArrayList<String>();
		questionsOrderedByIndex = new ArrayList<Integer>();
		answersForQuestions = new HashMap<String, ArrayList<String>>();
		// Randomize Order of Questions
		LinkedHashSet<Integer> orderOfQuestions = new LinkedHashSet<Integer>();
		while (orderOfQuestions.size() < 4) {
			orderOfQuestions
					.add(rand
							.nextInt((questions.getQuestions().size() - 1 - 0) + 1) + 0);
		}
		Iterator<Integer> iter = orderOfQuestions.iterator();
		while (iter.hasNext()) {
			int current = iter.next();
			questionsOrdered.add(questions.getQuestionAtIndex(current)
					.getQuestion());
			questionsOrderedByIndex.add(current);
		}

		// Randomize Order of Answers for Each Question Q1
		ArrayList<String> answersForQ1 = new ArrayList<String>();
		LinkedHashSet<Integer> orderOfAnswersQ1 = new LinkedHashSet<Integer>();
		while (orderOfAnswersQ1.size() < 3) {
			orderOfAnswersQ1.add(rand.nextInt((questions
					.getQuestionAtIndex(questionsOrderedByIndex.get(0))
					.getWrongAnswers().size() - 1 - 0) + 1) + 0);
		}
		iter = orderOfAnswersQ1.iterator();
		while (iter.hasNext()) {
			int current = iter.next();
			answersForQ1.add(questions.getQuestionAtIndex(
					questionsOrderedByIndex.get(0)).getWrongAnswerByIndex(
					current));
		}
		answersForQ1.add(rand.nextInt((3 - 0) + 1) + 0, questions
				.getQuestionAtIndex(questionsOrderedByIndex.get(0))
				.getCorrectAnswer());
		answersForQuestions.put(questionsOrdered.get(0), answersForQ1);

		// Q2
		ArrayList<String> answersForQ2 = new ArrayList<String>();
		LinkedHashSet<Integer> orderOfAnswersQ2 = new LinkedHashSet<Integer>();
		while (orderOfAnswersQ2.size() < 3) {
			orderOfAnswersQ2.add(rand.nextInt((questions
					.getQuestionAtIndex(questionsOrderedByIndex.get(1))
					.getWrongAnswers().size() - 1 - 0) + 1) + 0);
		}
		iter = orderOfAnswersQ2.iterator();
		while (iter.hasNext()) {
			int current = iter.next();
			answersForQ2.add(questions.getQuestionAtIndex(
					questionsOrderedByIndex.get(1)).getWrongAnswerByIndex(
					current));
		}
		answersForQ2.add(rand.nextInt((3 - 0) + 1) + 0, questions
				.getQuestionAtIndex(questionsOrderedByIndex.get(1))
				.getCorrectAnswer());
		answersForQuestions.put(questionsOrdered.get(1), answersForQ2);

		// Q3
		ArrayList<String> answersForQ3 = new ArrayList<String>();
		LinkedHashSet<Integer> orderOfAnswersQ3 = new LinkedHashSet<Integer>();
		while (orderOfAnswersQ3.size() < 3) {
			orderOfAnswersQ3.add(rand.nextInt((questions
					.getQuestionAtIndex(questionsOrderedByIndex.get(2))
					.getWrongAnswers().size() - 1 - 0) + 1) + 0);
		}
		iter = orderOfAnswersQ3.iterator();
		while (iter.hasNext()) {
			int current = iter.next();
			answersForQ3.add(questions.getQuestionAtIndex(
					questionsOrderedByIndex.get(2)).getWrongAnswerByIndex(
					current));
		}
		answersForQ3.add(rand.nextInt((3 - 0) + 1) + 0, questions
				.getQuestionAtIndex(questionsOrderedByIndex.get(2))
				.getCorrectAnswer());
		answersForQuestions.put(questionsOrdered.get(2), answersForQ3);

		// Q4
		ArrayList<String> answersForQ4 = new ArrayList<String>();
		LinkedHashSet<Integer> orderOfAnswersQ4 = new LinkedHashSet<Integer>();
		while (orderOfAnswersQ4.size() < 3) {
			orderOfAnswersQ4.add(rand.nextInt((questions
					.getQuestionAtIndex(questionsOrderedByIndex.get(3))
					.getWrongAnswers().size() - 1 - 0) + 1) + 0);
		}
		iter = orderOfAnswersQ4.iterator();
		while (iter.hasNext()) {
			int current = iter.next();
			answersForQ4.add(questions.getQuestionAtIndex(
					questionsOrderedByIndex.get(3)).getWrongAnswerByIndex(
					current));
		}
		answersForQ4.add(rand.nextInt((3 - 0) + 1) + 0, questions
				.getQuestionAtIndex(questionsOrderedByIndex.get(3))
				.getCorrectAnswer());
		answersForQuestions.put(questionsOrdered.get(3), answersForQ4);
		a1.setText(answersForQuestions.get(questionsOrdered.get(0)).get(0));
		a2.setText(answersForQuestions.get(questionsOrdered.get(0)).get(1));
		a3.setText(answersForQuestions.get(questionsOrdered.get(0)).get(2));
		a4.setText(answersForQuestions.get(questionsOrdered.get(0)).get(3));
		text = InteractWithFiles.drawQuestionsDifferentLines(
				questionsOrdered.get(0), 30);

		heightUsed[0] = false;
		heightUsed[1] = false;
		heightUsed[2] = false;
		heightUsed[3] = false;
		reviewNum = 0;
		review2.clear();
		stage.clear();
		stage.addActor(a1);
		stage.addActor(a2);
		stage.addActor(a3);
		stage.addActor(a4);
		if (game.mode == UDoraTheExplorer.GameState.advisement) {
			if (area.completed) {
				area.screenState = AdvisementArea.AdvisementScreen.end;
			} else if (success == true) {
				area.screenState = AdvisementArea.AdvisementScreen.reward;
			} else {
				area.screenState = AdvisementArea.AdvisementScreen.end;
			}
		} else if (game.mode == UDoraTheExplorer.GameState.online) {
			if (area.completed) {
				area.screenState = OnlineArea.SakaiScreen.end;
			} else if (success == true) {
				area.screenState = OnlineArea.SakaiScreen.reward;
			} else {
				area.screenState = OnlineArea.SakaiScreen.end;
			}
		} else if (game.mode == UDoraTheExplorer.GameState.calendar_dates) {
			if (area.completed) {
				area.screenState = CalendarArea.CalendarScreen.end;
			} else if (success == true) {
				area.screenState = CalendarArea.CalendarScreen.reward;
			} else {
				area.screenState = CalendarArea.CalendarScreen.end;
			}
		} else if (game.mode == UDoraTheExplorer.GameState.resources) {
			if (area.completed) {
				area.screenState = ResourcesArea.ResourcesScreen.end;
			} else if (success == true) {
				area.screenState = ResourcesArea.ResourcesScreen.reward;
			} else {
				area.screenState = ResourcesArea.ResourcesScreen.end;
			}
		}
	}

	public void stateChange() {
		/*
		 * Just so that my state changes are more organized.
		 */
		if (Qclicks > 3 && mini == GameState.questions) {
			/*
			 * This makes all of the buttons disappear except for the continue
			 * button
			 */
			stage.clear();
			stage.addActor(next);
		}
		if (Gdx.input.justTouched()) {
			if (mini == GameState.start) {
				int x = Gdx.input.getX();
				int y = Gdx.input.getY();
				score = 0;
				clicks = 0;
				Qclicks = 0;
				// font.setScale(0.25f);
				if(x > 240 && x < 445 && y > 322 && y < 350){
					mini = GameState.questions;
				}
			} else if (mini == GameState.score) {
				int numc = (int) (percentCorrect * (numTries * 4)) + score;
				numTries++;
				percentCorrect = ((float) numc) / ((float) (numTries * 4));
				if (score >= 3) {
					numSuccess++;
				}
				if (needToReview.size() == 0) {
					endGame();
				}
				mini = GameState.review;
			} else if (mini == GameState.review) {
				reviewNum++;
				if (needToReview.size() == reviewNum) {
					endGame();
				}
			}

		}
	}

	public boolean checkAnswerStatus(int index) {
		boolean result = questions
				.getQuestionAtIndex(questionsOrderedByIndex.get(Qclicks))
				.getCorrectAnswer()
				.equals(answersForQuestions.get(
						questions.getQuestionAtIndex(
								questionsOrderedByIndex.get(Qclicks))
								.getQuestion()).get(index));
		if (result == true) {
			if (getArea().getClass().equals(AdvisementArea.class)) {
				if (questionsOrderedByIndex.get(Qclicks) == 0) {
					CurrentPlayer.getStudent().increaseAdvisementFinalDecisions();
				}
				if (questionsOrderedByIndex.get(Qclicks) == 1) {
					CurrentPlayer.getStudent().increaseAdvisementBadRoomate();
				}
				if (questionsOrderedByIndex.get(Qclicks) == 2) {
					CurrentPlayer.getStudent().increaseAdvisementScheduling();
				}
				if (questionsOrderedByIndex.get(Qclicks) == 3) {
					CurrentPlayer.getStudent().increaseAdvisementLimit();
				}
			}
			if (getArea().getClass().equals(ResourcesArea.class)) {
				if (questionsOrderedByIndex.get(Qclicks) == 0) {
					CurrentPlayer.getStudent().increaseResourcesWritingCenter();
				}
				if (questionsOrderedByIndex.get(Qclicks) == 1) {
					CurrentPlayer.getStudent().increaseResourcesMathTutorialLab();
				}
				if (questionsOrderedByIndex.get(Qclicks) == 2) {
					CurrentPlayer.getStudent().increaseResourcesOfficeOfAcademicEnrichment();
				}
				if (questionsOrderedByIndex.get(Qclicks) == 3) {
					CurrentPlayer.getStudent().increaseResourcesCareerServices();
				}
			}
			if (getArea().getClass().equals(CalendarArea.class)) {
				if (questionsOrderedByIndex.get(Qclicks) == 0) {
					CurrentPlayer.getStudent().increaseCalendarDropAdd();
				}
				if (questionsOrderedByIndex.get(Qclicks) == 1) {
					CurrentPlayer.getStudent().increaseCalendarWithdrawal();
				}
				if (questionsOrderedByIndex.get(Qclicks) == 2) {
					CurrentPlayer.getStudent().increaseCalendarOverall();
				}
				if (questionsOrderedByIndex.get(Qclicks) == 3) {
					CurrentPlayer.getStudent().increaseCalendarOffTranscript();
				}
			}
			if (getArea().getClass().equals(OnlineArea.class)) {
				if (questionsOrderedByIndex.get(Qclicks) == 0) {
					CurrentPlayer.getStudent().increaseOnlineUDSIS();
				}
				if (questionsOrderedByIndex.get(Qclicks) == 1) {
					CurrentPlayer.getStudent().increaseOnlineAddClasses();
				}
				if (questionsOrderedByIndex.get(Qclicks) == 2) {
					CurrentPlayer.getStudent().increaseOnlineChangeMajor();
				}
				if (questionsOrderedByIndex.get(Qclicks) == 3) {
					CurrentPlayer.getStudent().increaseOnlineOtherPeople();
				}
			}
		}
		return result;
	}

	public String getReview() {
		return questions.getQuestionAtIndex(
				questionsOrderedByIndex.get(Qclicks)).getReview();
	}

	public void render() {
		Gdx.gl.glClearColor(0, 38 / 255f, 99 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.input.setInputProcessor(stage);
		stateChange();
		drawGame();
		if (nextscore) {
			mini = GameState.score;
			nextscore = false;
		}
	}

	public void drawGame() {

		/*
		 * The instructions will be updated.
		 * 
		 * The heights are actually widths
		 */
		if (first) {
			for (int i = 0; i < 4; i++) {
				int place = r.nextInt(4);
				while (heightUsed[place] == true) {
					place = r.nextInt(4);
				}
				if (i == 0) {
					a1.setBounds(heights[place], 265, 150, 60);
					stage.addActor(a1);
					heightUsed[place] = true;
				} else if (i == 1) {
					a2.setBounds(heights[place], 265, 150, 60);
					stage.addActor(a2);
					heightUsed[place] = true;
				} else if (i == 2) {
					a3.setBounds(heights[place], 265, 150, 60);
					stage.addActor(a3);
					heightUsed[place] = true;
				} else if (i == 3) {
					a4.setBounds(heights[place], 265, 150, 60);
					stage.addActor(a4);
					heightUsed[place] = true;
				}
			}
		}

		if (mini == GameState.questions) {
			basketball.render();
			batch.begin();
			if (Qclicks == 0) {
				for (int i = 0; i < text.size(); i++) {
					if (i == 0) {
						questionfont.draw(batch, "1: " + text.get(i), 50,
								450 - 50 * i);
					} else {
						questionfont.draw(batch, text.get(i), 50, 450 - 50 * i);
					}
				}
				a1.setText(answersForQuestions.get(
						questionsOrdered.get(Qclicks)).get(0));
				a2.setText(answersForQuestions.get(
						questionsOrdered.get(Qclicks)).get(1));
				a3.setText(answersForQuestions.get(
						questionsOrdered.get(Qclicks)).get(2));
				a4.setText(answersForQuestions.get(
						questionsOrdered.get(Qclicks)).get(3));
			}
			if (Qclicks == 1) {
				for (int i = 0; i < text.size(); i++) {
					if (i == 0) {
						questionfont.draw(batch, "2: " + text.get(i), 50,
								450 - 50 * i);
					} else {
						questionfont.draw(batch, text.get(i), 50, 450 - 50 * i);
					}
				}
				a1.setText(answersForQuestions.get(
						questionsOrdered.get(Qclicks)).get(0));
				a2.setText(answersForQuestions.get(
						questionsOrdered.get(Qclicks)).get(1));
				a3.setText(answersForQuestions.get(
						questionsOrdered.get(Qclicks)).get(2));
				a4.setText(answersForQuestions.get(
						questionsOrdered.get(Qclicks)).get(3));
			}
			if (Qclicks == 2) {
				for (int i = 0; i < text.size(); i++) {
					if (i == 0) {
						questionfont.draw(batch, "3: " + text.get(i), 50,
								450 - 50 * i);
					} else {
						questionfont.draw(batch, text.get(i), 50, 450 - 50 * i);
					}
				}
				a1.setText(answersForQuestions.get(
						questionsOrdered.get(Qclicks)).get(0));
				a2.setText(answersForQuestions.get(
						questionsOrdered.get(Qclicks)).get(1));
				a3.setText(answersForQuestions.get(
						questionsOrdered.get(Qclicks)).get(2));
				a4.setText(answersForQuestions.get(
						questionsOrdered.get(Qclicks)).get(3));
			}
			if (Qclicks == 3) {
				for (int i = 0; i < text.size(); i++) {
					if (i == 0) {
						questionfont.draw(batch, "4: " + text.get(i), 50,
								450 - 50 * i);
					} else {
						questionfont.draw(batch, text.get(i), 50, 450 - 50 * i);
					}
				}
				a1.setText(answersForQuestions.get(
						questionsOrdered.get(Qclicks)).get(0));
				a2.setText(answersForQuestions.get(
						questionsOrdered.get(Qclicks)).get(1));
				a3.setText(answersForQuestions.get(
						questionsOrdered.get(Qclicks)).get(2));
				a4.setText(answersForQuestions.get(
						questionsOrdered.get(Qclicks)).get(3));
			}
			font.setColor(Color.BLACK);
			font.draw(batch, "(Click answers below)",
					(Gdx.graphics.getWidth() / 2) - 80, 350);
			font.draw(batch, score + " out of " + Qclicks, 50, 100);
			font.setColor(Color.WHITE);
			batch.end();
			stage.draw();
		}

		if (mini == GameState.start) {
			batch.begin();
			font.draw(batch, "(click here to continue)", Gdx.graphics.getWidth() / 2 - 75, 150);
			font.draw(batch, InteractWithFiles.readMiniGameScreen(0),
					Gdx.graphics.getWidth() / 2 - 75,
					Gdx.graphics.getHeight() / 2 + 50);
			font.draw(batch, InteractWithFiles.readMiniGameScreen(1),
					Gdx.graphics.getWidth() / 2 - 200,
					Gdx.graphics.getHeight() / 2 + 25);
			font.draw(batch, InteractWithFiles.readMiniGameScreen(2),
					Gdx.graphics.getWidth() / 2 - 150,
					Gdx.graphics.getHeight() / 2);
			batch.end();
		}
		if (mini == GameState.score) {

			batch.begin();
			font.draw(batch, "(click to continue)", 200, 150);
			if (score >= 3) {
				font.draw(batch, "You passed!",
						Gdx.graphics.getWidth() / 2 - 125,
						Gdx.graphics.getHeight() / 2 + 50);
				font.draw(batch, score + " out of 4",
						Gdx.graphics.getWidth() / 2 - 125,
						Gdx.graphics.getHeight() / 2 - 5);
				success = true;
			} else {
				font.draw(batch, "You Failed...",
						Gdx.graphics.getWidth() / 2 - 125,
						Gdx.graphics.getHeight() / 2 + 50);
				font.draw(batch, score + " out of 4",
						Gdx.graphics.getWidth() / 2 - 125,
						Gdx.graphics.getHeight() / 2 - 5);
			}
			batch.end();
		}
		if (mini == GameState.review) {
			if (!first) {
				batch.begin();
				font.draw(batch, "Review Section", 200, 350);
				font.draw(batch, "(click to continue)", 200, 150);
				ArrayList<String> text = InteractWithFiles
						.drawQuestionsDifferentLines(
								needToReview.get(reviewNum), 50);
				for (int i = 0; i < text.size(); i++) {
					font.draw(batch, text.get(i), 50, 300 - 50 * i);
				}

				batch.end();
			}
		}
		first = false;
	}

	static enum GameState {
		/*
		 * The game will go through the phases of: The actual game (matching) A
		 * summary of each of the pairs A signal to end the game
		 */

		start, questions, score, review, end

	}
}
