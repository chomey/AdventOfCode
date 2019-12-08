package y2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P5 {
	public static void main(String[] args) throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		String[] inputArr = br.readLine().split(",");
		int[] values = new int[inputArr.length];
		for (int i = 0; i < inputArr.length; i++) {
			values[i] = Integer.parseInt(inputArr[i]);
		}
		int INPUT = 5;
		Loop:
		for (int i = 0; i < values.length; ) {
			boolean is1stParamImmediate = values[i] / 100 % 10 == 1;
			boolean is2ndParamImmediate = values[i] / 1000 == 1;
			switch (values[i] % 100) {
				//Add
				case 1:
					values[values[i + 3]] = select(values, values[i + 1], is1stParamImmediate) + select(values, values[i + 2], is2ndParamImmediate);
					i += 4;
					break;
				//Multiply
				case 2:
					values[values[i + 3]] = select(values, values[i + 1], is1stParamImmediate) * select(values, values[i + 2], is2ndParamImmediate);
					i += 4;
					break;
				//Write input
				case 3:
					values[values[i + 1]] = INPUT;
					i += 2;
					break;
				//Print
				case 4:
					int outputValue = select(values, values[i + 1], is1stParamImmediate);
					System.out.println(outputValue);
					i += 2;
					break;
				//Jump if true
				case 5:
					if (select(values, values[i + 1], is1stParamImmediate) != 0) {
						i = select(values, values[i + 2], is2ndParamImmediate);
					} else {
						i += 3;
					}
					break;
				//Jump if false
				case 6:
					if (select(values, values[i + 1], is1stParamImmediate) == 0) {
						i = select(values, values[i + 2], is2ndParamImmediate);
					} else {
						i += 3;
					}
					break;
				//Less than
				case 7:
					if (select(values, values[i + 1], is1stParamImmediate) < select(values, values[i + 2], is2ndParamImmediate)) {
						values[values[i + 3]] = 1;
					} else {
						values[values[i + 3]] = 0;
					}
					i+=4;
					break;
				//Equals
				case 8:
					if (select(values, values[i + 1], is1stParamImmediate) == select(values, values[i + 2], is2ndParamImmediate)) {
						values[values[i + 3]] = 1;
					} else {
						values[values[i + 3]] = 0;
					}
					i+=4;
					break;
				case 99:
					break Loop;
				default:
					throw new RuntimeException("this shouldn't happen");
			}
		}
	}

	private static int select(int[] values, int i, boolean isParamImmediate) {
		return isParamImmediate ? i : values[i];
	}
}
