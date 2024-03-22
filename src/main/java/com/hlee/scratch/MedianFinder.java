package com.hlee.scratch;

import java.util.*;

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
        return (input.get(len / 2 - 1) + input.get(len / 2)) * 0.5d; // * 0.5 is same as 1/2
    }
}

class MedianFinder2 {
    List<Integer> input = new LinkedList<>();

    /**
     * Time complexity: O(logN) + O(N) = binary search + insert and shift in the array
     * Space complexity: O(N) for the input array to hold data
     */
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
            return 1.0d * (input.get(len / 2 - 1) + mid) / 2;
        }
    }
}

class MedianFinder3 {

    // maxHeap will hold half of element of numbers if size of numbers are even
    // otherwise maxHeap will hold one more element than the minHeap if size is old
    private PriorityQueue<Integer> maxHeap; // stores smaller half of the numbers
    private PriorityQueue<Integer> minHeap; // stores larger half of the numbers

    public MedianFinder3() {
        this.maxHeap = new PriorityQueue<>();
        this.minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // add the number to the appropriate heap
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        // balance the heaps to ensure their sizes differ by at most 1
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            throw new IllegalStateException("No elements in the median finder");
        }

        // if the heaps have equal size, median is the average of their tops
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else { // otherwise, median is the top element of maxHeap.
            return maxHeap.peek();
        }
    }
}
