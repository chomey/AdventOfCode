package y2019;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class P19 {
	static int inputArryLength;

	public static void main(String[] args) {
		String[] inputArr = "109,424,203,1,21102,1,11,0,1106,0,282,21101,18,0,0,1105,1,259,2102,1,1,221,203,1,21101,0,31,0,1105,1,282,21101,0,38,0,1105,1,259,20101,0,23,2,21202,1,1,3,21101,0,1,1,21101,57,0,0,1105,1,303,1202,1,1,222,21002,221,1,3,21001,221,0,2,21102,1,259,1,21101,0,80,0,1105,1,225,21101,0,175,2,21102,1,91,0,1106,0,303,2101,0,1,223,21001,222,0,4,21102,259,1,3,21101,225,0,2,21102,1,225,1,21102,1,118,0,1105,1,225,21002,222,1,3,21101,70,0,2,21101,0,133,0,1105,1,303,21202,1,-1,1,22001,223,1,1,21102,1,148,0,1105,1,259,2102,1,1,223,21002,221,1,4,21002,222,1,3,21102,24,1,2,1001,132,-2,224,1002,224,2,224,1001,224,3,224,1002,132,-1,132,1,224,132,224,21001,224,1,1,21101,195,0,0,105,1,109,20207,1,223,2,21002,23,1,1,21101,0,-1,3,21102,1,214,0,1106,0,303,22101,1,1,1,204,1,99,0,0,0,0,109,5,2102,1,-4,249,21202,-3,1,1,22102,1,-2,2,21201,-1,0,3,21101,0,250,0,1106,0,225,21201,1,0,-4,109,-5,2105,1,0,109,3,22107,0,-2,-1,21202,-1,2,-1,21201,-1,-1,-1,22202,-1,-2,-2,109,-3,2105,1,0,109,3,21207,-2,0,-1,1206,-1,294,104,0,99,21202,-2,1,-2,109,-3,2106,0,0,109,5,22207,-3,-4,-1,1206,-1,346,22201,-4,-3,-4,21202,-3,-1,-1,22201,-4,-1,2,21202,2,-1,-1,22201,-4,-1,1,22101,0,-2,3,21101,343,0,0,1105,1,303,1105,1,415,22207,-2,-3,-1,1206,-1,387,22201,-3,-2,-3,21202,-2,-1,-1,22201,-3,-1,3,21202,3,-1,-1,22201,-3,-1,2,21201,-4,0,1,21101,0,384,0,1105,1,303,1105,1,415,21202,-4,-1,-4,22201,-4,-3,-4,22202,-3,-2,-2,22202,-2,-4,-4,22202,-3,-2,-3,21202,-4,-1,-2,22201,-3,-2,1,21201,1,0,-4,109,-5,2106,0,0".split(",");
		inputArryLength = inputArr.length;

		Map<Point, Boolean> state = new HashMap<>();
		int xInit = 2955;
		int yInit = 5199;
		for (int x = xInit; x < xInit + 100; x++) {
			System.out.println();
			for (int y = yInit; y < yInit + 100; y++) {
				long[] values = new long[inputArr.length + 10000];
				for (int i = 0; i < inputArr.length; i++) {
					values[i] = Long.parseLong(inputArr[i]);
				}
				AtomicInteger index = new AtomicInteger();
				Intcode intcode = new Intcode(values, supplier(new long[]{y, x}, index), consumer(state, x, y));
				intcode.run();
				Boolean isTrue = state.get(new Point(x, y));
				System.out.print(isTrue ? "#" : ".");
			}
			System.out.println();
		}
		System.out.println(state.values().stream().filter(v -> v).count());
	}

	private static Consumer<Long> consumer(Map<Point, Boolean> state, int x, int y) {
		return aLong -> state.put(new Point(x, y), aLong == 1);
	}

	private static Supplier<Long> supplier(long[] supply, AtomicInteger index) {
		return () -> supply[index.getAndIncrement()];
	}
}
