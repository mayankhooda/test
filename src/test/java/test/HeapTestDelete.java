package test;

import org.junit.Test;

public class HeapTestDelete {

    @Test
    public void test_Heap() {

    }

    class Heap<T extends Comparable<T>> {

        private final int capacity;
        private final Comparable<T> comparable;
        private final T[] elements;
        private int size;

        public Heap(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.elements = (T[]) new Comparable[capacity];
            this.comparable = null;
        }

        public Heap(int capacity, Comparable<T> comparable) {
            this.capacity = capacity;
            this.comparable = comparable;
            this.size = 0;
            this.elements = (T[]) new Object[capacity];
        }

        boolean add(T data) {
            if (size == capacity)
                return false;

            elements[size++] = data;
            bubbleUp();
            return true;
        }

        private void bubbleUp() {
            int child = size - 1;
            int parent = (child - 1) / 2;

            while(parent >= 0 && elements[child].compareTo(elements[parent]) < 0) {
                swap(child, parent);
                child = parent;
                parent = (child - 1) / 2;
            }
        }

        private void swap(int a, int b) {
            T temp = elements[a];
            elements[a] = elements[b];
            elements[b] = temp;
        }
    }
}
