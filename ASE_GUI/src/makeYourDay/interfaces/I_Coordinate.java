package makeYourDay.interfaces;

import makeYourDay.enums.Direction;

public interface I_Coordinate {
	public final byte MAX_VALUE_SECOND_MINUTES = 60;
	public final byte MIN_VALUE_SECOND_MINUTES_DEGREE = 0;
	public final short MAX_VALUE_DEGREE = 180;

	public String toString();

	public Direction getDirection();
}
