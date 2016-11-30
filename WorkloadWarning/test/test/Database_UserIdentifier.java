package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.erau.SE300_WW.Database;

public class Database_UserIdentifier {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Begining");
		File file = new File ("C:/Users/Elisa/Documents/CanvasDatabase.xlsx");
		Database data = new Database(file);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testNotUser() {
		assert.assertEquals(0, data.isUser("Patrick"));
		//TODO: find junit notes example
	}
	
	@Test
	public final void testStudent(){
		
	}
	
	@Test
	public final void testTeacher(){
		
	}

}
