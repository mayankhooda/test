import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BinarySearch {

	@Test
	public void test() {
		int[] arr = {5, 5, 5, 6, 6, 6, 7, 7};

		System.out.println(findCeil(6, arr));
		System.out.println(findFloor(6, arr));
		System.out.println(findUpperBound(6, arr));

		TreeSet<Integer> set = new TreeSet<>();
		set.add(4);
		set.add(1);
		set.add(3);
		set.add(2);

		Iterator<Integer> iter = set.descendingIterator();

		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

	int findUpperBound(int key, int[] arr) {
		int l = 0;
		int r = arr.length-1;

		int ceil = -1;
		while(l <= r) {
			int m = (l + r) / 2;

			if (arr[m] < key) {
				l = m+1;
			} else if (arr[m] > key) {
				ceil = m;
				r = m-1;
			} else {
				ceil = m;
				l = m+1;
			}
		}

		return ceil;
	}

	private int findFloor(int key, int[] arr) {
		int l = 0;
		int r = arr.length-1;

		int floor = -1;
		while(l <= r) {
			int m = (l + r) / 2;

			if (arr[m] < key) {
				floor = m;
				l = m+1;
			} else if (arr[m] > key) {
				r = m-1;
			} else {
				floor = m;
				break;
			}
		}

		return floor;
	}

	int findCeil(int key, int[] arr) {
		int l = 0;
		int r = arr.length-1;

		int ceil = -1;
		while(l <= r) {
			int m = (l + r) / 2;

			if (arr[m] < key) {
				l = m+1;
			} else if (arr[m] > key) {
				ceil = m;
				r = m-1;
			} else {
				ceil = m;
				break;
			}
		}

		return ceil;
	}
}
