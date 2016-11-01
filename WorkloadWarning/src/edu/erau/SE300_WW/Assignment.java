//workload warning project, SE300 Elisa Hawley

package src.edu.erau.SE300_WW;

import java.util.Date;

/**
 * Assignment class for Canvas-like assignment calendar
 * the Assignment object will store the data of an assignment including name, type, date, and course
 * @author Elisa
 */
public class Assignment {

	public String courseName;
	public String assignmentName;
	public Date assignmentDate;
	public String assignmentType;
	
	/**
	 * Assignment constructor stores the assignment as 1 object
	 * @param assignment: string entry of assignment name
	 * @param type: string entry of assignment type - Hw, Quiz, Exam
	 * @param date: string entry of assignment date
	 * @param course: string entry of course assignment is due in
	 * @author Elisa
	 */
	public Assignment(String assignment, String type, Date date, String course){
		assignmentName = assignment;
		assignmentType = type;
		assignmentDate = date;
		courseName = course;
	}

}