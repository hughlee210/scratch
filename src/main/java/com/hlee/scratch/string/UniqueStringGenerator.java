package com.hlee.scratch.string;

import java.util.*;

public class UniqueStringGenerator {

    public static void main(String[] args) {
        SEQ = 0;
//        List<String> inputStrings = Arrays.asList("", "", "");
//        List<String> inputStrings = Arrays.asList("A", "A", "A");
        List<String> inputStrings = Arrays.asList("AA", "AA", "AA", "AA0", "AA1", "");
        // "A", "A", "A" => "A", "A1", "A2"
        // "", "", "0", "1", "2" => "3", "4", "0", "1", "2"
        // "AA", "AA", "AA", "AA0", "AA1", "" => "AA", "AA2", "AA3", "AA0", "AA1", "4"

        System.out.println("input strings  = " + inputStrings);

        for (String s : inputStrings) {
            int count = STRING_COUNTS.getOrDefault(s, 0);
            STRING_COUNTS.put(s, count + 1);
        }

        System.out.println("existing String counts map: " + STRING_COUNTS);

        List<String> results = new ArrayList<>();
        for (String s : inputStrings) {
            String res = generateUniqueString(s);
            results.add(res);
        }
        System.out.println("output strings = " + results);
    }

    static final Map<String, Integer> STRING_COUNTS = new HashMap<>();
    static int SEQ = 0;

    static String generateUniqueString(String src) {
        String res;
        boolean checkUniqueness = false;
        if ("".equals(src)) {
            res = src + SEQ++;
            checkUniqueness = true;
        } else {
            int cnt = getOccurrence(src);
            if (cnt == 1) {
                res = src; // src string is unique, so can use it as is
            } else {
                res = src + SEQ++;
                checkUniqueness = true;
            }
        }

        if (checkUniqueness) {
            while (isExisting(res)) {
                res = generateUniqueString(src);
            }
        }
        return res;
    }

    static boolean isExisting(String s) {
        return STRING_COUNTS.containsKey(s);
    }

    static int getOccurrence(String s) {
        return STRING_COUNTS.getOrDefault(s, 0);
    }






}
