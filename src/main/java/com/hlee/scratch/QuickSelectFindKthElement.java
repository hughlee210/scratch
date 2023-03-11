package com.hlee.scratch;

import java.util.Arrays;
import java.util.Random;

/**
 * Quickselect uses the same overall approach as quicksort, choosing one element as a pivot 
 * and partitioning the data in two based on the pivot, accordingly as less than or greater than the pivot. 
 * However, instead of recursing into both sides as in quicksort, quickselect only recurses into one side
 * – the side with the element it is searching for. 
 * This reduces the average complexity from O(NlogN) to O(N), with a worst case of O(N^2).
 */
public class QuickSelectFindKthElement {

    public static void main(String[] args) {
        int[] arr = { 14, 2, 6, 9, 3 };
        int k = 1; // kth element where k starts from 1
        int indexToFind = k - 1;
        int result = quickSelectIter(arr, 0, arr.length - 1, indexToFind);
        System.out.println("quickSelectIter: " + k + "th smallest element in " + Arrays.toString(arr) + " = " + result);

        arr = new int[] { 200, 10, 8, 1, 40 };
        k = 4;
        indexToFind = k - 1;
        result = quickSelectIter(arr, 0, arr.length - 1, indexToFind);
        System.out.println("quickSelectIter: " + k + "th smallest element in " + Arrays.toString(arr) + " = " + result);

        arr = new int[] { 200, 10, 8, 1, 40 };
        int kthLargest = 1; // k starts from 1 where 1 means 1st largest
        indexToFind = arr.length - kthLargest;
        result = quickSelect(arr, 0, arr.length - 1, indexToFind);
        System.out.println("quickSelect: " + kthLargest + "th largest element in " + Arrays.toString(arr) + " = " + result);

        arr = new int[] { 200, 10, 8, 1, 40 };
        kthLargest = 4;
        indexToFind = arr.length - kthLargest;
        result = quickSelect(arr, 0, arr.length - 1, indexToFind);
        System.out.println("quickSelect: " + kthLargest + "th largest element in " + Arrays.toString(arr) + " = " + result);

    }

    /**
     * Partition method, in linear time, group a list (ranging from indices left to right) 
     * into two parts, those less than a pivot element, 
     * and those greater than or equal to the element.
     */
    private static int partition(int[] arr, int left, int right) {
        int pivotValue = arr[right];
        int partitionIndex = left; // initial store position to store element less than pivot value
        for (int j = left; j < right; j++) {
            if (arr[j] < pivotValue) {
                swap(arr, partitionIndex, j);
                partitionIndex++;
            }
        }
        swap(arr, partitionIndex, right); // pivot was moved to the right, now move pivot to its final sorted place
        return partitionIndex; // return sorted pivot position
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

    /**
     * Returns the element of the array at indexToFind position within left..right inclusive.
     * The search space within the array is changing for each round - but the list
     * is still the same size. Thus, indexToFind does not need to be updated with each round.
     */
    static int quickSelect(int[] arr, int left, int right, int indexToFind) {
        if (indexToFind < 0 || indexToFind > arr.length - 1)
            throw new IllegalArgumentException(indexToFind + " is not a valid argument");
        if (left == right && indexToFind < 2)
            return arr[left];

        int pivotIndex = partition(arr, left, right);

        // the pivot is in its final sorted position
        if (indexToFind == pivotIndex)
            return arr[indexToFind];
        else if (indexToFind < pivotIndex)
            return quickSelect(arr, left, pivotIndex - 1, indexToFind);
        else
            return quickSelect(arr, pivotIndex + 1, right, indexToFind);
    }

    // working! - iterative version by eliminating tail recursion
    static int quickSelectIter(int[] arr, int left, int right, int indexToFind) {
        if (arr == null || arr.length == 0)
            throw new RuntimeException("array is null or empty");
        if (left == right && indexToFind < 2)
            return arr[left];

        while (true) {
            int partitionIndex = partition(arr, left, right);
            if (indexToFind == partitionIndex)
                return arr[indexToFind];
            else if (indexToFind < partitionIndex)
                right = partitionIndex - 1;
            else
                left = partitionIndex + 1;
        }
    }

    /*
    Note the resemblance to quicksort: just as the minimum-based selection algorithm is a partial selection sort, 
    this is a partial quicksort, generating and partitioning only O(log n) of its O(n) partitions. 
    This simple procedure has expected linear performance, and, like quicksort, has quite good performance in practice. 
    It is also an in-place algorithm, requiring only constant memory overhead, since the tail recursion can be
    eliminated with a loop like this:
    
    function select(list, left, right, n)
     if left = right
         return list[left]
     loop
         pivotIndex := ...     // select pivotIndex between left and right
         pivotIndex := partition(list, left, right, pivotIndex)
         if n = pivotIndex
             return list[n]
         else if n < pivotIndex
             right := pivotIndex - 1
         else
             left := pivotIndex + 1
     */

}
