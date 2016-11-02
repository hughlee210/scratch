package com.hlee.scratch;

public class Fibonacci {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            System.out.print(fiboLoop(i) + " ");
        }
        System.out.println();
        long end = System.currentTimeMillis();
        System.out.println("time taken: " + (end - start) + " millisec");
    }

    // time: O(2^n) exponential
    static long fibo(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        long result = fibo(n - 1) + fibo(n - 2);
        return result;
    }

    // time: O(n) linear, space: O(1)
    static long fiboLoop(int n) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        long fibo1 = 0, fibo2 = 1, fibo = 1;
        for (int i = 2; i <= n; i++) {
            fibo = fibo1 + fibo2; // fibonacci number is sum of previous two fibonacci numbers
            fibo1 = fibo2;
            fibo2 = fibo;
        }
        return fibo;
    }

    static long[] fib = new long[10000];

    // time: O(n) linear, space: O(n)
    static long fibo_caching(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        if (fib[n] != 0)
            return fib[n]; // return cached result
        fib[n] = fibo_caching(n - 1) + fibo_caching(n - 2);
        return fib[n];
    }

}
