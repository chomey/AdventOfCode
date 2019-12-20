package y2019;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class P17 {
	public static void main(String[] args) {
		String inputStr = "########################\n" +
				                  "#...............b.C.D.f#\n" +
				                  "#.######################\n" +
				                  "#.....@.a.B.c.d.A.e.F.g#\n" +
				                  "########################";
		char maxChar = 'a';
		for (char c : inputStr.toCharArray()) {
			if (c > maxChar && c <= 'z') {
				maxChar = c;
			}
		}
		String[] input = inputStr.split("\n");

		int startX = 0;
		int startY = 0;
		char[][] grid = new char[input.length][input[0].length()];
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length(); j++) {
				char c = input[i].charAt(j);
				grid[i][j] = c;
				if (c == '@') {
					startX = j;
					startY = i;
				}
			}
		}
		Deque<SaveStateSteps> possibleStates = new ArrayDeque<>();
		Set<SaveState> alreadyTried = new HashSet<>();
		possibleStates.add(new SaveStateSteps(new SaveState(new Point(startX, startY), new HashSet<>()), 0));
		while (true) {
			SaveStateSteps saveStateSteps = possibleStates.poll();
			Point start = saveStateSteps.saveState.position;
			for (Direction d : Direction.values()) {
				try {
					Point nextPoint = start.apply(d);
					SaveState nextSaveState = new SaveState(nextPoint, new HashSet<>(saveStateSteps.saveState.keysFound));
					if (alreadyTried.contains(nextSaveState)) {
						continue;
					}
					char c = grid[nextPoint.y][nextPoint.x];
					SaveStateSteps newSaveStateSteps = new SaveStateSteps(nextSaveState, saveStateSteps.steps + 1);
					if (c == '.' || c == '@' || nextSaveState.keysFound.contains(c) || nextSaveState.keysFound.contains((char) (c + 32))) {
						possibleStates.add(newSaveStateSteps);
					} else if ('a' <= c && c <= 'z' && !nextSaveState.keysFound.contains(c)) {
						nextSaveState.keysFound.add(c);
						possibleStates.add(newSaveStateSteps);
					}
					if (newSaveStateSteps.saveState.keysFound.size() == maxChar - 'a' + 1) {
						System.out.println("Done: " + newSaveStateSteps.steps);
						return;
					}
					alreadyTried.add(nextSaveState);
				} catch (Exception e) {

				}
			}
		}
	}

	@Data
	public static class SaveState {
		final Point position;
		final Set<Character> keysFound;
	}

	@Data
	public static class SaveStateSteps {
		final SaveState saveState;
		final int steps;
	}
}