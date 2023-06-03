package com.hlee.scratch.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringPlay {

	public static void main(String[] args) {
		String str = "abcde";
		reverseStr(str);

        String testStr = "abcdef";
        System.out.println(testStr + " is unique? " + isUnique_bruteForce(testStr));
        System.out.println(testStr + " is unique? " + isUnique_usingFlag(testStr.toCharArray()));
        System.out.println(testStr + " is unique? " + isUnique_usingHashMap(testStr.toCharArray()));

        testStr = "abcdefaaa";
        System.out.println(testStr + " is unique? " + isUnique_bruteForce(testStr));
        System.out.println(testStr + " is unique? " + isUnique_usingFlag(testStr.toCharArray()));
        System.out.println(testStr + " is unique? " + isUnique_usingHashMap(testStr.toCharArray()));

	}

	private static void reverseStr(String str) {

		char[] arr = str.toCharArray();
		System.out.println("original str = " + Arrays.toString(arr));
		int l = 0;
		int r = arr.length - 1;
		char temp;
		while (l < r) {
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
			l++;
			r--;
		}
		System.out.println("reversed str = " + Arrays.toString(arr));
	}

    // time: O(n^2)
    static boolean isUnique_bruteForce(String str) {
        //
        if (str == null)
            return false;
        char[] arr = str.toCharArray();
        if (arr.length == 0)
            return false;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j])
                    return false;
            }
        }
        return true;
    }

    // time: O(n), space: O(n)
    static boolean isUnique_usingFlag(char[] arr) {
        if (arr == null || arr.length == 0)
            return false;

        // assume ascii character set
        int[] counts = new int[256];
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (counts[ch] > 0)
                return false;
            counts[ch]++;
        }
        return true;
    }

    static boolean isUnique_usingHashMap(char[] arr) {
        if (arr == null || arr.length == 0)
            return false;

        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (charMap.containsKey(arr[i]))
                return false;
            charMap.put(arr[i], 1);
        }
        return true;
    }
}
