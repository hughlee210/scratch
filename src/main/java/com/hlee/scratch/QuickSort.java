package com.hlee.scratch;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
		int[] x = { 9, 2, 4, 7, 3, 7, 10 };
		System.out.println(Arrays.toString(x));

		int low = 0;
		int high = x.length - 1;

		quickSort(x, low, high);
		System.out.println(Arrays.toString(x));
	}

	public static void quickSort(int[] arr, int low, int high) {
		if (arr == null || arr.length == 0)
			return;

		if (low >= high)
			return;

		// pick the pivot
		int middle = low + (high - low) / 2;
		int pivot = arr[middle];

		System.out.println("pivot value: " + pivot + " at index: " + middle);

		// make left < pivot and right > pivot
		int left = low, right = high;
		while (left <= right) {
			while (arr[left] < pivot) {
				left++;
			}

			while (arr[right] > pivot) {
				right--;
			}

			if (left <= right) {
				System.out.println(" swapping left " + arr[left] + " at " + left + " and right " + arr[right] + " at " + right + "...");
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				left++;
				right--;
				System.out.println(" arr: " + Arrays.toString(arr));
			}
		}

		System.out.println("  after partitioning: arr = " + Arrays.toString(arr) + " low < end ? " + (low < right) + ", start < high ? " + (left < high));

		// recursively sort two sub parts
		if (low < right) {
			System.out.println("  doing quickSort(arr, " + low + ", " + right + ")");
			quickSort(arr, low, right);
		}

		if (high > left) {
			System.out.println("  doing quickSort(arr, " + left + ", " + high + ")");
			quickSort(arr, left, high);
		}
	}
}