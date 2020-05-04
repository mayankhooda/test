package test;

import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class TreeDiameter {

	@Test
	public void testBinaryTreeDiameter() {
		Integer[] values = {1, 2, 3, 4, null, null, null};
		TreeNode root = BuildTree.buildTree(values);
		String s;
		//s.char
		//assertEquals(5, diameter(root)[0]);
		assertEquals(1, (int) Integer.valueOf("1"));
	}

	private int[] diameter(TreeNode node) {
		int[] ret = new int[2];

		if (node == null)
			return ret;

		int[] ldh = diameter(node.left);
		int[] rdh = diameter(node.right);

		ret[0] = Math.max(Math.max(ldh[0], rdh[0]), 1 + rdh[1] + ldh[1]);
		ret[1] = Math.max(rdh[1], ldh[1]) + 1;
		Integer.valueOf('1');

		return ret;
	}


	@Test
	public void testGold() {
		int[] A = {10, 13, 12, 14, 15};
		int n = A.length;
		int[][] dp = new int[n][2];

		dp[n-1][1] = 1;
		dp[n-1][0] = 1;

		TreeMap<Integer, Integer> map = new TreeMap<>();

		map.put(A[n-1], n-1);

		for (int i=n-2; i>=0; i--) {
			int x = A[i];

			Integer minUpper = map.ceilingKey(x);
			Integer maxLower = map.floorKey(x);

			if (minUpper != null) {
				dp[i][1] = dp[map.get(minUpper)][0];
			}
			if (maxLower != null) {
				dp[i][0] = dp[map.get(maxLower)][1];
			}

			map.put(x, i);

		}

		int count = 0;
		for (int i=0; i<n; i++) {
			if (dp[i][0] == 1)
				count++;
		}

		assertEquals(2, count);
	}

	int count = 0;

	private boolean backtrack(int n, int[] arr, StringBuilder sb) {
		String str = sb.toString();

		if (!str.equals("") && Integer.parseInt(str) > n)
			return false;

		for (int i=0; i<5; i++) {
			if (sb.toString().isEmpty() && i == 0)
				continue;

			sb.append(arr[i]);
			if (backtrack(n, arr, sb) && isValid(sb)) {
				count++;
				System.out.println(sb.toString());
			}
			sb.deleteCharAt(sb.length()-1);
		}

		return true;
	}

	private boolean isValid(StringBuilder sb) {
		String str = sb.toString();
		String rot = rotate(str);

		if (str.equals(rot))
			return false;
		else
			return true;
	}

	private String rotate(String str) {
		StringBuilder sb = new StringBuilder();
		for (Character c : str.toCharArray()) {
			if (c == '6')
				sb.append('9');
			else if (c == '9')
				sb.append('6');
			else
				sb.append(c);
		}
		return sb.reverse().toString();
	}
}
