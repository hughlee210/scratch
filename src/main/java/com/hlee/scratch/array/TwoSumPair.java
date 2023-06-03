package com.hlee.scratch.array;

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

        System.out.println("***using sort: array = " + Arrays.toString(arr));
        findSumPair_usingSort(arr, sum);

        System.out.println("***using hash map: array = " + Arrays.toString(arr));
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
            int numToFind = sum - arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (numToFind == arr[j])
                    System.out.println("found two num for sum: " + arr[i] + " + " + arr[j] + " = " + sum);
            }
        }
    }

    // time complexity: O(n**2), space: O(1)
    static int[] findSumPair_brute(int[] nums, int sum) {
        if (nums == null || nums.length == 0)
            return null;
        for (int p1 = 0; p1 < nums.length; p1++) {
            int numberToFind = sum - nums[p1];
            for (int p2 = p1 + 1; p2 < nums.length; p2++) {
                if (numberToFind == nums[p2]) {
                    System.out.printf("found two sum pair for sum: %d = %d + %d\n", sum, nums[p1], nums[p2]);
                    return new int[]{p1, p2};
                }
            }
        }
        return null;
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
    static int[] findSumPair_usingSort(int[] arr, int sum) {
        if (arr == null || arr.length == 0)
            return null;
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arr2); // O(nlogn)
        int l = 0;
        int r = arr2.length - 1;
        // time: O(n/2)
        while (l < r) {
            if (arr2[l] + arr2[r] > sum)
                r--;
            else if (arr2[l] + arr2[r] < sum)
                l++;
            else {
                System.out.println("found two num for sum: " + arr2[l] + " + " + arr2[r] + " = " + sum);
                l++;
                r--;
                return new int[]{l, r};
            }
        }
        return null; //TODO: fix this
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
            int numberToFind = sum - arr[i];
            if (numMap.containsKey(numberToFind))
                System.out.println("found a pair for sum: " + sum + " = " + (sum - arr[i]) + "(at index " + numMap.get(sum - arr[i]) + ") + " + arr[i] + "(at index " + i + ")");
            else
                numMap.put(arr[i], i);
        }
    }

    // ex:
    // int[] arr = { 1, 10, 2, 3, 9, 8, 7, 4, 5, 6 };
    // int sum = 10;
    // time: O(N), space: O(N)
    static int[] findSumPair_optimal(int[] nums, int sum) {
        if (nums == null || nums.length == 0)
            return null;
        // keeps numberToFind as a key and the index of the number used to
        // calculate numberToFind as a value
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int p = 0; p < nums.length; p++) {
            Integer currentMapVal = numsMap.get(nums[p]);
            if (currentMapVal != null) {
                return new int[]{currentMapVal, p};
            } else {
                int numberToFind = sum - nums[p];
                numsMap.put(numberToFind, p);
            }
        }
        return null;
    }
}
