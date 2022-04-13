package makeYourDay.core.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import makeYourDay.core.Coordinate;
import makeYourDay.core.Position;
import makeYourDay.enums.Direction;

class T_Place {
	private final Coordinate vertical = new Coordinate(Direction.N, (short) 49, (byte) 1, (byte) 34);
	private final Coordinate horizontal = new Coordinate(Direction.E, (short) 8, (byte) 23, (byte) 7);	
	private final Position position = new Position(vertical, horizontal);
	
	private final String nameToLong ="Lorem ipsum dolor";
	private final String nameAcceptabel="Lorem ipsum dolo";

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
