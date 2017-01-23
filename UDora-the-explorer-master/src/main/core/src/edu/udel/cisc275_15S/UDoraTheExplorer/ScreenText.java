package edu.udel.cisc275_15S.UDoraTheExplorer;

import java.util.ArrayList;

public class ScreenText {
	private ArrayList<String> text;
	public ScreenText(ArrayList<String> text) {
		this.text = text;
	}
	public ScreenText() {
		this.text = new ArrayList<String>();
	}
	public ArrayList<String> getText() {
		return text;
	}
	public void setText(ArrayList<String> text) {
		this.text = text;
	}
	public String getLineOfText(int index){
		if (index<=text.size()){
			return text.get(index);
		}
		else{
			return "";
		}
	}
	public int getLinesOfText(){
		return text.size();
	}
}
