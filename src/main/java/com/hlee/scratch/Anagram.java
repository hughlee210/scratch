package com.hlee.scratch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Anagram {

    public static void main(String[] args) {

        String s1 = "abcabc";
        String s2 = "cbacba";
        System.out.println(s1 + " and " + s2 + " are anagram to each other (using sort)?            " + isAnagram_usingSort(s1, s2));

        System.out.println(s1 + " and " + s2 + " are anagram to each other (using char count flag)? " + isAnagram_usingFlag(s1, s2));

        System.out.println(s1 + " and " + s2 + " are anagram to each other (using hash map)?        " + isAnagram_usingHashMap(s1, s2));

        char[] arr = { 'a', 'b', 'c', '1', '2', '3' };
        for (char ch : arr) {
            System.out.println(ch + " = " + (int) ch);
        }
        //        for (int i = 0; i < 256; i++) {
        //            System.out.println(i + " = " + (char) i);
        //        }
    }

    /**
     * Time complexity: O(NlogN), space: O(N) depending on sorting algorithm
     */
    public static boolean isAnagram_usingSort(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length())
            return false;
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1); // O(nlogn)
        Arrays.sort(arr2); // O(nlogn)

        // time: O(n)
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;

        //return Arrays.equals(arr1, arr2); // O(n)
    }

    /**
     * time complexity: O(n), space: O(n)
     */
    public static boolean isAnagram_usingFlag(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length())
            return false;

        int[] charCounts = new int[256]; // assume ascii characters
        // count chars in string1
        for (char ch : s1.toCharArray()) {
            charCounts[ch]++;
        }
        // compare char counts in string2 with those in string1
        for (char ch : s2.toCharArray()) {
            // charCounts[ch]--;
            if (--charCounts[ch] < 0)
                return false;
        }
        return true;
    }

    /**
     *  time: O(N), space: O(N)
     */
    public static boolean isAnagram_usingHashMap(String s1, String s2) {
        Map<Character, Integer> countsMap = new HashMap<>(); // space: O(N)

        // time: O(N)
        for (char ch : s1.toCharArray()) {
            if (countsMap.containsKey(ch)) {
                countsMap.put(ch, countsMap.get(ch) + 1);
            } else {
                countsMap.put(ch, 1);
            }
        }
        // time: O(N)
        for (char ch : s2.toCharArray()) {
            if (countsMap.containsKey(ch)) {
                if (countsMap.get(ch) == 0) {
                    return false;
                }
                countsMap.put(ch, countsMap.get(ch) - 1);
            } else {
                return false;
            }
        }
        return true;
    }

}
