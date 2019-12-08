package y2019;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P3 {
	public static void main(String[] args) throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		String[] input1 = br.readLine().split(",");
		List<String> pathList = path(input1);
		Set<String> path1 = new HashSet<>(pathList);
		String[] input2 = br.readLine().split(",");
		List<String> pathList2 = path(input2);
		Set<String> path2 = new HashSet<>(pathList2);

		int min = Integer.MAX_VALUE;
		Set<String> potentials = new HashSet<>();
		for (String s : path1) {
			if (!path2.contains(s)) {
				continue;
			}
			potentials.add(s);
			String[] parts = s.split("x");
			int sum = Math.abs(Integer.parseInt(parts[0]))+Math.abs(Integer.parseInt(parts[1]));
			if(sum < min){
				min = sum;
			}
		}
		System.out.println(min);
		System.out.println();

		int min2 = Integer.MAX_VALUE;
		for(String potential : potentials){
			int c1 = find(pathList, potential);
			int c2 = find(pathList2, potential);
			System.out.println(c1+c2);
			if(c1+c2 < min2){
				min2 = c1+c2;
			}
		}
		System.out.println();
		System.out.println(min2);
	}

	private static int find(List<String> pathList, String potential) {
		for(int i = 0; i < pathList.size();i++){
			if (pathList.get(i).equals(potential)){
				return i+1;
			}
		}
		throw new RuntimeException();
	}

	private static List<String> path(String[] input1) {
		List<String> path = new ArrayList<>();
		int xCurr = 0;
		int yCurr = 0;
		for (String s : input1) {
			char c = s.charAt(0);
			int magnitude = Integer.parseInt(s.substring(1));
			switch (c) {
				case 'U':
					for (int i = 1; i <= magnitude; i++) {
						path.add(xCurr + "x" + (yCurr + i));
					}
					yCurr += magnitude;
					break;
				case 'D':
					for (int i = 1; i <= magnitude; i++) {
						path.add(xCurr + "x" + (yCurr - i));
					}
					yCurr -= magnitude;
					break;
				case 'R':
					for (int i = 1; i <= magnitude; i++) {
						path.add((xCurr + i) + "x" + yCurr);
					}
					xCurr += magnitude;
					break;
				case 'L':
					for (int i = 1; i <= magnitude; i++) {
						path.add((xCurr - i) + "x" + yCurr);
					}
					xCurr -= magnitude;
			}
		}
		return path;
	}
}
