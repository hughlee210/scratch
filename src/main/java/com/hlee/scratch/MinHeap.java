package com.hlee.scratch;

public class MinHeap {

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

		// build a heap of n elements: O(n) time
		MinHeap minHeap = new MinHeap(arr);

		// Extract min (k-1) times: O( (k-1) logn)
		for (int i = 0; i < k - 1; i++) {
			minHeap.extractMin();
		}

		// now getMin returns kth smallest element
		return minHeap.getMin();
	}

	private int[] heapArr;
	private int size;

	public MinHeap(int[] arr) {
		this.size = arr.length;
		heapArr = arr;

		int i = (size - 1) / 2;
		while (i >= 0) {
			heapify(i);
			i--;
		}
	}

	private int getParent(int i) {
		return (i - 1) / 2;
	}

	private int getLeft(int i) {
		return 2 * i + 1;
	}

	private int getRight(int i) {
		return 2 * i + 2;
	}

	private int getMin() {
		return heapArr[0];
	}

	private int extractMin() {
		if (size == 0)
			return Integer.MAX_VALUE;

		// store min value
		int root = heapArr[0];

		// if there are more than one item, move the last item to root
		// and call heapify.
		if (size > 1) {
			heapArr[0] = heapArr[size - 1];
			heapify(0);
		}
		size--;
		return root;
	}

	private void swap(int[] arr, int m, int n) {
		int temp = arr[m];
		arr[m] = arr[n];
		arr[n] = temp;
	}

	/**
	 * Heapify subtree rooted with index i
	 * 
	 * @param i
	 */
	private void heapify(int i) {
		System.out.println("heapify called");
		int l = getLeft(i);
		int r = getRight(i);
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
