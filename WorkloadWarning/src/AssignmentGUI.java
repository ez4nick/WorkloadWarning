package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


/**
 * This class is responsible for launching the assignment GUI which allows the user
 * to input an assignment into the system. The result of successfully inputting an assignment
 * will depend on whether a student or teacher is logged in. For a teacher, as long as the assignment
 * is not already existing in the database then it is immediately added. For a student, a teacher
 * must confirm their assignment before it is added into the database.
 * @author Nick 
 *
 */
public class AssignmentGUI{
	
	Database d;
	JFrame frame;
	JTextField inputText;
	Date selectedDate;
	JComboBox courseSelection;
	JComboBox typeSelection;
	UtilDateModel model;
	
	Boolean noCourse;
	Boolean noTitle;
	Boolean noType;
	Boolean noDate;
	
	InstructorCalendar ic;
	
	/** Simple constructor used to initialize the assignment GUI
	 * @param dd A database object is required to access the courses as well as add the new assignments to the database.
	 */
	public AssignmentGUI(Database dd){
		d=dd; //Database object
	}
	
	/**
	 * @param dd A database object is required to access the courses as well as add the new assignments to the database.
	 * @param ii An InstructorCalendar object is required for access to adding an assignment directly to the instructor calendar. 
	 */
	public AssignmentGUI(Database dd, InstructorCalendar ii){
		d=dd;//Database object
		ic=ii;//InstructorCalendar object
	}
	
	/**
	 * This method is responsible for everything related to creating and showing the GUI for creating an assignment.
	 */
	public void openAssignmentGUI(){
		
		
		String[] courseList = d.getListofCourses(); //List of courses to be displayed
		frame = new JFrame("Assignment Creator"); //Frame for everything 
		
		
		
		//3 Panels
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		
		JLabel label1 = new JLabel("Assignment Title:");
		JLabel label2 = new JLabel("Assignment Date:");
		JLabel label3 = new JLabel("Course:");
		JLabel label4 = new JLabel("Assignment Type:");
		
		inputText = new JTextField();
		inputText.setPreferredSize(new Dimension(200,20));
		
		JButton button = new JButton("GO!");
		JButton button2 = new JButton("Clear");
		
		
		String[] typeList = {"Select an Assignment Type","Exam","Homework","Quiz"};
		courseSelection = new JComboBox(courseList);
		typeSelection = new JComboBox(typeList);
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		 
		panel1.add(label1,BorderLayout.WEST);
		panel1.add(inputText,BorderLayout.EAST);
		
		panel2.add(label2,BorderLayout.WEST);
		panel2.add(datePicker,BorderLayout.EAST);
		
		panel3.add(label3,BorderLayout.WEST);
		panel3.add(courseSelection,BorderLayout.EAST);
		panel3.add(button,BorderLayout.SOUTH);
		panel3.add(button2,BorderLayout.SOUTH);
		
		panel4.add(label4,BorderLayout.WEST);
		panel4.add(typeSelection,BorderLayout.WEST);
		
		frame.add(panel1);
		frame.add(panel4);
		frame.add(panel2);
		frame.add(panel3);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		frame.pack();
		frame.setSize(400,215);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		button2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Clear all of the inputs
				inputText.setText("");
				typeSelection.setSelectedIndex(0);
				courseSelection.setSelectedIndex(0);
				model.setSelected(false);
			}
		});
		
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedDate = (Date) datePicker.getModel().getValue();
				//Check for valid inputs
				
				noDate=selectedDate==(null);
				noType=typeSelection.getSelectedItem().toString().equals("Select an Assignment Type");
				noTitle=inputText.getText().equals("");
				noCourse=courseSelection.getSelectedItem().toString().equals("Select a Course");
				
				if(noDate || noType || noTitle || noCourse ){
					//One OR more of the requested inputs were not given
					
					if(noDate && noType && noTitle && noCourse ){
						JOptionPane.showMessageDialog(frame, "Everything was empty! :( Please try again.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(noType && noTitle && noCourse){
						//Only Date
						JOptionPane.showMessageDialog(frame, "No assignment title was entered, no course was selected, and no type was selected! Please try again.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(noDate && noType && noTitle){
						//Only Course
						JOptionPane.showMessageDialog(frame, "No assignment title was entered, no date was selected, and no assignment type was selected! Please try again.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(noDate && noType && noCourse){
						//Only Assignment Title
						JOptionPane.showMessageDialog(frame, "No course was selected, no date was selected, and no assignment type was selected! Please try again.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(noDate && noCourse && noTitle){
						//Only assignment type
						JOptionPane.showMessageDialog(frame, "No course was selected, no date was selected, and no assignment title was entered! Please try again.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(noCourse && noType){
						//Only Title and Date
						JOptionPane.showMessageDialog(frame, "No course was selected, and no type was selected! Please try again.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(noDate && noType){
						//Only Title and Course
						JOptionPane.showMessageDialog(frame, "No date was selected, and no type was selected! Please try again.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(noTitle && noType){
						//Only Date and Course
						JOptionPane.showMessageDialog(frame, "No assignment title was entered, and no type was selected! Please try again.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else if(noDate && noCourse){
						//Only Title and Type
						JOptionPane.showMessageDialog(frame, "No date was selected, and no course was selected! Please try again.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(frame, "One or more required items were missing. Please try again.","Error",JOptionPane.ERROR_MESSAGE);
					}
					
					
				}
				else{
					//All values are OK!
					if(LoginGUI.user.equals("Teacher")){
						ProfessorWarning p = new ProfessorWarning(AssignmentGUI.this);
						
					}
					else{
						JOptionPane.showMessageDialog(frame, "Your request has been sent to your teacher. Once your teacher approves the assignment it will be considered.","Request Semt",JOptionPane.INFORMATION_MESSAGE);
						inputText.setText("");
						courseSelection.setSelectedIndex(0);
						typeSelection.setSelectedIndex(0);
						model.setSelected(false);
					}
					
					
					
				}
				
				
			}
			
		});
	}
	
	/**
	 * This method is referenced from the ProfessorWarning class and shows the dialog indicating that
	 * the assignment was successfully created.
	 */
	public void showSuccessDialog(){
		if(LoginGUI.user.equals("Teacher")){
			System.out.println(selectedDate.toString().substring(4,7));
			ic.addAssignment(Integer.parseInt(selectedDate.toString().substring(8,10)), inputText.getText(), courseSelection.getSelectedItem().toString(),selectedDate.toString().substring(4,7));
			JOptionPane.showMessageDialog(frame, "Assignment Sucessfully Created\nTitle: "+inputText.getText()+"\nDate: "+selectedDate.toString().substring(0,10)+
					"\nCourse: "+courseSelection.getSelectedItem().toString()+"\nType: "+typeSelection.getSelectedItem().toString(),"Assignment Created",JOptionPane.INFORMATION_MESSAGE);
			
		}
		
		
		inputText.setText("");
		courseSelection.setSelectedIndex(0);
		typeSelection.setSelectedIndex(0);
		model.setSelected(false);
	}
	
}


