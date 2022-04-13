package makeYourDay.core;

import makeYourDay.exceptions.InvalidHouseNumberException;
import makeYourDay.interfaces.I_Address;

public class Address implements I_Address {
	private String street;
	private int houseNumber;
	private String addressAdditive;
	private String city;
	private String country;
	private String postCode;
	private String postCodeAdditive;
	
	public Address() {
		super();
	}
	
	public Address(String street, int houseNumber, String country, String city, String postCode) throws InvalidHouseNumberException {
		this.street = street;
		if (houseNumber > 0) {
			this.houseNumber = houseNumber;
		} else {
			throw new InvalidHouseNumberException();
		}
		this.country = country;
		this.city = city;
		this.postCode = postCode;
	}

	public Address(String street, int houseNumber, String addressAdditive, String country, String city, String postCode,
			String postCodeAdditive) throws InvalidHouseNumberException {
		this(street, houseNumber, country, city, postCode);
		this.addressAdditive = addressAdditive;
		this.postCodeAdditive = postCodeAdditive;
	}

	public String toString() {
		String address = this.street + " " + this.houseNumber + "\n";
		if (this.addressAdditive != null && !this.addressAdditive.isBlank()) {
			address += this.addressAdditive + "\n";
		}
		address += this.postCode + " " + this.city + "\n";
		if (this.postCodeAdditive != null && !this.postCodeAdditive.isBlank()) {
			address += this.postCodeAdditive + "\n";
		}
		address += this.country;
		return address;
	}
}