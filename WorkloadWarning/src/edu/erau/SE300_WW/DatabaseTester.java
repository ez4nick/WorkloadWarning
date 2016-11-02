package edu.erau.SE300_WW;

import java.io.File;
import java.util.ArrayList;

public class DatabaseTester {
	
	public static void main(String [] args){
		File file = new File ("C:/Users/Elisa/Documents/CanvasDatabase.xlsx");
		Database data = new Database(file);
		
		System.out.println("Professor A courses");
		ArrayList<String> courses = new ArrayList<String> (0);
		courses = data.searchTCourses("Professor A");
		for (String temp: courses){
			System.out.println(temp);
		}
		
		System.out.println("Professor B assignments");
		ArrayList <Assignment> teacher = new ArrayList <Assignment> (0);
		teacher = data.searchTAssignment("Professor B");
		for (Assignment temp: teacher){
			System.out.println(temp.assignmentName+temp.courseName);
		}
		
		System.out.println("Alley courses");
		ArrayList<String> course2 = new ArrayList <String> (0);
		course2 = data.searchSCourses("Alley");
		for (String temp: course2){
			System.out.println(temp);
		}
		
		System.out.println("Elisa assignments");
		ArrayList<Assignment> student = new ArrayList <Assignment> (0);
		student = data.searchSAssignment("Elisa");
		for (Assignment temp: student){
			System.out.println(temp.assignmentName + temp.courseName);
		}
		
		System.out.println("Alley messages");
		ArrayList<Messages> message = new ArrayList <Messages> (0);
		message = data.getMessages("Alley");
		for (Messages temp: message){
			System.out.println(temp.assignment+temp.status);
		}
	}
	
	
}