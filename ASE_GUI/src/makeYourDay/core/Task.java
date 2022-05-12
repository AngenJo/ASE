package makeYourDay.core;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

public class Task  implements I_Task{
	private String name;
	private int taskNumber;
	private LocalDateTime date;
	private Note note;
	private Priority priority;
	private Place place;
	private Topic currentTopic;
	//tag
	//repetition 

	public Task(String name, LocalDateTime date, Priority priority) {
		super();
		this.name = name;
		this.date = date;
		this.priority = priority;
		this.taskNumber = this.caculateTaskNumber(this.name);
		this.note = new Note();
		this.currentTopic = Topic.BACKLOG;

	}

	public Task(String name, LocalDateTime date, Priority priority, Place place) {
		this(name, date, priority);
		this.place = place;

	}

	public int caculateTaskNumber(String name) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy H:m:s:n");
			String string = name + date.format(formatter);
			byte[] hashBytes = md.digest(string.getBytes(StandardCharsets.UTF_8));
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
	public LocalDateTime getDate() {
		return this.date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	// note
	public String getNote() {
		return note.getNote();
	}

	public void setNote(String note) {
		this.note.writeNote(note);
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
