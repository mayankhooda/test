package google;

import org.junit.Test;

public class MaxProductSubset {

	@Test
	public void test() {
		double[] arr = {0.1, -1.2, -2.2, -1.0};
		int n = arr.length;
		int k = 3;

		double[][][] dp = new double[2][2][k+1];

		dp[0][0][0] = 1;
		dp[1][0][0] = 1;

		for (int i=1; i<=n; i++) {
			for (int j=1; j<=k; j++) {
				if (arr[i-1] >= 0) {
					dp[i%2][0][j] = Math.max(arr[i-1] * dp[(i-1)%2][0][j-1], dp[(i-1)%2][0][j]);
					dp[i%2][1][j] = Math.min(arr[i-1] * dp[(i-1)%2][1][j-1], dp[(i-1)%2][1][j]);
				} else if (arr[i-1] < 0) {
					dp[i%2][0][j] = Math.max(arr[i-1] * dp[(i-1)%2][1][j-1], dp[(i-1)%2][0][j]);
					dp[i%2][1][j] = Math.min(arr[i-1] * dp[(i-1)%2][0][j-1], dp[(i-1)%2][1][j]);
				}
			}
		}

		System.out.println(dp[n%2][0][k]);
	}

	double solve(int i, boolean positive, int k, int[] arr, int[][] memo) {
		if (k == 0) {
			return positive ? 1 : 0;
		}



		double ret;
		if (positive) {
			if (arr[i] >= 0)
				ret = Math.max(arr[i] * solve(i-1, true, k - 1, arr, memo), solve(i - 1, true, k, arr, memo));
			else
				ret = Math.max(arr[i] * solve(i-1, false, k-1, arr, memo), solve(i-1, true, k, arr, memo));
		} else {
			if (arr[i] >= 0)
				ret = Math.max(arr[i] * solve(i-1, false, k-1, arr, memo), solve(i-1, false, k, arr, memo));
			else
				ret = Math.min(arr[i] * solve(i-1, true, k-1, arr, memo), solve(i-1, false, k, arr, memo));
		}

		return ret;
	}
}
