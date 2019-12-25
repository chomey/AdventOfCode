package y2019;

public class P25Permutations {
	public static void main(String[] args) {
		String[] items = new String[]{"spool of cat6",
				"hypercube",
				"weather machine",
				"coin",
				"candy cane",
				"tambourine",
				"fuel cell",
				"mutex"};
		for (int i = 0; i < 256; i++) {
			String bs = Integer.toBinaryString(i);
			while (bs.length() < 8) {
				bs = "0" + bs;
			}
			StringBuilder result = new StringBuilder();
			result.append("drop spool of cat6\n" +
					              "drop hypercube\n" +
					              "drop weather machine\n" +
					              "drop coin\n" +
					              "drop candy cane\n" +
					              "drop tambourine\n" +
					              "drop fuel cell\n" +
					              "drop mutex\n");
			for (int j = 0; j < 8; j++) {
				if (bs.charAt(j) == '1') {
					result.append("take ").append(items[j]).append("\n");
				}
			}
			result.append("west\n");
			System.out.println(result);
		}
	}
}
