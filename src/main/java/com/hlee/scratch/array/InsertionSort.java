package com.hlee.scratch.array;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {

//        int[] nums = {7, 2, 4, 1, 5, 3};
        int[] nums = {7, 6, 5, 4, 3, 2};
        System.out.println("before sort: nums = " + Arrays.toString(nums));

        insertionSort2(nums);
        System.out.println("after  sort: nums = " + Arrays.toString(nums));
    }

    /**
     * 7, 6, 5, 4, 3, 2, 1
     * 5, 6, 7,
     *
     */

    /**
     * Time complexity: O(n^2) average/worst, O(n) best
     * Space complexity: O(1)
     * @param nums
     */
    static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int currVal = nums[i];
            int holeIndex = i;
            while (holeIndex > 0 && nums[holeIndex - 1] > currVal) {
                nums[holeIndex] = nums[holeIndex - 1];
                holeIndex--;
            }
            nums[holeIndex] = currVal;
        }
    }

    /**
     * Time complexity: O(n^2) average/worst, O(n) best
     * Space complexity: O(1)
     *
     * https://en.wikipedia.org/wiki/Insertion_sort
     *
     * Insertion sort iterates, consuming one input element each repetition,
     * and grows a sorted output list. At each iteration, insertion sort removes one element from the input data,
     * finds the location it belongs within the sorted list, and inserts it there.
     * It repeats until no input elements remain.
     */
    static void insertionSort2(int[] nums) {
        int i = 1;
        while (i < nums.length) {
            int j = i;
            while (j > 0 && nums[j - 1] > nums[j]) {
                swap(nums, j, j - 1);
                j--;
            }
            i++;
        }
    }

    static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

}
