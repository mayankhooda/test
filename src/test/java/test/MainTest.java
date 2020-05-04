package test;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void whenLimitInfiniteStream_thenGetFiniteElements() {
        Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);

        String a = "1";

        int ba = 10;
        Map<Integer, Integer> map = new HashMap<>();

        map.put(ba, ba);

        List<Integer> collect = infiniteStream
                .skip(3)
                .limit(5)
                .collect(Collectors.toList());

        Collections.binarySearch(collect, 8, Comparator.comparingInt(o -> o));

        //Collections.shuffle();

        Iterator<Integer> iterator = collect.iterator();
        while(iterator.hasNext()) {
            Integer next = iterator.next();
        }

        // skipped : 2 4 8
        // limited : 16 32 64 128 256
        assertEquals(collect, Arrays.asList(16, 32, 64, 128, 256));
    }

    @Test
    public void testThis() {
        int[] input = {7, 6, 4, 8, 5, 4, 7};
        int n = input.length;

        int anchor = 0, max = Integer.MIN_VALUE;
        for(int i=1; i<n-1; i++) {
            int c = Integer.compare(input[i], input[i-1]);
            if (c == 0) {
                anchor = i;
            } else {
                if (c * Integer.compare(input[i+1], input[i]) >= 0) {
                    max = Integer.max(max, i - anchor + 1);
                    anchor = i;
                }
            }
        }

        assertEquals(5, max);
    }


    @Test
    public void testDiceGame() {
        int n = 2;
        int[] rollMax = {1, 1, 1, 1, 1, 1};

        assertEquals(30, searchDice(n, 0, -1, rollMax));
    }

    int searchDice(int n, int currLen, int consecNum, int[] rollMax) {
        if (n == 0) {
            return 1;
        }

        int sum = 0;
        for(int i=0; i<6; i++) {
            if (consecNum == i && currLen >= rollMax[i])
                continue;
            if (consecNum == i) {
                sum += searchDice(n - 1, currLen + 1, i, rollMax);
            } else {
                sum += searchDice(n - 1, 1, i, rollMax);
            }
        }

        return sum;
    }

    @Test
    public void testUniqueBinaryTrees() {
        int n = 3;
        int[] dp = new int[n+1];
        int[] visited = new int[n+1];

        dp[0] = 1;
        visited[0] = 1;

        assertEquals(5, searchTrees(n, dp, visited));
    }

    int searchTrees(int n, int[] dp, int[] visited) {
        if (visited[n] != 0) {
            return dp[n];
        }

        int sum = 0;
        for (int i=1; i<=n; i++) {
            sum += searchTrees(i-1, dp, visited) * searchTrees(n-i, dp, visited);
        }

        dp[n] = sum;
        visited[n] = 1;
        return sum;
    }

    @Test
    public void testMergeSort() {
        List<Integer> list = new LinkedList<>();
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);

        MergeSort<Integer> sorter = new MergeSort<>();
        Integer[] intList = list.toArray(new Integer[0]);

        sorter.sort(intList, 0, list.size(), Comparator.comparingInt(Integer::intValue));
        System.out.println(Arrays.asList(intList));
    }

    public class MergeSort<T> {

        void sort(T[] list, int l, int r, Comparator<T> comparator) {
            if (r-l == 1)
                return;

            int m = (l + r) / 2;

            sort(list, l, m, comparator);
            sort(list, m, r, comparator);

            merge(list, l, m, r, comparator);
        }

        void merge(T[] list, int l, int m, int r, Comparator<T> comparator) {
            int i = l;
            int j = m;

            T[] auxList = list.clone();
            for(int k=l; k<r; k++) {
                if (i == m) {
                    list[k] = auxList[j++];
                } else if (j == r) {
                    list[k] = auxList[i++];
                } else {
                    if (comparator.compare(auxList[i], auxList[j]) > 0) {
                        list[k] = auxList[j++];
                    } else {
                        list[k] = auxList[i++];
                    }
                }
            }
        }
    }

    @Test
    public void testPowerLogN() {
        int x = 3;
        int y = 5;

        assertEquals(0, power(x, y));
    }

    private long power(int x, int y) {
        long res = 1;

        while(y != 0) {
            if ((y & 1) == 1) {
                res = res * x;
            }

            y = y >> 1;
            x = x * x;
        }

        return res;
    }

    @Test
    public void testDuplicate() {
        int arr[] = {1, 2, 3, 4, 5, 1, 2, 3};

        int xAndY = 0;
        for (int i : arr) {
            xAndY = xAndY ^ i;
        }

        int rightMostOne = xAndY;
        int value = 1;
        while((rightMostOne & 1) != 1) {
            rightMostOne = rightMostOne >> 1;
            value = value << 1;
        }

        for (int i : arr) {
            if ((value & i) == 1) {
                xAndY = xAndY ^ i;
            }
        }

        assertEquals(4, xAndY);
    }

    @Test
    public void testMedianOfTwoSortedArrays() {
        int arr1[] = {1, 12, 15, 26, 38};
        int arr2[] = {2, 13, 17, 30, 45};

        assertEquals(16, findMedian(arr1, 0, arr1.length, arr2, 0, arr2.length));

        BlockingQueue<Integer> blockingQueue = new LinkedBlockingDeque<>();
    }

    private int findMedian(int[] arr1, int l1, int r1, int[] arr2, int l2, int r2) {
        if (r1-l1 == 2 && r2-l2 == 2)
            return (Math.max(arr1[l1], arr2[l2]) + Math.min(arr1[r1-1], arr2[r2-1])) / 2;

        int m1 = (l1 + r1) / 2;
        int m2 = (l2 + r2) / 2;

        if (arr1[m1] == arr2[m2]) {
            return arr1[m1];
        } else if (arr1[m1] < arr2[m2]) {
            return findMedian(arr1, m1, r1, arr2, l2, m2+1);
        } else {
            return findMedian(arr1, l1, m1+1, arr2, m2, r2);
        }
    }

    @Test
    public void testBooleanParenthesization() {
        boolean[] symbol = {true, false, true};
        char[] operator = {'^', '&'};
        int[][][] memo = new int[symbol.length][symbol.length][2];
        boolean[][] visited = new boolean[symbol.length][symbol.length];

        assertEquals(2, waysOfParenthesization(visited, memo, symbol, operator, 0, symbol.length-1)[1]);
    }

    private int[] waysOfParenthesization(boolean[][] visited, int[][][] memo, boolean[] symbol, char[] operator, int start, int end) {
        if (visited[start][end])
            return memo[start][end];

        int[] ways = new int[2];
        if (start == end) {
            if (symbol[start])
                ways[1]++;
            else
                ways[0]++;
        } else {
            for (int i=start; i<end; i++) {
                int[] leftWays = waysOfParenthesization(visited, memo, symbol, operator, start, i);
                int[] rightWays = waysOfParenthesization(visited, memo, symbol, operator, i+1, end);

                if (operator[i] == '^') {
                    ways[1] += leftWays[0] * rightWays[1] + leftWays[1] * rightWays[0];
                    ways[0] += leftWays[0] * rightWays[0] + leftWays[1] * rightWays[1];
                } else if (operator[i] == '&') {
                    ways[1] += leftWays[1] * rightWays[1];
                    ways[0] += leftWays[1] * rightWays[0] + leftWays[0] * rightWays[1] + leftWays[0] * rightWays[0];
                } else {
                    ways[1] += leftWays[1] * rightWays[0] + leftWays[0] * rightWays[1] + leftWays[1] * rightWays[1];
                    ways[0] += leftWays[0] * rightWays[0];
                }
            }
        }

        memo[start][end] = ways;
        visited[start][end] = true;
        return ways;
    }
}
