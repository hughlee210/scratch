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
        long val = fibo(10, 0);
        System.out.println("result of fib with caching: " + val);
        System.out.println("time taken: " + (System.currentTimeMillis() - start) + " millisec");
        System.out.println("==============================================");

        start = System.currentTimeMillis();
        val = fiboIter(50, 0);
        System.out.println("result of fib using iteration: " + val);
        System.out.println("time taken: " + (System.currentTimeMillis() - start) + " millisec");
        System.out.println("==============================================");

        start = System.currentTimeMillis();
        val = fibo_caching(50, 0);
        System.out.println("result of fib with caching: " + val);
        System.out.println("time taken: " + (System.currentTimeMillis() - start) + " millisec");
        System.out.println("==============================================");
    }

    // time: O(2^n) exponential
    static long fibo(int n, int count) {
        count++;
        if (n == 0) {
            System.out.println("exec count = " + count);
            return 0;
        } else if (n == 1) {
            return 1;
        }
        long result = fibo(n - 1, count) + fibo(n - 2, count);
        return result;
    }

    // time: O(n) linear, space: O(1)
    static long[] fib = new long[10000];

    // time: O(n) linear, space: O(n)
    static long fibo_caching(int n, int count) {
        count++;
        if (n == 0) {
            System.out.println("exec count = " + count);
            return 0;
        } else if (n == 1) {
            return 1;
        }
        if (fib[n] != 0)
            return fib[n]; // return cached result
        fib[n] = fibo_caching(n - 1, count) + fibo_caching(n - 2, count);
        return fib[n];
    }

    static long fiboIter(int n, int count) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        long fibo1 = 0, fibo2 = 1, fibo = 1;
        for (int i = 2; i <= n; i++) {
            fibo = fibo1 + fibo2; // fibonacci number is sum of previous two fibonacci numbers
            fibo1 = fibo2;
            fibo2 = fibo;
            count++;
        }
        System.out.println("exec count = " + count);
        return fibo;
    }

    static long fiboIter_ex(int n) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        long fibo1 = 0, fibo2 = 1, fibo = 0;
        for (int i = 2; i <= n; i++) {
            // fibonacci number is sum of previous two fibonacci numbers
            fibo = fibo1 + fibo2;
            fibo1 = fibo2;
            fibo2 = fibo;
        }
        return fibo;
    }

}
