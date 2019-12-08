package y2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P8 {
	public static void main(String[] args) throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		int rows = 6;
		int columns = 25;
		String inputString = br.readLine();
		List<int[][]> images = new ArrayList<>();
		int minZeroes = Integer.MAX_VALUE;
		int ones = 0;
		int twos = 0;
		int layer = 0;
		while (!inputString.isEmpty()) {
			int endIndex = 25 * 6;
			String line = inputString.substring(0, endIndex);
			inputString = inputString.substring(endIndex);
			int[][] image = new int[rows][columns];
			int c0 = 0;
			int c1 = 0;
			int c2 = 0;
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					image[i][j] = Integer.parseInt("" + line.charAt(25 * i + j));
					switch (image[i][j]) {
						case 0:
							c0++;
							break;
						case 1:
							c1++;
							break;
						case 2:
							c2++;
							break;
					}
				}
			}
			if (c0 < minZeroes) {
				minZeroes = c0;
				ones = c1;
				twos = c2;
			}
			images.add(image);
		}
		System.out.println(ones * twos);

		int[][] finalImage = new int[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Images:
				for(int k = 0; k < images.size();k++){
					int value = images.get(k)[i][j];
					switch(value){
						case 2:
							continue;
						case 0:
						case 1:
							finalImage[i][j] = value;
							break Images;
					}
				}
			}
		}
		for(int[] row : finalImage){
			for(int i : row){
				if(i == 0){
					System.out.print(" ");
				} else {
					System.out.print(i);
				}
			}
			System.out.println();
		}
	}
}
