package y2019;

import java.util.ArrayList;
import java.util.List;

public class P22b {
	public static void main(String[] args) {
		int deckSize = 10007;
		String[] input = ("cut 9712\n" +
				                  "deal with increment 23\n" +
				                  "cut 6635\n" +
				                  "deal with increment 18\n" +
				                  "cut 887\n" +
				                  "deal with increment 47\n" +
				                  "deal into new stack\n" +
				                  "deal with increment 53\n" +
				                  "cut -1593\n" +
				                  "deal with increment 3\n" +
				                  "cut -6676\n" +
				                  "deal with increment 69\n" +
				                  "cut -4313\n" +
				                  "deal with increment 55\n" +
				                  "cut 609\n" +
				                  "deal with increment 42\n" +
				                  "deal into new stack\n" +
				                  "deal with increment 51\n" +
				                  "deal into new stack\n" +
				                  "cut 880\n" +
				                  "deal with increment 75\n" +
				                  "cut -964\n" +
				                  "deal with increment 33\n" +
				                  "cut 7911\n" +
				                  "deal with increment 71\n" +
				                  "deal into new stack\n" +
				                  "cut -5716\n" +
				                  "deal with increment 52\n" +
				                  "cut 5969\n" +
				                  "deal with increment 30\n" +
				                  "cut -3508\n" +
				                  "deal with increment 25\n" +
				                  "cut -7645\n" +
				                  "deal with increment 29\n" +
				                  "cut 8929\n" +
				                  "deal into new stack\n" +
				                  "cut 4850\n" +
				                  "deal with increment 34\n" +
				                  "cut -1\n" +
				                  "deal with increment 55\n" +
				                  "cut 7491\n" +
				                  "deal with increment 74\n" +
				                  "cut 3331\n" +
				                  "deal with increment 35\n" +
				                  "cut 8433\n" +
				                  "deal into new stack\n" +
				                  "deal with increment 66\n" +
				                  "cut 3725\n" +
				                  "deal with increment 3\n" +
				                  "deal into new stack\n" +
				                  "deal with increment 19\n" +
				                  "deal into new stack\n" +
				                  "cut -8821\n" +
				                  "deal with increment 27\n" +
				                  "deal into new stack\n" +
				                  "cut -9853\n" +
				                  "deal with increment 71\n" +
				                  "cut -9286\n" +
				                  "deal with increment 39\n" +
				                  "deal into new stack\n" +
				                  "cut 4288\n" +
				                  "deal into new stack\n" +
				                  "deal with increment 11\n" +
				                  "deal into new stack\n" +
				                  "deal with increment 50\n" +
				                  "cut 153\n" +
				                  "deal with increment 32\n" +
				                  "cut 6836\n" +
				                  "deal with increment 65\n" +
				                  "cut 9504\n" +
				                  "deal with increment 11\n" +
				                  "deal into new stack\n" +
				                  "cut -7646\n" +
				                  "deal with increment 4\n" +
				                  "cut 5795\n" +
				                  "deal with increment 65\n" +
				                  "deal into new stack\n" +
				                  "deal with increment 23\n" +
				                  "cut 7208\n" +
				                  "deal with increment 17\n" +
				                  "deal into new stack\n" +
				                  "cut -5333\n" +
				                  "deal with increment 18\n" +
				                  "deal into new stack\n" +
				                  "deal with increment 46\n" +
				                  "deal into new stack\n" +
				                  "deal with increment 73\n" +
				                  "cut 1661\n" +
				                  "deal with increment 34\n" +
				                  "cut 7121\n" +
				                  "deal with increment 13\n" +
				                  "cut 1266\n" +
				                  "deal with increment 71\n" +
				                  "cut 2328\n" +
				                  "deal with increment 6\n" +
				                  "cut 6005\n" +
				                  "deal with increment 49\n" +
				                  "cut -3871\n" +
				                  "deal with increment 9\n" +
				                  "cut 8441\n")
				                 .split("\n");
		List<Integer> deck = new ArrayList<>();
		for (int i = 0; i < deckSize; i++) {
			deck.add(i);
		}
		int offset = 0;
		int increment = 0;
		for (int i = 0; i < input.length; i++) {
			String instruction = input[i];
			if (instruction.startsWith("deal with increment ")) {
				int value = Integer.parseInt(instruction.replace("deal with increment ", "")) % deck.size();
				increment = (int) Math.pow(value, deckSize);
			} else if (instruction.startsWith("deal into new stack")) {
				increment *= -1;
				offset += increment;
			} else {
				int value = Integer.parseInt(instruction.replace("cut ", "")) % deck.size();
				offset += increment * value;
			}
		}
		return;
	}

	private static void convertToList(List<Integer> deck, int[] tempDeck) {
		deck.clear();
		for (int j = 0; j < tempDeck.length; j++) {
			deck.add(tempDeck[j]);
		}
	}
}
