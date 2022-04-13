package makeYourDay.exceptions;

public class InvalidNoteLengthException extends RuntimeException{
	public InvalidNoteLengthException(int index, int length) {
		super("Incorrect note text length: To mutch symbols! Note length cant be greater than " + String.valueOf(index)+". Was "+String.valueOf(length)+" symbols.");
	}

}
