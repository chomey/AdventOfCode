package y2019;

import java.util.HashMap;
import java.util.Map;

import static y2019.Direction.*;

public class P16b {
	static int base = 0;

	public static void main(String[] args) {
		String[] inputArr = "1,330,331,332,109,6690,1102,1,1182,16,1102,1,1505,24,102,1,0,570,1006,570,36,1002,571,1,0,1001,570,-1,570,1001,24,1,24,1106,0,18,1008,571,0,571,1001,16,1,16,1008,16,1505,570,1006,570,14,21102,58,1,0,1105,1,786,1006,332,62,99,21101,333,0,1,21102,73,1,0,1105,1,579,1102,0,1,572,1101,0,0,573,3,574,101,1,573,573,1007,574,65,570,1005,570,151,107,67,574,570,1005,570,151,1001,574,-64,574,1002,574,-1,574,1001,572,1,572,1007,572,11,570,1006,570,165,101,1182,572,127,1002,574,1,0,3,574,101,1,573,573,1008,574,10,570,1005,570,189,1008,574,44,570,1006,570,158,1106,0,81,21101,340,0,1,1106,0,177,21102,1,477,1,1106,0,177,21101,0,514,1,21102,1,176,0,1106,0,579,99,21101,0,184,0,1105,1,579,4,574,104,10,99,1007,573,22,570,1006,570,165,1001,572,0,1182,21101,375,0,1,21102,211,1,0,1106,0,579,21101,1182,11,1,21101,222,0,0,1105,1,979,21101,388,0,1,21101,0,233,0,1105,1,579,21101,1182,22,1,21102,1,244,0,1105,1,979,21101,401,0,1,21101,255,0,0,1105,1,579,21101,1182,33,1,21101,0,266,0,1105,1,979,21101,0,414,1,21101,277,0,0,1106,0,579,3,575,1008,575,89,570,1008,575,121,575,1,575,570,575,3,574,1008,574,10,570,1006,570,291,104,10,21101,1182,0,1,21102,1,313,0,1106,0,622,1005,575,327,1101,0,1,575,21101,327,0,0,1106,0,786,4,438,99,0,1,1,6,77,97,105,110,58,10,33,10,69,120,112,101,99,116,101,100,32,102,117,110,99,116,105,111,110,32,110,97,109,101,32,98,117,116,32,103,111,116,58,32,0,12,70,117,110,99,116,105,111,110,32,65,58,10,12,70,117,110,99,116,105,111,110,32,66,58,10,12,70,117,110,99,116,105,111,110,32,67,58,10,23,67,111,110,116,105,110,117,111,117,115,32,118,105,100,101,111,32,102,101,101,100,63,10,0,37,10,69,120,112,101,99,116,101,100,32,82,44,32,76,44,32,111,114,32,100,105,115,116,97,110,99,101,32,98,117,116,32,103,111,116,58,32,36,10,69,120,112,101,99,116,101,100,32,99,111,109,109,97,32,111,114,32,110,101,119,108,105,110,101,32,98,117,116,32,103,111,116,58,32,43,10,68,101,102,105,110,105,116,105,111,110,115,32,109,97,121,32,98,101,32,97,116,32,109,111,115,116,32,50,48,32,99,104,97,114,97,99,116,101,114,115,33,10,94,62,118,60,0,1,0,-1,-1,0,1,0,0,0,0,0,0,1,84,18,0,109,4,2101,0,-3,587,20102,1,0,-1,22101,1,-3,-3,21102,1,0,-2,2208,-2,-1,570,1005,570,617,2201,-3,-2,609,4,0,21201,-2,1,-2,1106,0,597,109,-4,2106,0,0,109,5,2102,1,-4,629,21001,0,0,-2,22101,1,-4,-4,21102,1,0,-3,2208,-3,-2,570,1005,570,781,2201,-4,-3,652,21001,0,0,-1,1208,-1,-4,570,1005,570,709,1208,-1,-5,570,1005,570,734,1207,-1,0,570,1005,570,759,1206,-1,774,1001,578,562,684,1,0,576,576,1001,578,566,692,1,0,577,577,21101,702,0,0,1106,0,786,21201,-1,-1,-1,1106,0,676,1001,578,1,578,1008,578,4,570,1006,570,724,1001,578,-4,578,21101,0,731,0,1106,0,786,1105,1,774,1001,578,-1,578,1008,578,-1,570,1006,570,749,1001,578,4,578,21102,756,1,0,1106,0,786,1106,0,774,21202,-1,-11,1,22101,1182,1,1,21102,1,774,0,1106,0,622,21201,-3,1,-3,1105,1,640,109,-5,2105,1,0,109,7,1005,575,802,20101,0,576,-6,21002,577,1,-5,1106,0,814,21102,0,1,-1,21102,0,1,-5,21102,1,0,-6,20208,-6,576,-2,208,-5,577,570,22002,570,-2,-2,21202,-5,85,-3,22201,-6,-3,-3,22101,1505,-3,-3,1201,-3,0,843,1005,0,863,21202,-2,42,-4,22101,46,-4,-4,1206,-2,924,21101,0,1,-1,1105,1,924,1205,-2,873,21101,0,35,-4,1105,1,924,2101,0,-3,878,1008,0,1,570,1006,570,916,1001,374,1,374,2102,1,-3,895,1102,1,2,0,2101,0,-3,902,1001,438,0,438,2202,-6,-5,570,1,570,374,570,1,570,438,438,1001,578,558,922,20101,0,0,-4,1006,575,959,204,-4,22101,1,-6,-6,1208,-6,85,570,1006,570,814,104,10,22101,1,-5,-5,1208,-5,61,570,1006,570,810,104,10,1206,-1,974,99,1206,-1,974,1101,0,1,575,21102,973,1,0,1105,1,786,99,109,-7,2106,0,0,109,6,21101,0,0,-4,21102,0,1,-3,203,-2,22101,1,-3,-3,21208,-2,82,-1,1205,-1,1030,21208,-2,76,-1,1205,-1,1037,21207,-2,48,-1,1205,-1,1124,22107,57,-2,-1,1205,-1,1124,21201,-2,-48,-2,1106,0,1041,21101,0,-4,-2,1105,1,1041,21101,0,-5,-2,21201,-4,1,-4,21207,-4,11,-1,1206,-1,1138,2201,-5,-4,1059,2101,0,-2,0,203,-2,22101,1,-3,-3,21207,-2,48,-1,1205,-1,1107,22107,57,-2,-1,1205,-1,1107,21201,-2,-48,-2,2201,-5,-4,1090,20102,10,0,-1,22201,-2,-1,-2,2201,-5,-4,1103,1202,-2,1,0,1105,1,1060,21208,-2,10,-1,1205,-1,1162,21208,-2,44,-1,1206,-1,1131,1106,0,989,21101,0,439,1,1106,0,1150,21102,477,1,1,1106,0,1150,21101,0,514,1,21101,1149,0,0,1106,0,579,99,21101,0,1157,0,1105,1,579,204,-2,104,10,99,21207,-3,22,-1,1206,-1,1138,1202,-5,1,1176,1202,-4,1,0,109,-6,2106,0,0,46,7,78,1,84,1,84,1,84,1,84,1,80,13,72,1,3,1,7,1,72,1,3,1,7,1,9,11,52,1,3,1,7,1,9,1,9,1,52,1,3,1,7,1,9,1,9,1,52,1,3,1,7,1,9,1,9,1,44,13,7,1,9,1,9,1,44,1,7,1,11,1,9,1,9,1,44,1,7,1,11,1,9,1,9,1,44,1,7,1,11,1,9,1,9,1,42,11,11,1,9,1,9,1,42,1,1,1,19,1,9,1,9,1,42,1,1,1,19,11,9,11,32,1,1,1,82,1,1,1,82,1,1,1,82,1,1,1,82,1,1,1,72,13,72,1,9,1,74,1,9,11,64,1,19,1,64,1,19,1,64,1,19,1,64,1,19,1,64,1,19,1,64,1,19,1,64,1,19,1,64,11,9,1,74,1,9,1,72,13,72,1,1,1,82,1,1,1,82,1,1,1,82,1,1,1,82,1,1,1,52,11,19,1,1,1,52,1,9,1,19,1,1,1,52,1,9,1,11,11,52,1,9,1,11,1,7,1,54,1,9,1,11,1,7,1,54,1,9,1,11,1,7,1,54,13,5,13,64,1,1,1,5,1,3,1,72,1,1,1,5,1,3,1,72,1,1,1,5,1,3,1,72,1,1,1,5,1,3,1,72,1,1,1,5,1,3,1,72,13,74,1,5,1,78,1,5,1,78,1,5,1,78,1,5,1,78,1,5,1,78,7,66".split(",");
		long[] values = new long[inputArr.length * 1000];
		for (int i = 0; i < inputArr.length; i++) {
			values[i] = Long.parseLong(inputArr[i]);
		}
		int movementIndex = 0;
		int[] movementArr = new int[]{65, 44, 66, 44, 65, 44, 66, 44, 67, 44, 67, 44, 66, 44, 65, 44, 66, 44, 67, 10, //A,B,A,B,C,C,B,A,B,C
				76, 44, 49, 48, 44, 82, 44, 49, 48, 44, 76, 44, 49, 48, 44, 76, 44, 49, 48, 10, //L,10,R,10,L,10,L,10,
				82, 44, 49, 48, 44, 82, 44, 49, 50, 44, 76, 44, 49, 50, 10, //R,10,R,12,L,12,
				82, 44, 49, 50, 44, 76, 44, 49, 50, 44, 82, 44, 54, 10, //R,12,L,12,R,6
				110, 10}; //y
		values[0] = 2;
		int lastOuput = 0;
		Point point = new Point(0, 0);
		int maxX = 0;
		int maxY = 0;
		Map<Point, Character> map = new HashMap<>();
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
				//Add
				case 1:
					index = (int) values[i + 3];
					if (thirdMode == 2) {
						index += base;
					}
					values[index] = firstValue + secondValue;
					i += 4;
					break;
				//Multiply
				case 2:
					index = (int) values[i + 3];
					if (thirdMode == 2) {
						index += base;
					}
					values[index] = firstValue * secondValue;
					i += 4;
					break;
				//Write input
				case 3:
					index = (int) values[i + 1];
					if (firstMode == 2) {
						index += base;
					}
					values[index] = movementArr[movementIndex++];
					i += 2;
					break;
				//Print
				case 4:
					int outputVal = firstValue.intValue();
					lastOuput = outputVal;
					switch (outputVal) {
						case 10:
							point = new Point(0, point.y + 1);
							if (point.y > maxY) {
								maxY = point.y;
							}
							break;
						default:
							map.put(point, (char) outputVal);
							point = point.apply(Direction.RIGHT);
							if (point.x > maxX) {
								maxX = point.x;
							}
							break;
					}
					//Do something with output
					i += 2;
					break;
				//Jump if true
				case 5:
					if (firstValue != 0) {
						i = secondValue.intValue();
					} else {
						i += 3;
					}
					break;
				//Jump if false
				case 6:
					if (firstValue == 0) {
						i = secondValue.intValue();
					} else {
						i += 3;
					}
					break;
				//Less than
				case 7:
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
				//Equals
				case 8:
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
				case 9:
					base += firstValue;
					i += 2;
					break;
				case 99:
					break Loop;
				default:
					throw new RuntimeException("this shouldn't happen");
			}
		}
		int sum = 0;
		for (Point p : map.keySet()) {
			if (map.get(p) == '#' &&
					    compare(map, p, UP) &&
					    compare(map, p, DOWN) &&
					    compare(map, p, RIGHT) &&
					    compare(map, p, LEFT)) {
				sum += p.x * p.y;
			}
		}
		System.out.println(sum);
		for (int y = 0; y < maxY; y++) {
			System.out.println();
			for (int x = 0; x < maxX; x++) {
				Point p = new Point(x, y);
				if (map.containsKey(p)) {
					System.out.print(map.get(p));
				}
			}
		}
		System.out.println(lastOuput);
	}

	private static boolean compare(Map<Point, Character> map, Point p, Direction direction) {
		Point apply = p.apply(direction);
		Character character = map.get(apply);
		return map.containsKey(apply) && character == '#';
	}

	private static long select(long[] values, long i, long mode) {
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
