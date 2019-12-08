package y2019;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P7b {

	public static void main(String[] args) throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		String[] inputArr = br.readLine().split(",");
		int[] values = new int[inputArr.length];
		for (int i = 0; i < inputArr.length; i++) {
			values[i] = Integer.parseInt(inputArr[i]);
		}
		Set<Integer> inputs = Set.of(5, 6, 7, 8, 9);
		Set<List<Integer>> permutations = permutationsOf(inputs, new ArrayList<>());
		int maxOutput = Integer.MIN_VALUE;
		for (List<Integer> permutation : permutations) {
			Amp a = new Amp(new int[inputArr.length], permutation.get(0));
			System.arraycopy(values, 0, a.values, 0, inputArr.length);

			Amp b = new Amp(new int[inputArr.length], permutation.get(1));
			System.arraycopy(values, 0, b.values, 0, inputArr.length);
			a.nextAmp = b;

			Amp c = new Amp(new int[inputArr.length], permutation.get(2));
			System.arraycopy(values, 0, c.values, 0, inputArr.length);
			b.nextAmp = c;

			Amp d = new Amp(new int[inputArr.length], permutation.get(3));
			System.arraycopy(values, 0, d.values, 0, inputArr.length);
			c.nextAmp = d;

			Amp e = new Amp(new int[inputArr.length], permutation.get(4));
			System.arraycopy(values, 0, e.values, 0, inputArr.length);
			d.nextAmp = e;
			e.nextAmp = a;
			Amp amp = a;
			try {
				while(true) {
					readTape(amp);
					int output = amp.output;
					amp = amp.nextAmp;
					amp.nextInput = output;
				}
			} catch (RuntimeException ex){
				//Lolz exceptions as flow control
			}
			if (e.output > maxOutput) {
				maxOutput = e.output;
			}
		}
		System.out.println(maxOutput);
	}

	private static void readTape(Amp amp) {
		for (int i = amp.readFrom; i < amp.values.length; ) {
			boolean is1stParamImmediate = amp.values[i] / 100 % 10 == 1;
			boolean is2ndParamImmediate = amp.values[i] / 1000 == 1;
			switch (amp.values[i] % 100) {
				//Add
				case 1:
					amp.values[amp.values[i + 3]] = select(amp.values, amp.values[i + 1], is1stParamImmediate) + select(amp.values, amp.values[i + 2], is2ndParamImmediate);
					i += 4;
					continue;
					//Multiply
				case 2:
					amp.values[amp.values[i + 3]] = select(amp.values, amp.values[i + 1], is1stParamImmediate) * select(amp.values, amp.values[i + 2], is2ndParamImmediate);
					i += 4;
					continue;
					//Write input
				case 3:
					amp.values[amp.values[i + 1]] = amp.first ? amp.firstInput : amp.nextInput;
					amp.first = false;
					i += 2;
					continue;
					//Print
				case 4:
					amp.output = select(amp.values, amp.values[i + 1], is1stParamImmediate);
					i += 2;
					amp.readFrom = i;
					return;
				//Jump if true
				case 5:
					if (select(amp.values, amp.values[i + 1], is1stParamImmediate) != 0) {
						i = select(amp.values, amp.values[i + 2], is2ndParamImmediate);
					} else {
						i += 3;
					}
					continue;
					//Jump if false
				case 6:
					if (select(amp.values, amp.values[i + 1], is1stParamImmediate) == 0) {
						i = select(amp.values, amp.values[i + 2], is2ndParamImmediate);
					} else {
						i += 3;
					}
					continue;
					//Less than
				case 7:
					if (select(amp.values, amp.values[i + 1], is1stParamImmediate) < select(amp.values, amp.values[i + 2], is2ndParamImmediate)) {
						amp.values[amp.values[i + 3]] = 1;
					} else {
						amp.values[amp.values[i + 3]] = 0;
					}
					i += 4;
					continue;
					//Equals
				case 8:
					if (select(amp.values, amp.values[i + 1], is1stParamImmediate) == select(amp.values, amp.values[i + 2], is2ndParamImmediate)) {
						amp.values[amp.values[i + 3]] = 1;
					} else {
						amp.values[amp.values[i + 3]] = 0;
					}
					i += 4;
					continue;
				case 99:
					throw new RuntimeException("Done");
			}
		}
		throw new IllegalStateException("This should never happen");
	}

	private static Set<List<Integer>> permutationsOf(Set<Integer> potential, List<Integer> picked) {
		if (potential.isEmpty()) {
			return Set.of(picked);
		}
		Set<List<Integer>> result = new HashSet<>();
		for (int i : potential) {
			List<Integer> tempPicked = new ArrayList<>(picked);
			tempPicked.add(i);
			Set<Integer> tempPotential = new HashSet<>(potential);
			tempPotential.remove(i);
			Set<List<Integer>> results = permutationsOf(tempPotential, tempPicked);
			result.addAll(results);
		}
		return result;
	}

	private static int select(int[] values, int i, boolean isParamImmediate) {
		return isParamImmediate ? i : values[i];
	}

	@Data
	static class Amp {
		final int[] values;
		final int firstInput;
		boolean first = true;
		int nextInput;
		int output;
		int readFrom;
		Amp nextAmp;
	}
}
