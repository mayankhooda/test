package dp;

import org.junit.Test;

public class BinarySearchTest {

	@Test
	public void test() {
		System.out.println(splitArray(new int[]{5,2,4,1,3,6,0}, 4));
	}

	public int splitArray(int[] nums, int m) {
		int sum = 0;
		int maxElement = -1;
		int[] prefix = new int[nums.length];
		for(int i=0; i<nums.length; i++) {
			sum+=nums[i];
			prefix[i] = sum;
			maxElement = Math.max(maxElement, nums[i]);
		}

		long l = maxElement;
		long r = sum;

		int ceiling = -1;
		while(l <= r) {
			int targetSum = (int) ((l + r) / 2);

			int res = validate(targetSum, m, prefix);

			if (res < 0) {
				l = targetSum+1;
			} else {
				ceiling = targetSum;
				r = targetSum-1;
			}
		}

		return ceiling;
	}

	// 15 -> 13
	// 13 + 15 = 28
	// 1 3 5 7  9  11
	// 1 4 9 16 25 36
	int validate(int targetSum, int groups, int[] prefix) {

		int x = targetSum;
		int k = 0;
		int idx = -1;
		while(idx < prefix.length-1) {
			idx = findFloor(x, prefix);
			x = prefix[idx] + targetSum;
			k++;
		}

		return groups-k;
	}

	int findFloor(int x, int[] arr) {
		int l = 0;
		int r = arr.length-1;

		int floor = -1;
		while(l <= r) {
			int m = (l + r) / 2;

			if (arr[m] < x) {
				floor = m;
				l = m+1;
			} else if (arr[m] > x) {
				r = m-1;
			} else {
				floor = m;
				break;
			}
		}

		return floor;
	}

	@Test
	public void faltu() {
		System.out.println(2147483646 + 2);
	}
}
