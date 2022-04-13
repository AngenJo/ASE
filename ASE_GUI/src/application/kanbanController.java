package application;

import java.util.LinkedList;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import makeYourDay.kanban.Kanban;
import makeYourDay.core.Note;
import makeYourDay.core.Task;

public class kanbanController {
	private AnchorPane anchorPane;
	private Kanban kanban;

	private LinkedList<ListView> listViews;
	private LinkedList<Label> labels;

	public kanbanController(AnchorPane anchorPane, Kanban kanban) {
		this.anchorPane = anchorPane;
		this.kanban = kanban;
		this.listViews = new LinkedList<ListView>();
		this.labels = new LinkedList<Label>();
		this.setNotes();
		this.initialize();
	}

	private void setNotes() {

		ObservableList<Node> nodes = anchorPane.getChildren();
		// toDo
		this.listViews.add((ListView) nodes.get(9));
		this.labels.add((Label) nodes.get(10));
		// onGoing
		this.labels.add((Label) nodes.get(4));
		this.listViews.add((ListView) nodes.get(5));
		// Done
		this.listViews.add(2, (ListView) nodes.get(0));
		this.labels.add(2, (Label) nodes.get(1));
		// Backlog
		this.listViews.add((ListView) nodes.get(3));
		this.labels.add((Label) nodes.get(4));
		// Shift
		this.listViews.add((ListView) nodes.get(7));
		this.labels.add((Label) nodes.get(8));
		// Title
		this.labels.add((Label) nodes.get(2));
	}

	private void initialize() {
		for (int i = 0; i < 5; i++) {
			setListViewContent(i);
		}
	}
	
	private void setListViewContent(int topicValue) {

		ListView listview = listViews.get(topicValue);
		LinkedList<Task> tasks = new LinkedList<Task>();
		tasks.addAll(kanban.getSection(topicValue).getTasks());
		if (tasks.size() > 0) {
			String[] taskTitels = new String[tasks.size()];
			for (int i = 0; i < tasks.size(); i++) {
				taskTitels[i] = tasks.get(i).getName();
			}
		 listview.getItems() .addAll(taskTitels);
		}
		
		listview.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {	
				makeDraggable((Node)arg0.getTarget());
			}
			
		});

	}
	private double startX;
	private double startY;
	
	private void makeDraggable(Node node) {
		node.setOnMousePressed(e -> {
			startX= e.getSceneX() - node.getTranslateX();
			startY= e.getSceneY() - node.getTranslateY();
		});
		
		node.setOnMouseDragged(e -> {
			node.setTranslateX(e.getSceneX() - startX);
			node.setTranslateY(e.getSceneY() - startY);
		});
	}
}