package edu.erau.SE300_WW.tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.erau.SE300_WW.Database;

public class Database_UserIdentifier {
	
	private static Database data;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Begining");
		File file = new File ("C:/Users/Elisa/Documents/CanvasDatabase.xlsx");
		data = new Database(file);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Finished");
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testNotUser() {
		//testing name not registered, in format used.
		assertEquals(0, data.isUser("Patrick"));
		System.out.println("Not Student");
	}
	
	@Test
	public final void testStudent(){
		//testing student name, all capital
		assertEquals(1, data.isUser("NICk"));
		System.out.println("Student");
	}
	
	@Test
	public final void testTeacher(){
		//testing teacher name, all lowercase
		assertEquals(2, data.isUser("professor c"));
		System.out.println("Teacher");
	}

}
