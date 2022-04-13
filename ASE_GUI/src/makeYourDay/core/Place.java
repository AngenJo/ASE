package makeYourDay.core;

import makeYourDay.interfaces.I_Place;

public class Place implements I_Place{
	private Position position;
	private String name;

	public Place(String name, Position position) {
		this.name = name;
		this.position = position;
	}

	public Place(String name) {
		this(name,null);
	}

	public Place(Position position) {
		this(null, position);
	}

	public String getName() {
		return this.name;
	}

	public String getPosition() {
		return (position != null ? this.position.toString() : "No position assigned!");
	}
}
