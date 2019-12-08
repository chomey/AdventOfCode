package y2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2 {
	public static void main(String[] args) throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		String[] inputArr = br.readLine().split(",");
		int[] values = new int[inputArr.length];
		for (int i = 0; i < inputArr.length; i++) {
			values[i] = Integer.parseInt(inputArr[i]);
		}
		for (int j = 0; j < 100; j++) {
			for (int k = 0; k < 100; k++) {
				int[] newValues = new int[inputArr.length];
				System.arraycopy(values, 0, newValues, 0, inputArr.length);
				newValues[1] = j;
				newValues[2] = k;
				Loop:
				for (int i = 0; i < newValues.length - 4; ) {
					switch (newValues[i]) {
						case 1:
							newValues[newValues[i + 3]] = newValues[newValues[i + 1]] + newValues[newValues[i + 2]];
							i += 4;
							break;
						case 2:
							newValues[newValues[i + 3]] = newValues[newValues[i + 1]] * newValues[newValues[i + 2]];
							i += 4;
							break;
						case 99:
							break Loop;
						default:
							throw new RuntimeException("this shoudln't happen");
					}
				}
				if (newValues[0] == 19690720) {
					System.out.println(100 * j + k);
				}
			}
		}
	}
}
