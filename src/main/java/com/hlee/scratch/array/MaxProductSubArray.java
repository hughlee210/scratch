package com.hlee.scratch.array;

import java.util.Arrays;

/**
 * Given an integer array nums, find a contiguous subarray that has the largest product,
 * and return the product.
 *
 * Case 1: All elements are positive: then your answer will be product of all the
 * elements in the array.
 * Case 2: Array has both positive and negative elements in the array:
 *   1. If the number of negative elements is even, then again your answer will be complete
 *   array because on multiplying all the numbers will become positive.
 *   2. If the number of negative elements is odd, then you have to remove just one negative element
 *   and for that you need to check your subarrays to get the max product.
 * Case 3: Array also contains 0: as soon as your product becomes 0, make it 1 for the next
 * iteration, now you will be searching new subarray and previous max will already be updated.
 *
 * Approach 1: For each index i keep updating the max and min. We are also keeping min because
 * on multiplying with any negative number your min will become max and max will become min.
 * So for every index i we will take max of (i-th element, prevMax * i-th element, preMin * i-th element).
 *
 * Approach 2: Just the slight modification of previous approach. As we know that
 * on multiplying with negative number
 * max will become min and
 * min will become max, so why not as soon as we encounter negative element,
 * we swap the max and min already.
 */
public class MaxProductSubArray {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int max = maxProduct1(nums);
        System.out.println("nums: " + Arrays.toString(nums) + ", max = " + max);

//        nums = new int[]{1, 2, 3, 0, 5};
        nums = new int[]{1, 0, 3};
        max = maxProduct1(nums);
        System.out.println("nums: " + Arrays.toString(nums) + ", max = " + max);

        nums = new int[]{1, 0, 2, 3, -1, 1, 10, 0, 2};
        max = maxProduct1(nums);
        System.out.println("nums: " + Arrays.toString(nums) + ", max = " + max);
    }

    static int maxProduct1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0], min = nums[0], ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // store current 'max' before updating max
            // because before updating 'min' your 'max' will already be updated
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
            if (max > ans) {
                ans = max;
            }
        }
        return ans;
    }

    static int maxProduct2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0], min = nums[0], ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = max; // store current max before updating max
            max = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            min = Math.min(nums[i], Math.min(temp * nums[i], min * nums[i]));
            if (max > ans) {
                ans = max;
            }
        }
        return ans;
    }

}
