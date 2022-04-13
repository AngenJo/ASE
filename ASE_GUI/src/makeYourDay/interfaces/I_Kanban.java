package makeYourDay.interfaces;

import makeYourDay.core.Task;
import makeYourDay.enums.Topic;

public interface I_Kanban {
	public void addTask(Task task);

	public void removeTask(Task task);
	
	public void moveTask(Task task, Topic newTopic) ;
}
