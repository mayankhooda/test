package test;

import org.junit.Test;

import java.util.*;

public class NCRAndGCDTest {

	@Test
	public void testNCR() {
		int R = 3;
		int n = 10;
		int r = 3;

		int numerator = 1;
		int denominator = 1;

		for (int i=1; i<=R; i++) {
			numerator = numerator * n;
			denominator = denominator * r;

			int gcd = findGCD(numerator, denominator);

			numerator /= gcd;
			denominator /= gcd;

			n--;
			r--;
		}

		System.out.println(numerator);
	}

	@Test
	public void testGCD() {
		int a = 12;
		int b = 9;

		System.out.println(findGCD(a, b));
	}

	private int findGCD(int a, int b) {
		if (b == 0)
			return a;
		if (a == 0)
			return b;

		if (a > b) {
			return findGCD(b, a % b);
		} else {
			return findGCD(a, b % a);
		}
	}

	@Test
	public void testShortestPath() {
		int[][] matrix = {{1, 1},
						  {2, 2}};
		int n = matrix.length;
		int m = matrix[0].length;
		int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
		int[][] visited = new int[n][m];
		int[][] dist = new int[n][m];

		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}

		PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);

		priorityQueue.add(new int[] {0, 0, 0});

		while(!priorityQueue.isEmpty()) {
			int[] u = priorityQueue.poll();
			visited[u[1]][u[2]] = 1;

			for (int i=0; i<4; i++) {
				int x = u[1] + dirs[i][0];
				int y = u[2] + dirs[i][1];

				if (x >= n || x < 0 || y >= m || y < 0 || visited[x][y] == 1)
					continue;

				int wt = 0;
				if (matrix[x][y] > matrix[u[1]][u[2]]) {
					wt = matrix[x][y] - matrix[u[1]][u[2]];
				}

				if (dist[x][y] > wt + u[0]) {
					dist[x][y] = wt + u[0];
					priorityQueue.add(new int[] {dist[x][y], x, y});
				}
			}
		}

		System.out.println(dist[n-1][m-1]);
	}
}
