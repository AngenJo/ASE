package makeYourDay.kanban;

import java.util.LinkedList;

import makeYourDay.core.Task;
import makeYourDay.enums.Topic;
import makeYourDay.interfaces.I_BenefitSection;

public class BenefitSection extends Section<Task> implements I_BenefitSection{
	public BenefitSection(Topic topic) {
		super(topic);
	}
	
	@Override
	public LinkedList<Task> getTasks(){
		return this.linkedList;
	}
}
