package com.hlee.scratch;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = { 9, 2, 4, 7, 3, 7, 10 };
        System.out.println("before sort: " + Arrays.toString(arr));

        quickSort2(arr, 0, arr.length - 1);
        System.out.println("after sort : " + Arrays.toString(arr));
    }

    // partition the array into two parts; elements in first half are less than pivot value
    // elements in second half are greater than pivot value.
    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right); // move pivot to end
        int storeIndex = left; // position to store element less than pivot value
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex); // move pivot to its final sorted place
        return storeIndex; // return sorted pivot position
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

    static void quickSort2(int[] arr, int low, int high) {
        if (low < high) {
            Random rand = new Random();
            int pivotIndex = low + rand.nextInt(high - low + 1);
            pivotIndex = partition(arr, low, high, pivotIndex);
            quickSort2(arr, low, pivotIndex - 1);
            quickSort2(arr, pivotIndex + 1, high);
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
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
            quickSort(arr, low, right);
        }

        if (high > left) {
            //            System.out.println("  doing quickSort(arr, " + left + ", " + high + ")");
            quickSort(arr, left, high);
        }
    }
}