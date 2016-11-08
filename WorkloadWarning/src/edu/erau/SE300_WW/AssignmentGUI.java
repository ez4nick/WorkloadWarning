package edu.erau.SE300_WW;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javafx.scene.control.DatePicker;
import javafx.stage.Stage;


/**
 * This class is responsible for launching the assignment GUI which allows the user
 * to input an assignment into the system. The result of successfully inputting an assignment
 * will depend on whether a student or teacher is logged in. For a teacher, as long as the assignment
 * is not already existing in the database then it is immediately added. For a student, a teacher
 * must confirm their assignment before it is added into the database.
 * @author Nicholas Krawczyk
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
	DatePicker datePicker;
	
	InstructorCalendar ic;
	
	Stage newStage;
	Boolean modifyingAnAssignment=false; //Is the assignment creator window modifying an assignment?
	
	/** Simple constructor used to initialize the assignment GUI
	 * @param dd A {@link Database} object is required to access the courses as well as add the new assignments to the database.
	 */
	public AssignmentGUI(Database dd){
		d=dd; //Database object
	}
	
	/**
	 * @param dd A {@link Database} object is required to access the courses as well as add the new assignments to the database.
	 * @param ii An {@link InstructorCalendar} object is required for access to adding an assignment directly to the instructor calendar. 
	 */
	public AssignmentGUI(Database dd, InstructorCalendar ii){
		d=dd;//Database object
		ic=ii;//InstructorCalendar object
	}
	
	/**
	 * This method is responsible for everything related to creating and showing the GUI for creating an assignment. 
	 * In addition, it also is flexible to be capable of being prefilled with information about an assignment
	 * if a teacher wishes to edit an assignment submitted by a student.
	 * @param prefillDefaults Should the assignment creation GUI start with prefilled items? Only should be the case if the request was made from a teacher pressing edit on a student's assignment request from a message.
	 * @param assignmentTitle Only applicable if prefillDefaults is True, title of the assignment.  
	 * @param assignmentDate Only applicable if prefillDefaults is True, date of the assignment.
	 * @param assignmentType Only applicable if prefillDefaults is True, type of the assignment (Exam, Homework, or Quiz).
	 * @param assignmentCourse Only applicable if prefillDefaults is True, course for the assignment.
	 */
	public void openAssignmentGUI(Boolean prefillDefaults, String assignmentTitle, String assignmentDate, String assignmentType, String assignmentCourse){
		
		ArrayList<String> tempCourseList = d.searchSCourses(LoginGUI.currentUserName); //List of courses to be displayed
		String courseList[]= new String[tempCourseList.size()+1];
		courseList[0]="Select a Course"; //Set the default display for the selection
		for(int x=0; x<tempCourseList.size();x++){
			courseList[x+1]=tempCourseList.get(x);
		}
		
		frame = new JFrame("Assignment Creator"); //Frame for everything 
		
		JMenuBar menuBar = new JMenuBar();
	    JMenu item1 = new JMenu("File");
	    JMenu item2 = new JMenu("Help");
	    JMenuItem helpItem1 = new JMenuItem("Adding an Assignment");
	    item2.add(helpItem1);
	    menuBar.add(item1);
	    menuBar.add(item2);
	    frame.setJMenuBar(menuBar);
	    
	    helpItem1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Help h = new Help("assignmentCreator","addAssign");
			}
	    	
	    });
		
		//4 Panels
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
		JButton clearButton = new JButton("Clear");
		
		
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
		panel3.add(clearButton,BorderLayout.SOUTH);
		
		panel4.add(label4,BorderLayout.WEST);
		panel4.add(typeSelection,BorderLayout.WEST);
		
		frame.add(panel1);
		frame.add(panel4);
		frame.add(panel2);
		frame.add(panel3);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		frame.pack();
		frame.setSize(400,235);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		if(prefillDefaults){
			/*
			 * If the Teacher is coming from a message and wishes to edit an assignment
			 * that a student submitted, then we will prefill the assignment creator 
			 * with what the student had already entered to give them a head start. Also
			 * changes the title of the window and text of the go button.
			 */
			modifyingAnAssignment=true;
			inputText.setText(assignmentTitle);
			
			for(int x=0; x<typeList.length;x++){
				if(typeList[x].equals(assignmentType)){
					typeSelection.setSelectedIndex(x);
				}
			}
			
			for(int y=0; y<courseList.length;y++){
				if(courseList[y].equals(assignmentCourse)){
					courseSelection.setSelectedIndex(y);
				}
			}
			button.setText("Modify");
	        model.setDate(Integer.parseInt(assignmentDate.substring(6, 10)), Integer.parseInt(assignmentDate.substring(0, 2))-1, Integer.parseInt(assignmentDate.substring(3, 5)));
			frame.setTitle("Modify Assignment");
			model.setSelected(true);
		}
		
		clearButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Clear all of the inputs
				resetAllFields();
			}
		});
		
		button.addActionListener(new ActionListener(){
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedDate = (Date) datePicker.getModel().getValue();
				
				//Check for valid inputs
				noDate=selectedDate==(null);
				noType=typeSelection.getSelectedItem().toString().equals("Select an Assignment Type");
				noTitle=inputText.getText().equals("");
				noCourse=courseSelection.getSelectedItem().toString().equals("Select a Course");
				
				if(noDate || noType || noTitle || noCourse ){
					//One OR more of the requested inputs were not given. Determine which to help the user understand their mistake!
					
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
					//All values are OK! Proceed to based on if the user is a teacher or a student
					if(LoginGUI.userType.equals("Teacher")){
						//If the user is a teacher, determine whether or not to send them the workload warning
						ProfessorWarning p = new ProfessorWarning(AssignmentGUI.this);
					}
					
					else{
						//User is a student, so send a request to the teacher of their course with the assignment
						
						/* *******************************************
						 * *******************************************
						 * *******************************************
						 * *******************************************
						 * *******************************************
						 * INSERT CODE TO SEND MESSAGE TO TEACHER HERE
						 * *******************************************
						 * *******************************************
						 * *******************************************
						 * *******************************************
						 * *******************************************
						 */
						JOptionPane.showMessageDialog(frame, "Your request has been sent to your teacher. Once your teacher approves the assignment it will be considered.","Request Semt",JOptionPane.INFORMATION_MESSAGE);
						resetAllFields();
					}
					
				}
				
			}
			
		});
	}
	
	/**
	 * This method is referenced from the ProfessorWarning class and shows the dialog indicating that
	 * the assignment was successfully created. This is only shown for a teacher user type since as long
	 * as the assignment they are trying to enter does not already exist in the databse then we will add
	 * it instantly.
	 */
	public void showSuccessDialog(){
		if(LoginGUI.userType.equals("Teacher")){
			
			//Set the time to 0 for purposes of comparing to see if the assignment already exists
			selectedDate.setHours(0);
			selectedDate.setMinutes(0);
			selectedDate.setSeconds(0);
			
			//Try to Add the assignment to the database first and then show the appropriate dialog after
			boolean inDatabase=LoginGUI.databaseShared.isAssignmentAlreadyInDatabase(new Assignment(inputText.getText(), typeSelection.getSelectedItem().toString(), selectedDate, courseSelection.getSelectedItem().toString()));
			
			if(!inDatabase){
				//If the assignment was not already in the database then add it and show a success dialog to the teacher
				ic.addAssignment(Integer.parseInt(selectedDate.toString().substring(8,10)), inputText.getText(), courseSelection.getSelectedItem().toString(),selectedDate.toString().substring(4,7));
				JOptionPane.showMessageDialog(frame, "Assignment Sucessfully Created\nTitle: "+inputText.getText()+"\nDate: "+selectedDate.toString().substring(0,10)+
					"\nCourse: "+courseSelection.getSelectedItem().toString()+"\nType: "+typeSelection.getSelectedItem().toString(),"Assignment Created",JOptionPane.INFORMATION_MESSAGE);
			
				if(modifyingAnAssignment){
					//If the user is modifying an assignment, close the window for them when they are finished and return to the messages view
					frame.dispose();
				}
			}
			else{
				//The assignment already exists in the database. Show warning and do not add it to the database.
				JOptionPane.showMessageDialog(frame, "That assignment already exists in the database. Unable to add.");
				frame.dispose();
				
			}
		}
		
		//Clear the boxes once the dialog is closed
		resetAllFields();
	}
	
	/**
	 * This method resets all of the fields of the assignment creation window.
	 */
	public void resetAllFields(){
		inputText.setText("");
		courseSelection.setSelectedIndex(0);
		typeSelection.setSelectedIndex(0);
		model.setSelected(false);
	}

	
}


