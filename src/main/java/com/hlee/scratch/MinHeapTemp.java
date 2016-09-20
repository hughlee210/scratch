package com.hlee.scratch;

import java.util.Arrays;

public class MinHeapTemp {

	public static void main(String[] args) {
		int[] unsortedArr = { 4, 1, 3, 10, 6, 5, 20 };
		for (int k = 1; k <= unsortedArr.length; k++) {
			int kth = getKthSmallest(unsortedArr, k);
			System.out.println(k + "th element in array " + Arrays.toString(unsortedArr) + " is " + kth);
		}

	}

	public static int getKthSmallest(int[] arr, int k) {

		// Build a min heap: O(n) time
		MinHeapTemp heap = new MinHeapTemp(arr);

		// Extract (k - 1) element from the heap: O((k-1)logn) time
		for (int i = 0; i < k - 1; i++) {
			heap.extractMin();
		}

		// Now get min returns Kth smallest
		int kthElement = heap.getMin();
		return kthElement;
	}

	private int[] heap;
	private int size;

	public MinHeapTemp(int[] arr) {
		size = arr.length;
		heap = new int[size];
		System.arraycopy(arr, 0, heap, 0, arr.length);

		buildHeap();
	}

	private void buildHeap() {
		int i = (size - 1) / 2;
		while (i >= 0) {
			bubbleDown(i);
			i--;
		}
	}

	private void bubbleDown(int i) {
		int left = getLeft(i);
		int right = getRight(i);
		int minIndex = i;
		if (left < size && heap[left] < heap[minIndex])
			minIndex = left;
		if (right < size && heap[right] < heap[minIndex])
			minIndex = right;
		if (minIndex != i) {
			swap(heap, i, minIndex);
			bubbleDown(minIndex);
		}
	}

	// O(log n)
	private int extractMin() {
		int root = heap[0];
		if (size > 1) {
			heap[0] = heap[size - 1];
			bubbleDown(0);
		}
		return root;
	}

	private int getMin() {
		return heap[0];
	}

	private int getLeft(int i) {
		return 2 * i + 1;
	}

	private int getRight(int i) {
		return 2 * i + 2;
	}

	private int getParent(int i) {
		return (i - 1) / 2;
	}

	private static void swap(int[] arr, int m, int n) {
		if (m != n && m >= 0 && n >= 0) {
			int temp = arr[m];
			arr[m] = arr[n];
			arr[n] = temp;
		}
	}
}
