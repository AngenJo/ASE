package makeYourDay.enums;

public enum Priority{
	VERY_HIGH( 0 ), HIGH( 1 ), MEDIUM( 2 ), LOW( 3 ), VERY_LOW( 4 );
	
	private int priority;
	
	Priority(int priority) {
		this.priority = priority;
	}
	public int getPriorityValue() {
		return this.priority;
	}
	
	public void setPriority(String priority) {
		if(priority.equals("Very High")){
			this.priority = 0;
			return;
		} else if(priority.equals("High")) {
			this.priority = 1;
			return;
		}else if(priority.equals("Medium")) {
			this.priority = 2;
			return;
		}else if(priority.equals("Low")) {
			this.priority = 3;
			return;
		}else if(priority.equals("Very Low")) {
			this.priority = 4;
			return;
		}
	}
}