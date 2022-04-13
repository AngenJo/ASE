package makeYourDay.enums;

public enum Topic {
	BACKLOG(0), TO_DO(1), ON_GOING(2), DONE(3),REMOVE(4), SHIFT(5);

	private final int topic;
	
	Topic(int topic) {
		this.topic = topic;
	}
	public int getTopicValue() {
		return this.topic;
	}
}
