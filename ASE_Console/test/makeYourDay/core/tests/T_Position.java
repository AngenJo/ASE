package makeYourDay.core.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import makeYourDay.core.Coordinate;
import makeYourDay.core.Position;
import makeYourDay.enums.Direction;
import makeYourDay.exceptions.InvalidPositionException;

class T_Position {
	private final Coordinate vertical = new Coordinate(Direction.N, (short) 49, (byte) 1, (byte) 34);
	private final Coordinate horizontal = new Coordinate(Direction.E, (short) 8, (byte) 23, (byte) 7);

	private Position position;

	@Test
	void testCorrectPositionToString() {
		this.position = new Position(this.vertical, this.horizontal);
		String reievedToString = this.position.toString();
		String expectedToString = this.vertical.toString() + " " + this.horizontal.toString();
		assertEquals(expectedToString, reievedToString);
	}

	@Test
	void testWrongPositionHorizontal() {
		String errorRecieved = testException(this.vertical, this.vertical);
		String errorMessage = "Invalid Position: Invalid driection transmittet. Must be " + Direction.E + " or "
				+ Direction.W + " got " + this.vertical.getDirection() + "!";
		assertEquals(errorMessage, errorRecieved);
	}

	@Test
	void testWrongPositionVertical() {
		String errorRecieved = testException(this.horizontal, this.horizontal);
		String errorMessage = "Invalid Position: Invalid driection transmittet. Must be " + Direction.N + " or "
				+ Direction.S + " got " + this.horizontal.getDirection() + "!";
		assertEquals(errorMessage, errorRecieved);
	}

	String testException(Coordinate horizontal, Coordinate vertical) {
		InvalidPositionException exception = Assertions.assertThrows(InvalidPositionException.class, () -> {
			this.position = new Position(vertical, horizontal);
		});
		return exception.getMessage();
	}

}
