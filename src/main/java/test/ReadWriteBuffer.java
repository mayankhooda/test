package test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReadWriteBuffer {

	Lock readLock = new ReentrantLock();
	Semaphore writeSemaphore = new Semaphore(1);

	int numReaders;

	void read() throws InterruptedException {
		readLock.lock();
		if (numReaders == 0) {
			writeSemaphore.acquire();
		}
		numReaders++;
		readLock.unlock();

		// read

		readLock.lock();
		numReaders--;
		if (numReaders == 0)
			writeSemaphore.release();
		readLock.unlock();
	}

	void write() throws InterruptedException {
		writeSemaphore.acquire();

		// write

		writeSemaphore.release();
	}
}
