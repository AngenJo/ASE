package makeYourDay.applicationLogic;

import makeYourDay.core.CustomLinkedList;
import makeYourDay.interfaces.I_Application;

public class Application extends CustomLinkedList<Day> implements I_Application{
	public Application() {
		super();
	}
}
