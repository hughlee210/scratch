package com.hlee.scratch.corejava.concurrent.ex1;

public class MyRunnable implements Runnable {

    private final long countUntil;
    private final long startTime;

    MyRunnable(long countUntil, long startTime) {
        this.countUntil = countUntil;
        this.startTime = startTime;
    }

    @Override
    public void run() {
        long sum = 0;
        for (long i = 1; i < countUntil; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        //        System.out.println("thread name: " + Thread.currentThread().getName() + ", sum = " + sum);
        //        System.out.format("Thread name: %20s, sum = %d%n", Thread.currentThread().getName(), sum);
        System.out.printf("Thread name: %20s, sum = %d, calculated in %d millisec. %n", Thread.currentThread().getName(), sum, (end - startTime));
    }
}
