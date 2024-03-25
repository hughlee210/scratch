package com.hlee.scratch.binarysearch;

import java.util.Arrays;

public class RangeSearch {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 5, 5, 5, 5, 7, 8};
        int target = 5;
        test(array, target);

        array = new int[]{1, 2, 3, 5, 7};
        test(array, target);

        array = new int[]{1, 2, 3, 5};
        test(array, target);

        array = new int[]{1, 2, 3, 5};
        test(array, 10);

    }

    static void test(int[] array, int target) {
        System.out.println("input array = " + Arrays.toString(array) + ", target = " + target);
        System.out.println("last index = " + (array.length - 1));

        int[] range = rangeSearch(array, target);
        System.out.println("range search result = " + Arrays.toString(range));

        range = rangeSearchUsingLowerBoundUpperBoundSearch(array, target);
        System.out.println("range search using lowerUpperBounds = " + Arrays.toString(range));
        System.out.println("-----------------------------------------");
    }

    /**
     * ex: {1, 2, 3, 5, 5, 5, 5, 7, 8}
     *
     * Time complexity: O(logn + logn + logn) = O(3logn) = O(logn)
     *
     */
    static int[] rangeSearch(int[] nums, int target) {
        if (nums.length == 0) {
            int[] notFound = {-1, -1};
            return notFound;
        }
        int firstPos = binarySearch(nums, 0, nums.length - 1, target);
        int startPos = firstPos;
        int endPos = firstPos;
        int temp1 = firstPos, temp2 = firstPos;
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
        return new int[]{startPos, endPos};
    }

    static int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    static int[] rangeSearchUsingLowerBoundUpperBoundSearch(int[] nums, int target) {
        int lowerBound = BinarySearchBounds.lowerBoundSearch(nums, target);
        int upperBound = BinarySearchBounds.upperBoundSearch(nums, target);
        if (upperBound != -1 && nums[upperBound] != target) {
            upperBound--;
        }
        return new int[]{lowerBound, upperBound};
    }
}
