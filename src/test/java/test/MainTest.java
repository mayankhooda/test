package test;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
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

        //PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt());

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
    public void testQuickSort() {

    }
}
