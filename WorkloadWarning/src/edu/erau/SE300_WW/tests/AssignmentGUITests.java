package edu.erau.SE300_WW.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.erau.SE300_WW.Assignment;
import edu.erau.SE300_WW.Database;



/**The purpose of this class is to conduct 2 JUnit test cases on the Assignment GUI class,
 * specifically testing to check whether or not the assignment entered is already in the database or not.
 * 2 test cases are provided here, 1 for an assignment in the database, and one for an assignment
 * that is not in the database.
 * @author Nicholas Krawczyk
 *
 */
public class AssignmentGUITests {
	static Database d;
	static Calendar c;
	
	@BeforeClass
	   public static void beforeClass() {
	      d = new Database(new File("C:/WorkloadWarning/CanvasDatabase.xlsx"));
	      
	   }

	@Test
	public void testAssignmentThatIsAlreadyInTheDatabase() {
		//Tests an assignment that is already in the database, should be true
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    Date da=new Date();
	    try {
			da=sdf.parse("10/19/2016");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(d.isAssignmentAlreadyInDatabase(new Assignment("Test 1", "Exam", da, "SE300")));
	}
	
	
	@Test
	public void testAssignmentThatIsNotAlreadyInTheDatabase() {
		//Tests an assignment that is not already in the database, should be false
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    Date da=new Date();
	    try {
			da=sdf.parse("10/25/2016");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(d.isAssignmentAlreadyInDatabase(new Assignment("Test 2", "Exam", da, "SE320"))==false);
	}

}
