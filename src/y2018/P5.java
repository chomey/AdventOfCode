package y2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class P5 {
	static final int LEFT = -1;
	static final int RIGHT = 1;

	static Set<LineThruple> alreadyAdded = new HashSet<>();

	public static void main(String[] args) throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		List<String> inputList = new ArrayList<>();
		while (true) {
			String next = br.readLine();
			if (next.isEmpty()) {
				break;
			}
			inputList.add(next);
		}

		//Find first straight down from x, y
		//Find surrounding two walls
		//Fill from bottom up; see if there's any lines to the left or right
		//If right, continue right, if left, continue left, if both, continue both

		TreeMap<Integer, Set<Line>> verticalLines = new TreeMap<>();
		TreeMap<Integer, Set<Line>> horizontalLines = new TreeMap<>();
		for (String inputStr : inputList) {
			String[] parts = inputStr.split("[, =.]");
			if (inputStr.startsWith("x")) {
				int x = Integer.parseInt(parts[1]);
				if (!verticalLines.containsKey(x)) {
					verticalLines.put(x, new HashSet<>());
				}
				verticalLines.get(x).add(new Line(x, Integer.parseInt(parts[4]), Integer.parseInt(parts[6])));
			} else {
				int y = Integer.parseInt(parts[1]);
				if (!horizontalLines.containsKey(y)) {
					horizontalLines.put(y, new HashSet<>());
				}
				horizontalLines.get(y).add(new Line(y, Integer.parseInt(parts[4]), Integer.parseInt(parts[6])));
			}
		}
		System.out.println(countSquares(horizontalLines.lastKey(), 500, 0, horizontalLines, verticalLines));
	}

	private static int countSquares(int bottomValue, int currX, int currY, Map<Integer, Set<Line>> horizontalLines, Map<Integer, Set<Line>> verticalLines) {
		int squares = 0;
		Line nextLineDown = findNextLineDown(currX, currY, horizontalLines);
		if (nextLineDown == null) {
			squares += bottomValue - currY;
			return squares;
		}
		int lineCurrY = nextLineDown.axis - 1;
		Line lineLeft = findNextLineSideways(LEFT, currX, lineCurrY, nextLineDown.start, verticalLines);
		Line lineRight = findNextLineSideways(RIGHT, currX, lineCurrY, nextLineDown.end, verticalLines);
		LineThruple lt = new LineThruple(nextLineDown, lineLeft, lineRight);
		if (alreadyAdded.contains(lt)) {
			return 0;
		}
		alreadyAdded.add(lt);
		int topWall = Math.min(lineLeft.start, lineRight.start);
		while (lineCurrY >= topWall) {
			squares += lineRight.axis - lineLeft.axis - 1;
			lineCurrY--;
			lineLeft = null;
			lineRight = null;
			lineLeft = findNextLineSideways(LEFT, currX, lineCurrY, nextLineDown.start, verticalLines);
			lineRight = findNextLineSideways(RIGHT, currX, lineCurrY, nextLineDown.end, verticalLines);
			if (lineCurrY < topWall || lineLeft == null || lineRight == null) {
				break;
			}
			topWall = Math.min(lineLeft.start, lineRight.start);
		}

		if (lineLeft == null) {
			squares += countSquares(bottomValue, nextLineDown.start - 1, lineCurrY, horizontalLines, verticalLines);
			squares++;
		}
		if (lineRight == null) {
			squares += countSquares(bottomValue, nextLineDown.end + 1, lineCurrY, horizontalLines, verticalLines);
			squares++;
		}
		if (lineLeft == null && lineRight == null) {
			squares += nextLineDown.end - nextLineDown.start + 1;
		} else if (lineLeft == null) {
			squares += lineRight.axis - nextLineDown.start;
		} else {
			squares += nextLineDown.end - lineLeft.axis;
		}
		squares += lineCurrY - currY - 1;
		return squares;
	}

	private static Line findNextLineSideways(int direction, int currX, int currY, int extreme, Map<Integer, Set<Line>> verticalLines) {
		TreeSet<Integer> orderedSide;
		Iterator<Integer> iter = null;
		switch (direction) {
			case LEFT:
				orderedSide = new TreeSet<>(verticalLines.keySet().stream().filter(i -> i < currX).collect(Collectors.toSet()));
				iter = orderedSide.descendingIterator();
				break;
			case RIGHT:
				orderedSide = new TreeSet<>(verticalLines.keySet().stream().filter(i -> i > currX).collect(Collectors.toSet()));
				iter = orderedSide.iterator();
				break;
		}

		while (iter.hasNext()) {
			Integer axis = iter.next();
			for (Line line : verticalLines.get(axis)) {
				if (line.start <= currY && line.end >= currY) {
					if (direction == LEFT) {
						if (line.axis < extreme) {
							continue;
						}
					} else {
						if (line.axis > extreme) {
							continue;
						}
					}
					return line;
				}
			}
		}
		return null;
	}

	private static Line findNextLineDown(int currX, int currY, Map<Integer, Set<Line>> horizontalLines) {
		Line result = null;
		//Treemap is ordered by keys sorting
		for (Map.Entry<Integer, Set<Line>> entry : horizontalLines.entrySet()) {
			if (currY >= entry.getKey()) {
				return result;
			}
			for (Line line : entry.getValue()) {
				if (line.start <= currX && line.end >= currX) {
					return line;
				}
			}
		}
		return null;
	}

	static class Line {

		final int axis; //could be x=0 or y=0
		final int start; //2
		final int end; //7

		Line(int index, int start, int end) {
			this.axis = index;
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return String.format("Axis: %d, Start: %d, End: %d", axis, start, end);
		}
	}

	static class LineThruple {
		final Line down;
		final Line left;
		final Line right;

		LineThruple(Line down, Line left, Line right) {
			this.down = down;
			this.left = left;
			this.right = right;
		}

		@Override
		public boolean equals(Object o) {
			// If the object is compared with itself then return true
			if (o == this) {
				return true;
			}

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
			if (!(o instanceof LineThruple)) {
				return false;
			}

			// typecast o to Complex so that we can compare data members
			LineThruple l = (LineThruple) o;

			// Compare the data members and return accordingly
			return down == l.down && left == l.left && right == l.right;

		}
	}
}