package makeYourDay.enums;

public enum Topic {
	 TO_DO(0), ON_GOING(1), DONE(2),BACKLOG(3), SHIFT(4);

	private int topic;
	
	Topic(int topic) {
		this.topic = topic;
	}
	public int getTopicValue() {
		return this.topic;
	}
	public void setTopic(String topic) {
		if(topic.equals("To Do")){
			this.topic = 0;
			return;
		} else if(topic.equals("On Going")) {
			this.topic = 1;
			return;
		}else if(topic.equals("Done")) {
			this.topic = 2;
			return;
		}else if(topic.equals("Backlog")) {
			this.topic = 3;
			return;
		}else if(topic.equals("Shift")) {
			this.topic = 4;
			return;
		}
	}
}
