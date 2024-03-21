package com.hlee.scratch.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BackspaceStringCompare {

    public static void main(String[] args) {
        String s = "ab###x";
        String t = "att##x";
        testBackspaceCompare_bf(s, t);
        testBackspaceCompare_optimal(s, t);

        s = "ab###bx#T";
        t = "att##bxz##T";
        testBackspaceCompare_bf(s, t);
        testBackspaceCompare_optimal(s, t);

        s = "##T";
        t = "ab####T";
        testBackspaceCompare_bf(s, t);
        testBackspaceCompare_optimal(s, t);

        s = "ab###bx#T";
        t = "att##bxz##T";
        testBackspaceCompare_bf(s, t);
        testBackspaceCompare_optimal(s, t);

        s = "ab##";
        t = "c#d#";
        testBackspaceCompare_bf(s, t);
        testBackspaceCompare_optimal(s, t);
    }

    static void testBackspaceCompare_bf(String s, String t) {
        System.out.println("s = " + s);
        System.out.println("t = " + t);
        boolean isSameString = backspaceCompare_bruteForce(s, t);
        System.out.println("is same string (brute force): " + isSameString);
    }

    static void testBackspaceCompare_optimal(String s, String t) {
        System.out.println("s = " + s);
        System.out.println("t = " + t);
        boolean isSameString = backspaceCompare_optimal(s, t);
        System.out.println("is same string (optimal): " + isSameString);
    }

    /**
     * Time complexity: O(N)
     * Space complexity: O(N)
     */
    static String buildString(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c != '#') {
                stack.push(c);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        return String.valueOf(stack);

//        List<Character> builtArray = new ArrayList<>();
//        for (int p = 0; p < s.length(); p++) {
//            if (s.charAt(p) != '#') {
//                builtArray.add(s.charAt(p));
//            } else {
//                if (builtArray.size() > 0) {
//                    builtArray.remove(builtArray.size() - 1);
//                }
//            }
//        }
//        return builtArray.toString();
    }

    /**
     * Time complexity: O(N)
     * Space complexity: O(N)
     */
    static boolean backspaceCompare_bruteForce(String s, String t) {
        String finalS = buildString(s); // time: O(n), space: O(n)
        String finalT = buildString(t); // time: O(n), space: O(n)
        System.out.println("final s = " + finalS);
        System.out.println("final t = " + finalT);

        if (finalS.length() != finalT.length()) {
            return false;
        } else {
            // Time complexity: O(n)
            for (int p = 0; p < finalS.length(); p++) {
                if (finalS.charAt(p) != finalT.charAt(p)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Time: O(N)
     * Space: O(1)
     *         String s = "ab###bx#T";
     *         String t = "att##bxz##T";
     */
    static boolean backspaceCompare_optimal(String s, String t) {
        int p1 = s.length() - 1; // start from the last char
        int p2 = t.length() - 1; // start from the last char
        while (p1 >= 0 || p2 >= 0) {
            if (p1 >= 0 && s.charAt(p1) == '#' || p2 >= 0 && t.charAt(p2) == '#') {
                if (p1 >= 0 && s.charAt(p1) == '#') {
                    int backCount = 2;
                    while (backCount > 0) {
                        p1--;
                        backCount--;
                        if (p1 >= 0 && s.charAt(p1) == '#') {
                            backCount = backCount + 2;
                        }
                    }
                }
                if (p2 >= 0 && t.charAt(p2) == '#') {
                    int backCount = 2;
                    while (backCount > 0) {
                        p2--;
                        backCount--;
                        if (p2 >= 0 && t.charAt(p2) == '#') {
                            backCount = backCount + 2;
                        }
                    }
                }
            } else {
                // regular char for both s and t case
                if (!(p1 >= 0 && p2 >= 0) || s.charAt(p1) != t.charAt(p2)) {
                    return false;
//                } else if (!(p1 >= 0 && p2 >= 0)) {
//                    return false;
                } else {
                    p1--;
                    p2--;
                }
            }
        }
        return true;
    }

}
