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

		index = findMinElementIndex(arr);
		System.out.println("arr: " + Arrays.toString(arr) + ", min index: " + +index);

		int[] rotatedArr = { 8, 9, 1, 2, 3, 4, 5, 6, 7 };
		index = findMinElementIndex(rotatedArr);
		System.out.println("arr: " + Arrays.toString(rotatedArr) + ", min index: " + +index);

		key = 1;
		index = findElementInRotatedArray(rotatedArr, key);
		System.out.println("arr: " + Arrays.toString(rotatedArr) + ", key: " + key + " -> key index in rotated arr: " + index);
	}

	// assumption: arr is sorted in ascending order
	public static int bs(int[] arr, int key) {
		if (arr == null || arr.length == 0)
			return -1;
		int lo = 0;
		int hi = arr.length - 1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (key < arr[mid])
				hi = mid - 1;
			else if (key > arr[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}

	public static int rBS(int[] arr, int key, int lo, int hi) {
		if (arr == null || arr.length == 0)
			return -1;
		if (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (key < arr[mid])
				return rBS(arr, key, lo, mid - 1);
			else if (key > arr[mid])
				return rBS(arr, key, mid + 1, hi);
			else
				return mid;
		}
		return -1;
	}

	/**
	 * This is similar to binary search, but modified to work for rotated sorted
	 * array, so more general than binary search as it works for both rotated
	 * and non-rotated arrays.
	 */
	public static int findElementInRotatedArray(int[] arr, int key) {
		if (arr == null || arr.length == 0)
			return -1;
		int lo = 0;
		int hi = arr.length - 1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (arr[mid] == key)
				return mid;

			if (arr[lo] <= arr[mid]) {
				// left half is sorted. now check if key is in this half
				if (arr[lo] <= key && key < arr[mid])
					hi = mid - 1;
				else
					lo = mid + 1;
			}
			else {
				// right half is sorted. now check if key is in this half
				if (arr[mid] < key && key <= arr[hi])
					lo = mid + 1;
				else
					hi = mid - 1;
			}
		}
		return -1;
	}

	/**
	 * Find rotation pivot index. Works for both rotated and non-rotated array.
	 */
	public static int findMinElementIndex(int[] arr) {
		if (arr == null || arr.length == 0)
			return -1;
		int lo = 0;
		int hi = arr.length - 1;
		// in rotated sorted array, leftmost element is always greater than rightmost element
		// if it is rotated, otherwise min element is the 1st element.
		while (arr[lo] > arr[hi]) {
			int mid = (lo + hi) / 2;
			if (arr[lo] < arr[mid])
				lo = mid + 1;
			else
				hi = mid;
		}
		return lo;
	}

}
