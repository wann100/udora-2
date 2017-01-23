package edu.udel.cisc275_15S.UDoraTheExplorer;

import java.util.ArrayList;

public class Question {
	private String question;
	private String correctAnswer;
	private ArrayList<String> wrongAnswers;
	private String review;
	
	public Question() {
		this.question = "Question";
		this.correctAnswer = "Correct";
		this.wrongAnswers = new ArrayList<String>();
		wrongAnswers.add("Wrong");
		wrongAnswers.add("Wrong");
		wrongAnswers.add("Wrong");
		this.review = "Review";
	}

	public Question(String question, String correctAnswer,
			ArrayList<String> wrongAnswers, String review) {
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.wrongAnswers = wrongAnswers;
		this.review = review;
		
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public ArrayList<String> getWrongAnswers() {
		return wrongAnswers;
	}
	
	public String getWrongAnswerByIndex(int index){
		return wrongAnswers.get(index);
	}

	public void setWrongAnswers(ArrayList<String> wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}
	public int getNumberOfWrongAnswers(){
		return wrongAnswers.size();
	}
	
	

}
