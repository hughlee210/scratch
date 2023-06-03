package com.hlee.scratch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MedianFinder {

    List<Integer> input = new ArrayList<>();

    void addNumber(int num) {
        input.add(num);
    }

    /**
     * Time complexity: O(NlogN)
     * Space complexity: O(N) / O(logN)
     */
    double findMedian() {
        Collections.sort(input);
        int len = input.size();
        if (len % 2 == 1) {
            return input.get(len / 2);
        }
        return (input.get(len / 2) + input.get(len / 2 - 1)) * 0.5; // * 0.5 is same as 1/2
    }
}

class MedianFinder2 {
    List<Integer> input = new LinkedList<>();

    void addNum(int num) {
        // assume input list is already sorted; binarySearch = O(logN)
        int idx = Collections.binarySearch(input, num);
        if (idx >= 0) {
            input.add(idx, num);
        } else {
            input.add(-idx - 1, num);
        }
    }

    double findMedian() {
        int len = input.size();
        int mid = input.get(len / 2);
        if (len % 2 == 1) {
            return mid;
        } else {
            return 1.0 * (input.get(len / 2 - 1) + mid) / 2;
        }
    }
}

