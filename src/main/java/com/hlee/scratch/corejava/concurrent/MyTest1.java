package com.hlee.scratch.corejava.concurrent;

import java.util.concurrent.TimeUnit;

public class MyTest1 {

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("Foo " + name);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Bar " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        thread.join();

    }

}
