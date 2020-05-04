import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphCycleDetection {

	static final int WHITE = 0;
	static final int GRAY = 1;
	static final int BLACK = 2;

	@Test
	public void test() {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		int n = 3;

		addEdge(graph, 0, 1);
		addEdge(graph, 0, 2);
		addEdge(graph, 1, 2);
		addEdge(graph, 2, 0);
		addEdge(graph, 2, 3);
		addEdge(graph, 3, 3);

		int[] color = new int[n];

		boolean isCyclic = false;
		for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
			int u = entry.getKey();

			if (color[u] == WHITE && isCyclic(graph, color, u)) {
				isCyclic = true;
				break;
			}
		}

		System.out.println(isCyclic);
	}

	private boolean isCyclic(Map<Integer, List<Integer>> graph, int[] color, int u) {

		color[u] = GRAY;

		List<Integer> neighbours = graph.get(u);
		for (Integer v : neighbours) {
			if (color[v] == GRAY || isCyclic(graph, color, v))
				return true;
		}

		color[u] = BLACK;

		return true;
	}

	void addEdge(Map<Integer, List<Integer>> graph, int u, int v) {
		graph.putIfAbsent(u, new ArrayList<>());
		graph.get(u).add(v);
	}
}
