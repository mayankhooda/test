package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        BlockingBuffer<Integer> buffer = new BlockingBuffer<>(5);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(true) {
                    try {
                        buffer.produce(++i);
                        System.out.println("produced    " + i);
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        executor.submit(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    Integer data = null;
                    try {
                        data = buffer.consume();
                        System.out.println("consumed    " + data);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
