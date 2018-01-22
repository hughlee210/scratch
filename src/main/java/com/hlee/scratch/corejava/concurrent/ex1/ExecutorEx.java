package com.hlee.scratch.corejava.concurrent.ex1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorEx {

    private static final int NTHREADS = 10;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
        for (int i = 0; i < 500; i++) {
            Runnable worker = new MyRunnable(10000000L + i, start);
            executor.execute(worker);
        }
        // This will make the executor accept no new threads
        // and finish all existing threads in the queue
        executor.shutdown();
        // Wait until all threads are finish
        // executor.awaitTermination(1, TimeUnit.MILLISECONDS);

        long end = System.currentTimeMillis();
        System.out.println("Finished all threads in " + (end - start) + " millisec.");
    }
}
