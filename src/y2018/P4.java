package y2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P4 {
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

		String[] inputArray = inputList.toArray(new String[]{});
		Arrays.sort(inputArray);


		Map<String, int[]> guardToMinuteToSleep = new HashMap<>();
		String currGuard = null;
		String sleepTime = null;
		for (String s : inputArray) {
			String[] parts = s.split(" ");
			String time = parts[1].substring(3, parts[1].length() - 1); //trim leading 00: and trailing ]
			if (s.contains("#")) {
				currGuard = parts[3];
				if (!guardToMinuteToSleep.containsKey(currGuard)) {
					guardToMinuteToSleep.put(currGuard, new int[60]);
				}
			} else if (s.contains("falls")) {
				sleepTime = time;
			} else {
				for (int i = Integer.parseInt(sleepTime); i < Integer.parseInt(time); i++) {
					guardToMinuteToSleep.get(currGuard)[i]++;
				}
			}
		}

		String sleepiestGuard = null;
		int maxSum = 0;
		for (String guard : guardToMinuteToSleep.keySet()) {
			int sum = 0;
			for (int i = 0; i < 60; i++) {
				sum += guardToMinuteToSleep.get(guard)[i];
			}
			if (sum > maxSum) {
				maxSum = sum;
				sleepiestGuard = guard;
			}
		}

		System.out.println("Sleepiest: " + sleepiestGuard);

		int maxTime = 0;
		int minute = 0;
		for (int i = 0; i < 60; i++) {
			if (guardToMinuteToSleep.get(sleepiestGuard)[i] > maxTime) {
				maxTime = guardToMinuteToSleep.get(sleepiestGuard)[i];
				minute = i;
			}
		}

		System.out.println("Time: " + minute);

		int currMax = 0;
		maxTime = 0;
		currGuard = null;
		for (String guard : guardToMinuteToSleep.keySet()) {
			int guardMax = 0;
			int guardTime = 0;
			for (int i = 0; i < 60; i++) {
				if (guardToMinuteToSleep.get(guard)[i] > guardMax) {
					guardMax = guardToMinuteToSleep.get(guard)[i];
					guardTime = i;
				}
			}
			if (guardMax > currMax) {
				currGuard = guard;
				currMax = guardMax;
				maxTime = guardTime;
			}
		}

		System.out.println(currGuard + " " + maxTime);
	}
}