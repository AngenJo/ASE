package application;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class kanbanListCell extends ListCell<String> {
	public kanbanListCell() {
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
			if (event.getGestureSource() != thisCell && event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}

			event.consume();
		});

		setOnDragEntered(event -> {
			if (event.getGestureSource() != thisCell && event.getDragboard().hasString()) {
				setOpacity(0.3);
			}
		});

		setOnDragExited(event -> {
			if (event.getGestureSource() != thisCell && event.getDragboard().hasString()) {
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
				getListView().fireEvent(event);
				ObservableList<String> items = getListView().getItems();
				if (items.indexOf(db.getString()) == -1) {
					getListView().getItems().add(db.getString());
				} else {
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
		});

		setOnDragDone(event -> {
		if (event.getGestureTarget()!=null) {
			System.out.print(event.getTarget());
			System.out.print("\n");
				Dragboard db = event.getDragboard();
				if (db.getString() == getItem()) {
					getListView().getItems().remove(getItem());
				}
				event.consume();
		}
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
