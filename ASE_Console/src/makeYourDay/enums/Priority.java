package makeYourDay.enums;

public enum Priority{
	VERY_HIGH( 0 ), HIGH( 1 ), MEDIUM( 2 ), LOW( 3 ), VERY_LOW( 4 );
	
	private final int priority;
	
	Priority(int priority) {
		this.priority = priority;
	}
	public int getPriorityValue() {
		return this.priority;
	}
}