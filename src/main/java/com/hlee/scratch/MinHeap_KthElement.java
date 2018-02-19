package com.hlee.scratch;

import java.util.Arrays;

// heap definition:
// https://www.cs.cmu.edu/~adamchik/15-121/lectures/Binary%20Heaps/heaps.html

public class MinHeap_KthElement {

	public static void main(String[] args) {

		// int[] arr = { 12, 3, 5, 7, 19 };
		// int n = arr.length;
		// int k = 3;
		// int result = getKthSmallest(arr, n, k);
		// System.out.println(k + "th smallest element is " + result);

		for (int k = 1; k <= 5; k++) {
			int[] arr = { 12, 3, 5, 7, 19 };
			int result = getKthSmallest(arr, k);
			System.out.println(k + "th smallest element is " + result);
		}
	}

	/**
	 * Return kth smalllest element in a given array.
	 */
	private static int getKthSmallest(int[] arr, int k) {

		if (k < 1 || k > arr.length) {
			throw new RuntimeException(k + " is not valid for k");
		}

        // build a heap of n elements: O(N) time
		MinHeap_KthElement minHeap = new MinHeap_KthElement(arr);

        // Extract min (k-1) times: O((k-1)logN)
		for (int i = 0; i < k - 1; i++) {
			minHeap.extractMin();
		}

        // now getMin returns kth smallest element in constant time
		return minHeap.getMin();
	}

    // heap properties
    //
	private int[] heapArr;
	private int size;

    // Create a Min Heap from an array
    // Time complexity: O(N)
    //
	public MinHeap_KthElement(int[] arr) {
		this.size = arr.length;
        this.heapArr = arr;

		int i = (size - 1) / 2;
		while (i >= 0) {
			heapify(i);
			i--;
		}
        System.out.println("Min Heap created from an array " + Arrays.toString(arr));
	}

	private int getParentIndex(int i) {
		return (i - 1) / 2;
	}

	private int getLeftIndex(int i) {
		return 2 * i + 1;
	}

	private int getRightIndex(int i) {
		return 2 * i + 2;
	}

    // Time complexity: O(1)
    //
	private int getMin() {
		return heapArr[0];
	}

    // Time complexity: O(logN)
    //
	private int extractMin() {
		if (size == 0)
			return Integer.MAX_VALUE;

		// store min value
		int rootValue = heapArr[0];

		// if there are more than one item, move the last item to root
		// and call heapify.
		if (size > 1) {
			heapArr[0] = heapArr[size - 1];
			heapify(0);
		}
		size--;
		return rootValue;
	}

	private void swap(int[] arr, int m, int n) {
		int temp = arr[m];
		arr[m] = arr[n];
		arr[n] = temp;
	}

	/**
	 * Heapify subtree rooted with index i
	 */
	private void heapify(int i) {
		System.out.println("heapify called");
		int l = getLeftIndex(i);
		int r = getRightIndex(i);
		int smallest = i;
		if (l < size && heapArr[l] < heapArr[i])
			smallest = l;
		if (r < size && heapArr[r] < heapArr[smallest])
			smallest = r;
		if (smallest != i) {
			swap(heapArr, smallest, i);
			heapify(smallest);
		}
	}

}
