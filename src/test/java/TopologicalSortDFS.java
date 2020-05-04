import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopologicalSortDFS {

	@Test
	public void test() {
		int n = 4;
		List<Integer>[] graph = new List[n];

		for (int i=0; i<n; i++) {
			graph[i] = new ArrayList<>();
		}

		addEdge(graph, 0, 1);
		addEdge(graph, 0, 2);
		addEdge(graph, 1, 3);
		addEdge(graph, 3, 2);

		List<Integer> sorted = new ArrayList<>();
		int[] visited = new int[n];

		for (int i=0; i<n; i++) {
			if (visited[i] != 1)
				dfs(graph, visited, sorted, i);
		}
		Collections.reverse(sorted);
		System.out.println(sorted);
	}

	void dfs(List<Integer>[] graph, int[] visited, List<Integer> sorted, int u) {
		visited[u] = 1;

		for (Integer v : graph[u]) {
			if (visited[v] != 1)
				dfs(graph, visited, sorted, v);
		}

		sorted.add(u);
	}

	void addEdge(List<Integer>[] graph, int u, int v) {
		graph[u].add(v);
	}
}
