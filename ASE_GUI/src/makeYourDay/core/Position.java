package makeYourDay.core;

import makeYourDay.enums.Direction;
import makeYourDay.exceptions.InvalidPositionException;
import makeYourDay.interfaces.I_Position;

public class Position implements I_Position {
	private Coordinate horizontal;
	private Coordinate vertical;

	public Position(Coordinate vertical, Coordinate horizontal) {
		if (vertical.getDirection() == Direction.N || vertical.getDirection() == Direction.S) {
			this.vertical = vertical;
		} else {
			throw new InvalidPositionException(Direction.N, Direction.S, vertical.getDirection());
		}
		if (horizontal.getDirection() == Direction.W || horizontal.getDirection() == Direction.E) {
			this.horizontal = horizontal;
		} else {
			throw new InvalidPositionException(Direction.E, Direction.W, vertical.getDirection());
		}
	}

	public Position(Direction vertical_direction, short vertical_degree, byte vertical_minute, byte vertical_second,
			Direction horizontal_direction, short horizontal_degree, byte horizontal_minute, byte horizontal_second) {
		Coordinate vertical = new Coordinate(vertical_direction, vertical_degree, vertical_minute, vertical_second);

		if (vertical.getDirection() == Direction.N || vertical.getDirection() == Direction.S) {
			this.vertical = vertical;
		} else {
			throw new InvalidPositionException(Direction.N, Direction.S, vertical.getDirection());
		}
		Coordinate horizontal = new Coordinate(horizontal_direction, horizontal_degree, horizontal_minute,
				horizontal_second);
		if (horizontal.getDirection() == Direction.W || horizontal.getDirection() == Direction.E) {
			this.horizontal = horizontal;
		} else {
			throw new InvalidPositionException(Direction.E, Direction.W, vertical.getDirection());
		}
	}

	public String toString() {
		String position = this.vertical.toString() + " " + this.horizontal.toString();
		return position;
	}
}
