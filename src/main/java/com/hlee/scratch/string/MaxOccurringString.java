package com.hlee.scratch.string;

import java.util.Map;
import java.util.TreeMap;

public class MaxOccurringString {

    public static void main(String[] args) {
        String str = "iamaboyiamboy";
        String maxOccurringString = findMaxOccurringString(str, 3);
        System.out.printf("input string: %s\n", str);
        System.out.printf("output      : %s\n", maxOccurringString);
    }

    /**
     * function to find the maximum occurring character in
     * an input string which is lexicographically first
     *
     * Time Complexity: O(N*(K + log K))
     * Auxiliary Space: O(N * K)
     */
    static String findMaxOccurringString(String str, int k) {
        String currentString = "";
        int i = 0, j = 0, n = str.length();
        // to store all substring and there number of occurrences
        // also use TreeMap because it stores all strings in lexicographical order
        TreeMap<String, Integer> map = new TreeMap<>();

        // sliding window approach to generate all substring
        while (j < n) {
            currentString += str.charAt(j);

            // window size less than k so increase only 'j'
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                // window size is equal to k. put current string into map and slide the window
                // by incrementing 'i' and 'j' to generate all substring
                map.put(currentString, map.getOrDefault(currentString, 0) + 1);
                currentString = currentString.substring(1); // update currentString to move one position forward
                i++;
                j++;
            }
        }

        int maxCount = -1; // to count max occurring string
        String ans = ""; // to store max occurring string
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            if (count > maxCount) {
                ans = entry.getKey();
                maxCount = count;
            }
        }
        return ans;
    }
}
