package edu.udel.cisc275_15S.UDoraTheExplorer;

public class CurrentPlayer {
	private static String username;
	private static Student student;
	public static String getUsername(){
		return username;
	}
	public static void setUsername(String u){
		username = u;
	}
	public static Student getStudent(){
		return student;
	}
	public static void setStudent(Student s){
		student = s;
	}
}
