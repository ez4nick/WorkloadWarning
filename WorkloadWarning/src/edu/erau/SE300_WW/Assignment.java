//workload warning project, SE300 Elisa Hawley

package edu.erau.SE300_WW;

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
	 * the constructor will save all data of the assignment
	 * @param data: String formatted as name.type.date(mm/dd/yy).course
	 * @author Elisa
	 */
	public Assignment(String assignment, String type, Date date, String course){
		assignmentName = assignment;
		assignmentType = type;
		assignmentDate = date;
		courseName = course;
	}
}