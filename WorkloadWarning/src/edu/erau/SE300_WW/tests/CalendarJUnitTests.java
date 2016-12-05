package edu.erau.SE300_WW.tests;


	

	import static org.junit.Assert.*;

	import java.io.File;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
	import java.util.Date;

	import org.junit.BeforeClass;
	import org.junit.Test;

	


	/**
	 * The purpose of this class is to conduct 5 JUnit test cases for the calendar classes.
 * This class is specifically testing to check whether or not each calendar class is pulling 
 * the correct content from the database for the intended user.
 * 5 test cases are provided here, 3 for Students in the database, and 2 for Professors in
 * the database.
	 * @author Chianti Ghalson
	 *
	 */
public class CalendarJUnitTests {
	
		static Database d;
		
		
		@BeforeClass
		   public static void beforeClass() {
			 d = new Database(new File("C:/WorkloadWarning/CanvasDatabase.xlsx"));
		      
				
		   }

		@Test
		public void testUserAssignment1() {
			
			 ArrayList<Assignment> student = new ArrayList <Assignment> (0);
				student = d.searchSAssignment("Elisa");
				Assignment assignment1 = student.get(1);
				String name = assignment1.assignmentName;
		
			
			assertEquals("Test 2",name);
		}
		
		@Test
		public void testUserAssignment2() {
			
			 ArrayList<Assignment> student = new ArrayList <Assignment> (0);
				student = d.searchSAssignment("Alley");
				Assignment assignment1 = student.get(1);
				String name = assignment1.assignmentName;
		
			
			assertEquals("Quiz 2",name);
		}
		
		@Test
		public void testUserAssignment3() {
			
			 ArrayList<Assignment> student = new ArrayList <Assignment> (0);
				student = d.searchSAssignment("Nick");
				Assignment assignment1 = student.get(1);
				String name = assignment1.assignmentName;
		
			
			assertEquals("Test 2",name);
		}
		
		@Test
		public void testUserAssignment4() {
			
			 ArrayList<Assignment> teacher = new ArrayList <Assignment> (0);
				teacher = d.searchTAssignment("Professor A");
				Assignment assignment1 = teacher.get(0);
				String name = assignment1.assignmentName;
		
			
			assertEquals("Test 1",name);
		}
		
		@Test
		public void testUserAssignment5() {
			
			 ArrayList<Assignment> teacher = new ArrayList <Assignment> (0);
				teacher = d.searchTAssignment("Professor B");
				Assignment assignment1 = teacher.get(0);
				String name = assignment1.assignmentName;
		
			
			assertEquals("Quiz 2",name);
		}
		
	

}
