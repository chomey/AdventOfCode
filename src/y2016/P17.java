package y2016;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class P17 {
	public static Set<Character> open = Set.of('B', 'C', 'D', 'E', 'F');

	public static void main(String[] args) throws NoSuchAlgorithmException {
		String start = "udskfozm";
		Queue<String> nextToTry = new LinkedBlockingQueue<>();
		nextToTry.add(start);
		int maxLength = 0;
		while (!nextToTry.isEmpty()) {
			String currInput = nextToTry.poll();
			int currX = getCurrX(start, currInput);
			int currY = getCurrY(start, currInput);
			if(currX == -1 || currX == 4 || currY == -1 ||currY ==4){
				continue;
			}
			if(currX == 3 && currY == 3){
				maxLength = Math.max(currInput.replace(start, "").length(), maxLength);
				continue;
			}
			String newHash = hashText(currInput).substring(0, 4);
			for (int i = 0; i < 4; i++) {
				if (open.contains(newHash.charAt(i))) {
					switch (i) {
						case 0:
							nextToTry.add(currInput+"U");
							break;
						case 1:
							nextToTry.add(currInput+"D");
							break;
						case 2:
							nextToTry.add(currInput+"L");
							break;
						case 3:
							nextToTry.add(currInput+"R");
							break;
					}
				}
			}
		}
		System.out.println(maxLength);
	}

	private static int getCurrX(String start, String currInput) {
		String str = currInput.replace(start, "");
		int location = 0;
		for(char c : str.toCharArray()){
			if(c == 'L'){
				location--;
			}
			if (c == 'R'){
				location++;
			}
		}
		return location;
	}

	private static int getCurrY(String start, String currInput) {
		String str = currInput.replace(start, "");
		int location = 0;
		for(char c : str.toCharArray()){
			if(c == 'U'){
				location--;
			}
			if (c == 'D'){
				location++;
			}
		}
		return location;
	}

	private static String hashText(String input) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] digest = md5.digest(input.getBytes());
		BigInteger number = new BigInteger(1, digest);
		return String.format("%1$032X", number);
	}
}
