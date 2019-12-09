package y2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P9 {
	static int base = 0;

	public static void main(String[] args) throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		String[] inputArr = br.readLine().split(",");
		long[] values = new long[inputArr.length * 1000];
		for (int i = 0; i < inputArr.length; i++) {
			values[i] = Long.parseLong(inputArr[i]);
		}
		int INPUT = 2;
		Loop:
		for (int i = 0; i < values.length; ) {
			long value = values[i];
			long firstMode = value / 100 % 10;
			long secondMode = value / 1000 % 10;
			long thirdMode = value / 10000;
			int index = 0;
			switch ((int) (value % 100)) {
				//Add
				case 1:
					index = (int) values[i + 3];
					if (thirdMode == 2) {
						index += base;
					}
					values[index] = select(values, values[i + 1], firstMode) + select(values, values[i + 2], secondMode);
					i += 4;
					break;
				//Multiply
				case 2:
					index = (int) values[i + 3];
					if (thirdMode == 2) {
						index += base;
					}
					values[index] = select(values, values[i + 1], firstMode) * select(values, values[i + 2], secondMode);
					i += 4;
					break;
				//Write input
				case 3:
					index = (int) values[i + 3];
					if (thirdMode == 2) {
						index += base;
					}
					values[index] = INPUT;
					i += 2;
					break;
				//Print
				case 4:
					System.out.println("Output: " + select(values, values[i + 1], firstMode));
					i += 2;
					break;
				//Jump if true
				case 5:
					if (select(values, values[i + 1], firstMode) != 0) {
						i = (int) select(values, values[i + 2], secondMode);
					} else {
						i += 3;
					}
					break;
				//Jump if false
				case 6:
					if (select(values, values[i + 1], firstMode) == 0) {
						i = (int) select(values, values[i + 2], secondMode);
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
					if (select(values, values[i + 1], firstMode) < select(values, values[i + 2], secondMode)) {
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
					if (select(values, values[i + 1], firstMode) == select(values, values[i + 2], secondMode)) {
						values[index] = 1;
					} else {
						values[index] = 0;
					}
					i += 4;
					break;
				case 9:
					base += select(values, values[i + 1], firstMode);
					i += 2;
					break;
				case 99:
					break Loop;
				default:
					throw new RuntimeException("this shouldn't happen");
			}
		}
		System.out.println(values[0]);
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
}
