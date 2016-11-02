package com.hlee.scratch;

public class Factorial {

    public static void main(String[] args) {

        System.out.println("factorial of 4 = " + fact_it(4));
    }

    // pre-condition: n >= 1
    static int fact(int n) {
        if (n == 1)
            return 1;
        else
            return n * fact(n - 1);
    }

    static int fact_it(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }
}
