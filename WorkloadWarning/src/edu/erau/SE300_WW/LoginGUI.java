package edu.erau.SE300_WW;


import java.io.File;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoginGUI extends Application {
	public static String userType;         //Currently logged in type of user ('Teacher' or 'Student')
	public static String currentUserName;  //User name of the currently logged in user
	public static String dataseFilePath;   //Path to the database file
	public static Database databaseShared; //Database object to be shared by all classes
	
	
	
	@Override
	public void start(Stage loginStage) throws Exception {
		
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		Menu menuHelp = new Menu("Help");
		MenuItem help1 = new MenuItem("Getting Started");
		MenuItem help2 = new MenuItem("About");
		MenuItem item1 = new MenuItem("Quit");
		
		menuHelp.getItems().add(help1);
		menuHelp.getItems().add(help2);
		menuFile.getItems().add(item1);
		
		menuBar.getMenus().addAll(menuFile,menuHelp);
		menuBar.prefWidthProperty().bind(loginStage.widthProperty());
		
		help1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				Help h = new Help("login","gettingStarted");		
			}
			
		});
		
		help2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				Help h = new Help("login","about");
			}
			
		});
		
		item1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the Workload Warning Application?","Quit?",JOptionPane.YES_NO_OPTION);
				
				if(x==0){
					//Quit
					loginStage.close();
				}
				else if(x==1){
					// Cancel the quit request, do nothing. Window is closed automatically...
				}
			}
			
		});
		
		
		//First check if the database file exists
		File f = new File("C:/WorkloadWarning/CanvasDatabase.xlsx");
		//File f = new File("CanvasDatabase.xlsx");
		if(!f.exists()){
			
			ButtonType locateButton = new ButtonType("Locate");
			ButtonType quitButton = new ButtonType("Quit");
			//Show warning alert
			Alert alert = new Alert(AlertType.WARNING,"",locateButton,quitButton);
			alert.setTitle("Warning!");
			alert.setHeaderText("Databse Not Found");
			alert.setContentText("Databse file was not found. The databse file must now be located.");
			alert.showAndWait();
			
			if(alert.getResult().getText().equals("Locate")){
				//Locate button was pressed, Show file chooser to pick the correct file
				
				
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().addAll(
		                new FileChooser.ExtensionFilter("XLSX", "*.xlsx")
		            );
				fileChooser.setTitle("Open Database File");
				
				File selectedFile =fileChooser.showOpenDialog(loginStage);
				if(selectedFile.exists()){
					dataseFilePath=selectedFile.getAbsolutePath();
					databaseShared=new Database(new File(selectedFile.getAbsolutePath()));
				}
				
			
			}
			else{
				//Quit was pressed, quit the application
				Platform.exit();
			    System.exit(0);
			}
			System.out.println();
			
			
		}
		else{
			//File was found in default location
			dataseFilePath="C:/WorkloadWarning/CanvasDatabase.xlsx";
			databaseShared=new Database(new File("C:/WorkloadWarning/CanvasDatabase.xlsx"));
		}
		
		//create the window
		Pane pane = new Pane();
		Scene scene = new Scene( pane, 350, 200);
		loginStage.setScene(scene);
		loginStage.setTitle("Workload Warning - Login");
		
		//Create user name text field
		TextField userNameField;
		userNameField = new TextField("username");
		userNameField.setLayoutX(100);
		userNameField.setLayoutY(80);
		
		//create the buttons
		Button loginBtn;
		loginBtn = new Button("Login");
		loginBtn.setLayoutX(100);
		loginBtn.setLayoutY(120);
		//create a label for time reaction
		Label label = new Label("Welcome! Login Below");
		label.setLayoutX(115);
		label.setLayoutY(50);
		
		
		//add buttons, labels and textField to the pane
		pane.getChildren().add(loginBtn);
		pane.getChildren().add(label);
		pane.getChildren().add(menuBar);
		pane.getChildren().add(userNameField);
		
		
		
		
		//button actions
		loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	
            	currentUserName=userNameField.getText(); //Replace with the name of the student who is logged in
            	int UserNameTest = databaseShared.isUser(currentUserName);
            	if(UserNameTest==0){
            		userNameField.setText("login failed!");
            	}
            	else if(UserNameTest==1){
            		userType="Student";
            		StudentCalendar wlc = new StudentCalendar();
                	wlc.setVisible(true);
                	loginStage.close();
            	}
            	else if(UserNameTest==2){
            		userType="Teacher";
            		InstructorCalendar ic = new InstructorCalendar();
                	ic.showInstructorCalendar();
                	loginStage.close();
                	
            	}
            	
            }
            
        });
		
		
		// Display the GUI
		loginStage.show();
		
	}
	

	
	public static void main(String[] args) { 
		
		launch(args); 
		
		
		}
		

}

