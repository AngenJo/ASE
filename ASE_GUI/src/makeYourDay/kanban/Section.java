package makeYourDay.kanban;

import java.util.LinkedList;

import makeYourDay.core.CustomLinkedList;
import makeYourDay.core.Task;
import makeYourDay.enums.Topic;

import makeYourDay.interfaces.I_Section;

public abstract class Section<T1> extends CustomLinkedList<T1> implements I_Section{
	private Topic topic;
	
	public Section(Topic topic) {
		super();
		this.topic = topic;
	}
	public Topic getTopic() {
		return this.topic;
	}
	
	public LinkedList<Task> getTasks() {
		return (LinkedList<Task>) this.linkedList;
	}
}
