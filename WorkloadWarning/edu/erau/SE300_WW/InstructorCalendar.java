package edu.erau.SE300_WW;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.table.*;
 
/**
 * The InstructorCalendar Class displays the assignment schedules of all 
 * the students enrolled in a specific course to the instructor.
 * @author Chianti Ghalson
 *
 */

@SuppressWarnings("serial")
public class InstructorCalendar extends JFrame{
 
  DefaultTableModel model;
  Calendar cal = new GregorianCalendar();
  JLabel label;
  String month;
  
  public InstructorCalendar(){
	  
  }
  
  public void showInstructorCalendar() {
 
	  
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("(InstructorView)WorkLoad Warning Calandar");
    this.setLayout(new BorderLayout());
    this.setVisible(true);
    this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    this.setSize(900, 250);
    this.setLocationRelativeTo(null);
   
 
    JMenuBar menuBar = new JMenuBar();
    JMenu item1 = new JMenu("File");
    JMenuItem fileItem1 = new JMenuItem("Create New Assignment");
    JMenuItem fileItem2 = new JMenuItem("View Messages");
    item1.add(fileItem1);
    item1.add(fileItem2);
    
    JMenu item2 = new JMenu("Help");
    JMenuItem helpItem1 = new JMenuItem("Using the Calendar");
    item2.add(helpItem1);
    
    menuBar.add(item1);
    menuBar.add(item2);
    setJMenuBar(menuBar);
    
    helpItem1.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Help h = new Help("calendar","usingCal");
		}
    });
    
    fileItem1.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Database d = new Database(new File("C:/WorkloadWarning/CanvasDatabase.xlsx"));
    		AssignmentGUI g = new AssignmentGUI(d,InstructorCalendar.this);
    		g.openAssignmentGUI();
		}
    });
    
    fileItem2.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Messages m = new Messages();
			m.openMessagesDisplay();
		}
    });
    
    label = new JLabel();
    label.setHorizontalAlignment(SwingConstants.CENTER);
 
    JButton b1 = new JButton("<");
    b1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        cal.add(Calendar.MONTH, -1);
        updateMonth();
      }
    });
 
    JButton b2 = new JButton(">");
    b2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        cal.add(Calendar.MONTH, +1);
        updateMonth();
      }
    });
 
    JButton b3 = new JButton("Create New Assignment");
    b2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        updateMonth();
      }
    });
    
    b3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          //launch Assignment GUI
        	Database d = new Database(new File("C:/WorkloadWarning/CanvasDatabase.xlsx"));
    		AssignmentGUI g = new AssignmentGUI(d,InstructorCalendar.this);
    		g.openAssignmentGUI();
    		
        }
      });
    
    
    JPanel panel = new JPanel();
    
    
    panel.setLayout(new BorderLayout());
    panel.add(b1,BorderLayout.WEST);
    panel.add(label,BorderLayout.CENTER);
    panel.add(b2,BorderLayout.EAST);
    //panel.add(b3,BorderLayout.SOUTH);
    
    String [] columns = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    model = new DefaultTableModel(null,columns);
    
    JTable table = new JTable(model);
    
    JScrollPane pane = new JScrollPane(table);
 
    this.add(panel,BorderLayout.NORTH);
    this.add(pane,BorderLayout.CENTER);
 
 
    this.updateMonth();
    
 
  }
  
  public void addAssignment(int day,String title, String course, String AssignmentMonth){
	  int offset=0;
	  if(AssignmentMonth.equals("Oct")){
		  offset=5;
		  if(month.equals("November")){
			  cal.add(Calendar.MONTH, -1);
		  }
		  if(month.equals("December")){
			  cal.add(Calendar.MONTH, -2);
		  }
		  updateMonth();
	  }
	  else if(AssignmentMonth.equals("Nov")){
		  offset=1;
		  if(month.equals("October")){
			  cal.add(Calendar.MONTH, +1);
		  }
		  else if(month.equals("December")){
			  cal.add(Calendar.MONTH, -1);
		  }
		  updateMonth();
	  }
	  else if(AssignmentMonth.equals("Dec")){
		  offset=3;
		  if(month.equals("October")){
			  cal.add(Calendar.MONTH, +2);
		  }
		  else if(month.equals("November")){
			  cal.add(Calendar.MONTH, +1);
		  }
		  updateMonth();
	  }
	  int new_day=day+offset;
	  model.setValueAt(day+" "+course+": "+title, new_day/7, new_day%7);
  }
 
  // 
  void updateMonth() {
	 
	 
	  
    cal.set(Calendar.DAY_OF_MONTH, 1);
    
 
    month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
    int year = cal.get(Calendar.YEAR);
    label.setText(month + " " + year);
 
    int startDay = cal.get(Calendar.DAY_OF_WEEK);
    int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
 
    model.setRowCount(0);
    model.setRowCount(weeks);
    
    int i = startDay-1;
    for(int day=1;day<=numberOfDays;day++){
      model.setValueAt(day, i/7 , i%7 );
      i = i + 1;
      
      //Populate calendar with Assignments retrieved from database 
    if (month.equals("October")& year==2016){
      String test1 = "19   SE300: Test 1/AS322: Test 2";
      model.setValueAt(test1,3, 3);
      
      String quiz2 = "17   CS225: Quiz 2";
      model.setValueAt(quiz2,3, 1);
    }
    if (month.equals("December")& year==2016){
        String final1 = "13   SE300: Final Exam";
        model.setValueAt(final1,2, 2);
        
        String test3 = "2   CS225: Test 3";
        model.setValueAt(test3,0, 5);
        
        String hw3 = "3   AS322: Homework 3";
        model.setValueAt(hw3,0, 6);
      }
      
      
      
    }
 
  }
 
 
}