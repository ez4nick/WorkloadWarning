package src.edu.erau.SE300_WW;

import java.io.File;
import java.util.ArrayList;

public class DatabaseTester {
	
	public static void main(String [] args){
		File file = new File ("C:/Users/Elisa/Documents/CanvasDatabase.xlsx");
		Database data = new Database(file);
		ArrayList<String> courses = new ArrayList<String> (0);
		courses = data.searchTCourses("Professor A");
		for (String temp: courses){
			System.out.println(temp);
		}
		ArrayList <Assignment> teacher = new ArrayList <Assignment> (0);
		teacher = data.searchTAssignment("Professor B");
		for (Assignment temp: teacher){
			System.out.println(temp.assignmentName+temp.courseName);
		}
		ArrayList<String> course2 = new ArrayList <String> (0);
		course2 = data.searchSCourses("Alley");
		for (String temp: course2){
			System.out.println(temp);
		}
		ArrayList<Assignment> student = new ArrayList <Assignment> (0);
		student = data.searchSAssignment("Elisa");
		for (Assignment temp: student){
			System.out.println(temp.assignmentName + temp.courseName);
		}
		ArrayList<Messages> message = new ArrayList <Messages> (0);
		message = data.getMessages("Alley");
		for (Messages temp: message){
			System.out.println(temp.assignment+temp.status);
		}
	}
	
	
}