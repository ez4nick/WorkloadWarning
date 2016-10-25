import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
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
	private ArrayList <Assignment> messageAL;
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
			Workbook workbook = new XSSFWorkbook(file);
			String [] col = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P",
							"Q","R","S","T","U","V","W","X","Y","Z","AA","AB","AC","AD","AE",
							"AF","AG","AH","AI","AJ","AK","AL","AM","AN","AO"}; 
			
			//students by course
			Sheet sheet = workbook.getSheetAt(0);
			//size of schedule
			CellReference ref = new CellReference("H1");
			Cell loc = sheet.getRow(ref.getRow()).getCell(ref.getCol());
			int rownum = (int)loc.getNumericCellValue();
			ref = new CellReference("F1");
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
					data.toUpperCase();
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
			int i = 0;
			loop = true;
			while (loop == true){
				ref = new CellReference ("A" + (i+2));
				try {
					loc = sheet.getRow(ref.getRow()).getCell(ref.getCol());
					data = loc.getStringCellValue();
					data = data.trim();
				} catch (NullPointerException exception){
					data = "";
				}
				if (data.isEmpty()){
					loop = false;
				} else {
					//System.out.println(data);
					assignmentAL.add(new Assignment(data));
					i++;
				}
				
				
			}
			
			//messages
			messageAL = new ArrayList<Assignment> (0);
			sheet = workbook.getSheetAt(2);
			i = 0;
			loop = true;
			while (loop == true){
				ref = new CellReference ("A" + (i+2));
				try {
					loc = sheet.getRow(ref.getRow()).getCell(ref.getCol());
					data = loc.getStringCellValue();
					data = data.trim();
				} catch (NullPointerException exception){
					data = "";
				}
				if (data.isEmpty()){
					loop = false;
				} else {
					//System.out.println(data);
					messageAL.add(new Assignment(data));
					i++;
				}
				
			}
				
			workbook.close();
		} catch (IOException exception){
			System.out.println("Database Population Error");
			System.out.println(exception);
		}
	}
	
	
	/**
	 * getAssignments() allows for reading the assignments stored in the database
	 * @return an ArrayList of Assignment objects
	 * @author Elisa
	 */
	public ArrayList <Assignment> getAssignments () {
		return assignmentAL;
	}
	
	
	/**
	 * getStudents() allows for reading the students stored in the database
	 * @return a 2D array of Strings, formatted [i][0] is the course and [i][n>0] is a student enrolled in that course
	 * @author Elisa 
	 */
	public String [][] getStudents () {
		return studentArray;
	}
	
	
	/**
	 * getMessages() allows for reading the assignments left in messages to the teacher
	 * @return an ArrayList of Assignment
	 * @author Elisa
	 */
	public ArrayList<Assignment> getMessages () {
		return messageAL;
	}
	
	
	/**
	 * getListofCources() allows for reading of the courses students are enrolled in
	 * @return a String array
	 * @author Nick
	 */
	public String[] getListofCourses(){
		String[] temp = new String[studentArray.length];
		temp[0]="Select a Course";
		for(int x=0; x<studentArray.length-1;x++){
			temp[x+1]=studentArray[x][0];
		}
		return temp;
	}
	
	//TODO: searchCourse
	/**
	 * searchCourses(string) allows for a course list to be generated for a given student
	 * @param student: string of student's first name
	 * @return an ArrayList of Strings containing the courses the student is enrolled in
	 * @author Elisa
	 */
	public ArrayList <String> searchCourses (String student) {
		ArrayList <String> courses = new ArrayList <String> (0);
		return courses;
	}
	
	//TODO: searchAssignment
	/**
	 * searchAssignment(String) allows for an assignments list to be generated for a given student
	 * @param student: string of student's first name
	 * @return an ArrayList of Assignment containing the assignments for the courses the student is enrolled in
	 * @author Elisa
	 */
	public Assignment [] searchAssignment (String student){
		Assignment [] work = {};
		return work;
	}
	
	//TODO: addAssignment
	//TODO: java doc
	public void addAssignment (Assignment work) {
		//add assignment to assignment array and excel database
	}
	
	//TODO: deleteAssignment
	//TODO: java doc
	public void deleteAssignment (int index) {
		//delete assignment from assignment array and excel database
	}
	
	//TODO: addMessage
	//TODO: java doc
	public void addMessage (Assignment work) {
		//add message to messageArray and excel database
	}
	
	//TODO: deleteMessage
	//TODO: java doc
	public void deleteMessage (int index) {
		//delete Assignment in both messageArray and excel database
	}
}