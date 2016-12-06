package edu.erau.SE300_WW;

/**
 * @author diprea
 * The Exams List GUI class displays a list of already scheduled exams
 * to the professor in order to aid in their selection of an exam date.
 */


import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingUtilities;
 
/**
 * The exams list gui shows a list of dates when there are other assignments when 
 * a teacher decides to reschedule an exam from when a warning was received. 
 *
 */
@SuppressWarnings("serial")
public class ExamsListGUI extends JFrame {
    private JList<String> dateList;
    public ExamsListGUI() {
        //create the model and add elements
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Dates Students have other Exams:");
        
        for(int x=0;x< NewWarningTrigger.datesOfTheConflicts.size();x++){
        	listModel.addElement(NewWarningTrigger.datesOfTheConflicts.get(x));
        }
 
        //create the list
        dateList = new JList<>(listModel);
        add(dateList);
         
        this.setTitle("Exams List");       
        this.setSize(250,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
     
          
}

