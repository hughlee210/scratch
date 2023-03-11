package com.hlee.scratch;

public class Fibonacci {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
//        for (int i = 0; i <= 50; i++) {
//            System.out.print(fiboIter(i, 1) + " ");
//        }
//        System.out.println();
//        long end = System.currentTimeMillis();
//        System.out.println("time taken: " + (end - start) + " millisec");
//        System.out.println("==============================================");

        start = System.currentTimeMillis();
        long val = fibo(20);
        System.out.println("result of fib without caching: " + val);
        System.out.println("time taken: " + (System.currentTimeMillis() - start) + " millisec");
        System.out.println("==============================================");

        start = System.currentTimeMillis();
        val = fibo_iter(50);
        System.out.println("result of fib using iteration: " + val);
        System.out.println("time taken: " + (System.currentTimeMillis() - start) + " millisec");
        System.out.println("==============================================");

        start = System.currentTimeMillis();
        val = fibo_caching(50);
        System.out.println("result of fib with caching: " + val);
        System.out.println("time taken: " + (System.currentTimeMillis() - start) + " millisec");
        System.out.println("==============================================");
    }

    // time: O(2^n) exponential
    static long fibo(int n) {
        if (n < 2) {
            return n;
        }
        return fibo(n - 1) + fibo(n - 2);
    }

    // time: O(n) linear, space: O(1)
    static long[] fib = new long[10000];

    // time: O(n) linear, space: O(n)
    static long fibo_caching(int n) {
        if (n < 2)
            return n;
        if (fib[n] != 0)
            return fib[n]; // return cached result
        fib[n] = fibo_caching(n - 1) + fibo_caching(n - 2);
        return fib[n];
    }

    // time: O(N), space: O(1) optimized
    static long fibo_iter(int n) {
        if (n < 2)
            return n;
        long fibo0 = 0, fibo1 = 1, fibo = 1;
        for (int i = 2; i <= n; i++) {
            fibo = fibo0 + fibo1; // fibonacci number is sum of previous two fibonacci numbers
            fibo0 = fibo1;
            fibo1 = fibo;
        }
        return fibo;
    }
}
