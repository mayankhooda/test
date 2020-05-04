import org.junit.Test;

import java.util.*;

public class TopologicalSortBFS {

	@Test
	public void test() {
		int n = 4;
		List<Integer>[] graph = new List[n];

		for (int i=0; i<n; i++) {
			graph[i] = new ArrayList<>();
		}

		graph[0].add(1);
		graph[0].add(2);
		graph[1].add(3);
		graph[3].add(2);

		Queue<Integer> queue = new LinkedList<>();
		int[] indegree = new int[n];

		for (int i=0; i<n; i++)
			for (int v : graph[i])
				indegree[v]++;

		for (int i=0; i<n; i++)
			if (indegree[i] == 0)
				queue.add(i);

		List<Integer> sorted = new ArrayList<>();
		while(!queue.isEmpty()) {
			int u = queue.poll();
			sorted.add(u);

			for (int v : graph[u]) {
				indegree[v]--;
				if (indegree[v] == 0)
					queue.add(v);
			}
		}

		System.out.println(sorted);
	}

	@Test
	public void testt() {
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		pq.size();
		String s;

		double[] res = medianSlidingWindow(new int[]{1,2,3,4,2,3,1,4,2}, 3);
		for (double r : res)
			System.out.println(r);
	}

	public double[] medianSlidingWindow(int[] nums, int k) {
		int n = nums.length;

		Comparator<Integer> comparator = (a, b) -> {return nums[a]-nums[b] == 0 ? a-b : nums[a]-nums[b];};
		TreeSet<Integer> maxSet = new TreeSet<>(comparator.reversed());
		TreeSet<Integer> minSet = new TreeSet<>(comparator);

		int windowStart = 0;

		double[] res = new double[n-k+1];
		for (int windowEnd = 0; windowEnd<n; windowEnd++) {
			if (windowEnd >= k) {
				minSet.remove(windowStart);
				maxSet.remove(windowStart);
				windowStart++;
				balanceSets(minSet, maxSet);

				addToSets(windowEnd, nums, minSet, maxSet);
				balanceSets(minSet, maxSet);

				res[windowEnd-k+1] = median(minSet, maxSet, nums);
			} else if (windowEnd == k-1) {
				addToSets(windowEnd, nums, minSet, maxSet);
				balanceSets(minSet, maxSet);
				res[0] = median(minSet, maxSet, nums);
			}
			else {
				addToSets(windowEnd, nums, minSet, maxSet);
				balanceSets(minSet, maxSet);
			}
		}

		return res;
	}

	void addToSets(int idx, int[] num, TreeSet<Integer> minSet, TreeSet<Integer> maxSet) {
		if (!minSet.isEmpty() && num[idx] > num[minSet.first()])
			minSet.add(idx);
		else if (!maxSet.isEmpty() && num[idx] < num[maxSet.first()])
			maxSet.add(idx);
		else
			minSet.add(idx);
	}

	double median(TreeSet<Integer> minSet, TreeSet<Integer> maxSet, int[] nums) {
		if (minSet.size() > maxSet.size())
			return nums[minSet.first()];
		else if (minSet.size() < maxSet.size())
			return nums[maxSet.first()];
		else
			return (nums[maxSet.first()] + nums[minSet.first()]) / 2.0;
	}

	void balanceSets(TreeSet<Integer> minSet, TreeSet<Integer> maxSet) {
		if (minSet.size() - maxSet.size() > 1)
			maxSet.add(minSet.pollFirst());
		else if (maxSet.size() - minSet.size() > 1)
			minSet.add(maxSet.pollFirst());
	}

	@Test
	public void testMinWindow() {
		System.out.println(minWindow("a", "aa"));

		Deque<Integer> dq = new LinkedList<>();
		List<Integer> lsi;
	}

	@Test
	public void palindromePartition() {
		String s = "abc";
		int k = 2;
		int n = s.length();
		char c = 'a' + 2;
		System.out.println(c);
		int[][] dp = new int[n][n];

		for (int size=2; size<=n; size++) {
			for (int i=0; i<n-size+1; i++) {
				int j = i+size-1;
				if (size==2)
					dp[i][j] = s.charAt(i)==s.charAt(j) ? 0 : 1;
				else {
					if (s.charAt(i) == s.charAt(j))
						dp[i][j] = dp[i+1][j-1];
					else
						dp[i][j] = dp[i+1][j-1] + 1;
				}
			}
		}

		System.out.println(dp[0][2]);
	}

	public String minWindow(String s, String t) {
		int n = s.length();
		int m = t.length();

		HashMap<Character, Integer> reqCount = new HashMap<>();
		HashMap<Character, Integer> currCount = new HashMap<>();

		for (Character c : t.toCharArray()) {
			reqCount.put(c, reqCount.getOrDefault(c, 0) + 1);
		}

		int windowStart = 0;
		long minWindowSize = Integer.MAX_VALUE, minWindowStart=-1, minWindowEnd=-1;

		int reqChars = reqCount.size();
		for (int windowEnd = 0; windowEnd < n; windowEnd++) {
			char endChar = s.charAt(windowEnd);

			if (reqCount.containsKey(endChar)) {
				currCount.put(endChar, currCount.getOrDefault(endChar, 0) + 1);
				if (currCount.get(endChar) == reqCount.get(endChar))
					reqChars--;
			}

			if (reqChars > 0)
				continue;

			while(reqChars == 0) {
				if (windowEnd-windowStart+1 < minWindowSize) {
					minWindowSize = windowEnd-windowStart+1;
					minWindowStart = windowStart;
					minWindowEnd = windowEnd;
				}

				char startChar = s.charAt(windowStart);
				if (currCount.containsKey(startChar)) {
					currCount.put(startChar, currCount.get(startChar) - 1);

					if (currCount.get(startChar)-reqCount.get(startChar) < 0)
						reqChars++;
				}

				windowStart++;
			}
		}

		//return minWindowStart!=-1 ? s.substring(minWindowStart, minWindowEnd+1) : "";
		return "";
	}
}
