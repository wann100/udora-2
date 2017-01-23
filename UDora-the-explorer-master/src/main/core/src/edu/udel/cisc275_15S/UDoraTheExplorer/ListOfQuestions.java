package edu.udel.cisc275_15S.UDoraTheExplorer;

import java.util.ArrayList;

public class ListOfQuestions {
	private ArrayList<Question> questions;

	public ListOfQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	public ListOfQuestions(){
		this.questions = new ArrayList<Question>();
	}
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public Question getQuestionAtIndex(int index){
		return questions.get(index);
	}
}
