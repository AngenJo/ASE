package makeYourDay.exceptions;

import makeYourDay.enums.Direction;

public class InvalidPositionException extends RuntimeException {
	public InvalidPositionException(Direction direction1, Direction direction2, Direction directionRecieved) {
		super("Invalid Position: Invalid driection transmittet. Must be "+ direction1 +" or "+direction2+" got " + directionRecieved+"!");
	}
}
