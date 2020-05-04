package google;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BricksTest {

	HashMap<Integer, List<List<Integer>>> memo;
	List<List<Integer>> list;

	@Test
	public void test() {
		int[] bricks = {1, 2, 3};
		int h = 1;
		int w = 6;

		memo = new HashMap<>();

		System.out.println(solve(h, w, bricks));
	}

	private List<List<Integer>> solve(int h, int w, int[] bricks) {
		if (w < 0)
			return null;

		if (w == 0)
			return new ArrayList<>();

		if (memo.containsKey(w)) {
			return new ArrayList<>(memo.get(w));
		}

		List<List<Integer>> ret = new ArrayList<>();
		for (int i=0; i<bricks.length; i++) {
			List<List<Integer>> ll = solve(h, w-bricks[i], bricks);

			if (ll == null)
				continue;

			if (ll.isEmpty()) {
				List<Integer> l = new ArrayList<>();
				l.add(i);
				ret.add(l);
			} else {
				for (List<Integer> l : ll) {
					if (l == null || l.isEmpty()) {
						l = new ArrayList<>();
					}
					l.add(i);
					ret.add(l);
				}
			}
		}

		memo.put(w, ret);
		return ret;
	}
}
