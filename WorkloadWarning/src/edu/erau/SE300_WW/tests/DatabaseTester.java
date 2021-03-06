package edu.erau.SE300_WW.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.erau.SE300_WW.Assignment;
import edu.erau.SE300_WW.Database;

public class DatabaseTester {
	
	public static void main(String [] args){
		File file = new File ("C:/Users/Elisa/Documents/CanvasDatabase.xlsx");
		Database data = new Database(file);
		
		/*
		System.out.println("Professor B courses");
		ArrayList<String> courses = new ArrayList<String> (0);
		courses = data.searchTCourses("Professor B");
		for (String temp: courses){
			System.out.print(temp + " ");
		}
		System.out.println("");
		*/
		
		/*
		System.out.println("Professor B assignments");
		ArrayList <Assignment> teacher = new ArrayList <Assignment> (0);
		teacher = data.searchTAssignment("Professor B");
		for (Assignment temp: teacher){
			System.out.print(temp.assignmentName+"-"+temp.courseName+"-"+temp.assignmentDate+" ");
		}
		System.out.println("");
		*/
		
		/*
		System.out.println("Alley courses");
		ArrayList<String> course2 = new ArrayList <String> (0);
		course2 = data.searchSCourses("Alley");
		for (String temp: course2){
			System.out.print(temp + " ");
		}
		System.out.println("");
		*/
		
		/*
		System.out.println("Elisa assignments");
		ArrayList<Assignment> student = new ArrayList <Assignment> (0);
		student = data.searchSAssignment("Elisa");
		for (Assignment temp: student){
			System.out.print(temp.assignmentName + "-" + temp.courseName+" ");
		}
		System.out.println("");
		*/
		
		/*
		System.out.println("Alley messages");
		ArrayList<Messages> message = new ArrayList <Messages> (0);
		message = data.getMessages("Alley");
		for (Messages temp: message){
			System.out.print(temp.assignment+"-"+temp.status+" ");
		}
		System.out.println("");
		*/
		
		/*
		System.out.println("Students in SE300");
		ArrayList<String> students = new ArrayList<String>(0);
		students = data.courseStudents("SE300");
		for (String temp: students){
			System.out.print(temp + " ");
		}
		System.out.println("");
		*/
		
		/*
		System.out.println("Student User Test");
		int user = data.isUser("Patrick");
		System.out.println("Patrick " + user);
		user = data.isUser("Nick");
		System.out.println("Nick " +user);
		user = data.isUser("Professor B");
		System.out.println("Professor B " +user);
		*/
		
		/*
		System.out.println("addAssignment");
		Date date = new Date(); 
		data.addAssignment(new Assignment("test5","Exam", date, "SE300"));
		
		
		/*
		System.out.println("searchTcourses test");
		ArrayList <String> something = data.searchTCourses("Professor B");
		for (String temp: something){
			System.out.println(temp);
		}
		*/
		
		/*
		System.out.println("searchScourses teacher test");
		ArrayList<String> another = data.searchSCourses("Professor B");
		for (String temp: another){
			System.out.println(temp);
		}
		*/
		
		/*
		ArrayList <Messages> mal = data.getAllMessages();
		for (Messages m: mal){
			System.out.println(m.assignment+"/"+m.course);
		}
		
		
		Date due = new Date();
		Messages m1 = new Messages("Assignment 5", "hw", due, "AS322", "Professor B", "Nick", "requested");
		Messages m2 = new Messages("hw 3", "hw", due, "CS225", "Professor C", "Elisa", "requested");
		
		data.addMessage(m1);
		data.addMessage(m2);
		System.out.println("Message Added");
		
		mal = data.getAllMessages();
		for (Messages m: mal){
			System.out.println(m.assignment+"/"+m.course+"/"+m.recipient+"/"+m.sender+"/"+m.status);
		}
		
		//data.changeMessageStatus(temp, false);
		
		
		data.deleteMessage(m1);
		System.out.println("Message Deleted");
		
		
		mal = data.getAllMessages();
		for (Messages m: mal){
			System.out.println(m.assignment+"/"+m.course+"/"+m.recipient+"/"+m.sender+"/"+m.status);
		} */
		
		
		ArrayList<Assignment> aal = data.getAllAssignments();
		System.out.println("Previous Database");
		for (Assignment a: aal){
			System.out.println(a.assignmentName+"/"+a.courseName);
		}
		
		Date due = new Date();
		Assignment a1 = new Assignment("Test 3", "Exam", due, "AS322");
		Assignment a2 = new Assignment("hw 3", "hw", due, "CS225");
		
		System.out.println("Adding Assignment");
		data.addAssignment(a1);
		data.addAssignment(a2);
		
		aal = data.getAllAssignments();
		System.out.println("After Add, if added");
		for (Assignment a: aal){
			System.out.println(a.assignmentName+"/"+a.courseName);
		}
	
		System.out.println("Deleting Assignment");
		data.deleteAssignment(a1);
		
		aal = data.getAllAssignments();
		System.out.println("After Delete, if deleted");
		for (Assignment a: aal){
			System.out.println(a.assignmentName+"/"+a.courseName);
		}
	}
	
	
}