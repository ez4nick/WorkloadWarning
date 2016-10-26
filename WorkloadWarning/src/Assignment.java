//workload warning project, SE300 Elisa Hawley

package src;

/**
 * Assignment class for Canvas-like assignment calendar
 * the Assignment object will store the data of an assignment including name, type, date, and course
 * @author Elisa
 */
public class Assignment {

	public String courseName;
	public String assignmentName;
	public String assignmentDate;
	public String assignmentType;
	
	/**
	 * the constructor will save all data of the assignment
	 * @param data: String formatted as name.type.date(mm/dd/yy).course
	 * @author Elisa
	 */
	public Assignment (String data) {
		String [] work = data.split("\\.");
		assignmentName = work [0].toLowerCase();
		assignmentType = work [1].toLowerCase();
		assignmentDate = work [2];
		courseName = work [3].toUpperCase();
		//System.out.println(assignmentName + assignmentType + assignmentDate + courseName);
	}
}