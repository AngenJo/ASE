package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ListOrganizer extends Application {



    @Override
    public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
		Scene scene = new Scene(root);
        stage.setScene(scene);
    	stage.resizableProperty().set(false);
    	stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}