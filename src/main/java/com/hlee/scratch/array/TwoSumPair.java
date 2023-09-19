package com.hlee.scratch.array;

import java.util.*;

public class TwoSumPair {

    public static void main(String[] args) {

        int[] arr = { 1, 10, 2, 3, 9, 8, 7, 4, 5, 6 };
        int sum = 10;

        System.out.println("***using brute force: array = " + Arrays.toString(arr));
        int[] resultPair = findSumPair_bruteForce(arr, sum);
        System.out.println("pair = " + Arrays.toString(resultPair));
        System.out.println("-----------------------------------------------");

//        System.out.println("***using sort: array = " + Arrays.toString(arr));
//        pair = findSumPair_usingSort(arr, sum);
//        System.out.println("pair = " + Arrays.toString(pair));
//        System.out.println("-----------------------------------------------");

        arr = new int[]{1, 10, 2, 3, 9, 8, 7, 4, 5, 6};
        System.out.println("***using hash map: array = " + Arrays.toString(arr));
        List<int[]> pairs = findSumPair_usingHashMap(arr, sum);
        System.out.println("pair = " + Arrays.deepToString(pairs.toArray()));
        System.out.println("-----------------------------------------------");

        arr = new int[]{1, 10, 2, 3, 9, 8, 7, 4, 5, 6};
        System.out.println("***using hash map 2: array = " + Arrays.toString(arr));
        pairs = findSumPair_optimal(arr, sum);
        System.out.println("pair = " + Arrays.deepToString(pairs.toArray()));
        System.out.println("-----------------------------------------------");

        System.out.println("************ triplet for sum *************");
        System.out.println("***using brute force");
        arr = new int[]{1, 10, 2, 3, 9, 8, 7, 4, 5, 6};
        findSumTriplet_bruteForce(arr, sum);

        System.out.println("***using sort");
        arr = new int[]{1, 10, 2, 3, 9, 8, 7, 4, 5, 6};
        findSumTriplet_usingSort(arr, sum);

    }

    // Time complexity: O(n^2), space complexity: O(1)
    static int[] findSumPair_bruteForce(int[] nums, int sum) {
        if (nums == null || nums.length == 0)
            return null;
        for (int i = 0; i < nums.length; i++) {
            // for nums[i] we need to find a number that makes sum
            int numberToFind = sum - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (numberToFind == nums[j]) {
                    System.out.println("found two numbers for sum " + sum + ": " + nums[i] + " + " + nums[j]);
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * Time complexity: O(N^2)
     * Space complexity: O(1)
     */
    static List<int[]> findSumPairAll_brute(int[] nums, int sum) {
        if (nums == null || nums.length == 0)
            return Collections.emptyList();
        List<int[]> pairs = new ArrayList<>();
        // using nested loops we compare a number to every other number that makes 'sum'
        for (int i = 0; i < nums.length; i++) {
            // for nums[i] we need to find a number to make 'sum'
            int numberToFind = sum - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == numberToFind) {
                    // found the number
                    pairs.add(new int[]{i, j});
                }
            }
        }
        return pairs;
    }

    static void findSumTriplet_bruteForce(int[] nums, int sum) {
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == sum)
                        System.out.println("found three numbers for sum: " + nums[i] + " + " + nums[j] + " + " + nums[k]);
                }
            }
        }
    }

    // this can find pair numbers, but indexes of those numbers are the ones of the sorted array
    static int[] findSumPair_usingSort(int[] nums, int sum) {
        if (nums == null || nums.length == 0)
            return null;
        int[] nums2 = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums2); // O(nlogn)
        int l = 0;
        int r = nums2.length - 1;
        // time: O(n/2)
        while (l < r) {
            if (nums2[l] + nums2[r] > sum)
                r--;
            else if (nums2[l] + nums2[r] < sum)
                l++;
            else {
                System.out.println("found two num for sum: " + nums2[l] + " + " + nums2[r] + " = " + sum);
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
    static List<int[]> findSumPair_usingHashMap(int[] nums, int sum) {
        if (nums == null || nums.length == 0)
            return null;
        List<int[]> pairs = new ArrayList<>();
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int numberToFind = sum - nums[i];
            if (numsMap.containsKey(numberToFind)) {
                System.out.println("found a pair for sum: " + sum + " = " + (numberToFind) + "(at index " + numsMap.get(numberToFind) + ") + " + nums[i] + "(at index " + i + ")");
                pairs.add(new int[]{numsMap.get(numberToFind), i});
            }
            else {
                numsMap.put(nums[i], i);
            }
        }
        return pairs;
    }

    /**
     * int[] nums = { 1, 10, 2, 3, 9, 8, 7, 4, 5, 6 };
     * int sum = 10;
     * time: O(N), space: O(N)
     *
     * hash map: {[9, 0], [0, 1], [8, 2], [7, 3], [1, 4], ...}
     */
    static List<int[]> findSumPair_optimal(int[] nums, int sum) {
        if (nums == null || nums.length == 0)
            return null;
        List<int[]> pairs = new ArrayList<>();
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int p = 0; p < nums.length; p++) {
            Integer currentMapVal = numsMap.get(nums[p]);
            if (currentMapVal != null) {
                // if map contains current number as a key, its map value is the index of the number
                // used to calculate the key number (= number to find = sum - the other number)
                pairs.add(new int[]{currentMapVal, p});
            } else {
                // keeps numberToFind as a key and the index of the number used to
                // calculate numberToFind as a value
                int numberToFind = sum - nums[p];
                numsMap.put(numberToFind, p);
            }
        }
        return pairs;
    }

    static List<int[]> findSumPair_good(int[] nums, int sum) {
        if (nums == null || nums.length == 0)
            return null;
        List<int[]> resultPairs = new ArrayList<>();
        // use a map to store key-value pairs of numberToFind as a key and the index of the number
        // used to calculate the numberToFind.
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int p = 0; p < nums.length; p++) {
            Integer currentMapValue = numsMap.get(nums[p]);
            if (currentMapValue != null) {
                // if the map contains current number as a key, its map value is the index of the number
                // used to calculate the key number (which is numberToFind = sum - the other number)
                resultPairs.add(new int[]{currentMapValue, p});
            } else {
                // keeps numberToFind as a key and the index of the number used to
                // calculate numberToFind as a value
                int numberToFind = sum - nums[p];
                numsMap.put(numberToFind, p);
            }
        }
        return resultPairs;
    }
}
