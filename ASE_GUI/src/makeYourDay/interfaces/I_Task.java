package makeYourDay.interfaces;

import java.time.LocalDateTime;

import makeYourDay.core.Note;
import makeYourDay.core.Place;
import makeYourDay.enums.Priority;
import makeYourDay.enums.Topic;

public interface I_Task {
	public int caculateTaskNumber(String name);

	// Getter and Setter for variables
	// name
	public String getName();

	public void setName(String name);

	// taskNumber
	public int getTaskNumber();

	public void setTaskNumber(int taskNumber);

	// date
	public LocalDateTime getDate();

	public void setDate(LocalDateTime date);

	// note
	public String getNote();

	public void setNote(String note);

	// priority
	public Priority getPriority();

	public void setPriority(Priority priority);

	// place
	public Place getPlace();

	public void setPlace(Place place);
	//currentTopic
	public Topic getCurrentTopic();
	
	public void setCurrentTopic(Topic currentTopic);
}
