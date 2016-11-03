//workload warning project, SE300 Elisa Hawley

package edu.erau.SE300_WW;

import java.util.Calendar;
import java.util.Date;

/**
 * Assignment class for Canvas-like assignment calendar.
 * the Assignment object will store the data of an assignment including name, type, date, and course
 * @author Elisa
 */
public class Assignment {

	public String courseName;
	public String assignmentName;
	public Date assignmentDate;
	public String assignmentType;
	
	/**
	 * Assignment constructor stores the assignment as one object
	 * @param assignment: {@link string} entry of assignment name
	 * @param type: {@link string} entry of assignment type - Hw, Quiz, Exam
	 * @param date: {@link string} entry of assignment date
	 * @param course: {@link string} entry of course assignment is due in
	 * @author Elisa
	 */
	public Assignment(String assignment, String type, Date date, String course){
		assignmentName = assignment;
		assignmentType = type;
		assignmentDate = date;
		courseName = course;
	}

}