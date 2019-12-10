package y2019;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P10b {
	public static void main(String[] args) throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		String line = br.readLine();
		List<String> inString = new ArrayList<>();
		while (line != null && !line.isEmpty()) {
			inString.add(line);
			line = br.readLine();
		}
		int width = inString.get(0).length();
		int height = inString.size();
		boolean[][] asteroids = new boolean[width][height];
		for (int y = 0; y < inString.size(); y++) {
			String inLine = inString.get(y);
			for (int x = 0; x < inLine.length(); x++) {
				asteroids[y][x] = inLine.charAt(x) == '#';
			}
		}
		int x = 27;
		int y = 19;
		Set<Double> radians = new TreeSet<>();
		Map<Double, Set<Point>> radianToPoint = new HashMap<>();
		for (int x2 = 0; x2 < width; x2++) {
			for (int y2 = 0; y2 < height; y2++) {
				if (x == x2 && y == y2) {
					continue;
				}
				if (!asteroids[y2][x2]) {
					continue;
				}
				double radian = Math.atan2(y - y2, x2 - x);
				radians.add(radian);
				if (!radianToPoint.containsKey(radian)) {
					radianToPoint.put(radian, new HashSet<>());
				}
				radianToPoint.get(radian).add(new Point(x2, y2));
				System.out.println(radian + " " + radianToPoint.get(radian).toString());
			}
		}
		Iterator<Double> iter = ((TreeSet<Double>) radians).descendingIterator();
		int count = 0;
		int totalCount = 0;
		while (iter.hasNext()) {
			Double next = iter.next();
			System.out.print(++totalCount + ": ");
			if (next <= Math.PI / 2) {
				System.out.println(++count + ": " + next + " " + radianToPoint.get(next));
			} else {
				System.out.println("Waiting: " + next + " " + radianToPoint.get(next));
			}
			if (count == 200) {
				System.out.println(radianToPoint.get(next));
				return;
			}
		}
	}

	@Data
	public static class Point {
		final int x;
		final int y;
	}
}