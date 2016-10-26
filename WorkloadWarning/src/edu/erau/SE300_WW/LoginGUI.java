package edu.erau.SE300_WW;

//push test


import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginGUI extends Application {
	public static String user;
	@Override
	public void start(Stage loginStage) throws Exception {
		
		//First check if the databse file exists
		File f = new File("C:/WorkloadWarning/CanvasDatabase.xlsx");
		if(!f.exists()){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning!");
			alert.setHeaderText("Databse Not Found");
			alert.setContentText("Databse file was not found. The databse file must be named 'CanvasDatabase.xlsx' and be located in 'C:/WorkloadWarning'.");
			alert.showAndWait();
		}
		
		//create the window
		Pane pane = new Pane();
		Scene scene = new Scene( pane, 350, 200);
		loginStage.setScene(scene);
		loginStage.setTitle("Workload Warning - Login");
		
		//create the buttons
		Button studentBtn, teacherBtn;
		studentBtn = new Button("Student");
		teacherBtn = new Button("Teacher");
		studentBtn.setLayoutX(100);
		studentBtn.setLayoutY(100);
		teacherBtn.setLayoutX(200);
		teacherBtn.setLayoutY(100);
		
		//create a label for time reaction
		Label label = new Label("Welcome! Login Below");
		label.setLayoutX(115);
		label.setLayoutY(50);
		
		
		//add buttons and labels to the pane
		pane.getChildren().add(studentBtn);
		pane.getChildren().add(teacherBtn);
		pane.getChildren().add(label);
		
		
		//button actions
		studentBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	user="Student";
            	StudentCalendar wlc = new StudentCalendar();
            	wlc.setVisible(true);
            	loginStage.close();
            }
            
        });
		
		teacherBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	user="Teacher";
            	InstructorCalendar ic = new InstructorCalendar();
            	ic.showInstructorCalendar();
            	loginStage.close();
            }
        });
		
		
		
		// Display the GUI
		loginStage.show();
		
	}
	

	
	public static void main(String[] args) { 
		
		launch(args); 
		
		
		}
		

}

