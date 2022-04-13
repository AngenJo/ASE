package makeYourDay.core;

import java.time.LocalDate;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import makeYourDay.enums.Priority;
import makeYourDay.enums.Topic;
import makeYourDay.interfaces.I_Task;

/**
 *
 * @author angenjo1
 */

public class Task extends CustomLinkedList<Task> implements I_Task{
	private String name;
	private int taskNumber;
	private LocalDate date;
	private Note note;
	private Priority priority;
	private Place place;
	private Topic currentTopic;
	//tag
	//repetition 

	public Task(String name, LocalDate date, Priority priority) {
		super();
		this.name = name;
		this.date = date;
		this.priority = priority;
		this.taskNumber = this.caculateTaskNumber(this.name);
		this.note = new Note();
		this.currentTopic = Topic.BACKLOG;

	}

	public Task(String name, LocalDate date, Priority priority, Place place) {
		this(name, date, priority);
		this.place = place;

	}

	public int caculateTaskNumber(String name) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = md.digest(name.getBytes(StandardCharsets.UTF_8));
			return hashBytes.hashCode();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return -1;
		}
	}

	// Getter and Setter for variables
	// name
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	// taskNumber
	public int getTaskNumber() {
		return this.taskNumber;
	}

	public void setTaskNumber(int taskNumber) {
		this.taskNumber = taskNumber;
	}

	// date
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	// note
	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	// priority
	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	// place
	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}
	//currentTopic
	public Topic getCurrentTopic() {
		return currentTopic;
	}

	public void setCurrentTopic(Topic currentTopic) {
		this.currentTopic = currentTopic;
	}

}
