package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public Test() {

    }

    public void execute() {
        MyClass i = new MyClass(0);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(new MyRunnable(i));
        executorService.submit(new MyRunnable2(i));
    }
}

class MyClass {
    private Integer i;
    public MyClass(Integer i) {
        this.i = i;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }
}

class MyRunnable2 implements Runnable {

    private MyClass i;

    public MyRunnable2(MyClass i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(String.format("modifying value -> %d", this.i.getI()));
        try {
            Thread.sleep(500);
            this.i.setI(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("to value -> %d", this.i.getI()));
    }
}

class MyRunnable implements Runnable {

    private final MyClass i;

    public MyRunnable(MyClass i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(String.format("First value -> %d", this.i.getI()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("Secnd value -> %d", this.i.getI()));
    }
}
