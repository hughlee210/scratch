package com.hlee.scratch.array;

import java.util.Arrays;

public class MergeTwoSortedArray {

    public static void main(String[] args) {

        int[] arr1 = { 1, 1, 3, 5, 7 };
        int[] arr2 = { 2, 4, 6, 8, 10, 12 };
        int mergeLength = 2;
        int[] mergedArr = mergeArrays(arr1, arr2, mergeLength);
        System.out.println("arr1 = " + Arrays.toString(arr1));
        System.out.println("arr2 = " + Arrays.toString(arr2));
        System.out.println("merged = " + Arrays.toString(mergedArr));
        System.out.println("---------------------------------------------------");

        arr1 = new int[]{1, 4, 5};
        arr2 = new int[]{2, 3, 60, 80, 90, 90, 90, 100, 2000};
        mergedArr = mergeArrays(arr1, arr2, mergeLength);
        System.out.println("arr1 = " + Arrays.toString(arr1));
        System.out.println("arr2 = " + Arrays.toString(arr2));
        System.out.println("merged = " + Arrays.toString(mergedArr));
        System.out.println("---------------------------------------------------");

        arr1 = null;
        arr2 = new int[]{1, 4, 5};
        mergedArr = mergeArrays(arr1, arr2, mergeLength);
        System.out.println("arr1 = " + Arrays.toString(arr1));
        System.out.println("arr2 = " + Arrays.toString(arr2));
        System.out.println("merged = " + Arrays.toString(mergedArr));
        System.out.println("---------------------------------------------------");

        arr1 = new int[]{1, 4, 5};
        arr2 = new int[0];
        mergedArr = mergeArrays(arr1, arr2, mergeLength);
        System.out.println("arr1 = " + Arrays.toString(arr1));
        System.out.println("arr2 = " + Arrays.toString(arr2));
        System.out.println("merged = " + Arrays.toString(mergedArr));
        System.out.println("---------------------------------------------------");

        arr1 = new int[0];
        arr2 = new int[0];
        mergedArr = mergeArrays(arr1, arr2, mergeLength);
        System.out.println("arr1 = " + Arrays.toString(arr1));
        System.out.println("arr2 = " + Arrays.toString(arr2));
        System.out.println("merged = " + Arrays.toString(mergedArr));
        System.out.println("---------------------------------------------------");



        // -1 to indicate unoccupied space
        int[] array1 = { 1, 18, 22, 100, 105, 1002, -1, -1, -1, -1, -1 };
        int[] array2 = { 16, 17, 19, 21, 1001 };

        System.out.println("--- Using merge inline...");
        System.out.println("array1 = " + Arrays.toString(array1));

        mergeArrays_noSpace(array1, array2);
        System.out.println("array2 = " + Arrays.toString(array2));
        System.out.println("merged = " + Arrays.toString(array1));
    }

    // time: O(1)
    static double findMedian(int[] arr) {
        double median;
        int n = arr.length;
        if (n % 2 == 1) { // size = odd number
            median = arr[n / 2];
        } else {
            median = (double) (arr[(n / 2) - 1] + arr[n / 2]) / 2;
        }
        return median;
    }

    // time complexity: O(n1 + n2), space: O(n1 + n2)
    //
    static int[] mergeArrays_extraSpace(int[] arr1, int[] arr2) {
        // assumption: arr1 and arr2 are not null
        int i = 0, j = 0, k = 0;
        int[] arr3 = new int[arr1.length + arr2.length];
        // traverse both arrays
        while (i < arr1.length && j < arr2.length) {
            // if element of arr1 is smaller than element of arr2, store arr1 element otherwise arr2 element into result array
            if (arr1[i] < arr2[j])
                arr3[k++] = arr1[i++];
            else
                arr3[k++] = arr2[j++];
        }
        // store remaining elements of arr1 into result array
        while (i < arr1.length) {
            arr3[k++] = arr1[i++];
        }
        // store remaining elements of arr2 into result array
        while (j < arr2.length) {
            arr3[k++] = arr2[j++];
        }
        return arr3;
    }

    // Time complexity: O(n1 + n2), Space complexity: O(n1 + n2)
    static int[] mergeArrays(int[] arr1, int[] arr2, int mergeLength) {
        if ((arr1 == null || arr1.length == 0) && (arr2 == null || arr2.length == 0)) {
            return new int[0];
        }
        int n1 = arr1 == null ? 0 : arr1.length;
        int n2 = arr2 == null ? 0 : arr2.length;
        int i = 0, j = 0, k = 0;
        //int[] mergedArr = new int[n1 + n2];
        int[] mergedArr = new int[mergeLength];
        // traverse both arrays
        while (i < n1 && j < n2 && k < mergeLength) {
            // if arr1 element is smaller than arr2 element, store arr1 element otherwise arr2 element into result array
            if (arr1[i] < arr2[j])
                mergedArr[k++] = arr1[i++];
            else
                mergedArr[k++] = arr2[j++];
        }
        // store remaining elements of arr1/arr2 into mergedArr
        while (i < n1 && k < mergeLength) {
            mergedArr[k++] = arr1[i++];
        }
        while (j < n2 && k < mergeLength) {
            mergedArr[k++] = arr2[j++];
        }
        //
        return mergedArr;
//        return Arrays.copyOfRange(mergedArr, 0, mergeLength);
    }

    // time complexity: O(n), space: O(1)
    static void mergeArrays_noSpace(int[] arr1, int[] arr2) {
        // assumption: arr1 has extra space to hold all elements of arr2
        //        -1 to indicate unoccupied space
        //        int[] arr1 = { 1, 18, 22, 100, 105, 1002, -1, -1, -1, -1, -1 };
        //        int[] arr2 = { 16, 17, 19, 21, 1001 };

        int i = arr1.length - arr2.length - 1; // i = last element index of valid elements (non -1) in arr1
        int j = arr2.length - 1; // last element index in arr2
        int k = arr1.length - 1; // result store index; last index of larger array (arr1) = arr1.length - 1;

        while (i >= 0 && j >= 0) {
            if (arr1[i] > arr2[j])
                arr1[k--] = arr1[i--];
            else
                arr1[k--] = arr2[j--];
        }

        // copy remaining elements of arr2 to arr1
        while (j >= 0) {
            arr1[k--] = arr2[j--];
        }
    }

    // Time complexity: O(n), Space complexity: O(1)
    static void mergeArrays_noSpace_ex(int[] arr1, int[] arr2) {
        // assume arr1 has extra space to hold all elements of arr2
        // -1 to indicate unoccupied space
        // int[] arr1 = { 1, 18, 22, 100, 105, 1002, -1, -1, -1, -1, -1 };
        // int[] arr2 = { 16, 17, 19, 21, 1001 };
        int i = arr1.length - arr2.length - 1; // last element index of valid elements in arr1
        int j = arr2.length - 1; // last element index of arr2
        int k = arr1.length - 1; // result store index. last index of larger array (arr1)
        while (i >= 0 && j >= 0) {
            if (arr1[i] > arr2[j]) {
                arr1[k--] = arr1[i--];
            } else {
                arr1[k--] = arr2[j--];
            }
        }
        // copy remaining elements of arr2 to arr1
        while (j >= 0) {
            arr1[k--] = arr2[j--];
        }
    }
}
