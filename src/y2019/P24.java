package y2019;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P24 {
	public static void main(String[] args) {
		String inputStr = "#..##\n" +
				                  "#.#..\n" +
				                  "#...#\n" +
				                  "##..#\n" +
				                  "#..##";

		String[] rows = inputStr.split("\n");
		Map<Point, Boolean> world = new HashMap<>();
		for (int y = 0; y < rows.length; y++) {
			for (int x = 0; x < rows[0].length(); x++) {
				world.put(new Point(x, y), rows[y].charAt(x) == '#');
			}
		}
		Set<Map<Point, Boolean>> seenWorlds = new HashSet<>();
		seenWorlds.add(world);
		while (true) {
			for (int y = 0; y < 5; y++) {
				System.out.println();
				for (int x = 0; x < 5; x++) {
					Point p = new Point(x, y);
					System.out.print(hasIs(world, p) ? '#' : '.');
				}
			}
			System.out.println();
			Map<Point, Boolean> newWorld = new HashMap<>();
			for (int y = 0; y < 5; y++) {
				for (int x = 0; x < 5; x++) {
					Point p = new Point(x, y);
					int count = 0;
					for (Direction d : Direction.values()) {
						Point newP = p.apply(d);
						if (hasIs(world, newP)) {
							count++;
						}
					}
					if (hasIs(world, p)) {
						if (count == 1) {
							newWorld.put(p, true);
						}
					} else {
						if (count == 1 || count == 2) {
							newWorld.put(p, true);
						}
					}
				}

			}
			world = newWorld;
			if (seenWorlds.contains(newWorld)) {
				break;
			}
			seenWorlds.add(newWorld);
		}
		long sum = 0;
		System.out.println(world);
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 5; x++) {
				long i = (long) Math.pow(2,  x + 5 * y);
				Point p = new Point(x, y);
				if (world.containsKey(p)) {
					System.out.println("Found " + p + ", adding: " + i);
					sum += i;
				}
			}
		}
		System.out.print(sum);
	}

	private static boolean hasIs(Map<Point, Boolean> world, Point newP) {
		return world.containsKey(newP) && world.get(newP);
	}
}
