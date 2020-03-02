package test;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MTest {

    @Test
    public void testQS() {
        List<Integer> list = new LinkedList<>();
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);

        QuickSort<Integer> sorter = new QuickSort<>();
        Integer[] intList = list.toArray(new Integer[0]);
        sorter.sort(intList, 0, list.size(), Comparator.comparingInt(Integer::intValue));
        System.out.println(Arrays.asList(intList));
    }

    class QuickSort<T> {

        void sort(T[] arr, int l, int r, Comparator<T> comparator) {
            if (l == r)
                return;

            int p = pivot(arr, l, r, comparator);

            sort(arr, l, p, comparator);
            sort(arr, p+1, r, comparator);
        }

        /*
        r = 10
        l = 1
         */
        private int pivot(T[] arr, int l, int r, Comparator<T> comparator) {
            int p = (int)(Math.random() * (r - l)) + l;

            swap(arr, p, r-1);
            int k = l;
            for(int i=l; i<r-1; i++) {
                if (comparator.compare(arr[i], arr[r-1]) < 0) {
                    swap(arr, k++, i);
                }
            }
            swap(arr, k, r-1);
            return k;
        }

        private void swap(T[] arr, int a, int b) {
            T temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }

    @Test
    public void testMS() {
        List<Integer> list = new LinkedList<>();
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(1);
        list.add(2);

        Collections.reverse(list);

        MergeSort<Integer> sorter = new MergeSort<>();
        Integer[] intList = list.toArray(new Integer[0]);
        System.out.println(sorter.sort(intList, 0, list.size(), Comparator.comparingInt(Integer::intValue)));
        System.out.println(Arrays.asList(intList));
    }

    class MergeSort<T> {
        int sort(T[] arr, int l, int r, Comparator<T> comparator) {
            if (r - l == 1)
                return 0;

            int m = (r + l) / 2;

            int leftInversion = sort(arr, l, m, comparator);
            int rightInversion = sort(arr, m, r, comparator);

            return merge(arr, l, m, r, comparator) + leftInversion + rightInversion;
        }

        private int merge(T[] arr, int l, int m, int r, Comparator<T> comparator) {
            int i = l;
            int j = m;
            int swaps = 0;

            T[] aux = arr.clone();
            for(int k=l; k<r; k++) {
                if (i == m)
                    arr[k] = aux[j++];
                else if (j == r)
                    arr[k] = aux[i++];
                else {
                    if (comparator.compare(aux[i], aux[j]) > 0) {
                        arr[k] = aux[j++];
                        swaps += m - i;
                    }
                    else
                        arr[k] = aux[i++];
                }
            }

            return swaps;
        }


    }
}
