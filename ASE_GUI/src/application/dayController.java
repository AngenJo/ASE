package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import makeYourDay.applicationLogic.Application;
import makeYourDay.applicationLogic.Day;
import makeYourDay.core.Task;
import makeYourDay.enums.Priority;


public class dayController implements Initializable{
	@FXML
	private ListView<String> dayList;
	@FXML
	private Label dayLabel;
	@FXML
	private AnchorPane dayAnchor;
	
	String[] days = new String[2];
	
	int currentDayIndex;
	
	Application app;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// SETUP Kanban   ///////////////////////////////////////////////////////////////////
		//TASK
		Task task1 = new Task("Einkaufen", LocalDate.now(), Priority.HIGH);
		Task task2 = new Task("Sport", LocalDate.now(), Priority.LOW);
		Task task3 = new Task("Hausaufgaben", LocalDate.now(), Priority.MEDIUM);
		Task task4 = new Task("Gassigehen", LocalDate.now(), Priority.VERY_LOW);
		//Day             ///////////////////////////////////////////////////////////////////
		Day day1 = new Day(LocalDate.of(2022, 4, 11));
		day1.addItem(task1);
		day1.addItem(task2);
		Day day2 = new Day(LocalDate.of(2022, 4, 12));
		day2.addItem(task3);
		day2.addItem(task4);
		//Application      ///////////////////////////////////////////////////////////////////
		Application app  = new Application();
		app.addItem(day1);
		app.addItem(day2);
		days = app.getDays();
		/////////////////////////////////////////////////////////////////////////////////////
		
		
		this.app = app;
		
		
		
		dayList.getItems().addAll(days);
		dayLabel.setText(days[0]);
		
				dayList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				dayLabel.setText(arg2);
				currentDayIndex = Arrays.asList(days).indexOf(arg2);
			}
			
		});
	}
	
	@FXML
	private void loadKanban(ActionEvent event) throws IOException {
		Stage oldStage = (Stage)dayList.getScene().getWindow();
		oldStage.close();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Kanban.fxml"));
		Parent parent  = (Parent)  fxmlLoader.load();
		Stage stage =  new Stage();
		stage.setTitle("Make My Day");
		stage.getIcons().add(new Image("Icon.png"));
		stage.setScene(new Scene(parent));
		stage.show();
		new kanbanController((AnchorPane) parent, app.getDay(currentDayIndex).getKanban());
	}
	
	

}
