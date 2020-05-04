package test;

import java.util.*;

public class DSU {
	private final int[] rank;
	private final int[] parent;

	public DSU(int size) {
		List<Character> list = new ArrayList<>();

		this.parent = new int[size];
		this.rank = new int[size];
	}

	void makeSet(int v) {
		parent[v] = v;
		rank[v] = 0;
	}

	int find(int v) {
		int root = v;
		while(root != parent[root]) {
			root = parent[root];
		}

		while(parent[v] != root) {
			int p = parent[v];
			parent[v] = root;
			v = p;
		}
		return root;
	}

	void union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);

		if (xRoot == yRoot)
			return;

		if (rank[xRoot] < rank[yRoot]) {
			int temp = xRoot;
			xRoot = yRoot;
			yRoot = temp;
		}

		parent[yRoot] = xRoot;
		if (rank[xRoot] == rank[yRoot]) {
			rank[xRoot]++;
		}
	}
}
