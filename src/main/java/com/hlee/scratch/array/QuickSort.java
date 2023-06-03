package com.hlee.scratch.array;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = { 9, 2, 4, 7, 3, 7, 1, -3, 10 };
        System.out.println("before sort: " + Arrays.toString(arr));

        quickSort(arr, 0, arr.length - 1);
        System.out.println("after sort : " + Arrays.toString(arr));

        int k = 2;
        int kthLargestElement = getKthLargest(arr, k);
        System.out.println(String.format("%d(th) largest element in array %s = %d (using quick sort)",
                k, Arrays.toString(arr), kthLargestElement));

        kthLargestElement = getKthLargestUsingQuickSelect(arr, k);
        System.out.println(String.format("%d(th) largest element in array %s = %d (using quick select)",
                k, Arrays.toString(arr), kthLargestElement));
    }

    /**
     * Partition method, in linear time, group a list (ranging from indices left to right)
     * into two parts, those less than a pivot element,
     * and those greater than or equal to the pivot element.
     * [5,3,4,2,6,1]
     *  i         pivot
     *  j
     *
     * @return pivot position
     */
    private static int partition(int[] nums, int left, int right) {
        int pivotValue = nums[right];
        int i = left; // i (partition index) initialize to left. position to store element less than pivot value
        for (int j = left; j < right; j++) { // j: scan index, number at j to compare with pivot value
            if (nums[j] < pivotValue) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right); // move pivot element to its final sorted place
        return i; // return sorted pivot position (partition position)
    }

    private static void swap(int[] arr, int x, int y) {
        if (arr == null || arr.length == 0)
            return;
        if (x < 0 || x > arr.length - 1)
            throw new IndexOutOfBoundsException("index " + x + " is out of bound of arr " + arr);
        else if (y < 0 || y > arr.length - 1)
            throw new IndexOutOfBoundsException("index " + y + " is out of bound of arr " + arr);

        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    // Time: O(NlogN); partition time (N) * number of partition call (logN)
    // Space: O(logN); how many times it's called (stack space)
    static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(nums, left, right); // Time: O(N)
            quickSort(nums, left, partitionIndex - 1);
            quickSort(nums, partitionIndex + 1, right);
        }
    }

    /**
     * Returns the element of the array at indexToFind position within left..right inclusive.
     * The search space within the array is changing for each round - but the list
     * is still the same size. Thus, indexToFind does not need to be updated with each round.
     *
     * Time: O(N + N/2 + N/4 + N/8 + ...) = O(2N) = O(N)
     * Space: O(1) if compiler supports tail recursion
     */
    static int quickSelect(int[] nums, int left, int right, int indexToFind) {
        if (left < right) {
            int partitionIndex = partition(nums, left, right);
            if (partitionIndex == indexToFind) {
                return nums[partitionIndex];
            } else if (indexToFind < partitionIndex) {
                return quickSelect(nums, left, partitionIndex - 1, indexToFind);
            } else {
                return quickSelect(nums, partitionIndex + 1, right, indexToFind);
            }
        }
        throw new IllegalArgumentException("left and right arguments are not a valid");
    }

    int quickSelect_iter(int[] arr, int left, int right, int indexToFind) {
        while (true) {
            int partitionIndex = partition(arr, left, right);
            if (indexToFind == partitionIndex) {
                return arr[indexToFind];
            } else if (indexToFind < partitionIndex) {
                right = partitionIndex - 1;
            } else {
                left = partitionIndex + 1;
            }
        }
    }

    static int getKthLargest(int[] arr, int k) {
        // using quick sort
        int indexToFind = arr.length - k;
        quickSort(arr, 0, arr.length - 1);
        return arr[indexToFind];
    }

    static int getKthLargestUsingQuickSelect(int[] arr, int k) {
        int indexToFind = arr.length - k;
        return quickSelect(arr, 0, arr.length - 1, indexToFind);
    }

    public static void quickSort_iter(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
            return;

        if (low >= high)
            return;

        // pick the pivot
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];

        //        System.out.println("pivot value: " + pivot + " at index: " + middle);

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
                //                System.out.println(" swapping left " + arr[left] + " at " + left + " and right " + arr[right] + " at " + right + "...");
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
                //                System.out.println(" arr: " + Arrays.toString(arr));
            }
        }

        //        System.out.println("  after partitioning: arr = " + Arrays.toString(arr) + " low < end ? " + (low < right) + ", start < high ? " + (left < high));

        // recursively sort two sub parts
        if (low < right) {
            //            System.out.println("  doing quickSort(arr, " + low + ", " + right + ")");
            quickSort_iter(arr, low, right);
        }

        if (high > left) {
            //            System.out.println("  doing quickSort(arr, " + left + ", " + high + ")");
            quickSort_iter(arr, left, high);
        }
    }

}
