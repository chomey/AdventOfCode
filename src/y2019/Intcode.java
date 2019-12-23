package y2019;

import lombok.Data;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Data
public class Intcode {
	final long[] values;
	final Supplier<Long> supplier;
	final Consumer<Long> consumer;
	int base = 0;

	public void run() {
		Loop:
		for (int i = 0; i < values.length; ) {
			long value = values[i];
			int opcode = (int) value % 100;
			long firstMode = value / 100 % 10;
			long secondMode = value / 1000 % 10;
			long thirdMode = value / 10000;
			int index;
			Long firstValue = null;
			Long secondValue = null;
			try {
				firstValue = select(values, values[i + 1], firstMode);
				secondValue = select(values, values[i + 2], secondMode);
			} catch (Exception e) {
				//Only happens when you're literally at the end of array
			}
			switch (opcode) {
				case 1: //Add
					index = (int) values[i + 3];
					if (thirdMode == 2) {
						index += base;
					}
					values[index] = firstValue + secondValue;
					i += 4;
					break;
				case 2: //Multiply
					index = (int) values[i + 3];
					if (thirdMode == 2) {
						index += base;
					}
					values[index] = firstValue * secondValue;
					i += 4;
					break;
				case 3: //Input
					index = (int) values[i + 1];
					if (firstMode == 2) {
						index += base;
					}
					values[index] = supplier.get();
					i += 2;
					break;
				case 4: //Output
					consumer.accept(firstValue);
					i += 2;
					break;
				case 5: //Jump if true
					if (firstValue != 0) {
						i = secondValue.intValue();
					} else {
						i += 3;
					}
					break;
				case 6: //Jump if false
					if (firstValue == 0) {
						i = secondValue.intValue();
					} else {
						i += 3;
					}
					break;
				case 7: //Less than
					index = (int) values[i + 3];
					if (thirdMode == 2) {
						index += base;
					}
					if (firstValue < secondValue) {
						values[index] = 1;
					} else {
						values[index] = 0;
					}
					i += 4;
					break;
				case 8: //Equals
					index = (int) values[i + 3];
					if (thirdMode == 2) {
						index += base;
					}
					if (firstValue.equals(secondValue)) {
						values[index] = 1;
					} else {
						values[index] = 0;
					}
					i += 4;
					break;
				case 9: //Change base
					base += firstValue;
					i += 2;
					break;
				case 99: //End
					break Loop;
				default:
					throw new RuntimeException("this shouldn't happen: " + opcode);
			}
		}
	}

	private long select(long[] values, long i, long mode) {
		switch ((int) mode) {
			case 0: //position
				return values[(int) i];
			case 1://immediate
				return i;
			case 2://relative
				return values[base + (int) i];
		}
		throw new RuntimeException("no happen");
	}
}