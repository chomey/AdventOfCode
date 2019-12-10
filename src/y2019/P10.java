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
		boolean[][] asteroids = new boolean[inString.size()][inString.get(0).length()];
		for (int i = 0; i < inString.size(); i++) {
			String inLine = inString.get(i);
			for (int j = 0; j < inLine.length(); j++) {
				asteroids[j][i] = inLine.charAt(j) == '#';
			}
		}
		int max = 0;
		for (int x1 = 0; x1 < asteroids.length; x1++) {
			for (int y1 = 0; y1 < asteroids[x1].length; y1++) {
				if (!asteroids[x1][y1]) {
					continue;
				}
				Set<Double> radians = new TreeSet<>();
				for (int x2 = 0; x2 < asteroids.length; x2++) {
					for (int y2 = 0; y2 < asteroids[x2].length; y2++) {
						if (x1 == x2 && y1 == y2) {
							continue;
						}
						if (!asteroids[x2][y2]) {
							continue;
						}
						radians.add(Math.atan2(y2 - y1, x2 - x1));
					}
				}
				if (radians.size() > max) {
					max = radians.size();
					System.out.println(x1 + " " + y1);
				}
			}
		}
		System.out.println(max);
	}
}
