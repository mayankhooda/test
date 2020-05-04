package test;

import java.util.Collections;
import java.util.List;

class Edge implements Comparable<Edge>{
	int u;
	int v;
	int weight;

	@Override
	public int compareTo(Edge o) {
		return o.weight;
	}
}
public class Kruskal {

	int findCost(List<Edge> edges, int m) {
		int cost = 0;

		DSU dsu = new DSU(m);

		Collections.sort(edges);

		for (Edge edge : edges) {
			if (dsu.find(edge.u) != dsu.find(edge.v)) {
				cost += edge.weight;
				dsu.union(edge.u, edge.v);
			}
		}

		return cost;
	}
}
