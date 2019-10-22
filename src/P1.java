import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P1 {
	public static void main(String[] args) throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		int sum = 0;
		Set<Integer> visitedFrequencies = new HashSet<>();
		visitedFrequencies.add(0);

		List<String> inputList = new ArrayList<>();
		while (true) {
			String next = br.readLine();
			if (next.isEmpty()) {
				break;
			}
			inputList.add(next);
		}
		for (String next : inputList) {
			sum += Integer.parseInt(next);
		}
		System.out.println(sum);

		sum = 0;
		while (true) {
			for (String next : inputList) {
				sum += Integer.parseInt(next);
				if (visitedFrequencies.contains(sum)) {
					System.out.println("Revisited: " + sum);
					return;
				}
				visitedFrequencies.add(sum);
			}
		}
	}
}
