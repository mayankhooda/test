package test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingBuffer<E> {
    private final Queue<E> queue;
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition isBufferMaxedOut = lock.newCondition();
    private final Condition isBufferEmpty = lock.newCondition();
    private int capacity;
    private int size;

    public BlockingBuffer(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.queue = new LinkedList<>();
    }

    public void produce(E data) throws InterruptedException {
        lock.lock();
        try {
            if (size == capacity)
                isBufferMaxedOut.await();
            queue.add(data);
            size++;
            isBufferEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E consume() throws InterruptedException {
        lock.lock();
        E data;
        try {
            if (size == 0)
                isBufferEmpty.await();

            data = queue.poll();
            size--;
            isBufferMaxedOut.signal();
        } finally {
            lock.unlock();
        }
        return data;
    }
}
