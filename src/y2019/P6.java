package y2019;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P6 {
	public static void main(String[] args) throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		Map<String, Node> nodes = new HashMap<>();
		while (true) {
			String next = br.readLine();
			if (next.isEmpty()) {
				break;
			}
			String[] inputParts = next.split("\\)");
			String first = inputParts[0];
			if (!nodes.containsKey(first)) {
				nodes.put(first, new Node(first));
			}
			String second = inputParts[1];
			if (!nodes.containsKey(second)) {
				nodes.put(second, new Node(second));
			}
			nodes.get(first).children.add(nodes.get(second));
			nodes.get(second).parent = nodes.get(first);
		}
		Node com = nodes.get("COM");
		Map<String, Integer> childrenCounts = new HashMap<>();
		for (String node : nodes.keySet()) {
			childrenCounts.put(node, 0);
		}
		populateSize(com, childrenCounts);
		int sum = 0;
		for (int i : childrenCounts.values()) {
			sum += i;
		}
		System.out.println(sum);

		Node you = nodes.get("YOU");
		List<Node> pathToYou = new ArrayList<>();
		while (you != null) {
			pathToYou.add(you.parent);
			you = you.parent;
		}
		Node san = nodes.get("SAN");
		List<Node> pathToSan = new ArrayList<>();
		while (san != null) {
			pathToSan.add(san.parent);
			san = san.parent;
		}
		int commonYou = 0;
		for (int i = 0; i < pathToYou.size(); i++) {
			if (pathToSan.contains(pathToYou.get(i))) {
				commonYou = i;
				break;
			}
		}
		int commonSan = 0;
		for (int i = 0; i < pathToSan.size(); i++) {
			if (pathToYou.contains(pathToSan.get(i))) {
				commonSan = i;
				break;
			}
		}
		System.out.println(commonYou+commonSan);
	}

	private static void populateSize(Node node, Map<String, Integer> childrenCounts) {
		if (node.children.size() == 0) {
			return;
		}
		int sum = node.children.size();
		for (Node childNode : node.children) {
			populateSize(childNode, childrenCounts);
			sum += childrenCounts.get(childNode.value);
		}
		childrenCounts.put(node.value, sum);
	}

	@Data
	static class Node {
		final String value;
		Node parent;
		Set<Node> children = new HashSet<>();

		public Node(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}
}
