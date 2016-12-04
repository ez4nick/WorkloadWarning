package edu.erau.SE300_WW;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//workload warning project, SE300 Elisa Hawley

/**
 * Database Class for a Canvas-like assignment calendar
 * the Database object will read a properly formatted excel file,
 * storing the information into the object for reference,
 * and manipulating the excel file along with the saved data
 * @author Elisa
 *
 */
public class Database {
	
	private ArrayList <Assignment> assignmentAL;
	private String [][] studentArray;
	private ArrayList <Messages> messageAL;
	private File theExcel;
	
	/**
	 * Database ({@link File} excelFile) saves all data from the properly formatted excel file
	 * @param excelFile: {@link File} for the excel file to be read
	 * @author Elisa
	 */
	public Database (File excelFile) {
		//populate attributes with excel database
		
		try{
			theExcel = excelFile;
			FileInputStream file = new FileInputStream(theExcel);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			String [] col = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P",
							"Q","R","S","T","U","V","W","X","Y","Z","AA","AB","AC","AD","AE",
							"AF","AG","AH","AI","AJ","AK","AL","AM","AN","AO"}; 
			
			//students by course
			Sheet sheet = workbook.getSheetAt(0);
			//size of schedule
			CellReference ref = new CellReference("I1");
			Cell loc = sheet.getRow(ref.getRow()).getCell(ref.getCol());
			int rownum = (int)loc.getNumericCellValue();
			ref = new CellReference("G1");
			loc = sheet.getRow(ref.getRow()).getCell(ref.getCol());
			int colnum = (int)loc.getNumericCellValue();
			studentArray = new String [rownum][colnum];
			//System.out.println(rownum + " " + colnum);
			//cell by cell population of studentArray
			int j = 0, k = 0;
			String data;
			boolean loop = true;
			while (loop == true){
				//System.out.println(j + " " + k);
				ref = new CellReference (col[k] + (j+2));
				try {
					loc = sheet.getRow(ref.getRow()).getCell(ref.getCol());
					data = loc.getStringCellValue();
					data = data.trim();
				} catch (NullPointerException exception){
					data = "";
				}
				
				if (data.isEmpty()){
					if(k!=0){
						j++;
						k=0;
					} else {
						loop = false;
					}
				} else {
					//System.out.println(data + " " + j + " " + k );
					studentArray[j][k] = data;
					k++;
					if (k>= colnum){
						k = 0;
						j++;
					}
				}
				if (j >= rownum){
					loop = false;
				}
			}
			
			//assignments
			assignmentAL = new ArrayList<Assignment> (0);
			sheet = workbook.getSheetAt(1);
			int i = 2;
			data = "";
			loop = true;
			CellReference a1, t2, d3, c4;
			Cell assignmentLoc, typeLoc, dateLoc, courseLoc;
			String name = "", type = "", course = "";
			Date date = new Date();
			while (loop == true){
				try {
					a1 = new CellReference ("A"+i);
					assignmentLoc = sheet.getRow(a1.getRow()).getCell(a1.getCol());
					t2 = new CellReference ("B" +i);
					typeLoc = sheet.getRow(t2.getRow()).getCell(t2.getCol());
					d3 = new CellReference ("C" +i);
					dateLoc = sheet.getRow(d3.getRow()).getCell(d3.getCol());
					c4 = new CellReference ("D" +i);
					courseLoc = sheet.getRow(c4.getRow()).getCell(c4.getCol());

					data = assignmentLoc.getStringCellValue();
					name = data.trim();
					data = typeLoc.getStringCellValue();
					type = data.trim();
					date = dateLoc.getDateCellValue();
					data = courseLoc.getStringCellValue();
					course = data.trim();
				} catch (NullPointerException exception){
					data = "";
				}
				if (data.isEmpty()){
					loop = false;
				} else {
					assignmentAL.add(new Assignment(name, type, date, course));
					//System.out.println("New assignment " + name);
					i++;
				}
			}
			
			//messages
			messageAL = new ArrayList<Messages> (0);
			sheet = workbook.getSheetAt(2);
			i = 2;
			data = "";
			loop = true;
			CellReference t5, f6, s7;
			Cell toLoc, fromLoc, statusLoc;
			String to = "", from = "", status = "";
			while (loop == true){
				try {
					a1 = new CellReference ("A"+i);
					assignmentLoc = sheet.getRow(a1.getRow()).getCell(a1.getCol());
					t2 = new CellReference ("B" +i);
					typeLoc = sheet.getRow(t2.getRow()).getCell(t2.getCol());
					d3 = new CellReference ("C" +i);
					dateLoc = sheet.getRow(d3.getRow()).getCell(d3.getCol());
					c4 = new CellReference ("D" +i);
					courseLoc = sheet.getRow(c4.getRow()).getCell(c4.getCol());
					t5 = new CellReference("E" +i);
					toLoc = sheet.getRow(t5.getRow()).getCell(t5.getCol());
					f6 = new CellReference("F" +i);
					fromLoc = sheet.getRow(f6.getRow()).getCell(f6.getCol());
					s7 = new CellReference("G" +i);
					statusLoc = sheet.getRow(s7.getRow()).getCell(s7.getCol());

					data = assignmentLoc.getStringCellValue();
					name = data.trim();
					data = typeLoc.getStringCellValue();
					type = data.trim();
					date = dateLoc.getDateCellValue();
					data = courseLoc.getStringCellValue();
					course = data.trim();
					data = toLoc.getStringCellValue();
					to = data.trim();
					data = fromLoc.getStringCellValue();
					from = data.trim();
					data = statusLoc.getStringCellValue();
					status = data.trim();
				} catch (NullPointerException exception){
					data = "";
				}
				if (data.isEmpty()){
					loop = false;
				} else {
					messageAL.add(new Messages(name, type, date, course, to, from, status));
					//System.out.println("New Message");
					i++;
				}
			}
				
			workbook.close();
			file.close();
		} catch (IOException exception){
			System.out.println("Database Population Error");
			System.out.println(exception);
		}
	}
	
	
	/**
	 * getAllAssignments() allows for reading the assignments stored in the database
	 * @return an {@link ArrayList} of {@link Assignment} objects
	 * @author Elisa
	 */
	public ArrayList <Assignment> getAllAssignments () {
		return assignmentAL;
	}
	
	/**
	 * getAllMessages() allows for reading the messages stored in the database
	 * @return an {@link ArrayList} of {@link Messages} objects
	 * @author Elisa
	 */
	public ArrayList<Messages> getAllMessages(){
		return messageAL;
	}
	
	/**getAllExamsForACourse({@link String} course) creates a list of all of the exams in the course specified by the user.
	 * @param course {@link String} of the course you wish to get a list of all of the assignments for
	 * @return An {@link ArrayList} of {@link Assignment} objects containing all of the exams for the course you entered
	 * @author Nicholas Krawczyk 
	 */
	public ArrayList <Assignment> getAllExamsForACourse(String course){
		ArrayList <Assignment> allExamsInYourCourse = new ArrayList<Assignment>(); //Create an array list to hold all of the exams
		
		for(int x=0; x<getAllAssignments().size();x++){
			if(getAllAssignments().get(x).courseName.equals(course) && getAllAssignments().get(x).assignmentType.equals("Exam")){
				//If course name matches what was entered and the assignment type is Exam then it is a match, add it to the list
				allExamsInYourCourse.add(getAllAssignments().get(x));
				
			}
		}
		
		
		return allExamsInYourCourse;
		
	}
	
	
	/**isAssignmentAlreadyInDatabase({@link Assignment} assignmentToVerify) checks to see if the given assignment already exists in the database.
	 * @param assignmentToVerify {@link Assignment} that you wish to check if it already exists
	 * @return Returns true if the assignment is already in the database, false if it is not
	 * @author Nicholas Krawczyk
	 */
	public boolean isAssignmentAlreadyInDatabase(Assignment assignmentToVerify){
		boolean isInDatabase=false;
		
		ArrayList<Assignment> allAssignments = getAllAssignments();
		
		for(int x=0;x<allAssignments.size();x++){
			if(assignmentToVerify.assignmentDate.getTime().equals(allAssignments.get(x).assignmentDate.getTime()) &&
			   assignmentToVerify.assignmentType.equals(allAssignments.get(x).assignmentType) &&
			   assignmentToVerify.assignmentName.equals(allAssignments.get(x).assignmentName) &&
			   assignmentToVerify.courseName.equals(allAssignments.get(x).courseName)){
				//If all of this is true then it already is in the database!!!
				isInDatabase=true;
				break;
			}
		}
		
		return isInDatabase;
	}
	
	
	/** getTeacherOfCourse({@link String} course) returns the name of the teacher who is teaching a specified 
	 * course. This method is intended to be used when sending a message to a professor by determining who
	 * the recipient of a message would be. 
	 * @param course {@link String} the course you want to know the professor for
	 * @return Returns a {@link String} of the name of the teacher
	 * @author Nicholas Krawczyk
	 */
	public String getTeacherOfCourse(String course){
		String[][] allStudents = getAllStudents();
		String professor="";
		for(int x=0; x<allStudents.length; x++){
			if(searchTCourses(allStudents[x][0]).contains(course)){
				professor=allStudents[x][0];
			}
		}
		return professor;
	}
	
	
	/**
	 * getAllStudents() allows for reading the enrollment of all courses stored in the database
	 * @return 2D array of {@link String} formatted [i][0] is the Teacher, [i][1] is the course, and [i][n>1] is a student enrolled in that course
	 * @author Elisa 
	 */
	public String [][] getAllStudents () {
		return studentArray;
	}
	
	
	/**
	 * getMessages({@link String} recipient) allows for reading messages to the given receiver
	 * ignores letter case
	 * @param recipient: {@link String} containing the name of the receiver
	 * @return an {@link ArrayList} of {@link Messages} going to the given recipient
	 * @author Elisa
	 */
	public ArrayList<Messages> getMessages (String recipient) {
		ArrayList <Messages> checkMail = new ArrayList <Messages> (0);
		for (Messages temp: messageAL){
			if (temp.recipient.equalsIgnoreCase(recipient)){
				checkMail.add(temp);
			}
		}
		return checkMail;
	}

	/**
	 * courseStudents({@link String} course) generates an {@link Arraylist} of {@link String} containing the Students of a Course
	 * ignores letter case
	 * @param course: {@link String} containing the course name
	 * @return {@link ArrayList} of {@link String} containing the names of the Students in the course
	 * @author Elisa
	 */
	public ArrayList <String> courseStudents (String course) {
		ArrayList <String> students = new ArrayList <String> (0);
		int j = 0, k = 2;
		for (j = 0; j<studentArray.length; j++){
			try {
				if (studentArray[j][1].equalsIgnoreCase(course)){
					for(k = 2; k<studentArray[j].length; k++){
						if (studentArray[j][k] != null ){
							students.add(studentArray[j][k]);
						}
					}
				}
			} catch (NullPointerException exception) {
				j = studentArray.length;
			}
		}
		return students;
	}
	
	/**
	 * searchTCourses ({@link String} teacher) allows for a course list to be generated for a given teacher
	 * ignores letter case
	 * @param teacher: {@link string} of teacher's name
	 * @return an {@link ArrayList} of {@link String} containing the courses the teacher teaches 
	 * @author Elisa
	 */
	public ArrayList<String> searchTCourses (String teacher){
		ArrayList<String> courseList = new ArrayList<String>(0);
		int i = 0;
		for (i = 0; i<studentArray.length; i++){
			try {
				if (studentArray[i][0].equalsIgnoreCase(teacher)){
					courseList.add(studentArray[i][1]);
				}
			} catch (NullPointerException exception) {
				
			}
		}
		return courseList;
	}
	
	/**
	 * searchSCourses({@link String} student) allows for a course list to be generated for a given student
	 * ignores letter case
	 * @param student: {@link string} of student's first name
	 * @return an {@link ArrayList} of {@link String} containing the courses the student is enrolled in
	 * @author Elisa
	 */
	public ArrayList <String> searchSCourses (String student) {
		ArrayList <String> courses = new ArrayList <String> (0);
		int j = 0;
		int k = 0;
		for (j = 0; j < studentArray.length; j++){
			try {
				for (k = 2; k < studentArray[j].length; k++){
					if (studentArray[j][k].equalsIgnoreCase(student)){
						courses.add(studentArray[j][1]);
					}
				}
			} catch (NullPointerException exception) {
				
			}
		}
		return courses;
	}
	
	/**
	 * isUser ({@link String} name) tests to see if the user name provided is a user, and returns no, student, or teacher in int value
	 * @param user: {@link string} containing user name
	 * @return {@link int} value: 0 if not a user, 1 if student, 2 if teacher
	 * @author Elisa
	 */
	public int isUser (String name){
		int user = 0;
		ArrayList <String> courses = searchSCourses(name);
		if (courses.isEmpty()){
			courses = searchTCourses(name);
			if (courses.isEmpty()){
				
			} else {
				user = 2;
			}
		} else {
			user = 1;
		}
		return user;
	}
	
	/**
	 * searchSAssignment({@link String} student) allows for an assignments list to be generated for a given student
	 * ignores letter case
	 * @param student: {@link string} of student's first name
	 * @return an {@link ArrayList} of {@link Assignment} containing the assignments for the courses the student is enrolled in
	 * @author Elisa
	 */
	public ArrayList<Assignment> searchSAssignment (String student){
		ArrayList<Assignment> work = new ArrayList<Assignment>(0);
		ArrayList<String> courses = searchSCourses(student);
		for (Assignment temp: assignmentAL){
			for (String courseTemp: courses){
				if (temp.courseName.equalsIgnoreCase(courseTemp)){
					work.add(temp);
				}
			}
		}
		return work;
	}
	
	/**
	 * searchTAssignment({@link String} teacher) allows for an assignments list to be generated for a given Teacher
	 * ignores letter case
	 * @param Teacher: {@link string} of Teacher's name
	 * @return an {@link ArrayList} of {@link Assignment} containing the assignments for the courses the Teacher teaches
	 * @author Elisa
	 */
	public ArrayList<Assignment> searchTAssignment (String teacher){
		ArrayList<Assignment> work = new ArrayList<Assignment>(0);
		ArrayList<String> courses = searchTCourses(teacher);
		for (Assignment temp: assignmentAL){
			for (String courseTemp: courses){
				if (temp.courseName.equalsIgnoreCase(courseTemp)){
					work.add(temp);
				}
			}
		}
		return work;
	}
	//new method: sum exams by date
	public int getNumberOfExams(String teacher,String datein){
		int m =0;
		for(int x=0; x<searchTAssignment(teacher).size();x++){
			if(searchTAssignment(teacher).get(x).assignmentType.equals("Exam")){
				
				Calendar date = searchTAssignment(teacher).get(x).assignmentDate;
				date.add(Calendar.DATE, 1);
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				String formatted = format1.format(date.getTime());
				
				if (formatted == datein){
					m++;
					
				}
			}
		}
		return m;
		}
	/**
	 * addAssignmet({@link Assignment} work) will add the given assignment 
	 * to both the assignment array for further use and to the excel file for record. 
	 * @param work: {@link Assignment} to be stored
	 * @author Elisa, Help from Eric Nicolas at http://stackoverflow.com/questions/9431089/how-to-correctly-format-a-date-cell-and-populate-content-using-apache-poi-3-7
	 */
	public void addAssignment (Assignment work) {
		
		
		//add assignment to assignment array and excel database
		assignmentAL.add(work);
		try{
			FileInputStream file = new FileInputStream (theExcel);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			file.close();

			//manipulation
			Sheet sheet = workbook.getSheetAt(1);
			CellReference ref = new CellReference ("A1");
			Cell loc = sheet.getRow(ref.getRow()).getCell(ref.getCol());
			Row row = sheet.getRow(ref.getRow());
			boolean loop = true;
			int i = 2;
			while (loop == true){
				row = sheet.getRow(i);
				if (row == null){
					//System.out.println("NULL found");
					row = sheet.createRow(i);
					loop = false;
				} else {
					loc = sheet.getRow(i).getCell(0);
					if (loc == null || loc.getStringCellValue().isEmpty()){
						loop = false;
					} else {
						i++;
					}
				}
			}

			loc = row.createCell(0);
			loc.setCellValue(work.assignmentName);
			loc = row.createCell(1);
			loc.setCellValue(work.assignmentType);
			loc = row.createCell(2);
			
			//provided by Eric Nicolas
			//at http://stackoverflow.com/questions/9431089/how-to-correctly-format-a-date-cell-and-populate-content-using-apache-poi-3-7
			//note: his reference sheet is wrong but can be used as a guide to find the correct number
			XSSFCellStyle style = workbook.createCellStyle();
			style.setDataFormat((short)14);
			
			loc.setCellValue(work.assignmentDate);
			loc.setCellStyle(style);
			
			//System.out.println(work.assignmentDate + " " + calendar.getTime());
			
			loc = row.createCell(3);
			loc.setCellValue(work.courseName);
			
			FileOutputStream out = new FileOutputStream (theExcel);
			workbook.write(out);
			workbook.close();
			out.close();
		} catch (IOException exception){
			System.out.println("Database addAssignment Error");
			
			
		}
		
	}
	
	/**
	 * deleteAssignment({@link Assignment} work) removes the given assignment
	 * from the assignment array and from the excel file record
	 * @param work: {@link Assignment} the assignment to be removed
	 * @author Elisa
	 */
	public void deleteAssignment (Assignment work) {
		//delete assignment from assignment array and excel database
		int it = -1, i = 0;
		Assignment temp;
		for (i = 0; i<assignmentAL.size(); i++){
			temp = assignmentAL.get(i);
			if (temp.assignmentName.equalsIgnoreCase(work.assignmentName)){
				if (temp.assignmentType.equalsIgnoreCase(work.assignmentType)){
					if (temp.assignmentDate.equals(work.assignmentDate)){
						if (temp.courseName.equalsIgnoreCase(work.courseName)){
							it = i;
						}
					}
				}
			}
		}
		
		if (it == -1){
			System.out.println("Assignment to delete not found");
		} else {
			assignmentAL.remove(it);
			
			try{
				FileInputStream file = new FileInputStream (theExcel);
				XSSFWorkbook workbook = new XSSFWorkbook(file);
				file.close();
	
				//manipulation
				Sheet sheet = workbook.getSheetAt(1);

				Cell loc = sheet.getRow(0).getCell(0);
				Row row = sheet.getRow(0);

				Cell assignment = sheet.getRow(0).getCell(0);
				Cell type = sheet.getRow(0).getCell(0);
				Cell date = sheet.getRow(0).getCell(0);
				Cell course = sheet.getRow(0).getCell(0);
				
				boolean loop = true;
				i = 2;
				Date due = work.assignmentDate.getTime();
				while (loop == true){
					row = sheet.getRow(i);
					if (row == null){
						//System.out.println("NULL found");
						loop = false;
					} else {
						loc = sheet.getRow(i).getCell(0);
						if (loc == null || loc.getStringCellValue().isEmpty()){
							loop = false;
						} else {
							assignment = sheet.getRow(i).getCell(0);
							type = sheet.getRow(i).getCell(1);
							date = sheet.getRow(i).getCell(2);
							course = sheet.getRow(i).getCell(3);
			
							if (work.courseName.equalsIgnoreCase(course.getStringCellValue())
								&& due.equals(date.getDateCellValue())
								&& work.assignmentType.equalsIgnoreCase(type.getStringCellValue())
								&& work.assignmentName.equalsIgnoreCase(assignment.getStringCellValue())){
								System.out.println("Assignment found");
								
								//i= assignment to overwrite
								loop = false;
						
							}else {
								i++;
							}
						}
					}
				}
				
				//overwrite
				loop = true;
				Cell assignment2, type2, date2, course2;
				while (loop == true){
					row = sheet.getRow(i);
					if (row == null){ // row i is empty
						//System.out.println("NULL found");
						loop = false;
					} else { // row i is empty
						loc = sheet.getRow(i).getCell(0);
						if (loc == null || loc.getStringCellValue().isEmpty()){
							loop = false;
							
						} else { // row to move to i is empty
							row = sheet.getRow(i+1);
							if (row == null){
								sheet.removeRow(sheet.getRow(i));
								loop = false;
							} else { //row to move to i is empty
								loc = sheet.getRow(i+1).getCell(0);
								if (loc == null || loc.getStringCellValue().isEmpty()){
									sheet.removeRow(sheet.getRow(i));
									loop = false;
									
								} else { //move i+1 to i
														
									assignment2 = sheet.getRow(i+1).getCell(0);
									type2 = sheet.getRow(i+1).getCell(1);
									date2 = sheet.getRow(i+1).getCell(2);
									course2 = sheet.getRow(i+1).getCell(3);
									 
									assignment.setCellValue(assignment2.getStringCellValue());
									type.setCellValue(type2.getStringCellValue());
									date.setCellValue(date2.getDateCellValue());
									course.setCellValue(course2.getStringCellValue());
									
									i++;
									
									assignment = assignment2;
									type = type2;
									date = date2;
									course = course2;
					
								}
							}
						}
					}
				}
				
				FileOutputStream out = new FileOutputStream (theExcel);
				workbook.write(out);
				workbook.close();
				out.close();
			} catch (IOException exception){
				System.out.println("Database deleteMessage Error");
				
				
			}
		
		}
	}
	
	/**
	 * studentExamList creates a sorted list of a students exams by date
	 * 
	 * @param  the UserName of student's first name
	 * @return an {@link ArrayList} of {@link Assignment} 
	 * @author Thomas Ellis
	 */
	public ArrayList<Assignment> studentExamList(String userName){
		ArrayList<String> studentClassList = searchSCourses(userName);
		ArrayList<Assignment> assignList = getAllAssignments();
		ArrayList<Assignment> studentAssignList = new ArrayList<Assignment>();
		
		for(int count=0; count<studentClassList.size();count++){
			for(int countA = 0; countA<assignList.size();countA++){
				if(assignList.get(countA).courseName.equals(studentClassList.get(count)) && assignList.get(countA).assignmentType.equalsIgnoreCase("exam")){
					Assignment temp = assignList.get(countA);
					studentAssignList.add(temp);
				}
			}
		}
		for(int count = 0; count<studentAssignList.size();count++){
			if(count+1>studentAssignList.size()){
				break;
			}
			else if(studentAssignList.get(count).assignmentDate.after(studentAssignList.get(count+1).assignmentDate)){
				Assignment temp = studentAssignList.get(count+1);
				studentAssignList.remove(count+1);
				studentAssignList.add(count, temp);
				
			}
		}
		return studentAssignList;
	}
	
	/**
	 * addMessage({@link Messages} message) adds the given message
	 * to both the message array for further use and to the excel file for record.
	 * @param message: {@link Messages} to be stored
	 * @author Elisa
	 */
	public void addMessage (Messages message) {
		//add message to messageArray and excel database
		messageAL.add(message);
		try{
			FileInputStream file = new FileInputStream (theExcel);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			file.close();

			//manipulation
			Sheet sheet = workbook.getSheetAt(2);
			CellReference ref = new CellReference ("A1");
			Cell loc = sheet.getRow(ref.getRow()).getCell(ref.getCol());
			Row row = sheet.getRow(ref.getRow());
			boolean loop = true;
			int i = 2;
			while (loop == true){
				row = sheet.getRow(i);
				if (row == null){
					//System.out.println("NULL found");
					row = sheet.createRow(i);
					loop = false;
				} else {
					loc = sheet.getRow(i).getCell(0);
					if (loc == null || loc.getStringCellValue().isEmpty()){
						loop = false;
					} else {
						i++;
					}
				}
			}

			loc = row.createCell(0);
			loc.setCellValue(message.assignment);
			loc = row.createCell(1);
			loc.setCellValue(message.type);
			loc = row.createCell(2);
			
			//provided by Eric Nicolas
			//at http://stackoverflow.com/questions/9431089/how-to-correctly-format-a-date-cell-and-populate-content-using-apache-poi-3-7
			//note: his reference sheet is wrong but can be used as a guide to find the correct number
			XSSFCellStyle style = workbook.createCellStyle();
			style.setDataFormat((short)14);
			
			loc.setCellValue(message.date);
			loc.setCellStyle(style);
			
			//System.out.println(work.assignmentDate + " " + calendar.getTime());
			
			loc = row.createCell(3);
			loc.setCellValue(message.course);
			loc = row.createCell(4);
			loc.setCellValue(message.recipient);
			loc = row.createCell(5);
			loc.setCellValue(message.sender);
			loc = row.createCell(6);
			loc.setCellValue(message.status);
			
			FileOutputStream out = new FileOutputStream (theExcel);
			workbook.write(out);
			workbook.close();
			out.close();
		} catch (IOException exception){
			System.out.println("Database addMessage Error");
			
			
		}
		
	}
	
	/**
	 * deleteMessage({@link Messages} message) removes the given message
	 * from the messages array and from the excel file record
	 * @param message: {@link Messages} the message to be removed
	 * @author Elisa
	 */
	public void deleteMessage (Messages message) {
		//delete message in both messageArray and excel database
		int it = -1, i = 0;
		Messages temp;
		for (i = 0; i<messageAL.size(); i++){
			temp = messageAL.get(i);
			if (temp.assignment.equalsIgnoreCase(message.assignment)){
				if (temp.type.equalsIgnoreCase(message.type)){
					if (temp.date.equals(message.date)){
						if (temp.course.equalsIgnoreCase(message.course)){
							if (temp.recipient.equalsIgnoreCase(message.recipient)){
								if(temp.sender.equalsIgnoreCase(message.sender)){
									if (temp.status.equalsIgnoreCase(message.status)){
										it = i;
									}
								}
							}
						}
					}
				}
			}
		}
		
		if (it == -1){
			System.out.println("Message to delete not found");
		} else {
			messageAL.remove(it);
			
			try{
				FileInputStream file = new FileInputStream (theExcel);
				XSSFWorkbook workbook = new XSSFWorkbook(file);
				file.close();
	
				//manipulation
				Sheet sheet = workbook.getSheetAt(2);

				Cell loc = sheet.getRow(0).getCell(0);
				Row row = sheet.getRow(0);

				Cell assignment = sheet.getRow(0).getCell(0);
				Cell type = sheet.getRow(0).getCell(0);
				Cell date = sheet.getRow(0).getCell(0);
				Cell course = sheet.getRow(0).getCell(0);
				Cell to = sheet.getRow(0).getCell(0);
				Cell from = sheet.getRow(0).getCell(0);
				Cell status = sheet.getRow(0).getCell(0);
				
				boolean loop = true;
				i = 2;
				while (loop == true){
					row = sheet.getRow(i);
					if (row == null){
						//System.out.println("NULL found");
						loop = false;
					} else {
						loc = sheet.getRow(i).getCell(0);
						if (loc == null || loc.getStringCellValue().isEmpty()){
							loop = false;
						} else {
							assignment = sheet.getRow(i).getCell(0);
							type = sheet.getRow(i).getCell(1);
							date = sheet.getRow(i).getCell(2);
							course = sheet.getRow(i).getCell(3);
							to = sheet.getRow(i).getCell(4);
							from = sheet.getRow(i).getCell(5);
							status = sheet.getRow(i).getCell(6);
							if (message.recipient.equals(to.getStringCellValue()) 
								&& message.sender.equals(from.getStringCellValue())
								&& message.status.equals(status.getStringCellValue())
								&& message.course.equals(course.getStringCellValue())
								&& message.date.equals(date.getDateCellValue())
								&& message.type.equals(type.getStringCellValue())
								&& message.assignment.equals(assignment.getStringCellValue())){
								
								//i = message row to overwrite
								loop = false;
						
							}else {
								i++;
							}
						}
					}
				}
				//overwriting
				loop = true;
				Cell assignment2, type2, date2, course2, to2, from2, status2;
				while (loop == true){
					row = sheet.getRow(i);
					if (row == null){ // row i is empty
						//System.out.println("NULL found");
						loop = false;
					} else { // row i is empty
						loc = sheet.getRow(i).getCell(0);
						if (loc == null || loc.getStringCellValue().isEmpty()){
							loop = false;
							
						} else { // row to move to i is empty
							row = sheet.getRow(i+1);
							if (row == null){
								sheet.removeRow(sheet.getRow(i));
								loop = false;
							} else { //row to move to i is empty
								loc = sheet.getRow(i+1).getCell(0);
								if (loc == null || loc.getStringCellValue().isEmpty()){
									sheet.removeRow(sheet.getRow(i));
									loop = false;
									
								} else { //move i+1 to i
														
									assignment2 = sheet.getRow(i+1).getCell(0);
									type2 = sheet.getRow(i+1).getCell(1);
									date2 = sheet.getRow(i+1).getCell(2);
									course2 = sheet.getRow(i+1).getCell(3);
									to2 = sheet.getRow(i+1).getCell(4);
									from2 = sheet.getRow(i+1).getCell(5);
									status2 = sheet.getRow(i+1).getCell(6);
									 
									assignment.setCellValue(assignment2.getStringCellValue());
									type.setCellValue(type2.getStringCellValue());
									date.setCellValue(date2.getDateCellValue());
									course.setCellValue(course2.getStringCellValue());
									to.setCellValue(to2.getStringCellValue());
									from.setCellValue(from2.getStringCellValue());
									status.setCellValue(status2.getStringCellValue());
								
									
									i++;
									
									assignment = assignment2;
									type = type2;
									date = date2;
									course = course2;
									to = to2;
									from = from2;
									status = status2;
								}
							}
						}
					}
				}
				
				
				FileOutputStream out = new FileOutputStream (theExcel);
				workbook.write(out);
				workbook.close();
				out.close();
			} catch (IOException exception){
				System.out.println("Database deleteMessage Error");
				
				
			}
		
		}
	}
	
	/**
	 * changeMessageStatus({@link Messages} oldMessage, {@link boolean} status) updates the Messages records to reflect
	 * a Teacher's decision on Student submitted assignments
	 * @param oldMessage: {@link Message} that is being updated
	 * @param status: {@link boolean} of true for approved, false for denied
	 * @author Elisa
	 */
	public void changeMessageStatus (Messages oldMessage, boolean status){
		String newStat = "Denied";
		if (status == true){
			newStat = "Approved";
		}
		Messages newMessage = new Messages (oldMessage.assignment, oldMessage.type, 
				oldMessage.date, oldMessage.course, 
				oldMessage.sender, oldMessage.recipient, newStat);
		//delete current message in both array and excel
		deleteMessage(oldMessage);
		//add new message with swapped recipient and sender, and new status 
		//in both array and excel
		addMessage(newMessage);
	}
}