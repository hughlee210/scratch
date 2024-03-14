package com.hlee.scratch;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {

    public static void main(String[] args) {

        String article = "jfjdkgjdfkdfifjdkfdve _fdmgge_ jjkhklolnlej @vm vifllfionv vdfodlddcdlar!";
        String ransomNote = "give me one _million_ dollar!@";
        boolean result = canConstructBF(ransomNote, article);
        System.out.println("[" + ransomNote + "] can be created from [" + article + "]:\n" + result);

        result = canConstruct(ransomNote, article);
        System.out.println("[" + ransomNote + "] can be created from [" + article + "]:\n" + result);

        result = isRansomNotePossible(ransomNote, article);
        System.out.println("[" + ransomNote + "] can be created from [" + article + "]:\n" + result);
    }

    /**
     * Time complexity: O(n * m) where n is length of ransomNode and m is length of magazine
     * Space complexity: O(n + m)
     */
    static boolean canConstructBF(String ransomNote, String magazine) {
        char[] note = ransomNote.toCharArray();
        char[] mag = magazine.toCharArray();
        boolean found = false;
        for (int i = 0; i < note.length; i++) {
            for (int j = 0; j < mag.length; j++) {
                if (note[i] == mag[j]) {
                    // char in ransomNote found in magazine
                    found = true;
                    mag[j] = ' ';
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    /**
     * Time complexity: O(m)
     * Space complexity: O(1) hash map of 26 keys considered as constant
     */
    static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> magazineCounts = makeCountsMap(magazine);
        for (char ch : ransomNote.toCharArray()) {
            int count = magazineCounts.getOrDefault(ch, 0);
            if (count == 0) {
                return false;
            } else {
                magazineCounts.put(ch, count - 1);
            }
        }
        return true;
    }

    static Map<Character, Integer> makeCountsMap(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : str.toCharArray()) {
            Integer count = map.getOrDefault(ch, 0);
            map.put(ch, count + 1);
        }
        return map;
    }

    // case sensitive: g is different from G 
    // Time complexity: O(N+M), Space: O(1), charCount array is fixed size, so it can be O(1)
    static boolean isRansomNotePossible(String ransomNote, String article) {

        if (ransomNote == null || ransomNote.length() == 0 || article == null || article.length() == 0) {
            return false;
        }

        int[] charCount = new int[256]; // assuming ascii character set

        for (int i = 0; i < article.length(); i++) {
            charCount[article.charAt(i)]++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            if (charCount[ransomNote.charAt(i)]-- == 0) {
                return false;
            }
        }
        return true;
    }
}
