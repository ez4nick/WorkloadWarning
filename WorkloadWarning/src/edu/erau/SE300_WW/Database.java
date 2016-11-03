package edu.erau.SE300_WW;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//workload warning project, SE300 Elisa Hawley

/**
 * Database Class for a Canvas-like assignment calendar
 * the Database object will read a properly formatted excel file,
 * storing the information into the object for reference,
 * and manipulating the excel file along with the saved data
 * @author Elisa
 */
public class Database {
	
	private ArrayList <Assignment> assignmentAL;
	private String [][] studentArray;
	private ArrayList <Messages> messageAL;
	private File theExcel;
	
	/**
	 * The constructor saves all data from the properly formatted excel file
	 * @param excelFile: File handler for the excel file to be read
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
	 * @return an ArrayList of Assignment objects
	 * @author Elisa
	 */
	public ArrayList <Assignment> getAllAssignments () {
		return assignmentAL;
	}
	
	
	/**
	 * getAllStudents() allows for reading the students stored in the database
	 * @return a 2D array of Strings, formatted [i][0] is the course and [i][n>0] is a student enrolled in that course
	 * @author Elisa 
	 */
	public String [][] getAllStudents () {
		return studentArray;
	}
	
	
	/**
	 * getMessages(String recipient) allows for reading the assignments left in messages
	 * ignores letter case
	 * @return an ArrayList of Messages
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
	 * courseStudents(String course) geterates an Arraylist of Strings containing the Students of a Course
	 * ignores letter case
	 * @param course: String containing the course name
	 * @return ArrayList of Strings containing the names of the Students in the course
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
	 * searchTCourses (String Teacher) allows for a course list to be generated for a given teacher
	 * ignores letter case
	 * @param teacher: string of teacher's name
	 * @return an ArrayList of Strings containing the courses the teacher teaches 
	 * @author Elisa
	 */
	public ArrayList<String> searchTCourses (String Teacher){
		ArrayList<String> courseList = new ArrayList<String>(0);
		int i = 0;
		for (i = 0; i<studentArray.length; i++){
			try {
				if (studentArray[i][0].equalsIgnoreCase(Teacher)){
					courseList.add(studentArray[i][1]);
				}
			} catch (NullPointerException exception) {
				
			}
		}
		return courseList;
	}
	
	/**
	 * searchSCourses(string) allows for a course list to be generated for a given student
	 * ignores letter case
	 * @param student: string of student's first name
	 * @return an ArrayList of Strings containing the courses the student is enrolled in
	 * @author Elisa
	 */
	public ArrayList <String> searchSCourses (String student) {
		ArrayList <String> courses = new ArrayList <String> (0);
		int j = 0;
		int k = 0;
		for (j = 0; j < studentArray.length; j++){
			try {
				for (k = 0; k < studentArray[j].length; k++){
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
	 * isUser (string) tests to see if the user name provided is a user, and returns no, student, or teacher in int value
	 * @param user: string containing user name
	 * @return int value: 0 if not a user, 1 if student, 2 if teacher
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
	 * searchSAssignment(String) allows for an assignments list to be generated for a given student
	 * ignores letter case
	 * @param student: string of student's first name
	 * @return an ArrayList of Assignment containing the assignments for the courses the student is enrolled in
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
	 * searchTAssignment(String) allows for an assignments list to be generated for a given Teacher
	 * ignores letter case
	 * @param Teacher: string of Teacher's name
	 * @return an ArrayList of Assignment containing the assignments for the courses the Teacher teaches
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
	
	/**
	 * addAssignmet(assignment) will add the given assignment 
	 * to both the assignment array for further use and to the excel file for record
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
	
	//TODO: deleteAssignment
	//TODO: java doc
	public void deleteAssignment (Assignment work) {
		//delete assignment from assignment array and excel database
	}
	
	//TODO: addMessage
	//TODO: java doc
	public void addMessage (Messages message) {
		//add message to messageArray and excel database
	}
	
	//TODO: deleteMessage
	//TODO: java doc
	public void deleteMessage (Messages message) {
		//delete Assignment in both messageArray and excel database
	}
	
	/**
	 * changeMessageStatus(Messages, boolean) updates the Messages records to reflect
	 * a Teacher's decision on Student submitted assignments
	 * @param oldMessage: Message object that is being updated
	 * @param status: boolean value of true for approved, false for denied
	 * @author Elisa
	 */
	public void changeMessageStatus (Messages oldMessage, boolean status){
		//delete current message in both array and excel
		//add new message with swapped recipient and sender, and new status in both array and excel
	}
}