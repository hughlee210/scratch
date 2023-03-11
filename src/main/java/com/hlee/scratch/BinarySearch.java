package com.hlee.scratch;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        int key = 6;
        int index = bs(arr, key);
        System.out.println("arr: " + Arrays.toString(arr) + ", key: " + key + " -> key index by iteration: " + index);

        index = rBS(arr, key, 0, arr.length - 1);
        System.out.println("arr: " + Arrays.toString(arr) + ", key: " + key + " -> key index by recursion: " + index);

        index = indexOfMinElement(arr);
        System.out.println("arr: " + Arrays.toString(arr) + ", min index: " + +index);

        int[] rotatedArr = { 5, 6, 7, 8, 9, 1, 2, 3, 4 };
        index = indexOfMinElement(rotatedArr);
        System.out.println("arr: " + Arrays.toString(rotatedArr) + ", min index in rotated sorted array: " + +index);

        int[] rArr = { 6, 1, 2, 3, 4, 5 };
        index = indexOfMinElement(rArr);
        System.out.println("arr: " + Arrays.toString(rArr) + ", min index in rotated sorted array: " + index);

        int[] rArr2 = { 6, -10, 2, 3, 4, 5 };
        index = indexOfMinElement(rArr2);
        System.out.println("arr: " + Arrays.toString(rArr2) + ", min index in rotated sorted array: " + index);

        key = 1;
        index = indexOfElementInRotatedArray(rotatedArr, key);
        System.out.println("arr: " + Arrays.toString(rotatedArr) + ", key: " + key + " -> key index in rotated sorted arr: " + index);

        key = 7;
        index = indexOfElementInRotatedArray(rotatedArr, key);
        System.out.println("arr: " + Arrays.toString(rotatedArr) + ", key: " + key + " -> key index in rotated sorted arr: " + index);

    }

    /**
     * assume arr is sorted in ascending order
     * time: O(logN), space: O(1) 
     * return index of key
     */
    public static int bs(int[] arr, int key) {
        if (arr == null || arr.length == 0)
            return -1;
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            //int mid = (left + right) / 2; // this can cause integer overflow
            int midIndex = left + (right - left) / 2; // to avoid integer overflow
            if (key == arr[midIndex]) {
                return midIndex;
            } else if (key < arr[midIndex]) {
                right = midIndex - 1;
            } else {
                left = midIndex + 1;
            }
        }
        return -1;
    }

    /**
     * time: O(logN), space: O(logN) call stack frames
     */
    public static int rBS(int[] arr, int key, int left, int right) {
        if (arr == null || arr.length == 0)
            return -1;
        if (left < right) {
            int mid = left + (right - left) / 2; // to avoid integer overflow
            if (key == arr[mid])
                return mid;
            else if (key < arr[mid])
                return rBS(arr, key, left, mid - 1);
        }
        return -1;
    }

    /**
     * This is similar to binary search, but modified to work for rotated sorted
     * array, so more general than binary search as it works for both rotated
     * and non-rotated arrays.
     * In rotated sorted array [5,6,7,8,9,1,2,3,4], 
     * leftmost element is always greater than rightmost element if array is rotated.
     */
    public static int indexOfElementInRotatedArray(int[] arr, int key) {
        if (arr == null || arr.length == 0)
            return -1;
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (key == arr[mid]) {
                return mid;
            }
            // now check two parts: first half < mid and second half >= mid element
            if (arr[left] < arr[mid]) {
                // left half is sorted, so check if key is in this half
                if (arr[left] <= key && key < arr[mid])
                    right = mid - 1;
                else
                    left = mid;
            } else {
                // right half is sorted, so check if key is in this half
                if (arr[mid] < key && key <= arr[right])
                    left = mid + 1;
                else
                    right = mid;
            }
        }
        return -1;
    }

    /**
     * Find rotation pivot index. Works for both rotated and non-rotated array.
     * Return the index of min element in rotated sorted array.
     * In rotated sorted array [3,4,5,1,2],
     * leftmost element is always greater than rightmost element if rotated,
     * otherwise min element is the 1st element. 
     */
    public static int indexOfMinElement(int[] arr) {
        if (arr == null || arr.length == 0)
            return -1;
        int left = 0;
        int right = arr.length - 1;
        // in rotated sorted array, leftmost element is always greater than
        // rightmost element if it is rotated,
        // otherwise min element is the 1st element.
        while (arr[left] > arr[right]) {
            int mid = left + (right - left) / 2;
            if (arr[left] > arr[mid])
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    static int indexOfMinElem_ex(int[] arr) {
        // exercise
        return -1;
    }

}
