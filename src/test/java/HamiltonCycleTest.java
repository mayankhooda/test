import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class HamiltonCycleTest {

	@Test
	public void testCycle() {
		int[][] graph = {{1,2,3},{0},{0, 3},{0, 2}};
		int n = graph.length;

		boolean[][] dp = new boolean[n][1<<n];
		boolean[][] visited = new boolean[n][1<<n];

		int sum = 0;
		for (int i=0; i<n; i++) {
			if (solve(i, (1<<n)-1, n, dp, graph, visited)) {
				sum++;
			}
		}
		System.out.println(sum);
	}

	private boolean solve(int curr, int state, int n, boolean[][] dp, int[][] graph, boolean[][] visited) {
		if (visited[curr][state])
			return dp[curr][state];

		int[] neighbours = graph[curr];
		int nextState = state & (((1<<n)-1) ^ (1<<curr));

		if (nextState == 0)
			return true;

		boolean found = false;
		for (int neighbour : neighbours) {
			if (((nextState & (1<<neighbour)) != 0) && solve(neighbour, nextState, n, dp, graph, visited)) {
				found = true;
				break;
			}
		}

		visited[curr][state] = true;
		dp[curr][state] = found;
		return found;
	}



	@Test
	public void test() {
		int[][] graph = {{1,2,3},{0},{0},{0}};
		int n = graph.length;
		int[][] distance = new int[n][1<<n];
		for (int i=0; i<n; i++) {
			Arrays.fill(distance[i], Integer.MAX_VALUE);
		}

		Queue<int[]> queue = new LinkedList<>();
		for (int i=0; i<n; i++) {
			queue.add(new int[]{i, 1<<i, 0});
		}

		int shortestDistance = -1;
		while(!queue.isEmpty()) {
			int[] u = queue.poll();

			int curr = u[0];
			int state = u[1];
			int currDistance = u[2];

			if (state == (1<<n)-1) {
				shortestDistance = currDistance;
				break;
			}

			distance[curr][state] = currDistance;
			for (int neighbour : graph[curr]) {
				int nextState = state | (1<<neighbour);
				int nextDistance = currDistance+1;
				if (nextDistance < distance[neighbour][nextState])
					queue.add(new int[]{neighbour, nextState, nextDistance});
			}

		}
		System.out.println(shortestDistance);
	}
}
