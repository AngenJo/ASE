package makeYourDay.core;

import makeYourDay.interfaces.I_Building;

public class Building  extends Place implements I_Building{
	private Address address;
	
	public Building(Position position, Address address) {
		super(position);
		this.address = address;
	}
	
	public Building(String name, Address address) {
		super(name);
		this.address = address;
	}
	
	public Building(String name, Position position, Address address) {
		super(name, position);
		this.address = address;
	}
	
	public String getAddress() {
		return this.address.toString();
	}
}
