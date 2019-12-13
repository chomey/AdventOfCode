package y2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P10 {
	public static void main(String[] args) throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		String line = br.readLine();
		List<String> inString = new ArrayList<>();
		while (line != null && !line.isEmpty()) {
			inString.add(line);
			line = br.readLine();
		}
		Set<Point> asteroids = new HashSet<>();
		for (int y = 0; y < inString.size(); y++) {
			String inLine = inString.get(y);
			for (int x = 0; x < inLine.length(); x++) {
				if (inLine.charAt(x) == '#') {
					asteroids.add(new Point(x, y));
				}
			}
		}
		int max = 0;
		int x = 0;
		int y = 0;
		TreeMap<Double, Set<Point>> maxRadiansToPoint = new TreeMap<>();
		for (Point p : asteroids) {
			TreeMap<Double, Set<Point>> radiansToPoint = new TreeMap<>();
			asteroids.stream().filter(otherP -> p != otherP).forEach(otherP -> {
				double radian = Math.atan2(p.y - otherP.y, otherP.x - p.x);
				radiansToPoint.putIfAbsent(radian, new HashSet<>());
				radiansToPoint.get(radian).add(otherP);
			});
			if (radiansToPoint.size() > max) {
				max = radiansToPoint.size();
				maxRadiansToPoint = radiansToPoint;
				x = p.x;
				y = p.y;
			}
		}
		System.out.println("Max: (" + x + "," + y + ")");
		Iterator<Double> iter = maxRadiansToPoint.descendingKeySet().iterator();
		int count = 0;
		boolean firstPass = true;
		while (iter.hasNext()) {
			Double next = iter.next();
			if (!firstPass || next <= Math.PI / 2) {
				count++;
			}
			if (count == 200) {
				Point p = closestPoint(x, y, maxRadiansToPoint.get(next));
				System.out.println(p.x * 100 + p.y);
				return;
			}
			//Iterate until end. Reset iterator since it's going to be in the 2nd quadrant
			if (!iter.hasNext()) {
				iter = maxRadiansToPoint.descendingKeySet().iterator();
				firstPass = false;
			}
		}
	}

	private static Point closestPoint(int x, int y, Set<Point> points) {
		double minDistance = Double.MAX_VALUE;
		Point closestPoint = null;
		for (Point p : points) {
			double distance = Math.sqrt(Math.pow(p.y - y, 2) + Math.pow(p.x - x, 2));
			if (distance < minDistance) {
				minDistance = distance;
				closestPoint = p;
			}
		}
		return closestPoint;
	}
}