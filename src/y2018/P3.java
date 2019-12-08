package y2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P3 {
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
		int maxW = 0;
		int maxH = 0;
		for (String s : inputList) {
			String[] parts = s.split("\\s+|,|:\\s|x");
			int x = Integer.parseInt(parts[2]);
			int y = Integer.parseInt(parts[3]);
			int w = Integer.parseInt(parts[4]);
			int h = Integer.parseInt(parts[5]);

			if (x + w > maxW) {
				maxW = x + w;
			}
			if (y + h > maxH) {
				maxH = y + h;
			}
		}

		int[][] bigArray = new int[maxW][];
		for (int i = 0; i < maxW; i++) {
			bigArray[i] = new int[maxH];
		}

		for (String s : inputList) {
			String[] parts = s.split("\\s+|,|:\\s|x");
			int x = Integer.parseInt(parts[2]);
			int y = Integer.parseInt(parts[3]);
			int w = Integer.parseInt(parts[4]);
			int h = Integer.parseInt(parts[5]);
			for (int i = x; i < x + w; i++) {
				for (int j = y; j < y + h; j++) {
					bigArray[i][j]++;
				}
			}
		}

		int squares = 0;
		for (int i = 0; i < bigArray.length; i++) {
			for (int j = 0; j < bigArray[i].length; j++) {
				if (bigArray[i][j] >= 2) {
					squares++;
				}
			}
		}
		System.out.println(squares);

		for (String s : inputList) {
			String[] parts = s.split("\\s+|,|:\\s|x");
			int x = Integer.parseInt(parts[2]);
			int y = Integer.parseInt(parts[3]);
			int w = Integer.parseInt(parts[4]);
			int h = Integer.parseInt(parts[5]);
			boolean winner = true;
			for (int i = x; i < x + w; i++) {
				for (int j = y; j < y + h; j++) {
					if (bigArray[i][j] != 1) {
						winner = false;
						break;
					}
				}
				if (!winner) {
					break;
				}
			}
			if (winner) {
				System.out.println("Winner: " + s);
			}
		}
	}
}