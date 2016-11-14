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
			//Database d = new Database(new File(LoginGUI.dataseFilePath));
			
    		AssignmentGUI g = new AssignmentGUI(LoginGUI.databaseShared,InstructorCalendar.this);
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
        	
    		AssignmentGUI g = new AssignmentGUI(LoginGUI.databaseShared,InstructorCalendar.this);
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
      

			}
		      //Populate calendar with Assignments retrieved from database 
      
      ArrayList <Assignment> a = new ArrayList <Assignment> (0);
     a = LoginGUI.databaseShared.searchTAssignment("Professor A");
     for (Assignment temp: a){
    	
    	 // retrieve assignment date from database
    	 Calendar n;
			n = temp.assignmentDate;
			//Set date obtained from database to string
			String date = n.toString();
			//Break string into substrings
			
			//obtains month from date string ("Oct")
			String month1 = date.substring(4,7);
			
			//obtains day of week from date string ("Mon")
			String day1 = date.substring(0,3);
			
			//obtains year from date string ("2016")
			String year1 = date.substring(24,28);
			
			//obtains day from date string ("17")
			String daynum = date.substring(8,10);
			
			//Initialize column variable which determines the (column 
			//in the calendar/day of the week) where assignment information will
			//be transfered.
			int column = 0;
			String fromcal = null;
			
			
			//Populate for October
			if (day1 == "Sun" && month1 == "Oct" && year1 == "2016" && month.equals("October")&& year==2016){
				column = 0;
				
				int k = (int) model.getRowCount();
				int i2=1;
				int stop = 0;
				while (stop == 0&& i2<k) {
				
					int checknum = (int) model.getValueAt(i2,column);
					fromcal = Integer.toString(checknum); 
					i2++;
					if(fromcal.equals(daynum)){
						stop = 1;
					}
					
				}
				
				//input assignment
				
				model.setValueAt(temp.assignmentName,i2-1, column);
				
			}
		else if (day1 == "Mon"&& month1 == "Oct" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 1;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Tue"&& month1 == "Oct" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 2;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Wed"&& month1 == "Oct" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 3;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Thu"&& month1 == "Oct" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 4;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Fri"&& month1 == "Oct" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 5;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Sat"&& month1 == "Oct" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 6;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
			
			
			
			
			
			//Populate for November
			if (day1 == "Sun" && month1 == "Nov" && year1 == "2016" && month.equals("October")&& year==2016){
				column = 0;
				
				int k = (int) model.getRowCount();
				int i2=1;
				int stop = 0;
				while (stop == 0&& i2<k) {
				
					int checknum = (int) model.getValueAt(i2,column);
					fromcal = Integer.toString(checknum); 
					i2++;
					if(fromcal.equals(daynum)){
						stop = 1;
					}
					
				}
				
				//input assignment
				
				model.setValueAt(temp.assignmentName,i2-1, column);
				
			}
		else if (day1 == "Mon"&& month1 == "Nov" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 1;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Tue"&& month1 == "Nov" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 2;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Wed"&& month1 == "Nov" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 3;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Thu"&& month1 == "Nov" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 4;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Fri"&& month1 == "Nov" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 5;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Sat"&& month1 == "Nov" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 6;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
			
			
			
		
			//Populate for December
			if (day1 == "Sun" && month1 == "Dec" && year1 == "2016" && month.equals("October")&& year==2016){
				column = 0;
				
				int k = (int) model.getRowCount();
				int i2=1;
				int stop = 0;
				while (stop == 0&& i2<k) {
				
					int checknum = (int) model.getValueAt(i2,column);
					fromcal = Integer.toString(checknum); 
					i2++;
					if(fromcal.equals(daynum)){
						stop = 1;
					}
					
				}
				
				//input assignment
				
				model.setValueAt(temp.assignmentName,i2-1, column);
				
			}
		else if (day1 == "Mon"&& month1 == "Dec" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 1;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Tue"&& month1 == "Dec" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 2;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Wed"&& month1 == "Dec" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 3;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Thu"&& month1 == "Dec" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 4;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Fri"&& month1 == "Dec" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 5;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Sat"&& month1 == "Dec" && year1 == "2016" && month.equals("October")&& year==2016){
			column = 6;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		
			
			
			
			
			
			//Populate for January
			if (day1 == "Sun" && month1 == "Jan" && year1 == "2017" && month.equals("October")&& year==2016){
				column = 0;
				
				int k = (int) model.getRowCount();
				int i2=1;
				int stop = 0;
				while (stop == 0&& i2<k) {
				
					int checknum = (int) model.getValueAt(i2,column);
					fromcal = Integer.toString(checknum); 
					i2++;
					if(fromcal.equals(daynum)){
						stop = 1;
					}
					
				}
				
				//input assignment
				
				model.setValueAt(temp.assignmentName,i2-1, column);
				
			}
		else if (day1 == "Mon"&& month1 == "Jan" && year1 == "2017" && month.equals("October")&& year==2016){
			column = 1;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Tue"&& month1 == "Jan" && year1 == "2017" && month.equals("October")&& year==2016){
			column = 2;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Wed"&& month1 == "Jan" && year1 == "2017" && month.equals("October")&& year==2016){
			column = 3;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Thu"&& month1 == "Jan" && year1 == "2017" && month.equals("October")&& year==2016){
			column = 4;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Fri"&& month1 == "Jan" && year1 == "2017" && month.equals("October")&& year==2016){
			column = 5;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Sat"&& month1 == "Jan" && year1 == "2017" && month.equals("October")&& year==2016){
			column = 6;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		
			
			
			
			
			
			//Populate for February
			if (day1 == "Sun" && month1 == "Feb" && year1 == "2017" && month.equals("October")&& year==2016){
				column = 0;
				
				int k = (int) model.getRowCount();
				int i2=1;
				int stop = 0;
				while (stop == 0&& i2<k) {
				
					int checknum = (int) model.getValueAt(i2,column);
					fromcal = Integer.toString(checknum); 
					i2++;
					if(fromcal.equals(daynum)){
						stop = 1;
					}
					
				}
				
				//input assignment
				
				model.setValueAt(temp.assignmentName,i2-1, column);
				
			}
		else if (day1 == "Mon"&& month1 == "Feb" && year1 == "2017" && month.equals("October")&& year==2016){
			column = 1;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Tue"&& month1 == "Feb" && year1 == "2017" && month.equals("October")&& year==2016){
			column = 2;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Wed"&& month1 == "Feb" && year1 == "2017" && month.equals("October")&& year==2016){
			column = 3;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Thu"&& month1 == "Feb" && year1 == "2017" && month.equals("October")&& year==2016){
			column = 4;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Fri"&& month1 == "Feb" && year1 == "2017" && month.equals("October")&& year==2016){
			column = 5;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		else if (day1 == "Sat"&& month1 == "Feb" && year1 == "2017" && month.equals("October")&& year==2016){
			column = 6;
			
			int k = (int) model.getRowCount();
			int i2=1;
			int stop = 0;
			while (stop == 0&& i2<k) {
			
				int checknum = (int) model.getValueAt(i2,column);
				fromcal = Integer.toString(checknum); 
				i2++;
				if(fromcal.equals(daynum)){
					stop = 1;
				}
				
			}
			
			//input assignment
			
			model.setValueAt(temp.assignmentName,i2-1, column);
			
		}
		
		
			
		
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
 
 
