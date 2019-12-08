package y2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1 {
	public static void main(String[] args) throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		int totalFuel = 0;
		while (true) {
			String next = br.readLine();
			if (next.isEmpty()) {
				break;
			}
			int thisFuel = Integer.parseInt(next) / 3 - 2;
			int additionalFuel = thisFuel;
			while (additionalFuel > 0) {
				int newAdditionalFuel = additionalFuel / 3 - 2;
				if (newAdditionalFuel <= 0) {
					break;
				}
				thisFuel += newAdditionalFuel;
				additionalFuel = newAdditionalFuel;
			}
			totalFuel += thisFuel;
		}
		System.out.println(totalFuel);
	}
}
