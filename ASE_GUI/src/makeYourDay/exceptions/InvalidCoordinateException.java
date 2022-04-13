package makeYourDay.exceptions;

public class InvalidCoordinateException extends RuntimeException{
	public InvalidCoordinateException(String name, String min, String max, String value) {
		super("Incorrect coordinates exception: "+ name +" must be greater than "
				+ min + " and smaller than "
				+ max + ". "+name+" was " + value+ ".");
	}
}
