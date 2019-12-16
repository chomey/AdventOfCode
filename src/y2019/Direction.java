package y2019;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Direction {
	LEFT(1, -1, 0), RIGHT(2, 1, 0), UP(3, 0, 1), DOWN(4, 0, -1);

	final int code;
	final int xDelta;
	final int yDelta;

	public Direction opposite() {
		switch (this) {
			case LEFT:
				return RIGHT;
			case RIGHT:
				return LEFT;
			case DOWN:
				return UP;
			case UP:
				return DOWN;
		}
		throw new RuntimeException("Shouldn't happen");
	}

	public Direction left() {
		switch (this) {
			case LEFT:
				return DOWN;
			case RIGHT:
				return UP;
			case DOWN:
				return RIGHT;
			case UP:
				return LEFT;
		}
		throw new RuntimeException("Shouldn't happen");
	}

	public Direction right() {
		switch (this) {
			case LEFT:
				return UP;
			case RIGHT:
				return DOWN;
			case DOWN:
				return LEFT;
			case UP:
				return RIGHT;
		}
		throw new RuntimeException("Shouldn't happen");
	}
}
