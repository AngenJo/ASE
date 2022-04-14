package makeYourDay.kanban;

import java.util.LinkedList;

import makeYourDay.core.CustomLinkedList;
import makeYourDay.core.Task;
import makeYourDay.enums.Priority;
import makeYourDay.interfaces.I_Categorie;

public class Categorie extends CustomLinkedList<Task> implements I_Categorie{
	private Priority priority;

	public Categorie(Priority priority) {
		super();
		this.priority = priority;
	}

	@Override
	public void addItem(int index, Task task) {
		task.setPriority(this.priority);
		this.linkedList.add(index, task);
	}
	
	public LinkedList<Task> getTasks() {
		return this.linkedList;
	}

	public Priority getPriority() {
		return this.priority;
	}
	
	public Task getTask(String Name) {
		LinkedList<Task> tasks =(LinkedList<Task>) this.linkedList;
		for(Task task:tasks) {
			if(task.getName() == Name) {
				return task;
			}
		}
		return null;
	}
}
