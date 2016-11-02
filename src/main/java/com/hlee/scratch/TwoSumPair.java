package com.hlee.scratch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumPair {

    public static void main(String[] args) {

        int[] arr = { 1, 10, 2, 3, 9, 8, 7, 4, 5, 6 };
        System.out.println("array: " + Arrays.toString(arr));

        int sum = 10;
        System.out.println("***using brute force");
        findSumPair_bruteForce(arr, sum);

        System.out.println("***using sort");
        findSumPair_usingSort(arr, sum);

        System.out.println("***using hash map");
        findSumPair_usingHashMap(arr, sum);

        System.out.println("************ triplet for sum *************");
        System.out.println("***using brute force");
        findSumTriplet_bruteForce(arr, sum);

        System.out.println("***using sort");
        findSumTriplet_usingSort(arr, sum);

    }

    // time complexity: O(n**2), space: O(1)
    static void findSumPair_bruteForce(int[] arr, int sum) {
        if (arr == null || arr.length == 0)
            return;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == sum)
                    System.out.println("found two num for sum: " + arr[i] + " + " + arr[j] + " = " + sum);
            }
        }
    }

    static void findSumTriplet_bruteForce(int[] arr, int sum) {
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (arr[i] + arr[j] + arr[k] == sum)
                        System.out.println("found three numbers for sum: " + arr[i] + " + " + arr[j] + " + " + arr[k]);
                }
            }
        }
    }

    // time complexity: O(nlogn), space: vary depending on sorting algorithm
    static void findSumPair_usingSort(int[] arr, int sum) {
        if (arr == null || arr.length == 0)
            return;
        Arrays.sort(arr); // O(nlogn)
        int l = 0;
        int r = arr.length - 1;
        // time: O(n/2)
        while (l < r) {
            if (arr[l] + arr[r] > sum)
                r--;
            else if (arr[l] + arr[r] < sum)
                l++;
            else {
                System.out.println("found two num for sum: " + arr[l] + " + " + arr[r] + " = " + sum);
                l++;
                r--;
            }
        }
    }

    static void findSumTriplet_usingSort(int[] arr, int sum) {
        // sort the array
        Arrays.sort(arr); // O(nlogn)
        int l;
        int r;
        // now fix the first element one by one and find the other two elements
        // O(n^2)
        for (int i = 0; i < arr.length - 2; i++) {
            // find the other two elements 
            l = i + 1;
            r = arr.length - 1;
            while (l < r) {
                if (arr[i] + arr[l] + arr[r] == sum) {
                    System.out.println("found triplet for sum: " + arr[i] + " + " + arr[l] + " + " + arr[r] + " = " + sum);
                    l++;
                    r--;
                } else if (arr[i] + arr[l] + arr[r] < sum)
                    l++;
                else
                    r--;
            }
        }
    }

    // time: O(N), space: O(N)
    static void findSumPair_usingHashMap(int[] arr, int sum) {
        if (arr == null || arr.length == 0)
            return;
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (numMap.containsKey(sum - arr[i]))
                System.out.println("found a pair for sum: " + sum + " = " + (sum - arr[i]) + " + " + arr[i]);
            else
                numMap.put(arr[i], i);
        }
    }
}
