package com.hlee.scratch;

public class Gcd {

    public static void main(String[] args) {

        int a = 17, b = 13;
        System.out.println("gcd(" + a + ", " + b + ") = " + gcdIter(a, b, 1));

        a = 10;
        b = 4;
        System.out.println("gcd(" + a + ", " + b + ") = " + gcdIter(a, b, 1));

        a = -10;
        b = 5;
        System.out.println("gcd(" + a + ", " + b + ") = " + gcdIter(a, b, 1));

        a = -10;
        b = -5;
        System.out.println("gcd(" + a + ", " + b + ") = " + gcdIter(a, b, 1));

        a = 5;
        b = 10;
        System.out.println("gcd(" + a + ", " + b + ") = " + gcdIter(a, b, 1));

    }

    public static int gcd(int a, int b, int i) {
        System.out.println("i = " + i);

        // base case 
        if (b == 0) {
            return a;
        }
        if (a < 0) {
            a = -a;
        }
        if (b < 0) {
            b = -b;
        }
        return gcd(b, a % b, ++i);
    }

    public static int gcdIter(int a, int b, int i) {
        // i is loop counter to calculate time complexity
        if (a < 0) {
            a = -a;
        }
        if (b < 0) {
            b = -b;
        }

        int remainder;
        while (b > 0) {
            remainder = a % b;
            a = b;
            b = remainder;
            i++;
        }
        System.out.println("i = " + i);
        return a;
    }
}
