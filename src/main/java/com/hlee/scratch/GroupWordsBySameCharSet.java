package com.hlee.scratch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupWordsBySameCharSet {

    public static void main(String[] args) {

        String words[] = { "may", "student", "students", "dog",
                "studentssess", "god", "cat", "act", "tab",
                "bat", "flow", "wolf", "lambs", "amy", "yam",
                "balms", "looped", "poodle", "lee", "NJIT", "NJITIT" };

        printWordsWithSameCharSet(words);

    }

    static final int MAX_CHAR = 26;

    // Generate a key from given word. The key contains all unique characters of given word
    // in sorted order.
    // Time complexity: O(N), Space: O(1)
    static String getKey(String word) {
        boolean[] existenceFlags = new boolean[MAX_CHAR];
        for (int i = 0; i < word.length(); i++) {
            // store the existence of character in word
            existenceFlags[word.charAt(i) - 'a'] = true; // in order to start from 0 for 'a'; 25 for 'z'
        }

        // store all unique characters of word in key
        String key = "";
        for (int i = 0; i < MAX_CHAR; i++) { // 0 for 'a' to 25 for 'z'
            if (existenceFlags[i]) {
                key = key + (char) (i + 'a'); // to restore to original char
            }
        }
        return key;
    }

    static String getKey2(String word) {
        int[] charInts = new int[MAX_CHAR];
        for (int i = 0; i < word.length(); i++) {
            charInts[word.charAt(i) - 'a'] = word.charAt(i);
        }

        String key = "";
        for (int i = 0; i < MAX_CHAR; i++) {
            if (charInts[i] > 0) {
                key = key + (char) charInts[i];
            }
        }
        return key;
    }

    static final int MAX_ASCII = 256;
    static String getKey3(String word) {
        int[] charInts = new int[MAX_ASCII];
        for (int i = 0; i < word.length(); i++) {
            charInts[word.charAt(i)] = word.charAt(i);
        }

        String key = "";
        for (int i = 0; i < MAX_ASCII; i++) {
            if (charInts[i] > 0) {
                key = key + (char) charInts[i];
            }
        }
        return key;
    }

    // print words by same character set
    // Time complexity: O(Word Count * N), O(Word Count)
    //
    static void printWordsWithSameCharSet(String[] words) {
        // Map key is a unique character set for word
        // Map value stores a list of array index of each word in words array that have the same set of unique characters
        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            String key = getKey3(words[i]);
            if (map.containsKey(key)) {
                map.get(key).add(Integer.valueOf(i)); // shortcut way to update the map value (= List<Integer>)
            } else {
                List<Integer> wordIndexes = new ArrayList<>();
                wordIndexes.add(Integer.valueOf(i));
                map.put(key, wordIndexes);
            }
        }
        
        // print all words with the same unique character sets
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            System.out.print(entry.getKey() + " => ");
            List<Integer> indexList = entry.getValue();
            for (int index = 0; index < indexList.size(); index++) {
                System.out.print(words[indexList.get(index)]);
                if (index < indexList.size() - 1)
                    System.out.print(", ");
                else
                    System.out.println();
            }
        }

        System.out.println("map = " + map);
    }

}
