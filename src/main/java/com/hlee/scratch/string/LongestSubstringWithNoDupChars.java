package com.hlee.scratch.string;

import java.util.Arrays;
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

    /**
     * time complexity: O(n^2)
     * space complexity: O(n) for hashmap
     */
    static int lengthOfLongestSubstring_brute(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int longest = 0;
        for (int left = 0; left < s.length(); left++) {
            Map<Character, Integer> seenChars = new HashMap<>();
            int currentLength = 0;
            for (int right = left; right < s.length(); right++) {
                char currentChar = s.charAt(right);
                if (seenChars.get(currentChar) != null) {
                    break;
                }
                currentLength++;
                // put currentChar in seenChars hashmap
                // and update longest count
                seenChars.put(currentChar, right);
                longest = Math.max(longest, currentLength);
            }
        }
        return longest;
    }


    // using two pointer technique (sliding window)
    // Time: O(N)
    // Space: O(N)
    public static int lengthOfLongestSubstring_optimal(String s) {
        if (s.length() <= 1) { // little optimization
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

    static int lengthOfLongestSubstring_optimal_ex(String s) {
        if (s.length() <= 1) {
            return s.length(); // little optimization
        }
        Map<Character, Integer> seenChars = new HashMap<>();
        int left = 0, longest = 0;
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            Integer seenCharIndex = seenChars.get(currentChar);
            if (seenCharIndex != null && seenCharIndex >= left) {
                left = seenCharIndex + 1;
            }
            // put currentChar in the hashmap and update longest length
            seenChars.put(currentChar, right);
            longest = Math.max(longest, right - left + 1);
        }
        return longest;
    }

    public static int lengthOfLongestSubstring_usingIntArrayFlag(String s) {
        int longestLength = 0;
        int left = 0;
        int[] seenCharIndexes = new int[256]; // assume int str is ascii characters
        Arrays.fill(seenCharIndexes, -1);

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            if (seenCharIndexes[currentChar] > -1 && seenCharIndexes[currentChar] >= left) {
                left = seenCharIndexes[currentChar] + 1;
            }
            seenCharIndexes[currentChar] = right;
            longestLength = Math.max(longestLength, right - left + 1);
        }
        return longestLength;
    }

}
