package com.hlee.scratch.binarysearch;

public class BinarySearchBounds {

    public static void main(String[] args) {
        int[] array = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;
        test(array, target);

        target = 10;
        test(array, target);

    }

    static void test(int[] array, int target) {
        // Finding lower bound of 2
        int lowerBound = lowerBoundSearch(array, target);
        System.out.println("Lower Bound of " + target + ": " + lowerBound);

        // Finding upper bound of 2
        int upperBound = upperBoundSearch(array, target);
        System.out.println("Upper Bound of " + target + ": " + upperBound);
    }

    public static int lowerBoundSearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid; // mid is inclusive on the lower bound
            }
        }
        if (arr[left] != target) {
            return -1;
        }
        return left; // return index will be the index of the first target
    }

    public static int upperBoundSearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                left = mid + 1; // mid is not inclusive, so it's upper bound. return index will be just after target
            } else {
                right = mid;
            }
        }
        if (arr[left] == target || ((left - 1 >= 0) && arr[left - 1] == target)) {
            return left;
        }
        return -1;
    }
}
