package com.hlee.scratch;

public class Factorial {

    public static void main(String[] args) {

        int input = 4;
        System.out.println("factorial of " + input + " = " + fact(input));
        System.out.println("factorial of " + input + " = " + tailFact(input, 1));

        input = 10;
        System.out.println("factorial of " + input + " = " + fact(input));
        System.out.println("factorial of " + input + " = " + tailFact(input, 1));
    }

    // pre-condition: n >= 1
    static int fact(int n) {
        assert n >= 0;
        if (n <= 1)
            return 1;
        else
            return n * fact(n - 1);
    }

    static int tailFact(int n, int totalSoFar) {
        if (n == 0) {
            return totalSoFar;
        } else {
            return tailFact(n - 1, totalSoFar * n);
        }
    }

    static int factIter(int n) {
        assert n >= 0;
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result = result * i;
        }
        return result;
    }
}
