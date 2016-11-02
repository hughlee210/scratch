package com.hlee.scratch;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithNoDupChars {

    public static void main(String[] args) {
        String str = "zabcdefabcdab";
        int[] result = lengthOfLongestSubstring(str);
        System.out.println("length of longest substring of " + str + ": " + result[0] + " starting index " + result[1] + 
                " = " + str.substring(result[1], result[1] + result[0]));

        int length = lengthOfLongestSubstring_usingIntArrFlag(str);
        System.out.println("length of longest substring of " + str + ": " + length);
    }

    public static int[] lengthOfLongestSubstring(String str) {
        int maxLen = 0, start = 0, curr = 0, maxStart = 0;
        Map<Character, Integer> charPosMap = new HashMap<>();
        while (curr < str.length()) {
            char ch = str.charAt(curr);
            if (charPosMap.containsKey(ch) && charPosMap.get(ch) >= start) {
                if ((curr - start) > maxLen)
                    maxStart = start;
                // we hit a recurrence, so update max length and start position
                maxLen = Math.max(maxLen, curr - start);
                start = charPosMap.get(ch) + 1;
            }
            charPosMap.put(ch, curr);
            curr++;
        }
        if ((curr - start) > maxLen)
            maxStart = start;
        maxLen = Math.max(maxLen, curr - start);
        int[] result = { maxLen, maxStart };
        return result;
    }

    // this is wrong
    public static int lengthOfLongestSubstring_usingIntArrFlag(String str) {
        int maxLen = 0;
        int start = 0, end = 0;
        int[] flags = new int[256]; // assume input str is ascii characters
        while (end < str.length()) {
            char ch = str.charAt(end);
            if (flags[ch] > 0 && flags[ch] >= start) {
                maxLen = Math.max(maxLen, end - start);
                start = flags[ch] + 1;
            }
            flags[ch] = end;
            end++;
        }
        maxLen = Math.max(maxLen, end - start);
        return maxLen;
    }

}
