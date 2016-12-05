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
 
@SuppressWarnings("serial")
public class ExamsListGUI extends JFrame {
    private JList<String> dateList;
    public ExamsListGUI() {
        //create the model and add elements
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Dates of already scheduled exams:");
        listModel.addElement("10/16/2016");
        listModel.addElement("10/17/2016");
        listModel.addElement("10/19/2016");
        listModel.addElement("10/20/2016");
        listModel.addElement("10/26/2016");
        listModel.addElement("11/24/2016");
        listModel.addElement("11/30/2016");
 
        //create the list
        dateList = new JList<>(listModel);
        add(dateList);
         
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Exams List");       
        this.setSize(250,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ExamsListGUI();
            }
        });
    }       
}

