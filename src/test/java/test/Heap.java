package test;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Heap {

    @Test
    public void testHeap() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 5;

        // build max heap in O(k)
        for (int i=k/2; i>=0; i--) {
            heapify(i, arr, k);
        }

        // O((n-k) * log k)
        for (int i=k/2; i<arr.length; i++) {
            if (arr[i] < arr[0]) {
                swap(arr, i, 0);
                heapify(0, arr, k);
            }
        }

        // O(k) without sorted
        // O(k * logk) with sorted
        System.out.println("building heap");
        System.out.println(Arrays.stream(arr).collect(Collectors.toList()));


        PriorityQueue<Integer> queue = new PriorityQueue<>();

        queue.add(5);
        queue.add(1);
        queue.add(4);
        queue.add(2);

        System.out.println(queue.poll());

    }

    private void heapify(int i, Integer[] arr, int n) {
        if (i >= n / 2)
            return;

        int largest = -1;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n)
            largest = arr[left] > arr[i] ? left : i;

        if (right < n)
            largest = arr[right] > arr[largest] ? right : largest;

        if (largest != i) {
            swap(arr, largest, i);
            heapify(largest, arr, n);
        }
    }

    void swap(Integer[] arr, int a, int b) {
        Integer temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
