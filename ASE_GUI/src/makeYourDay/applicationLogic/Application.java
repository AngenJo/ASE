package makeYourDay.applicationLogic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import makeYourDay.core.CustomLinkedList;
import makeYourDay.interfaces.I_Application;

public class Application extends CustomLinkedList<Day> implements I_Application{
	public Application() {
		super();
	}
	
	public String[] getDays() {
		String[] returnValue = new String[linkedList.size()];
		for(int i = 0; i<linkedList.size();i++) {
			LocalDate currentDate = linkedList.get(i).getDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL YYYY");
			returnValue[i] = currentDate.format(formatter);
		}
		return returnValue;
	}
	
	public Day getDay(int index) {
		return linkedList.get(index);
	}
}
