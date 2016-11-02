package com.hlee.scratch;

public class Palindrome {

    public static void main(String[] args) {
        String str = "abcba";
        boolean isPalindrome = isPalindrome(str.toCharArray());
        System.out.println(str + " is palindrome: " + isPalindrome);
    }

    // time: O(n/2), space: O(1)
    static boolean isPalindrome(char[] arr) {
        if (arr == null || arr.length == 0)
            return false;
        int l = 0;
        int r = arr.length - 1;
        int count = 0;
        while (l < r) {
            if (arr[l] != arr[r])
                return false;
            l++;
            r--;
            count++;
        }
        System.out.println("loop count: " + count);
        return true;
    }
}
