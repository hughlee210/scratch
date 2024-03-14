package com.hlee.scratch.string;

public class StringSearch {

    public static void main(String[] args) {
        String s = "internet-internet-internetOfThings-internet";
        String subStr = "internetOfThings";
        test1(s, subStr);

        s = "internetOfThing-internetOfThing-internetOfThing-internetOfThing_";
        subStr = "internetOfThing-internetOfThing-internetOfThing-internetOfThinZ";
        test1(s, subStr);
    }

    static int count = 0;

    static void test1(String s, String subStr) {
        int index = indexOf(s, subStr);
        System.out.println("string : " + s + ", length = " + s.length());
        System.out.println("sub str: " + subStr + ", length = " + subStr.length());
        System.out.println("index of sub string = " + index);
        System.out.println("loop count = " + count);

    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    static int indexOf(String str, String subStr) {
        if (str == null || str.length() == 0 || subStr == null || subStr.length() == 0 || str.length() < subStr.length())
            return -1;
        int i = 0, j = 0;
        int strLeng = str.length();
        int subStrLeng = subStr.length();
        while (i < str.length() && j < subStr.length()) {
            if (str.charAt(i) == subStr.charAt(j)) {
                i++;
                j++;
            } else {
                // set i and j to next starting index
                i = i - j + 1;
                j = 0;
                System.out.println("str length = " + str.length() + ", i = " + i + ", subStr length = " + subStr.length());
                if (strLeng - i < subStrLeng) {
                    break;
                }
            }
            count++;
        }
        if (j == subStr.length())
            return i - j;
        return -1;
    }

    static int indexOf_ex(String str, String substr) {
        if (str == null || str.length() == 0 || substr == null || substr.length() == 0 || str.length() < substr.length()) {
            return -1;
        }
        int i = 0, j = 0;
        while (i < str.length() && j < substr.length()) {
            if (str.charAt(i) == substr.charAt(j)) {
                i++;
                j++;
            } else {
                // set i and j to next starting index
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == substr.length()) {
            return i - j;
        }
        return -1;
    }
}
