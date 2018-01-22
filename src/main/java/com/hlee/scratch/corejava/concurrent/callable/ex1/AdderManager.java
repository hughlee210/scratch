package com.hlee.scratch.corejava.concurrent.callable.ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AdderManager {

    public static void main(String[] args) {

        AdderManager man = new AdderManager();
        man.sumParallel();
        man.sumSequential();

    }

    void sumParallel() {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Integer>> list = new ArrayList<>();
        int prev = 1;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println("* submitting " + prev + " and " + i);
                Future<Integer> future = executorService.submit(new CallableAdder(prev, i));
                list.add(future);
            }
            prev = i;
        }

        int sum = 0;
        for (Future<Integer> future : list) {
            try {
                sum += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();

        System.out.println("*** sum parellel = " + sum);
        System.out.println("*** Time taken = " + (System.currentTimeMillis() - start) + " millisec");
    }

    void sumSequential() {
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println("*** sum sequential = " + sum);
        System.out.println("*** Time taken = " + (System.currentTimeMillis() - start) + " millisec");
    }
}

class CallableAdder implements Callable<Integer> {

    private Integer operand1;
    private Integer operand2;

    public CallableAdder(Integer operand1, Integer operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public Integer call() throws Exception {

        System.out.println(Thread.currentThread().getName() + " says: partial sum for " + operand1 + " + " + operand2 + " = " + (operand1 + operand2));
        return operand1 + operand2;
    }

}

