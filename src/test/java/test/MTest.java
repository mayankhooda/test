package test;

import org.junit.Test;

import java.util.Arrays;
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
        Random random = new Random();

        void sort(T[] input, int l, int r, Comparator<T> comparator) {
            if (l >= r) {
                return;
            }

            int p = pivot(input, l, r, comparator);

            sort(input, l, p, comparator);
            sort(input, p+1, r, comparator);
        }

        private int pivot(T[] input, int l, int r, Comparator<T> comparator) {
            int p = random.nextInt(r - l) + l;

            swap(input, p, r-1);
            T pivotValue = input[r-1];

            int i = l;
            for (int j=l; j<r; j++) {
                if (comparator.compare(input[j], pivotValue) < 0) {
                    swap(input, j, i++);
                }
            }

            swap(input, i, r-1);
            return i;
        }

        private void swap(T[] input, int a, int b) {
            T temp = input[b];
            input[b] = input[a];
            input[a] = temp;
        }
    }

    @Test
    public void testMS() {
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

    class MergeSort<T> {

        void sort(T[] input, int l, int r, Comparator<T> comparator) {
            if (r - l == 1) {
                return;
            }

            int m = (l + r) / 2;

            sort(input, l, m, comparator);
            sort(input, m, r, comparator);

            merge(input, l, m, r, comparator);
        }

        private void merge(T[] input, int l, int m, int r, Comparator<T> comparator) {
            int i = l;
            int j = m;

            T[] aux = input.clone();
            for (int k=l; k<r; k++) {
                if (i == m) {
                    input[k] = aux[j++];
                } else if (j == r) {
                    input[k] = aux[i++];
                } else {
                    if (comparator.compare(aux[i], aux[j]) >= 0) {
                        input[k] = aux[j++];
                    } else {
                        input[k] = aux[i++];
                    }
                }
            }
        }
    }
}
