package com.hlee.scratch.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ThenApplyExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenApply();

        thenAccept();
    }

    public static void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                // delaying the thread by 2 seconds
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "World";
        }).thenApply(name -> "Hello " + name); // Attaching a callback to the Future

        System.out.println("waiting for completableFuture to complete");
        // get() blocks and gets the result of the Future
        String result = completableFuture.get();
        System.out.println("result = " + result);
        System.out.println("---end of thenApply");
    }

    public static void thenAccept() {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                // delaying the thread by 2 seconds
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "World";
        }).thenAccept(name -> System.out.println("Hello " + name)); // Attaching a callback to the Future

        // get() blocks and gets the result of the Future
//        System.out.println(completableFuture.get());

        System.out.println("waiting for completableFuture to complete");
        completableFuture.join();
        System.out.println("---end of thenAccept");
    }

}
