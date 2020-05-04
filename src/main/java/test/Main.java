package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        double maxSpeedup = 0;
        int maxSpeedupProcessors = Integer.MAX_VALUE;
        long numberOfParallelProcesses = 50;
        for (int j=1; j<=50; j++) {
            ExecutorService executorService = Executors.newFixedThreadPool(j);
            long totalTimeTaken = 0;
            for (int i=1; i<=10; i++) {
                long currentTime = System.currentTimeMillis();

                List<Future<?>> processes = new ArrayList<>();
                for (int l=1; l<=numberOfParallelProcesses; l++) {
                    processes.add(executorService.submit(new Process(i*10)));
                }
                try {
                    for (Future<?> process : processes)
                        process.get();

                    long timeTaken = System.currentTimeMillis() - currentTime;
                    totalTimeTaken+=timeTaken;
                    //System.out.println("time taken for " + i*10 +"ms long process\t:\t" + timeTaken);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            double speedup = ((double)550 / totalTimeTaken)*100;
            if (speedup > maxSpeedup) {
                maxSpeedup = speedup;
                maxSpeedupProcessors = j;
            }
            System.out.println("speedup on " + j + " threads\t:\t" + speedup);
            executorService.shutdown();
        }
        System.out.println("\n####################################################\n");
        System.out.println("Number of parallel proccesses\t:\t" + numberOfParallelProcesses);
        System.out.println("Max Speedup achieved\t\t:\t" + maxSpeedup);
        System.out.println("Optimal number of threads\t:\t" + maxSpeedupProcessors);
        System.exit(0);
    }
}

class Process implements Runnable{

    private final long time;

    Process(long time) {
        this.time = time;
    }

    @Override
    public void run() {
        long curr = System.currentTimeMillis();
        while(System.currentTimeMillis()-curr < time) {}
    }
}
