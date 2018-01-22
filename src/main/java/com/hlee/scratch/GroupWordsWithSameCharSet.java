package com.hlee.scratch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupWordsWithSameCharSet {

    public static void main(String[] args) {

        String words[] = { "may", "student", "students", "dog",
                "studentssess", "god", "cat", "act", "tab",
                "bat", "flow", "wolf", "lambs", "amy", "yam",
                "balms", "looped", "poodle"};

        printWordsWithSameCharSet(words);

    }

    static final int MAX_CHAR = 26;

    // Generate a key from given word. The key contains all unique characters of given word
    // in sorted order.
    static String getKey(String word) {
        boolean[] existFlags = new boolean[MAX_CHAR];
        for (int i = 0; i < word.length(); i++) {
            // store the existence of character of word
            existFlags[word.charAt(i) - 'a'] = true;
        }

        // store all unique characters of word in key
        String key = "";
        for (int i = 0; i < MAX_CHAR; i++) {
            if (existFlags[i])
                key = key + (char) ('a' + i);
        }
        return key;
    }

    // print words with same character sets
    static void printWordsWithSameCharSet(String[] words) {
        // store indexes of words that have the same set of unique characters
        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            String key = getKey(words[i]);
            if (map.containsKey(key)) {
                List<Integer> wordsIndexes = map.get(key);
                wordsIndexes.add(Integer.valueOf(i));
                map.put(key, wordsIndexes);
            } else {
                List<Integer> wordsIndexes = new ArrayList<>();
                wordsIndexes.add(Integer.valueOf(i));
                map.put(key, wordsIndexes);
            }
        }
        
        // print all words with the same unique character sets
        for (List<Integer> list : map.values()) {
            for (int index = 0; index < list.size(); index++) {
                System.out.print(words[list.get(index)]);
                if (index < list.size() - 1)
                    System.out.print(", ");
                else
                    System.out.println();
            }
        }

        // short version using stream
        //        map.values().forEach(list -> {
        //            list.forEach(index -> {
        //                System.out.print(words[index.intValue()]);
        //                System.out.print(", ");
        //            });
        //            System.out.println();
        //        });
    }

}
