package makeYourDay.exceptions;

public class InvalidHouseNumberException extends RuntimeException {

	public InvalidHouseNumberException() {
		super("Incorrect house number: House numbers must be greater than 0");
	}

}
