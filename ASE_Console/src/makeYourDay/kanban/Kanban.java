package makeYourDay.kanban;

import makeYourDay.core.CustomLinkedList;
import makeYourDay.core.Task;
import makeYourDay.enums.Topic;
import makeYourDay.interfaces.I_Kanban;


@SuppressWarnings("rawtypes")
public class Kanban extends CustomLinkedList<Section> implements I_Kanban{
	public Kanban() {
		super();
		BenefitSection backlog = new BenefitSection(Topic.BACKLOG);
		this.linkedList.add(backlog);
		ProcedureSection toDo = new ProcedureSection(Topic.TO_DO);
		this.linkedList.add(toDo);
		ProcedureSection onGoing = new ProcedureSection(Topic.ON_GOING);
		this.linkedList.add(onGoing);
		ProcedureSection done = new ProcedureSection(Topic.DONE);
		this.linkedList.add(done);
		BenefitSection shift = new BenefitSection(Topic.SHIFT);
		this.linkedList.add(shift);
		BenefitSection remove = new BenefitSection(Topic.REMOVE);
		this.linkedList.add(remove);
	}

	public void addTask(Task task) {
		Topic topic = task.getCurrentTopic();
		if(topic.getTopicValue() == 2 || topic.getTopicValue() == 3 || topic.getTopicValue() == 4) {
			ProcedureSection section =  (ProcedureSection)this.linkedList.get(topic.getTopicValue());
			section.addTask(task);
		}
		else {
			BenefitSection section =  (BenefitSection)this.linkedList.get(topic.getTopicValue());
			section.addItem(task);
		}
	}

	public void removeTask(Task task) {
		Topic topic = task.getCurrentTopic();
		if(topic.getTopicValue() == 2 || topic.getTopicValue() == 3 || topic.getTopicValue() == 4) {
			ProcedureSection section =  (ProcedureSection)this.linkedList.get(topic.getTopicValue());
			section.removeTask(task);
		}
		else {
			BenefitSection section =  (BenefitSection)this.linkedList.get(topic.getTopicValue());
			int index = section.getIndex(task);
			section.removeItem(index);
		}
	}
	
	public void moveTask(Task task, Topic newTopic) {
		this.removeTask(task);
		task.setCurrentTopic(newTopic);
		this.addTask(task);
	}
}
