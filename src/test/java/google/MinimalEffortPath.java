package google;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MinimalEffortPath {
	int N, M;

	@Test
	public void test() {
		int[][] matrix = {
				{1, 4, 7},
				{4, 5, 10},
				{6, 8, 10}
		};
		int limit = 2;
		N = matrix.length;
		M = matrix[0].length;

		visited = new int[N][M];

		System.out.println(dfs(0, 0, matrix, limit));
		System.out.println(dijkstra(matrix));
	}

	private int dijkstra(int[][] matrix) {
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0]-b[0]);
		int[][] dist = new int[N][M];
		visited = new int[N][M];
		for (int i=0; i<N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		heap.add(new int[]{0, 0, 0});

		while(!heap.isEmpty()) {
			int[] u = heap.poll();
			visited[u[1]][u[2]] = 1;
			for (int i=0; i<4; i++) {
				int x = u[1] + dirs[i][0];
				int y = u[2] + dirs[i][1];

				if (x<0 || x>=N || y<0 || y>=M || visited[x][y] == 1)
					continue;

				int wt = Math.max(u[0], Math.abs(matrix[x][y] - matrix[u[1]][u[2]]));

				if (dist[x][y] > wt) {
					dist[x][y] = wt;
					heap.add(new int[]{wt, x, y});
				}
			}
		}

		return dist[N-1][M-1];
	}

	int[][] dirs = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0,-1}
	};
	int[][] visited;
	private boolean dfs(int i, int j, int[][] matrix, int limit) {
		if (i == N-1 && j == M-1)
			return true;

		visited[i][j] = 1;

		for (int k=0; k<4; k++) {
			int x = i + dirs[k][0];
			int y = j + dirs[k][1];

			if (x < 0 || x >= N || y < 0 || y >= M || visited[x][y] == 1 || Math.abs(matrix[x][y] - matrix[i][j]) > limit)
				continue;

			if (dfs(x, y, matrix, limit))
				return true;
		}

		return false;
	}
}
