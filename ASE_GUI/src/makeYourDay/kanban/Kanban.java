package makeYourDay.kanban;


import makeYourDay.core.CustomLinkedList;
import makeYourDay.core.Task;
import makeYourDay.enums.Topic;
import makeYourDay.interfaces.I_Kanban;


@SuppressWarnings("rawtypes")
public class Kanban extends CustomLinkedList<Section> implements I_Kanban{
	public Kanban() {
		super();
		ProcedureSection toDo = new ProcedureSection(Topic.TO_DO);
		this.linkedList.add(toDo);
		ProcedureSection onGoing = new ProcedureSection(Topic.ON_GOING);
		this.linkedList.add(onGoing);
		ProcedureSection done = new ProcedureSection(Topic.DONE);
		this.linkedList.add(done);
		BenefitSection backlog = new BenefitSection(Topic.BACKLOG);
		this.linkedList.add(backlog);
		BenefitSection shift = new BenefitSection(Topic.SHIFT);
		this.linkedList.add(shift);
	}

	public void addTask(Task task) {
		Topic topic = task.getCurrentTopic();
		if(topic.getTopicValue() <=2 ) {
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
		if(topic.getTopicValue() <=2 ) {
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
	
	public Section getSection(int index) {
		return linkedList.get(index);
	}
}
