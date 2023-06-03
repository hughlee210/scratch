package com.hlee.scratch.array;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {

//        int[] nums = {7, 2, 4, 1, 5, 3};
        int[] nums = {7, 6, 5, 4, 3, 2};
        System.out.println("before sort: nums = " + Arrays.toString(nums));

        insertionSort(nums);
        System.out.println("after  sort: nums = " + Arrays.toString(nums));
    }

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
}
