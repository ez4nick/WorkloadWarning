package edu.erau.SE300_WW;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/** The purpose of this class is to determine when to show the warning to a professor
 * as well as creates a list of other dates when students have assignments to be shown
 * on the exam list gui.
 * @author Nicholas Krawczyk
 *
 */
public class NewWarningTrigger {

	Boolean showTheWarning=false;
	public static ArrayList <String> datesOfTheConflicts;
	public NewWarningTrigger(String course, Date assignmentDate){
		
		assignmentDate.setHours(0);
		assignmentDate.setMinutes(0);
		assignmentDate.setSeconds(0);
		
		int examSum=0;
		datesOfTheConflicts = new ArrayList<String>();
		ArrayList <Assignment> allOfTheStudentsExamsThatConflict = new ArrayList<Assignment>();
		System.out.println("Unformatted date: "+assignmentDate);
		//First get all of the students in your class...
		ArrayList <String> allOfTheStudents = new ArrayList<String>();
		allOfTheStudents = LoginGUI.databaseShared.courseStudents(course);
		System.out.println("How many students: "+allOfTheStudents.size());
		
		//Then get all of the exams for those students...
		for(int x=0;x<allOfTheStudents.size();x++){
			System.out.println("Outter loop "+x);
			for(int y=0;y<LoginGUI.databaseShared.studentExamList(allOfTheStudents.get(x)).size();y++){
				System.out.println("Inner loop "+y);
				assignmentDate.setHours(0);
				assignmentDate.setMinutes(0);
				assignmentDate.setSeconds(0);
				
				
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				
				
			
				if(assignmentDate.getDate()==(LoginGUI.databaseShared.studentExamList(allOfTheStudents.get(x)).get(y).getTheAssignmentDateObject().getDate())
						&& assignmentDate.getMonth()==LoginGUI.databaseShared.studentExamList(allOfTheStudents.get(x)).get(y).getTheAssignmentDateObject().getMonth()){
					
					
					System.out.println("Conflict");
					examSum=examSum+1;
				}
				else{
					if(datesOfTheConflicts.contains(LoginGUI.databaseShared.studentExamList(allOfTheStudents.get(x)).get(y).getTheAssignmentDateObject())){
						System.out.println("Not adding to list");
					}
					else{
						System.out.println("Adding to list");
						datesOfTheConflicts.add(sdf.format(LoginGUI.databaseShared.studentExamList(allOfTheStudents.get(x)).get(y).getTheAssignmentDateObject()));
					}
				}
				
				
				
			}
			
		}
		
		if(allOfTheStudents.size()>0 && examSum/allOfTheStudents.size()>2){
			//Well trigger the warning..........
			showTheWarning=true;
		}
		
	}
	
	
	public boolean doWeShowTheWarning(){
		return showTheWarning;
	}
}
