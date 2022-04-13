package makeYourDay.core;

import makeYourDay.enums.Direction;
import makeYourDay.exceptions.InvalidCoordinateException;
import makeYourDay.interfaces.I_Coordinate;

public class Coordinate implements I_Coordinate {
	private short degree;
	private byte minute;
	private byte second;
	Direction direction;

	public Coordinate(Direction direction, short degree, byte minute, byte second) {
		this.direction = direction;
		if (second <= MAX_VALUE_SECOND_MINUTES && second >= MIN_VALUE_SECOND_MINUTES_DEGREE) {
			this.second = second;
		} else {
			throw new InvalidCoordinateException("Second", String.valueOf(MIN_VALUE_SECOND_MINUTES_DEGREE),
					String.valueOf(MAX_VALUE_SECOND_MINUTES), String.valueOf(second));
		}
		if (minute <= MAX_VALUE_SECOND_MINUTES && minute >= MIN_VALUE_SECOND_MINUTES_DEGREE) {
			this.minute = minute;
		} else {
			throw new InvalidCoordinateException("Minute", String.valueOf(MIN_VALUE_SECOND_MINUTES_DEGREE),
					String.valueOf(MAX_VALUE_SECOND_MINUTES), String.valueOf(minute));
		}
		if (degree <= MAX_VALUE_DEGREE && degree >= MIN_VALUE_SECOND_MINUTES_DEGREE) {
			this.degree = degree;
		} else {
			throw new InvalidCoordinateException("Degree", String.valueOf(MIN_VALUE_SECOND_MINUTES_DEGREE),
					String.valueOf(MAX_VALUE_DEGREE), String.valueOf(degree));
		}
	}

	public String toString() {
		String coordinate;
		coordinate = String.valueOf(this.degree) + "°" + String.valueOf(this.minute) + "'" + String.valueOf(this.second)
				+ "\"" + direction;
		return coordinate;
	}

	public Direction getDirection() {
		return this.direction;
	}

}
