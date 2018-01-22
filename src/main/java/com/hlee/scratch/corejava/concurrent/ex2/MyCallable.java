package com.hlee.scratch.corejava.concurrent.ex2;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Long> {

    private String name;

    public MyCallable(String name) {
        this.name = name;
    }

    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (long i = 0; i <= 100; i++) {
            sum += i;
        }
        System.out.println("from " + name + ": sum = " + sum);
        return sum;
    }
}
