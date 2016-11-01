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
	}
	
	
}