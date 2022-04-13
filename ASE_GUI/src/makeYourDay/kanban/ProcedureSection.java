package makeYourDay.kanban;

import java.util.LinkedList;

import makeYourDay.enums.Topic;
import makeYourDay.core.Task;
import makeYourDay.enums.Priority;
import makeYourDay.interfaces.I_ProcedureSection;

public class ProcedureSection extends Section<Categorie> implements I_ProcedureSection{
	public ProcedureSection(Topic topic) {
		super(topic);
		this.linkedList = new LinkedList<Categorie>();
		Categorie veryHigh = new Categorie(Priority.VERY_HIGH);
		this.linkedList.add(veryHigh);
		Categorie high = new Categorie(Priority.HIGH);
		this.linkedList.add(high);
		Categorie medium = new Categorie(Priority.MEDIUM);
		this.linkedList.add(medium);
		Categorie low = new Categorie(Priority.LOW);
		this.linkedList.add(low);
		Categorie veryLow = new Categorie(Priority.VERY_LOW);
		this.linkedList.add(veryLow);
	}

	public void addTask(Task task) {
		Priority priority = task.getPriority();
		Categorie categorie = this.linkedList.get(priority.getPriorityValue());
		categorie.addItem(task);
	}

	public void removeTask(Task task) {
		Priority priority = task.getPriority();
		Categorie categorie = this.linkedList.get(priority.getPriorityValue());
		int index = categorie.getIndex(task);
		categorie.removeItem(index);
	}
	
	@Override
	public LinkedList<Task> getTasks() {
		LinkedList<Task> tasks = new LinkedList<Task>();
		for(int i = 0;i<this.linkedList.size();i++) {
			tasks.addAll(this.linkedList.get(i).getTasks());
		}
		return tasks;
	}
}
