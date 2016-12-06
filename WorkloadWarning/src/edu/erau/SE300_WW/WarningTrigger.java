package edu.erau.SE300_WW;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;

import edu.erau.SE300_WW.LoginGUI;
import javafx.application.Application;
import javafx.stage.Stage; 

/**
 * The Warning Trigger class determines when
 * to display the Professor Warning based on the
 * amount of exams for any student enrolled in
 * the logged-in professor's class on a selected  
 * date.
 * @author diprea
 *
 */

public class WarningTrigger {
	
	ArrayList <String> courses = new ArrayList <String> (0);
	ArrayList <String> courseStudents = new ArrayList <String> (0);
	String courseName = new String();
	ArrayList <String> allCourses = new ArrayList <String> (0);
	ArrayList <String> searchSCourses = new ArrayList <String> (0);
	ArrayList <Assignment> getAllExamsForACourse = new ArrayList<Assignment>();
	
	Boolean showTheWarning=false; //True or false, show the warning or not
	
	public WarningTrigger() {
	
			//only triggers in teacher view
			if (LoginGUI.userType.equals("Teacher")); {

				//determine course
				courseName = Assignment.courseName;

				//determines students in course
				courses = LoginGUI.databaseShared.courseStudents(courseName);

				//determines all courses the above students are enrolled in
				//reads each name in student array
				for (int i = 0; i < courseStudents.size(); i++) {
					String studentName = courseStudents.get(i);
					int classSize = i;
					allCourses = LoginGUI.databaseShared.courseStudents(studentName);
					ArrayDeque<String> allCourses = new ArrayDeque<>();
					allCourses.addAll(allCourses);
					System.out.println("Loop 3");
				}
				//determines percentage of students in selected course with exams already
				//determines exam dates for every course
				ArrayList<String> courseCount = new ArrayList<String>();
				String[] courseCode = {"SE300", "AS322", "CS225", "CEC220"};
				//ArrayList <Assignment> allExams = LoginGUI.databaseShared.getAllExamsForACourse(courseCode);
				
				
				ArrayList<Assignment> exams = new ArrayList <Assignment> ();
				int classSize=5;
				int counter = 0;
				int percentage = 0;
				for (int code = 0; code < 4; code++) {
					String lastItem = courseCode[code];
					System.out.println("Last item top:" +lastItem);
					for(int i1 = 0; i1 < courseCode.length; i1++){
						while (lastItem != courseName) {	//ignore course count for selected course
							exams = LoginGUI.databaseShared.getAllExamsForACourse(courseCode[i1]);
							if(courseCode[i1].equals(lastItem)){
								counter++;
								System.out.println("Course name:" +courseName);
								System.out.println("Last item:" +lastItem);
								System.out.println("Loop 1");
							}
							else {
								courseCount.add(lastItem);
								counter = 1;
							}
							lastItem = courseCode[i1];
							percentage = counter/classSize;
						}
					}
				}

				//compares against date selected 
				Date assignmentDate = new Date(0);
				Calendar dateSelected = Assignment.assignmentDate;
				for (int i2 = 0; i2 < ((CharSequence) exams).length(); i2++) {
					Object examDate = exams.get(i2);
					System.out.println("Loop 2");
					if (dateSelected == examDate && percentage >= 50) {
						//trigger warning GUI
						showTheWarning =true;
					}
				}
			}
	}
	
	public boolean doWeShowTheWarning(){
		return showTheWarning;
	}
}



