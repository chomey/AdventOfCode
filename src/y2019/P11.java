package y2019;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class P11 {
	static final int BLACK = 0;
	static final int WHITE = 1;

	static final int LEFT = 0;
	static final int RIGHT = 1;

	static final boolean COLOR = false;
	static final boolean DIRECTION = true;
	static int base = 0;

	public static void main(String[] args) throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		String[] inputArr = br.readLine().split(",");
		long[] values = new long[inputArr.length * 1000];
		for (int i = 0; i < inputArr.length; i++) {
			values[i] = Long.parseLong(inputArr[i]);
		}
		Map<Point, Integer> colorMap = new HashMap<>();
		Point current = new Point(0, 0);
		colorMap.put(current, 1);
		boolean mode = COLOR;
		Direction facing = Direction.UP;
		Loop:
		for (int i = 0; i < values.length; ) {
			long value = values[i];
			int opcode = (int) value % 100;
			long firstMode = value / 100 % 10;
			long secondMode = value / 1000 % 10;
			long thirdMode = value / 10000;
			int index;
			Long firstValue = null;
			Long secondValue = null;
			try {
				firstValue = select(values, values[i + 1], firstMode);
				secondValue = select(values, values[i + 2], secondMode);
			} catch (Exception e) {

			}
			switch (opcode) {
				//Add
				case 1:
					index = (int) values[i + 3];
					if (thirdMode == 2) {
						index += base;
					}
					values[index] = firstValue + secondValue;
					i += 4;
					break;
				//Multiply
				case 2:
					index = (int) values[i + 3];
					if (thirdMode == 2) {
						index += base;
					}
					values[index] = firstValue * secondValue;
					i += 4;
					break;
				//Write input
				case 3:
					index = (int) values[i + 1];
					if (thirdMode == 2) {
						index += base;
					}
					values[index] = getInput(colorMap, current);
					i += 2;
					break;
				//Print
				case 4:
					int outputVal = firstValue.intValue();
					if (mode == COLOR) {
						colorMap.put(current, outputVal);
					} else {
						//Direction
						boolean left = outputVal == 0;
						Point delta = left ? facing.left : facing.right;
						current = new Point(current.x + delta.x, current.y + delta.y);

						int directionChange = left ? -1 : 1;
						int newIndex = (facing.ordinal() + directionChange) % Direction.values().length;
						if (newIndex == -1) {
							newIndex = Direction.values().length - 1;
						}
						facing = Direction.values()[newIndex];
					}

					mode = !mode;
					i += 2;
					break;
				//Jump if true
				case 5:
					if (firstValue != 0) {
						i = secondValue.intValue();
					} else {
						i += 3;
					}
					break;
				//Jump if false
				case 6:
					if (firstValue == 0) {
						i = secondValue.intValue();
					} else {
						i += 3;
					}
					break;
				//Less than
				case 7:
					index = (int) values[i + 3];
					if (thirdMode == 2) {
						index += base;
					}
					if (firstValue < secondValue) {
						values[index] = 1;
					} else {
						values[index] = 0;
					}
					i += 4;
					break;
				//Equals
				case 8:
					index = (int) values[i + 3];
					if (thirdMode == 2) {
						index += base;
					}
					if (firstValue == secondValue) {
						values[index] = 1;
					} else {
						values[index] = 0;
					}
					i += 4;
					break;
				case 9:
					base += firstValue;
					i += 2;
					break;
				case 99:
					break Loop;
				default:
					throw new RuntimeException("this shouldn't happen");
			}
		}
		System.out.println(colorMap.size());

		int xMin = Integer.MAX_VALUE;
		int xMax = Integer.MIN_VALUE;
		int yMin = Integer.MAX_VALUE;
		int yMax = Integer.MIN_VALUE;
		for (Point p : colorMap.keySet()) {
			if (p.x < xMin) {
				xMin = p.x;
			}
			if (p.x > xMax) {
				xMax = p.x;
			}
			if (p.y < yMin) {
				yMin = p.y;
			}
			if (p.y > yMax) {
				yMax = p.y;
			}
		}
		boolean[][] grid = new boolean[yMax - yMin + 1][xMax - xMin + 1];
		for (Point p : colorMap.keySet()) {
			grid[-p.y][p.x] = colorMap.get(p) == 1;
		}
		for (int i = 0; i < grid.length; i++) {
			System.out.println();
			for (int j = 0; j < grid[i].length; j++) {
				String output = grid[i][j] ? "*" : " ";
				System.out.print(output);
			}
		}
	}

	private static int getInput(Map<Point, Integer> colorMap, Point current) {
		if (!colorMap.containsKey(current)) {
			colorMap.put(current, BLACK);
		}
		return colorMap.get(current);
	}

	private static long select(long[] values, long i, long mode) {
		switch ((int) mode) {
			case 0: //position
				return values[(int) i];
			case 1://immediate
				return i;
			case 2://relative
				return values[base + (int) i];
		}
		throw new RuntimeException("no happen");
	}

	@Data
	public static class Point {
		final int x;
		final int y;
	}

	@RequiredArgsConstructor
	public enum Direction {
		LEFT(new Point(0, -1), new Point(0, 1)),
		UP(new Point(-1, 0), new Point(1, 0)),
		RIGHT(new Point(0, 1), new Point(0, -1)),
		DOWN(new Point(1, 0), new Point(-1, 0));

		final Point left;
		final Point right;
	}
}
