package edu.udel.cisc275_15S.UDoraTheExplorer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ListofStudents {
	private HashMap<String,Student> students;

	public ListofStudents(HashMap<String, Student> students) {
		this.students = students;
	}

	public ListofStudents() {
		this.students = new HashMap<String,Student>();
	}
	
	public void addStudent(Student s){
		students.put(s.getUsername(),s);
	}
	public boolean containsStudent(String username){
		return students.containsKey(username);
	}
	public Student removeStudent(String username){
		Student s = students.get(username);
		students.remove(username);
		return s;
	}
	public String toString(){
		String returnString = "";
		for(Entry<String, Student> entry : students.entrySet()){
			returnString+=entry.getKey() + ": " + entry.getValue().toString() + "\n";
		}
		return returnString;
	}

}
