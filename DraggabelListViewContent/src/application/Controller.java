package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class Controller implements Initializable{
	@FXML
	ListView<String> list1;
	@FXML
	ListView<String> list2;
	
    private static final ObservableList<String> fruits = FXCollections.observableArrayList(
            "Bannane",
            "Apfel",
            "Birne",
            "Annanas"
    );
    
    private static final ObservableList<String> fruits2 = FXCollections.observableArrayList(
            "Bannane2",
            "Apfel2",
            "Birne2",
            "Annanas2"
    );

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		list1.getItems().addAll(fruits);
        
		list1.setCellFactory(param -> new BirdCell());
		
		list2.getItems().addAll(fruits2);
        
		list2.setCellFactory(param -> new BirdCell());
		
		initializeList(list1);
		initializeList(list2);
	}
	
	
	
	
	
	
    private void initializeList(ListView<String> list) {
    	list.setOnDragOver(event -> {
            if (event.getGestureSource() != list &&
                   event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });
    	
    	list.setOnDragDropped(event -> {
    		Dragboard db = event.getDragboard();
    		if( db.hasString()) {
    			list.getItems().add(db.getString());
    		}
    		event.setDropCompleted(true);
            event.consume();
    	});
    	
    	
		if(list.getItems().isEmpty()) {
			
		}
		
	}






	public class BirdCell extends ListCell<String> {
        private final ImageView imageView = new ImageView();

        public BirdCell() {
            ListCell thisCell = this;



            setOnDragDetected(event -> {
                if (getItem() == null) {
                    return;
                }

                ObservableList<String> items = getListView().getItems();

                Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(getItem());
                dragboard.setContent(content);

                event.consume();
            });

            setOnDragOver(event -> {
                if (event.getGestureSource() != thisCell &&
                       event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            });

            setOnDragEntered(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    setOpacity(0.3);
                }
            });

            setOnDragExited(event -> {
                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    setOpacity(1);
                }
            });

            setOnDragDropped(event -> {
                if (getItem() == null) {
                    return;
                }

                Dragboard db = event.getDragboard();
                boolean success = false;

                if (db.hasString()) {

                    ObservableList<String> items = getListView().getItems();
                    if(items.indexOf(db.getString())==-1) {
                    	getListView().getItems().add(db.getString());
                    }
                    else {
                    int draggedIdx = items.indexOf(db.getString());
                    int thisIdx = items.indexOf(getItem());
                    
                    items.set(draggedIdx, getItem());
                    items.set(thisIdx, db.getString());

                    List<String> itemscopy = new ArrayList<>(getListView().getItems());
                    getListView().getItems().setAll(itemscopy);
                    }
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume(); 
                //asdas
            });

            setOnDragDone(event ->{
            	Dragboard db = event.getDragboard();
            	if(db.getString() == getItem()) {
            		getListView().getItems().remove(getItem());
            	}
            	event.consume();
            });
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                setText(item);
            }
        }
    }
}
