package edu.erau.SE300_WW;

import java.util.ArrayList;
import java.util.Date;

public class NewWarningTrigger {

	Boolean showTheWarning=false;
	public NewWarningTrigger(String course, Date assignmentDate){
		
		assignmentDate.setHours(0);
		assignmentDate.setMinutes(0);
		assignmentDate.setSeconds(0);
		
		int examSum=0;
		
		ArrayList <Assignment> allOfTheStudentsExamsThatConflict = new ArrayList<Assignment>();
		System.out.println(assignmentDate);
		//First get all of the students in your class...
		ArrayList <String> allOfTheStudents = new ArrayList<String>();
		allOfTheStudents = LoginGUI.databaseShared.courseStudents(course);
		System.out.println("How many students: "+allOfTheStudents.size());
		
		//Then get all of the exams for those students...
		
		
		for(int x=0;x<allOfTheStudents.size();x++){
			System.out.println("Outter loop "+x);
			for(int y=0;y<LoginGUI.databaseShared.studentExamList(allOfTheStudents.get(x)).size();y++){
				System.out.println("Inner loop "+y);
				System.out.println((LoginGUI.databaseShared.studentExamList(allOfTheStudents.get(x)).get(y).assignmentDate));
				if((LoginGUI.databaseShared.studentExamList(allOfTheStudents.get(x)).get(y).assignmentDate.equals(assignmentDate))){
					//allOfTheStudentsExamsThatConflict.add(LoginGUI.databaseShared.studentExamList(allOfTheStudents.get(x)));
					examSum=examSum+1;
				}
			}
			
			
			
		}
		
		
		
		
		
		System.out.println("Conflicts: "+examSum);
		
		//Then from these, get how many are on the same day as the date in question
	}
	
	public boolean doWeShowTheWarning(){
		return showTheWarning;
	}
}
