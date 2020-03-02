package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testQS() {
        Integer[] arr = {5, 4, 3, 2, 1};

        QS<Integer> qs = new QS<>();
        qs.sort(arr, 0, arr.length, Comparator.comparingInt(o -> (int) o));

        System.out.println(Arrays.asList(arr));
    }

    private class QS<T> {

        public void sort(T[] arr, int l, int r, Comparator<T> comparator) {
            if (l >= r)
                return;

            int p = pivot(arr, l, r, comparator);

            sort(arr, l, p, comparator);
            sort(arr, p+1, r, comparator);
        }

        private int pivot(T[] arr, int l, int r, Comparator<T> comparator) {
            int p = (int)(Math.random() * (r - l)) % (r - l) + l;
            
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
        Integer[] arr = {5, 4, 3, 2, 1};

        MS<Integer> ms = new MS<>();
        ms.sort(arr, 0, arr.length, Comparator.comparingInt(o -> o));

        System.out.println(Arrays.asList(arr));
    }

    private class MS<T> {

        public void sort(T[] arr, int l, int r, Comparator<T> comparator) {
            if (r - l == 1)
                return;

            int m = (l + r) / 2;

            sort(arr, l, m, comparator);
            sort(arr, m, r, comparator);

            merge(arr, l, m, r, comparator);
        }

        private void merge(T[] arr, int l, int m, int r, Comparator<T> comparator) {
            int i = l;
            int j = m;

            T[] aux = arr.clone();
            for(int k=l; k<r; k++) {
                if (i == m) {
                    arr[k] = aux[j++];
                } else if (j == r) {
                    arr[k] = aux[i++];
                } else {
                    if (comparator.compare(aux[i], aux[j]) > 0) {
                        arr[k] = aux[j++];
                    } else {
                        arr[k] = aux[i++];
                    }
                }
            }
        }
    }
}
