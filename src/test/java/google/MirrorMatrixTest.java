package google;

import org.junit.Test;

public class MirrorMatrixTest {

	@Test
	public void test() {
		int[][] matrix = {
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,1,0},
				{0,0,0,0,1,0,0,0},
				{0,0,1,0,0,0,0,0},
				{2,0,1,0,1,0,1,0},
				{1,0,0,0,0,0,0,0},
				{1,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0}
		};

		System.out.println(solve(matrix));
	}

	private int solve(int[][] matrix) {
		int n = matrix.length;
		int[] rowcol = new int[n*2];

		int count = 0;

		int totalDest = 0;
		int maxDest = 0;
		int maxDestRowColIdx = -1;
		int startingIdxX = -1;
		int startingIdxY = -1;

		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (matrix[i][j] == 2) {
					startingIdxX = i;
					startingIdxY = j;
				}
			}
		}

		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (matrix[j][i] == 1)
					rowcol[i] += matrix[j][i];
			}

			totalDest += rowcol[i];

			if (rowcol[i] > maxDest) {
				maxDest = rowcol[i];
				maxDestRowColIdx = i;
			}
		}

		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (matrix[i][j] == 1)
					rowcol[n + i] += matrix[i][j];
			}

			totalDest += rowcol[i];

			if (rowcol[n + i] > maxDest) {
				maxDest = rowcol[n + i];
				maxDestRowColIdx = i;
			}
		}

		if (rowcol[n + startingIdxX] > rowcol[startingIdxY]) {
			totalDest -= rowcol[n + startingIdxX];
			for (int i=0; i<n; i++) {
				rowcol[i] -= matrix[startingIdxX][i];
			}
		}
		else {
			totalDest -= rowcol[startingIdxY];
			for (int i=0; i<n; i++) {
				rowcol[n + i] -= matrix[i][startingIdxY];
			}
		}
		while(totalDest != 0) {
			int idx = findMaxIdx(rowcol);
			totalDest -= rowcol[idx];
			rowcol[idx] = 0;

			if (idx < n) {
				for (int i=0; i<n; i++) {
					rowcol[n+i] -= matrix[i][idx];
				}
			} else {
				for (int i=0; i<n; i++) {
					rowcol[i] -= matrix[idx - n][i];
				}
			}
			count++;
		}
		return count;
	}

	private int findMaxIdx(int[] rowcol) {
		int max = -1;
		int maxIdx = -1;
		for (int i=0; i<rowcol.length; i++) {
			if (rowcol[i] > max) {
				max = rowcol[i];
				maxIdx = i;
			}
		}

		return maxIdx;
	}
}
