package test;

import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(1));

        ListenableFuture lfuture = listeningExecutorService.submit(new MyRunnable());
        lfuture.addListener(new MyListenerRunnable(), listeningExecutorService);
    }

    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("doing work...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("done work....");
        }
    }

    private static class MyListenerRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("doing work...");
        }
    }
}
