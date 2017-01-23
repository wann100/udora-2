package edu.udel.cisc275_15S.UDoraTheExplorer;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class InteractWithFiles {
	public static String insertNewLineSpaces(String start, int characters) {
		StringBuilder sb = new StringBuilder(start);

		int i = 0;
		while ((i = sb.indexOf(" ", i + characters)) != -1) {
			sb.replace(i, i + 1, "\n");
		}
		return sb.toString();
	}

	public static ArrayList<String> drawQuestionsDifferentLines(String start,
			int characters) {
		StringBuilder sb = new StringBuilder(start);
		ArrayList<String> finalText = new ArrayList<String>();
		int i = 0;
		while ((i = sb.indexOf(" ", i + characters)) != -1) {
			sb.replace(i, i + 1, "#");
		}
		String text = sb.toString();
		while (text.contains("#")) {
			finalText.add(text.substring(0, text.indexOf("#")));
			text = text.substring(text.indexOf("#") + 1, text.length());
		}
		finalText.add(text);
		return finalText;
	}

	public static String readIntroScreen(int index) {
		FileHandle file = Gdx.files.internal("dialogs/IntroScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	public static int getSizeOfIntro() {
		FileHandle file = Gdx.files.internal("dialogs/IntroScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLinesOfText();
	}

	/*
	 * 
	 * 
	 * ADVISEMENT FILES
	 */
	public static String readAdvisementEndScreen(int index) {
		FileHandle file = Gdx.files
				.internal("dialogs/AdvisementEndScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	public static ListOfQuestions readAdvisementQuestions() {
		FileHandle file = Gdx.files
				.internal("dialogs/AdvisementQuestions.json");
		String text = file.readString();
		Json json = new Json();
		ListOfQuestions textLine = json.fromJson(ListOfQuestions.class, text);
		return textLine;
	}

	public static String readAdvisementIntroScreen(int index) {
		FileHandle file = Gdx.files
				.internal("dialogs/AdvisementIntroScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	public static String readAdvisementTeachingScreen(int index) {
		FileHandle file = Gdx.files
				.internal("dialogs/AdvisementTeachingScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	public static int getSizeOfAdvisementTeaching() {
		FileHandle file = Gdx.files
				.internal("dialogs/AdvisementTeachingScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLinesOfText();
	}

	public static String readAdvisementTeachingImages(int index) {
		FileHandle file = Gdx.files
				.internal("AdvisementPictures/ListOfPictures.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	public static int getSizeOfAdvisementTeachingImages() {
		FileHandle file = Gdx.files
				.internal("AdvisementPictures/ListOfPictures.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLinesOfText();
	}

	/*
	 * 
	 * 
	 * CALENDAR FILES
	 */
	public static ListOfQuestions readCalendarQuestions() {
		FileHandle file = Gdx.files.internal("dialogs/CalendarQuestions.json");
		String text = file.readString();
		Json json = new Json();
		ListOfQuestions textLine = json.fromJson(ListOfQuestions.class, text);
		return textLine;
	}

	public static String readCalendarEndScreen(int index) {
		FileHandle file = Gdx.files.internal("dialogs/CalendarEndScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	public static String readCalendarIntroScreen(int index) {
		FileHandle file = Gdx.files
				.internal("dialogs/CalendarIntroScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	public static String readCalendarTeachingScreen(int index) {
		FileHandle file = Gdx.files
				.internal("dialogs/CalendarTeachingScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	public static int getSizeOfCalendarTeaching() {
		FileHandle file = Gdx.files
				.internal("dialogs/CalendarTeachingScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLinesOfText();
	}

	public static String readCalendarTeachingImages(int index) {
		FileHandle file = Gdx.files
				.internal("CalendarPictures/ListOfPictures.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	public static int getSizeOfCalendarTeachingImages() {
		FileHandle file = Gdx.files
				.internal("CalendarPictures/ListOfPictures.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLinesOfText();
	}

	/*
	 * 
	 * 
	 * RESOURCES FILES
	 */
	public static ListOfQuestions readResourcesQuestions(){
		FileHandle file = Gdx.files.internal("dialogs/ResourcesQuestions.json");
		String text = file.readString();
		Json json = new Json();
		ListOfQuestions textLine = json.fromJson(ListOfQuestions.class, text);
		return textLine;
	}

	public static String readResourcesEndScreen(int index) {
		FileHandle file = Gdx.files.internal("dialogs/ResourcesEndScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	public static String readResourcesIntroScreen(int index) {
		FileHandle file = Gdx.files
				.internal("dialogs/ResourcesIntroScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	public static String readResourcesTeachingScreen(int index) {
		FileHandle file = Gdx.files
				.internal("dialogs/ResourcesTeachingScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}
	
	public static int getSizeOfResourcesTeachingImages() {
		FileHandle file = Gdx.files
				.internal("ResourcesPictures/ListOfPictures.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLinesOfText();
	}

	public static String readResourcesTeachingImages(int index) {
		FileHandle file = Gdx.files
				.internal("ResourcesPictures/ListOfPictures.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	public static int getSizeOfResourcesTeaching() {
		FileHandle file = Gdx.files
				.internal("dialogs/ResourcesTeachingScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLinesOfText();
	}

	/*
	 * 
	 * 
	 * MAIN SCREEN FILES
	 */
	public static String readMainScreen(int index) {
		FileHandle file = Gdx.files.internal("dialogs/MainScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	/*
	 * 
	 * 
	 * STUDENT STORAGE FILES
	 */
	/*
	 * 
	 * Here is code for creating some students, sending them to storage and
	 * reading them back from storage They are stored as student objects which
	 * is stored as a listofStudents object to make parsing easier
	 */
	public static void writeToStudentStorage(ListofStudents students) {
		FileHandle file = Gdx.files.local("StudentStorage.json");
		Json json = new Json();
		String studentsString = json.prettyPrint(students);
		file.writeString(studentsString, false);
		return;
	}

	public static void addStudentToStudentStorage(Student student) {
		ListofStudents students = readFromStudentStorage();
		students.addStudent(student);
		InteractWithFiles.writeToStudentStorage(students);
		return;
	}

	public static Student removeStudentFromStudentStorage(String username) {
		ListofStudents students = InteractWithFiles.readFromStudentStorage();
		if (students.containsStudent(username)) {
			Student s = students.removeStudent(username);
			InteractWithFiles.writeToStudentStorage(students);
			return s;
		} else {
			Student s = new Student();
			s.setUsername(username);
			return s;
		}
	}

	public static ListofStudents readFromStudentStorage() {
		FileHandle file = Gdx.files.local("StudentStorage.json");
		Json json = new Json();
		if (file.exists()) {
			String text = file.readString();
			ListofStudents students = json.fromJson(ListofStudents.class, text);
			return students;
		} else {
			ListofStudents students = new ListofStudents();
			String studentsString = json.prettyPrint(students);
			file.writeString(studentsString, false);
			return students;
		}
	}

	/*
	 * 
	 * 
	 * MINIGAME FILES
	 */
	public static String readMiniGameScreen(int index) {
		FileHandle file = Gdx.files.internal("dialogs/MiniGame.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	/*
	 * 
	 * 
	 * ONLINE FILES
	 */
	public static ListOfQuestions readOnlineQuestions(){
		FileHandle file = Gdx.files.internal("dialogs/OnlineQuestions.json");
		String text = file.readString();
		Json json = new Json();
		ListOfQuestions textLine = json.fromJson(ListOfQuestions.class, text);
		return textLine;
	}

	public static String readOnlineEndScreen(int index) {
		FileHandle file = Gdx.files.internal("dialogs/OnlineEndScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	public static String readOnlineIntroScreen(int index) {
		FileHandle file = Gdx.files.internal("dialogs/OnlineIntroScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	public static String readOnlineTeachingScreen(int index) {
		FileHandle file = Gdx.files
				.internal("dialogs/OnlineTeachingScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	public static int getSizeOfOnlineTeaching() {
		FileHandle file = Gdx.files
				.internal("dialogs/OnlineTeachingScreen.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLinesOfText();
	}

	public static String readOnlineTeachingImages(int index) {
		FileHandle file = Gdx.files
				.internal("OnlineResourcesPictures/ListOfPictures.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLineOfText(index);
	}

	public static int getSizeOfOnlineTeachingImages() {
		FileHandle file = Gdx.files
				.internal("OnlineResourcesPictures/ListOfPictures.json");
		String text = file.readString();
		Json json = new Json();
		ScreenText textLine = json.fromJson(ScreenText.class, text);
		return textLine.getLinesOfText();
	}


}
