package com.hlee.scratch.binarysearch;

public class RangeSearch {


    /**
     * ex: {1, 2, 3, 5, 5, 5, 5, 7, 8}
     *
     * Time complexity: O(logn + logn + logn) = O(3logn) = O(logn)
     *
     */
    static Integer[] rangeSearch(int[] nums, int target) {
        if (nums.length == 0) {
            Integer[] notFound = new Integer[]{-1, -1};
            return notFound;
        }
        int firstPos = binarySearch(nums, 0, nums.length - 1, target);
        int startPos = firstPos;
        int endPos = firstPos;
        int temp1 = 0, temp2 = 0;
        while (startPos != -1) {
            temp1 = startPos; // to last non -1 value
            startPos = binarySearch(nums, 0, startPos - 1, target);
        }
        startPos = temp1;

        while (endPos != -1) {
            temp2 = endPos; // to last non -1 value;
            endPos = binarySearch(nums, endPos + 1, nums.length - 1, target);
        }
        endPos = temp2;
        return new Integer[] {startPos, endPos};
    }

    static int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            int midValue = nums[mid];
            if (midValue == target) {
                return mid;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
