package makeYourDay.core.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import makeYourDay.core.Coordinate;
import makeYourDay.enums.Direction;
import makeYourDay.exceptions.InvalidCoordinateException;
class T_Coordinate {
	public final byte MAX_VALUE_SECOND_MINUTES = 60;
	public final byte MIN_VALUE_SECOND_MINUTES_DEGREE = 0;
	public final short MAX_VALUE_DEGREE = 180;
	

	
	private final Direction direction = Direction.N;
	
	private final short degree = 49;
	private final short degreeToHigh = -1;
	private final short degreeToLow = 181;
	
	private final byte minute = 1;
	private final byte minuteToHigh = 61;
	private final byte minuteToLow = -1;
	
	private final byte second = 34;
	private final byte secondToHigh = 61;
	private final byte secondToLow = -1;
	
	
	private Coordinate coordinate;

	@Test
	void testCorrectCoordinateToString() {
		this.coordinate = new Coordinate(direction, degree, minute, second);
		String recievedToString = this.coordinate.toString();
		String expectedToString = String.valueOf(this.degree) + "°" + String.valueOf(this.minute) + "'" + String.valueOf(this.second)
		+ "\"" + direction;
		assertEquals(expectedToString, recievedToString);
	}
	@Test
	void testWrongToHighDegreeCoordinate() {
		String errorRecieved = testInvalidCoordinateException(direction, degreeToHigh, minute, second);
		String errorMessage = "Incorrect coordinates exception: " + "Degree must be greater than "
				+ String.valueOf(MIN_VALUE_SECOND_MINUTES_DEGREE) + " and smaller than "
				+ String.valueOf(MAX_VALUE_DEGREE) + ". Degree was " + String.valueOf(degreeToHigh)+".";
		assertEquals(errorMessage, errorRecieved);
		
	}
	@Test
	void testWrongToLowDegreeCoordinate() {
		String errorRecieved = testInvalidCoordinateException(direction, degreeToLow, minute, second);
		String errorMessage = "Incorrect coordinates exception: " + "Degree must be greater than "
				+ String.valueOf(MIN_VALUE_SECOND_MINUTES_DEGREE) + " and smaller than "
				+ String.valueOf(MAX_VALUE_DEGREE) + ". Degree was " + String.valueOf(degreeToLow)+".";
		assertEquals(errorMessage, errorRecieved);
	}
	
	@Test
	void testWrongToHighMinuteCoordinate() {
		String errorRecieved = testInvalidCoordinateException(direction, degree, minuteToHigh, second);
		String errorMessage = "Incorrect coordinates exception: " + "Minute must be greater than "
				+ String.valueOf(MIN_VALUE_SECOND_MINUTES_DEGREE) + " and smaller than "
				+ String.valueOf(MAX_VALUE_SECOND_MINUTES) + ". Minute was " + String.valueOf(minuteToHigh)+".";
		assertEquals(errorMessage, errorRecieved);
	}
	@Test
	void testWrongToLowMinuteCoordinate() {
		String errorRecieved = testInvalidCoordinateException(direction, degree, minuteToLow, second);
		String errorMessage = "Incorrect coordinates exception: " + "Minute must be greater than "
				+ String.valueOf(MIN_VALUE_SECOND_MINUTES_DEGREE) + " and smaller than "
				+ String.valueOf(MAX_VALUE_SECOND_MINUTES) + ". Minute was " + String.valueOf(minuteToLow)+".";
		assertEquals(errorMessage, errorRecieved);
	}
	
	@Test
	void testWrongToHighSecondCoordinate() {
		String errorRecieved = testInvalidCoordinateException(direction, degree, minute, secondToHigh);
		String errorMessage ="Incorrect coordinates exception: " + "Second must be greater than "
				+ String.valueOf(MIN_VALUE_SECOND_MINUTES_DEGREE) + " and smaller than "
				+ String.valueOf(MAX_VALUE_SECOND_MINUTES) + ". Second was " + String.valueOf(secondToHigh)+".";
		assertEquals(errorMessage, errorRecieved);
	}
	@Test
	void testWrongToLowSecondCoordinate() {
		String errorRecieved = testInvalidCoordinateException(direction, degree, minute, secondToLow);
		String errorMessage ="Incorrect coordinates exception: " + "Second must be greater than "
				+ String.valueOf(MIN_VALUE_SECOND_MINUTES_DEGREE) + " and smaller than "
				+ String.valueOf(MAX_VALUE_SECOND_MINUTES) + ". Second was " + String.valueOf(secondToLow)+".";
		assertEquals(errorMessage, errorRecieved);
	}

	String testInvalidCoordinateException(Direction direction, short degree, byte minute, byte second) {
		InvalidCoordinateException exception = Assertions.assertThrows(InvalidCoordinateException.class, () ->{
			this.coordinate = new Coordinate(direction,degree,minute,second);
		});
		return  exception.getMessage();
	}
}
