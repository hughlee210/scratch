package com.hlee.scratch.array;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] nums = {1, 10, 3, 6, 2, 9, -19};
        System.out.println("before sort: " + Arrays.toString(nums));
        sort(nums);
        System.out.println("after sort: " + Arrays.toString(nums));
    }

    private static void sort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    swap(i, j, nums);
                }
            }
        }
    }

    private static void swap(int x, int y, int[] nums) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
