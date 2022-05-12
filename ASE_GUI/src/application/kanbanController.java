package application;

import java.io.IOException;
import java.time.LocalTime;
import java.util.LinkedList;

import application.kanbanController;
import javafx.collections.ObservableList;
import javafx.event.EventTarget;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import makeYourDay.kanban.Kanban;
import makeYourDay.kanban.Section;
import makeYourDay.core.Task;
import makeYourDay.enums.Priority;
import makeYourDay.enums.Topic;

public class kanbanController {
	private Stage stage;
	private Scene scene;
	private AnchorPane anchorPane;
	private Kanban kanban;

	private LinkedList<ListView<String>> listViews;
	private LinkedList<Label> labels;

	public kanbanController(AnchorPane anchorPane, Kanban kanban) {
		this.anchorPane = anchorPane;
		this.scene = anchorPane.getScene();
		this.stage = (Stage) scene.getWindow();
		stage.setTitle("Make My Day");
		stage.getIcons().add(new Image("Icon.png"));
		stage.resizableProperty().set(false);
		this.kanban = kanban;
		this.listViews = new LinkedList<ListView<String>>();
		this.labels = new LinkedList<Label>();
		this.setNotes();
		this.initialize();
	}

	private void setNotes() {

		ObservableList<Node> nodes = anchorPane.getChildren();
		// toDo
		this.listViews.add((ListView<String>) nodes.get(9));
		this.labels.add((Label) nodes.get(10));
		// onGoing
		this.labels.add((Label) nodes.get(4));
		this.listViews.add((ListView<String>) nodes.get(5));
		// Done
		this.listViews.add(2, (ListView<String>) nodes.get(0));
		this.labels.add(2, (Label) nodes.get(1));
		// Backlog
		this.listViews.add((ListView<String>) nodes.get(3));
		this.labels.add((Label) nodes.get(4));
		// Shift
		this.listViews.add((ListView<String>) nodes.get(7));
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

		ListView<String> listview = listViews.get(topicValue);
		LinkedList<Task> tasks = new LinkedList<Task>();
		tasks.addAll(kanban.getSection(topicValue).getTasks());
		if (tasks.size() > 0) {
			String[] taskTitels = new String[tasks.size()];
			for (int i = 0; i < tasks.size(); i++) {
				taskTitels[i] = tasks.get(i).getName();
			}
			listview.getItems().addAll(taskTitels);
		}
		listview.setOnDragOver(event -> {
			if (event.getGestureSource() != listview && event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		});

		listview.setOnDragDropped(event -> {
			if (event.getGestureSource() == null) {
				return;
			}
			Dragboard db = event.getDragboard();
			if (db.hasString()) {
				listview.getItems().add(db.getString());
			}
			event.setDropCompleted(true);
			event.consume();
			changeTask(event.getGestureSource(), event.getTarget(), db.getString());
		});

		listview.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) {
				Node node = (Node) event.getTarget();
				if (node.getClass() == kanbanListCell.class || node.getParent().getClass() == kanbanListCell.class) {
					if (node.getParent().getClass() == kanbanListCell.class) {
						node = node.getParent();
					}
					kanbanListCell cell = (kanbanListCell) node;
					ListView<String> listView = (ListView<String>) cell.getListView();
					int targetIndex = getIndex(listView);
					Section<?> targetSection = kanban.getSection(targetIndex);
					Task task = targetSection.getTask(cell.getItem());
					listView.getItems().remove(cell.getItem());
					kanban.removeTask(task);
					try {
						openTaskWindow(task, listView);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		});

		listview.setCellFactory(param -> new kanbanListCell());
	}

	private void openTaskWindow(Task task, ListView<String> listView) throws IOException {
		AnchorPane secondaryLayout = FXMLLoader.load(getClass().getResource("Task.fxml"));

		Scene secondScene = new Scene(secondaryLayout);
		setUpTaskWindow(secondaryLayout, task ,listView);
		// New window (Stage)
		Stage newWindow = new Stage();
		newWindow.setTitle("Second Stage");
		newWindow.setScene(secondScene);
		newWindow.setOnCloseRequest(event ->{
			kanban.addTask(task);
			listView.getItems().add(task.getName());
		});

		// Specifies the modality for new window.
		newWindow.initModality(Modality.WINDOW_MODAL);

		// Specifies the owner Window (parent) for new window
		newWindow.initOwner(stage);
		newWindow.setTitle("Make My Day");
		newWindow.getIcons().add(new Image("Icon.png"));
		newWindow.resizableProperty().set(false);
		// Set position of second window, related to primary window.
		newWindow.show();
	}

	private void setUpTaskWindow(AnchorPane parent, Task task, ListView<String> listView) {
		ObservableList<Node> fields = parent.getChildren();
		for (Node node : fields) {
			if (node.getId() != null) {
				if (node.getId().equals("datePicker")) {
					DatePicker datePicker = (DatePicker) node;
					datePicker.setValue(task.getDate().toLocalDate());
				} else if (node.getId().equals("nameField")) {
					TextField nameField = (TextField) node;
					nameField.setText(task.getName());
				} else if (node.getId().equals("choicePriority")) {
					ChoiceBox<String> choicePriority = (ChoiceBox<String>) node;
					String[] prioStrings = { "Very High", "High", "Medium", "Low", "Very Low" };
					Priority priority = task.getPriority();
					int indexPriority = priority.getPriorityValue();
					ObservableList<String> priorities = choicePriority.getItems();
					priorities.addAll(prioStrings);
					choicePriority.setItems(priorities);
					choicePriority.setValue(prioStrings[indexPriority]);
				} else if (node.getId().equals("taskNumberField")) {
					TextField taskNumberField = (TextField) node;
					taskNumberField.setText(Integer.toString(task.getTaskNumber()));
				} else if (node.getId().equals("choiceTopic")) {
					ChoiceBox<String> choiceTopic = (ChoiceBox<String>) node;
					String[] topicStrings = { "To Do", "On Going", "Done", "Backlog", "Shift" };
					Topic topic = task.getCurrentTopic();
					int indexTopic = topic.getTopicValue();
					ObservableList<String> topics = choiceTopic.getItems();
					topics.addAll(topicStrings);
					choiceTopic.setItems(topics);
					choiceTopic.setValue(topicStrings[indexTopic]);
				} else if (node.getId().equals("noteArea")) {
					TextField noteArea = (TextField) node;
					noteArea.setText(task.getNote());
				} else if (node.getId().equals("buSave")) {
					Button buSave = (Button) node;
					buSave.setOnAction(event -> {
						saveTask(fields, task, listView);
						Scene scene = parent.getScene();
						Stage stage = (Stage)scene.getWindow();
						stage.close();
						kanban.addTask(task);
						
					});
				}
			}
		}
	}

	private void changeTask(Object source, EventTarget target, String Name) {
		ListView<String> listViewSource;
		ListView<String> listViewTarget;
		if (source.getClass() == kanbanListCell.class) {
			kanbanListCell listcellSource = (kanbanListCell) source;
			listViewSource = listcellSource.getListView();
		} else {
			listViewSource = (ListView<String>) source;
		}
		if (target.getClass() == kanbanListCell.class) {
			kanbanListCell listcellTarget = (kanbanListCell) target;
			listViewTarget = listcellTarget.getListView();
		} else {
			listViewTarget = (ListView<String>) target;
		}
		int sourceIndex = getIndex(listViewSource);
		int targetIndex = getIndex(listViewTarget);
		Section<?> sourceSection = kanban.getSection(sourceIndex);
		Section<?> targetSection = kanban.getSection(targetIndex);
		Task task = sourceSection.getTask(Name);
		kanban.removeTask(task);
		Topic targetTopic = targetSection.getTopic();
		task.setCurrentTopic(targetTopic);
		kanban.addTask(task);
	}

	private int getIndex(ListView<String> listview) {
		for (int i = 0; i < listViews.size(); i++) {
			if (listViews.get(i).equals(listview)) {
				return i;
			}
		}
		return -1;
	}
	

	public void saveTask(ObservableList<Node> fields, Task task, ListView<String> listView) {
		for (Node node : fields) {
			if (node.getId() != null) {
				if (node.getId().equals("datePicker")) {
					DatePicker datePicker = (DatePicker) node;
					task.setDate(datePicker.getValue().atTime(LocalTime.now()));
				} else if (node.getId().equals("nameField")) {
					TextField nameField = (TextField) node;
					task.setName(nameField.getText());
				} else if (node.getId().equals("choicePriority")) {
					ChoiceBox<String> choicePriority = (ChoiceBox<String>) node;
					String value = choicePriority.getValue();
					Priority newPrio = Priority.HIGH;
					newPrio.setPriority(value);
					task.setPriority(newPrio);
				} else if (node.getId().equals("choiceTopic")) {
					ChoiceBox<String> choiceTopic = (ChoiceBox<String>) node;
					String value = choiceTopic.getValue();
					Topic newTopic = Topic.BACKLOG;
					newTopic.setTopic(value);
					task.setCurrentTopic(newTopic);
				} else if (node.getId().equals("noteArea")) {
					TextField noteArea = (TextField) node;
					task.setNote(noteArea.getText());
					listView.getItems().add(task.getName());
				}
			}
		}
	}
}
