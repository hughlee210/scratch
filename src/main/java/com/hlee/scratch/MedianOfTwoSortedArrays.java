package com.hlee.scratch;

import java.util.Arrays;

public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {

        int[] arr1 = { 1, 4, 5 };
        int[] arr2 = { 2, 3, 60, 80, 90, 100, 200 };
        System.out.println("arr1 = " + Arrays.toString(arr1) + ", median = " + findMedian(arr1));
        System.out.println("arr2 = " + Arrays.toString(arr2) + ", median = " + findMedian(arr2));
        System.out.println("-----------------------------------");
        
        double median = findMedian_usingMerge(arr1, arr2);
        System.out.println("Median of two arrays = " + median);
        System.out.println("-----------------------------------");

        median = findMedian_withoutMerge_usingPartition(arr1, arr2);
        System.out.println("Median of two arrays (using binary search partition) = " + median);
        System.out.println("-----------------------------------");

        int[] arrX = { 1, 3, 8, 9, 15 };
        int[] arrY = { 6, 10, 17, 19, 21, 25 };
        System.out.println("arrX = " + Arrays.toString(arrX) + ", median = " + findMedian(arr1));
        System.out.println("arrY = " + Arrays.toString(arrY) + ", median = " + findMedian(arr2));

        median = findMedian_withoutMerge_usingPartition(arrX, arrY);
        System.out.println("Median of two arrays (using binary search partition) = " + median);
        System.out.println("-----------------------------------");

    }

    // Find median of two sorted arrays after merging two arrays
    // Time complexity: O(m + n), Space: O(m + n)
    //
    static double findMedian_usingMerge(int[] arr1, int[] arr2) {

        int[] mergedArr = MergeTwoSortedArray.mergeArrays_extraSpace(arr1, arr2);
        System.out.println("merged array = " + Arrays.toString(mergedArr));
        double median = findMedian(mergedArr);
        return median;
    }

    // find median of a sorted array
    static double findMedian(int[] arr) {
        int n = arr.length;
        if (n % 2 == 1) { // size = odd number
            return arr[n / 2];
        } else {
            return (arr[(n / 2) - 1] + arr[n / 2]) / 2d;
        }
    }

    // https://www.youtube.com/watch?v=LPFhl65R7ww
    // Binary Search : Median of two sorted arrays of different sizes
    //
    // To solve this problem, we need to understand "What is the use of median". In statistics, the median is used for:
    // "Dividing a set into two equal length subsets, that one subset is always greater than the other."
    //
    // Use binary search technique to find a correct partition of both arrays
    // such that the combined number of elements of array X and array Y on left side of partition is equal to 
    // the combined number of elements of array X and array Y on right side of partition.
    // If total number of elements of both arrays is odd, left side has one more element than right side.
    //
    // Example:
    //
    // x1 x2 | x3 x4 x5 x6  (6 elements)
    // 
    // y1 y2 y3 y4 y5 | y6 y7 y8  (8 elements)
    //
    // Let's assume we found the correct partition as described above, 
    // every element on the left side would be less than every element on the right of partition.
    // So, we can derive the following from the above statement.
    // x2 <= y6
    // y5 <= x3 
    // If we find the partition, we know that those are the four elements which matter
    // because the median should be around those 4 elements.
    // So, we know that the median in the above case.
    // Median = avg( max(x2,y5), min(x3,y6)) for even number of total elements
    // Median = max(x2, y5) for odd number of total elements
    // 
    // [Condition to satisfy to use this algorithm]
    //
    // Number of elements of arrayX on left side of partition + Number of elements of arrayY on left side of partition
    // = half of total number of elements of both arrays
    //
    // partitionX + partitionY = (x + y + 1) / 2   : + 1 is to take care of odd size case 
    // 
    // partitionY = (x + y + 1) / 2 - partitionX
    // 
    // If we found the correct partition, below must be true:
    //              max of Left X <= min of Right Y 
    //              max of Left Y <= min of Right X
    //
    // else if      max of Left X >  min of Right Y,  we are too much on the right side
    //              move towards left side in X
    //
    // else         move towards right side in X
    //
    // Time complexity: O(log(min(x,y))), Space: O(1)
    // 
    static double findMedian_withoutMerge_usingPartition(int[] arrX, int[] arrY) {
        //if input1 length is greater, then switch them so that input1 is smaller than input2.
        // in order to achieve less time complexity. 
        if (arrX.length > arrY.length) {
            return findMedian_withoutMerge_usingPartition(arrY, arrX);
        }
        int x = arrX.length;
        int y = arrY.length;

        int low = 0;
        int high = x;

        // partitionX is the number of elements in partition X
        // partitionY is the number of elements in partition Y

        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            //if partitionX is 0 it means nothing is there on left side. Use -INFINITY for maxLeftX
            //if partitionX is length of input then there is nothing on right side. Use +INFINITY for minRightX
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : arrX[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : arrX[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : arrY[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : arrY[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // We have partitioned array at correct place
                // Now get max of left elements and min of right elements to get the median in case of even length combined array size
                // or get max of left for odd length combined array size.
                if ((x + y) % 2 == 0) {
                    return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                } else {
                    return (double) Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) { //we are too far on right side for partitionX. Go on left side.
                high = partitionX - 1;
            } else { //we are too far on left side for partitionX. Go on right side.
                low = partitionX + 1;
            }
        }
        //Only we we can come here is if input arrays were not sorted. Throw in that scenario.
        throw new IllegalArgumentException();
    }


    // https://leetcode.com/problems/median-of-two-sorted-arrays/solution/
    // this works, but can be made easier to understand
    //
    static double findMedianSortedArrays_temp(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = iMin + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = iMax - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
