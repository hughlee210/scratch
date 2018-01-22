package com.hlee.scratch;

public class Factorial {

    public static void main(String[] args) {

        int input = 5;
        System.out.println("factorial of " + input + " = " + fact(input, 1));

        input = 10;
        System.out.println("factorial of " + input + " = " + fact(input, 1));
    }

    // pre-condition: n >= 1
    static int fact(int n, int count) {
        System.out.println("exec count = " + count);
        assert n >= 0;
        if (n == 0)
            return 1;
        else
            return n * fact(n - 1, ++count);
    }

    static int fact_ex(int n) {
        // exercise
        assert n >= 0;
        if (n == 0)
            return 1;
        else
            return n * fact_ex(n - 1);
    }

    static int fact_it(int n) {
        assert n >= 0;
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    static int fact_it_ex(int n) {
        assert n >= 0;
        int result = 1;
        for (int i = n; i > 1; i--) {
            result = result * i;
        }
        return result;
    }
}
