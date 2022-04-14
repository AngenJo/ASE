package makeYourDay.interfaces;

import java.time.LocalDate;

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
	public LocalDate getDate();

	public void setDate(LocalDate date);

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
