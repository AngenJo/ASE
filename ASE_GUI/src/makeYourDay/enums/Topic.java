package makeYourDay.enums;

public enum Topic {
	 TO_DO(0), ON_GOING(1), DONE(2),BACKLOG(3), SHIFT(4);

	private final int topic;
	
	Topic(int topic) {
		this.topic = topic;
	}
	public int getTopicValue() {
		return this.topic;
	}
}
