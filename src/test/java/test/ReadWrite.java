package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReadWrite {

	class Buffer {

		int waitingWriter;
		int numberOfWriters;
		int waitingReaders;
		int numberOfReaders;

		Lock lock = new ReentrantLock();

		Condition canRead = lock.newCondition();
		Condition canWrite = lock.newCondition();

		void read() {
			lock.lock();

			if (numberOfWriters == 1 || waitingWriter > 0) {
				waitingReaders++;
				try {
					canRead.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				waitingReaders--;
			}
			numberOfReaders++;
			canRead.notify();

			lock.unlock();

			// reading

			lock.lock();
			numberOfReaders--;
			if (numberOfReaders == 0) {
				canWrite.signal();
			}
			lock.unlock();
		}

		void write() {
			lock.lock();

			if (numberOfWriters == 1 || numberOfReaders > 0) {
				waitingWriter++;

				try {
					canWrite.wait();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				waitingWriter--;
			}

			numberOfWriters = 1;
			lock.unlock();

			// write

			lock.lock();
			numberOfWriters = 0;
			if (waitingReaders > 0) {
				canRead.signal();
			} else {
				canWrite.signal();
			}
			lock.unlock();
		}


	}
}
