package src.edu.erau.SE300_WW;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**This class is responsible for obtaining messages that are available as well, showing them to the 
 * user and allowing the user to take appropriate action on the message.
 * @author Nicholas Krawczyk
 *
 */
public class Messages {

	public String assignment;
	public String type;
	public Date date;
	public String course;
	public String recipient;
	public String sender;
	public String status;
	
	/**
	 * Messages constructor populates the fields of a message from a given series of strings
	 * @param assignmentName: string containing the name of the assignment
	 * @param assignmentType: string containing the type of the assignment - Hw, Quiz, Exam
	 * @param dueDate: string containing the date the assignment is due
	 * @param assignmentCourse: string containing the course the assingment is due in
	 * @param to: string containing the name of the recipient of the message
	 * @param from: string containing the sender of the message
	 * @param messageStatus: string containing the status of the message - request, approved, denied
	 * @author Elisa
	 */
	public Messages (String assignmentName, String assignmentType, 
			Date dueDate, String assignmentCourse, String to, String from, String messageStatus){
		assignment = assignmentName;
		type = assignmentType;
		date = dueDate;
		course = assignmentCourse;
		recipient = to;
		sender = from;
		status = messageStatus;
	}
	//TODO: javadoc
	/**
	 * @author Nick
	 */
	public void openMessagesDisplay(){
        JFrame f = new JFrame();
        f.setTitle("Messages");
        f.setLayout(new BorderLayout());
        JButton openButton = new JButton("Open Selected Message");
        JButton deleteButton = new JButton("Delete Selected Message");
		
        Panel p = new Panel(new BorderLayout());
        p.add(openButton, BorderLayout.NORTH);
        p.add(deleteButton, BorderLayout.SOUTH);
        
		String[] data = {"Message 1", "Message 2"};
		JList list = new JList(data);
		f.add(list,BorderLayout.NORTH);
		f.add(p,BorderLayout.SOUTH);
		
		f.pack();
		f.setSize(400,235);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		openButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(list.getSelectedValue()==null){
					JOptionPane.showMessageDialog(f, "No message was selected.","Error!",JOptionPane.ERROR_MESSAGE);
				}
				else if(list.getSelectedValue().equals("No messages to display.")){
					//Do nothing
				}
				else{
				JOptionPane.showMessageDialog(f, "Full text of the message.....", ""+list.getSelectedValue() ,JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		
		deleteButton.addActionListener(new ActionListener(){
			int positionToDelete=0;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedValue()==null){
					JOptionPane.showMessageDialog(f, "No message was selected.","Error!",JOptionPane.ERROR_MESSAGE);
				}
				else if(list.getSelectedValue().equals("No messages to display.")){
					//Do nothing
				}
				else{
					 positionToDelete=list.getSelectedIndex();
				int box = JOptionPane.showOptionDialog(f,"Are you sure you want to delete "+list.getSelectedValue()+"?", "Delete Message?",JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE, 
				        null, new String[]{"Delete", "Cancel"}, e);
				if(box==JOptionPane.OK_OPTION){
					data[positionToDelete]="";
					if(data[0].equals("") && data[1].equals("")){
						data[0]="No messages to display.";
					}
					SwingUtilities.updateComponentTreeUI(f);
				}
				}
				
			}
			
		});
	}
}
