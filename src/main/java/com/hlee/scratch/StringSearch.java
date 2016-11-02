package com.hlee.scratch;

public class StringSearch {

    public static void main(String[] args) {
        String s = "internet-internet-internetOfThings-internet";
        String subStr = "internetOfThings";
        int index = indexOf(s, subStr);
        System.out.println("string : " + s + ", length = " + s.length());
        System.out.println("sub str: " + subStr + ", length = " + subStr.length());
        System.out.println("index of sub string = " + index);
        System.out.println("loop count = " + count);
    }

    static int count = 0;

    static int indexOf_ex(String str, String subStr) {
        if (str == null || str.length() == 0 || subStr == null || subStr.length() == 0)
            return -1;
        int i = 0, j = 0;
        while (i < str.length() && j < subStr.length()) {
            if (str.charAt(i) == subStr.charAt(j)) {
                i++;
                j++;
            } else {
                // set index i and j to next starting position
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == subStr.length())
            return i - j;
        return -1;
    }

    static int indexOf(String str, String subStr) {
        if (str == null || str.length() == 0 || subStr == null || subStr.length() == 0 || str.length() < subStr.length())
            return -1;
        int i = 0, j = 0;
        while (i < str.length() && j < subStr.length()) {
            if (str.charAt(i) == subStr.charAt(j)) {
                i++;
                j++;
            } else {
                // set i and j to next starting index
                i = i - j + 1;
                j = 0;
            }
            count++;
        }
        if (j == subStr.length())
            return i - j;
        return -1;
    }
}
