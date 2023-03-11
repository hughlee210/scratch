package com.hlee.scratch;

public class Palindrome {

    public static void main(String[] args) {
        String str1 = "abcba";
        boolean isPalindrome = isPalindrome(str1.toCharArray());
        System.out.println(str1 + " is a palindrome? " + isPalindrome);

        boolean isAlmostPalindrome = isAlmostPalindrome(str1.toCharArray());
        System.out.println(str1 + " is an almost palindrome?" + isAlmostPalindrome);

        String str2 = "abccdba";
        isAlmostPalindrome = isAlmostPalindrome(str2.toCharArray());
        System.out.println(str2 + " is an almost palindrome?" + isAlmostPalindrome);

        String str3 = "abcwawcdba";
        isAlmostPalindrome = isAlmostPalindrome(str3.toCharArray());
        System.out.println(str3 + " is an almost palindrome?" + isAlmostPalindrome);
    }

    // time: O(n/2), space: O(1)
    static boolean isPalindrome(char[] arr) {
        if (arr == null)
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

    // abccdba, abcwawcdba
    static boolean isAlmostPalindrome(char[] arr) {
        if (arr == null) {
            return false;
        }
        int left = 0, right = arr.length - 1;
        while (left < right) {
            if (arr[left] != arr[right]) {
                return validSubPalindrome(arr, left + 1, right) || validSubPalindrome(arr, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    static boolean validSubPalindrome(char[] arr, int left, int right) {
        while (left < right) {
            if (arr[left] != arr[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
