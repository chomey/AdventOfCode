import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P2 {
	public static void main(String[] args) throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		int twos = 0;
		int threes = 0;
		List<String> inputList = new ArrayList<>();
		while (true) {
			String next = br.readLine();
			if (next.isEmpty()) {
				break;
			}
			inputList.add(next);
		}
		for (String s : inputList) {
			Map<Character, Integer> letterCounts = new HashMap<>();
			for (char c : s.toCharArray()) {
				if (!letterCounts.containsKey(c)) {
					letterCounts.put(c, 0);
				}
				letterCounts.put(c, letterCounts.get(c) + 1);
			}
			for (char c : letterCounts.keySet()) {
				if (letterCounts.get(c) == 2) {
					twos++;
					break;
				}
			}
			for (char c : letterCounts.keySet()) {
				if (letterCounts.get(c) == 3) {
					threes++;
					break;
				}
			}
		}
		System.out.println(twos * threes);

		//Part 2
		Set<String> substrings = new HashSet<>();
		for (String s : inputList) {
			Set<String> thisSubstrings = new HashSet<>();
			for (int i = 0; i < s.length() - 1; i++) {
				String s1 = s.substring(0, i);
				String s2 = s.substring(i + 1);
				String combined = s1.concat(s2);
				if (substrings.contains(combined)) {
					System.out.println("Common: " + combined);
					return;
				}
				thisSubstrings.add(combined);
			}
			substrings.addAll(thisSubstrings);
		}
	}

}
