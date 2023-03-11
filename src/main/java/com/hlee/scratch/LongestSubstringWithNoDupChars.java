package com.hlee.scratch;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithNoDupChars {

    public static void main(String[] args) {
        //String str = "zabcdefabcdab";
        //String str = "abccabb";
        String str = "aa";
        int length = lengthOfLongestSubstring_bruteForce(str);
        System.out.println("Brute force: length of longest substring of " + str + ": " + length);

        length = lengthOfLongestSubstring_optimal(str);
        System.out.println("Optimal (using hash map): length of longest substring of " + str + ": " + length);

        length = lengthOfLongestSubstring_usingIntArrayFlag(str);
        System.out.println("Optimal (using int flag array): length of longest substring of " + str + ": " + length);
    }

    // brute force solution
    // Time: O(N^2)
    // Space: O(N)
    // abccdefgjjk
    // L pointer
    // R pointer
    public static int lengthOfLongestSubstring_bruteForce(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int longestLength = 0;
        for (int left = 0; left < s.length(); left++) {
            Map<Character, Integer> seenCharIndexMap = new HashMap<>();
            int currentLength = 0;
            for (int right = left; right < s.length(); right++) {
                char currentChar = s.charAt(right);
                Integer seenCharIndex = seenCharIndexMap.get(currentChar);
                if (seenCharIndex == null) {
                    currentLength++;
                    seenCharIndexMap.put(currentChar, right);
                    longestLength = Math.max(longestLength, currentLength);
                } else {
                    break;
                }
            }
        }
        return longestLength;
    }

    // using two pointer technique (sliding window)
    // Time: O(N)
    // Space: O(N)
    public static int lengthOfLongestSubstring_optimal(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        Map<Character, Integer> seenCharIndexMap = new HashMap<>();
        int left = 0, longestLength = 0;
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            Integer seenCharIndex = seenCharIndexMap.get(currentChar);
            if (seenCharIndex != null && seenCharIndex >= left) {
                left = seenCharIndex + 1;
            }
            seenCharIndexMap.put(currentChar, right);
            longestLength = Math.max(longestLength, right - left + 1);
        }
        return longestLength;
    }

    public static int lengthOfLongestSubstring_usingIntArrayFlag(String s) {
        int longestLength = 0;
        int left = 0;
        int[] flags = new int[256]; // assume int str is ascii characters
        for (int i = 0; i < flags.length; i++) {
            flags[i] = -1;
        }

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            if (flags[currentChar] > -1 && flags[currentChar] >= left) {
                left = flags[currentChar] + 1;
            }
            flags[currentChar] = right;
            longestLength = Math.max(longestLength, right - left + 1);
        }
        return longestLength;
    }

}
