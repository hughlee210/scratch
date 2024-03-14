package com.hlee.scratch.binarysearch;

/**
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check. Since
 * each version is developed based on the previous version, all the versions after
 * a bad version are also bad.
 *
 * Suppose you have n versions {1, 2, ..., n} and you want to find out the first bad
 * one, which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which returns whether 'version' is
 * bad. Implement a function to find bad version. you should minimize the number of
 * calls to the API.
 *
 * constraints: 1 <= bad <= n <= 2^31 - 1
 */
public class FirstBadVersion {

    /**
     * Time complexity: O(n) assuming isBadVersion(version) takes constant time to run
     * Space complexity: O(1)
     */
    int firstBadVersionBF(int n) {
        for (int i = 1; i < n; i++) {
            if (isBadVersion(i)) {
                return i;
            }
        }
        return n;
    }

    /**
     * Time complexity: O(logN). The search space is halved each time.
     * Space complexity: O(1)
     */
    int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    boolean isBadVersion(int version) {
        // just a mock
        return true;
    }
}
