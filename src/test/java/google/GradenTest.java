package google;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

public class GradenTest {

	@Test
	public void test() {
		int[] arr = {1, 2, 2, 3, 4, 6, 3, 1, 4, 2, 8};
		int n = arr.length;
		int k = 5;

		int[] left2right = new int[n];
		int[] right2left = new int[n];

		Arrays.fill(left2right, -1);
		Arrays.fill(right2left, -1);

		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);

		int sum = 0;
		for (int i=0; i<n; i++) {
			sum += arr[i];
			if (map.containsKey(sum - k)) {
				int idx = map.get(sum - k);
				left2right[i] = i - idx;
				if (i > 0 && left2right[i-1] != -1)
					left2right[i] = Math.min(left2right[i-1], left2right[i]);
			} else if (i!=0){
				left2right[i] = left2right[i-1];
			}
			map.put(sum, i);
		}

		map.clear();
		map.put(0, n);
		sum = 0;
		for (int i=n-1; i>=0; i--) {
			sum += arr[i];
			if (map.containsKey(sum-k)) {
				int idx = map.get(sum-k);
				right2left[i] = idx - i;
				if (i < n-1 && right2left[i+1] != -1)
					right2left[i] = Math.min(right2left[i+1], right2left[i]);
			} else if (i!=n-1){
				right2left[i] = right2left[i+1];
			}
			map.put(sum, i);
		}

		int res = Integer.MAX_VALUE;
		for (int i=0; i<n-1; i++) {
			if (left2right[i] != -1 && right2left[i+1] != -1)
				res = Math.min(res, left2right[i] + right2left[i+1]);
		}

		System.out.println(res);
	}
}
