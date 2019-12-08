package y2019;

public class P4 {
	public static void main(String[] args) {
		exactlyTwo(Integer.toString(123444));
		int countA = 0;
		int countB = 0;
		for (int i = 236491; i <= 713787; i++) {
			String iString = Integer.toString(i);
			boolean adjacent = isAdjacent(iString);
			boolean increasing = isIncreasing(iString);
			if (adjacent && increasing) {
				countA++;
			}
			boolean exactlyTwo = exactlyTwo(iString);
			if (exactlyTwo && increasing) {
				countB++;
			}
		}
		System.out.println(countA);
		System.out.println(countB);
	}

	private static boolean isAdjacent(String iString) {
		for (int i = 0; i < iString.length() - 1; i++) {
			if (iString.charAt(i) == iString.charAt(i + 1)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isIncreasing(String iString) {
		for (int i = 0; i < iString.length() - 1; i++) {
			int left = Integer.parseInt(iString.substring(i, i + 1));
			int right = Integer.parseInt(iString.substring(i + 1, i + 2));
			if (right < left) {
				return false;
			}
		}
		return true;
	}

	private static boolean exactlyTwo(String iString) {
		for (int i = 0; i < iString.length() - 1; i++) {
			char c = iString.charAt(i);
			if (c == iString.charAt(i + 1)) {
				boolean leftGood = i == 0 || iString.charAt(i - 1) != c;
				boolean rightGood = i == iString.length() - 2 || iString.charAt(i + 2) != c;
				if (leftGood && rightGood) {
					return true;
				}
			}
		}
		return false;
	}
}
