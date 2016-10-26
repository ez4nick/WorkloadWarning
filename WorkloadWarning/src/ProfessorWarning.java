package src;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JLabel;

public class ProfessorWarning extends JFrame {

	private JPanel contentPane;
	AssignmentGUI g;

	/**
	 * Launch the application.
	 */
	
	public ProfessorWarning(AssignmentGUI g) {
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblAtLe = new JLabel("At least half of your students have two or more exams scheduled that day. ");
		contentPane.add(lblAtLe);
		
		JButton btnProceed = new JButton("Proceed");
		contentPane.add(btnProceed);
		
		JButton btnReschedule = new JButton("Reschedule");
		contentPane.add(btnReschedule);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
		btnProceed.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				g.showSuccessDialog();
				
				
			}
			
		});
		btnReschedule.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
			
		});
	}

}
