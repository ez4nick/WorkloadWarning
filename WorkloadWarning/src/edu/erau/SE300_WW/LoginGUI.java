package src.edu.erau.SE300_WW;

import java.awt.event.WindowEvent;

//push test


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class LoginGUI extends Application {
	public static String user; //Currently logged in user
	public static String dataseFilePath; //Path to the database file
	@Override
	public void start(Stage loginStage) throws Exception {
		
		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		Menu menuHelp = new Menu("Help");
		MenuItem help1 = new MenuItem("Getting Started");
		MenuItem help2 = new MenuItem("About");
		
		menuHelp.getItems().add(help1);
		menuHelp.getItems().add(help2);
		
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
		
		
		//First check if the database file exists
		File f = new File("C:/WorkloadWarning/CanvasDatabase.xlsx");
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
		pane.getChildren().add(menuBar);
		
		
		
		
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

