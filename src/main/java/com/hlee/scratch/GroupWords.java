package com.hlee.scratch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupWords {

    public static void main(String[] args) {

        String[] words = { "hello", "helloooo", "hhelol", "cat", "tca", "tac", "tictac", "tictactictac" };

        printWordsBySameCharset(words);
    }

    // Time complexity: O((number_of_words * n) where n = number of characters in word
    // Space: map and int array of ascii
    static void printWordsBySameCharset(String[] words) {
        // map key: unique charset
        // map value: list of index of each word in words array
        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            // get unique charset for each word
            String charset = getUniqueCharset2(words[i]);
            if (map.containsKey(charset)) {
                map.get(charset).add(Integer.valueOf(i));
            } else {
                List<Integer> indexList = new ArrayList<>();
                indexList.add(Integer.valueOf(i));
                map.put(charset, indexList);
            }
        }

        // iterate the map entry set to print words grouped by same character set
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            System.out.print("charset: " + entry.getKey() + " => ");
            List<Integer> indexList = entry.getValue();
            for (int i = 0; i < indexList.size(); i++) {
                System.out.print(words[indexList.get(i)]);
                if (i < indexList.size() - 1) {
                    System.out.print(", ");
                } else {
                    System.out.println();
                }
            }
        }
    }

    static int CHARSET_SIZE = 256;

    // get unique charset for word. assume the input is ascii charset of 256 characters
    // Time complexity: O(n) where n = number of characters in word
    // Space: O(1)
    static String getUniqueCharset(String word) {
        int[] charInts = new int[CHARSET_SIZE];
        String uniqueCharset = "";
        for (int i = 0; i < word.length(); i++) {
            charInts[(int) word.charAt(i)] = (int) word.charAt(i);
        }

        for (int i = 0; i < charInts.length; i++) {
            if (charInts[i] > 0) { // this is a bug: ascii 0 is valid integer value for null
                uniqueCharset += (char) charInts[i];
            }
        }
        return uniqueCharset;
    }

    static String getUniqueCharset2(String word) {
        boolean[] existenceFlags = new boolean[CHARSET_SIZE];
        String uniqueCharset = "";
        for (int i = 0; i < word.length(); i++) {
            existenceFlags[(int) word.charAt(i)] = true;
        }

        for (int i = 0; i < existenceFlags.length; i++) {
            if (existenceFlags[i]) {
                uniqueCharset += (char) i;
            }
        }
        return uniqueCharset;
    }
}
