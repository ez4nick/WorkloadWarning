package edu.erau.SE300_WW;


import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**The help class creates either a swing or javaFX help dialog containing relevant
 * help information based off of where the user clicked the help menu from.
 * @author Nicholas Krawczyk
 *
 */
public class Help{
	String title; //Title of the dialog
	String labelText; //Help content
	int stageDimx;
	int stageDimy;
	
	/**This constructor generates the appropriate text and dimensions for the help dialog to be shown based off of where
	 * the request came from.
	 * @param location {@link String} where the request came from (login, calendar or assignment creator)
	 * @param item {@link String} which item within the help menu was clicked
	 */
	public Help(String location, String item){
		if(location.equals("login") && item.equals("gettingStarted")){
			//Help with logging in to the application
			stageDimx=300;
			stageDimy=300;
			title="Getting Started";
			labelText="The Workload Warning application is designed to make scheduling assignments"
					+ " easier for both students and faculty members. \n\nThe first step to using the application is logging in."
					+ " Your system administrator will provide you with a user ID which you must use to login. Please contact your"
					+ " system administrator if you have not been given this information.";
			openFXBox();
		}
		else if(location.equals("login") && item.equals("about")){
			//General info about the application
			stageDimx=350;
			stageDimy=250;
			title="About";
			labelText="The Workload Warning application was created in partial fulfillment of the requirements of"
					+ " the course SE 300 at Embry-Riddle Aeronautical University in the Fall of 2016. The following group members"
					+ " assisted with the development and testing of this application:\n\nNicholas Krawczyk\nElisa Hawley\nChianti Ghalson\nAlexandra Dipre\nThomas Ellis";
			openFXBox();
		}
		else if(location.equals("calendar") && item.equals("usingCal")){
			//help with using the calendar
			title="Using the Calendar";
			labelText="<html> <p align='justify'>"+"The Calendar display shows all of the assignments belonging to the user who is currently signed in."
					+ " Similar to normal calendars, you can switch between months to get to the desired month to view your assignments."
					+ " For assignments that do not exist in the calendar, you must navigate to 'File' -> 'Add an assignment' button in order to add"
					+ " a new assignment into the calendar. Navigating to 'File' -> 'Messages' will open a window showing any messages that may"
					+ " be waiting for you. It is important to regularly check the messages as their content will be time sensitive relating"
					+ " to the schedule of an assignment."+"</p> <html>";
			openSwingBox();
		}
		else if(location.equals("assignmentCreator") && item.equals("addAssign")){
			//Help with adding an assignment
			title="Adding an Assignment";
			labelText="<html> <p align='justify'>"+"The Assignment Creator allows you to add an assignment to the calendar that does not"
					+ " already exist in the database. All fields are required, and attempting to submit an assignment with incomplete"
					+ " information entered will create an error indicating so. It should be noted that whenever a teacher creates an assignment"
					+ " it is immediately added to the calendar and database as long as this assignment does not already exist in the database."
					+ " However, when a student creates an assignment it will first go to"
					+ " the teacher of the course for approval before it is saved to the database and shown in the calendar."+"</p> <html>";
			openSwingBox();
		}
		
		
		
		
	}
	
	/**
	 * This method creates a help dialog box using the swing package.
	 */
	public void openSwingBox(){
		JDialog diag = new JDialog();
		diag.setBounds(100,100,400,250);
		diag.setLocationRelativeTo(null);
		diag.setTitle(title);
		JLabel text = new JLabel(labelText);
		text.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		
		diag.add(text);
		diag.setVisible(true);
		
	}
	
	/**
	 * This method creates a help dialog box using the javaFX package.
	 */
	public void openFXBox(){
		Stage newStage = new Stage();
		newStage.setTitle(title);
		VBox comp = new VBox();
		comp.setPadding(new Insets(10, 10, 10, 10));
		Label text = new Label(labelText); 
		text.setWrapText(true);
		text.setTextAlignment(TextAlignment.JUSTIFY);
		comp.getChildren().add(text);
		Scene stageScene = new Scene(comp, stageDimx, stageDimy);
		newStage.setScene(stageScene);
		newStage.show();
	}
	
}
