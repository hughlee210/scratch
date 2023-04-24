package com.hlee.scratch;

import java.util.*;

public class Permutation {

    // http://www.ericleschinski.com/c/java_permutations_recursion/

    public static void main(String[] args) {

        String str = "abc";
//        List<String> list = generatePerm(str);
//        System.out.println("Source string: " + str + " => Permutation generated: " + list);
//
//        System.out.println("substring 0,0: " + str.substring(0, 0));
//        System.out.println("substring 3: " + str.substring(3));
//
//        permutation("", "ABCD");

        // permutationIter("ABCD");

        List<String> result = permutations(str);
        System.out.println("input string = " + str);
        System.out.println("permutations = " + result);
    }

    /**
     * Permutations of {'a', 'b', 'c'}: [abc, bac, bca, acb, cab, cba]
     * Given a set of n characters, there are n! permutations.
     *
     *                            [ ]                            ----  0
     *
     *    a                        |
     *
     *                            [a]                            ----  1
     *
     *    b               /              \
     *
     *               [b,a]                    [a,b]              ----  2
     *
     *    c    /        |       \        /        |       \
     *
     *     [c,b,a]  [b,c,a]  [b,a,c]  [c,a,b]  [a,c,b]  [a,b,c]  ----  3
     *
     *     permutations: 3!
     *
     * Time complexity: O(n!) see the above tree diagram
     * Space complexity: O(n^2)
     *      O(n) call stack memory (n stack frames) for recursion
     *      within each of the stack frame, create a copy of string (char array)
     *      so, space is O(n * n) = O(n^2)
     */
    static List<String> permutations(String str) {
        if (str.length() == 0) {
            List<String> list = new ArrayList<>();
            list.add("");
            return list;
        }
        char firstEl = str.charAt(0);
        String rest = str.substring(1); // this creates a copy of new array, so O(n) space
        List<String> permWithoutFirst = permutations(rest);
        List<String> allPermutations = new ArrayList<>();

        for (int i = 0; i < permWithoutFirst.size(); i++) {
            String perm = permWithoutFirst.get(i);
            for (int j = 0; j <= perm.length(); j++) {
                String permWithFirst = perm.substring(0, j) + firstEl + perm.substring(j);
                allPermutations.add(permWithFirst);
            }
        }
        return allPermutations;
    }

    /**
     * recursive
     * time: O(n!)
     */
    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0)
            System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++) {
                System.out.println("prefix: " + prefix);
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1));
            }
        }
    }

    private static void perm(String prefix, String str) {
        int len = str.length();
        if (len == 0)
            System.out.println(prefix);
        else {
            for (int i = 0; i < len; i++) {
                System.out.println("prefix: " + prefix);
                perm(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1));
            }
        }
    }

    /**
     * Iterative
     *
     * Example: if we have 3 character string e.g. "abc", we can form
     * permuations as below.
     *
     * 1) construct a string with first character e.g. 'a' and store that in
     * results.
     *
     * char[] chars = test_str.toCharArray(); results.add(new String("" +
     * chars[0])); 2) Now take next character in string (i.e. 'b') and insert
     * that in all possible positions of previously contsructed strings in
     * results. Since we have only one string in results ("a") at this point,
     * doing so gives us 2 new strings 'ba', 'ab'. Insert these newly
     * constructed strings in results and remove "a".
     *
     * for(int j=cur_size-1; j>=0; j--) { String str = results.remove(j);
     * for(int k=0; k<=str.length(); k++) { results.add(str.substring(0,k) + c +
     * str.substring(k)); } } 3) Repeat 2) for every character in the given
     * string.
     *
     * for(int i=1; i<chars.length; i++) { char c = chars[i]; .... .... } This
     * gives us "cba", "bca", "bac" from "ba" and "cab", "acb" and "abc" from
     * "ab"
     */
    public static void permutationIter(String input) {
        List<String> results = new ArrayList<>();
        char[] chars = input.toCharArray();
        results.add("" + chars[0]);
        for (int i = 1; i < chars.length; i++) {
            char ch = chars[i];
            int size = results.size();
            // create new permutations combing char 'c' with each of the
            // existing permutations
            for (int j = size - 1; j >= 0; j--) {
                String str = results.remove(j);
                System.out.println("removing " + str);
                for (int k = 0; k <= str.length(); k++) {
                    results.add(str.substring(0, k) + ch + str.substring(k));
                    System.out.println("adding " + str.substring(0, k) + ch + str.substring(k));
                    count++;
                }
            }
        }
        System.out.println("Number of Permutations: " + results.size());
        System.out.println(results);
        System.out.println("iter count: " + count);
    }

    private static int count = 0;

    public static List<String> generatePerm(String input) {
        List<String> list = new ArrayList<>();
        if (Objects.equals(input, ""))
            return list;

        Character a = input.charAt(0);

        if (input.length() > 1) {
            input = input.substring(1);

            List<String> permSet = generatePerm(input);

            for (String x : permSet) {
                for (int i = 0; i <= x.length(); i++) {
                    list.add(x.substring(0, i) + a + x.substring(i));
                }
            }
        } else {
            list.add(a + "");
        }
        return list;
    }

}
