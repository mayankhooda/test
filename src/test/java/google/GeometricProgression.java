package google;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GeometricProgression {

	@Test
	public void test() {
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 12};
		int n = arr.length;

		Map<Integer, Integer> left = new HashMap<>();
		Map<Integer, Integer> right = new HashMap<>();

		for (int i=0; i<n; i++) {
			right.putIfAbsent(arr[i], 0);
			right.put(arr[i], right.get(arr[i]) + 1);
		}

		left.put(arr[0], 1);
		int sum = 0;
		for (int i=1; i<n-1; i++) {
			if (arr[i]%2 == 0 && left.containsKey(arr[i] / 2) && right.containsKey(arr[i] * 2)) {
				sum += left.get(arr[i] / 2) * right.get(arr[i] * 2);
				System.out.println(arr[i]);
			}

			left.putIfAbsent(arr[i], 0);
			left.put(arr[i], left.get(arr[i]) + 1);
			right.put(arr[i+1], right.get(arr[i+1]) - 1);
		}
		System.out.println();
		System.out.println(sum);
	}
}
