package makeYourDay.applicationLogic;

import java.time.LocalDate;

import makeYourDay.core.CustomLinkedList;
import makeYourDay.core.Task;
import makeYourDay.interfaces.I_Day;
import makeYourDay.kanban.Kanban;

public class Day extends CustomLinkedList<Task> implements I_Day{
	private LocalDate date;
	private Kanban kanban;
	
	public Day(LocalDate date) {
		super();
		this.date = date;
		this.kanban = new Kanban();
	}
	public Day(LocalDate date, Task task) {
		super();
		this.date = date;
		this.kanban = new Kanban();
		this.linkedList.add(task);
		this.kanban.addTask(task);
	}
	public LocalDate getDate() {
		return this.date;
	}
	public Kanban getKanban() {
		return this.kanban;
	}
	
	public void setKanban(Kanban kanban) {
		this.kanban = kanban;
	}
	
	@Override
	public void addItem(Task task) {
		this.kanban.addTask(task);
		this.linkedList.add(task);
	}
	
	@Override
	public Task removeItem(int index) {
		Task task = this.linkedList.remove(index);
		this.kanban.removeTask(task);
		return task;
	}
}
