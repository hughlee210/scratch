package com.hlee.scratch.array;

import java.util.Arrays;

/**
 * <p>
 * Given an integer array nums, find a contiguous subarray that has the largest product,
 * and return the product.
 * </p><br>
 *<p>
 * Case 1: All elements are positive: then your answer will be product of all the
 * elements in the array.<br>
 * Case 2: Array has both positive and negative elements in the array:<br>
 *   1. If the number of negative elements is even, then again your answer will be complete
 *   array because on multiplying all the numbers will become positive.<br>
 *   2. If the number of negative elements is odd, then you have to remove just one negative element
 *   and for that you need to check your subarrays to get the max product.<br>
 * Case 3: Array also contains 0: as soon as your product becomes 0, make it 1 for the next
 * iteration, now you will be searching new subarray and previous max will already be updated.
 *</p><br>
 * <p>
 * Approach 1: For each index i keep updating the max and min. We are also keeping min because
 * on multiplying with any negative number your min will become max and max will become min.
 * So for every index i we will take max of (i-th element, prevMax * i-th element, preMin * i-th element).
 *</p><br>
 * <p>
 * Approach 2: Just the slight modification of previous approach. As we know that
 * on multiplying with negative number
 * max will become min and
 * min will become max, so why not as soon as we encounter negative element,
 * we swap the max and min already.
 * </p>
 */
public class MaxProductSubArray {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int max = maxProduct1(nums);
        int maxBf = maxProduct_bf(nums);
        System.out.println("nums: " + Arrays.toString(nums) + ", max = " + max);
        System.out.println("nums: " + Arrays.toString(nums) + ", max = " + maxBf + " (brute force)");

//        nums = new int[]{1, 2, 3, 0, 5};
        nums = new int[]{1, 0, 3};
        max = maxProduct1(nums);
        maxBf = maxProduct_bf(nums);
        System.out.println("nums: " + Arrays.toString(nums) + ", max = " + max);
        System.out.println("nums: " + Arrays.toString(nums) + ", max = " + maxBf + " (brute force)");

        nums = new int[]{1, 0, 2, 3, -1, 1, 10, 0, 2};
        max = maxProduct1(nums);
        maxBf = maxProduct_bf(nums);
        System.out.println("nums: " + Arrays.toString(nums) + ", max = " + max);
        System.out.println("nums: " + Arrays.toString(nums) + ", max = " + maxBf + " (brute force)");
    }

    static int maxProduct_bf(int[] nums) {
        if (nums == null | nums.length == 0) {
            return 0;
        }
        int ans =  nums[0];
        for (int i = 0; i < nums.length; i++) {
            int accu = 1;
            for (int j = i; j < nums.length; j++) {
                accu *= nums[j];
                ans = Math.max(ans, accu);
            }
        }
        return ans;
    }

    /**
     * While going through numbers in nums, we will have to keep track of the maximum
     * product up to that number (we will call max_so_far) and minimum product up to
     * that number (we will call min_so_far). The reason behind keeping track of max_so_far
     * is to keep track of the accumulated product of positive numbers. The reason behind
     * keeping track of min_so_far is to properly handle negative numbers.
     *
     * Time complexity: O(N) where N is the size of nums
     * Space complexity: O(1) since no extra scalable space is used
     */
    static int maxProduct1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxSoFar = nums[0], minSoFar = nums[0], ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // store current 'max' before updating max
            // because before updating 'min' your 'max' will already be updated
            int curr = nums[i];
            int tempMax = Math.max(curr, Math.max(maxSoFar * curr, minSoFar * curr));
            minSoFar = Math.min(curr, Math.min(maxSoFar * curr, minSoFar * curr));
            maxSoFar = tempMax;
            ans = Math.max(maxSoFar, ans);
        }
        return ans;
    }

}
