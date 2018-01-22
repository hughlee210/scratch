package com.hlee.scratch;

public class Recursion {

    public static void main(String[] args) {
        printInt(2);
    }

    static void printInt(int k) {
        if (k == 0) {
            return;
        }
        System.out.println("k=" + k);
        printInt(k - 1);
        System.out.println("all done; k=" + k);
    }
}
