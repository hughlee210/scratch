package com.hlee.scratch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MediansOfRunningNumbers {

    public static void main(String[] args) {

        int[] arr = { 1, 2, 3, 4, 5 };
        double[] medians = getMedians(arr);
        System.out.println("arr = " + Arrays.toString(arr));
        System.out.println("medians = " + Arrays.toString(medians));

    }

    static double[] getMedians(int[] arr) {

        // max heap
        PriorityQueue<Integer> lowers = new PriorityQueue<>((o1, o2) -> {
            return -1 * o1.compareTo(o2); // big element on top
        });

        // min heap
        PriorityQueue<Integer> highers = new PriorityQueue<>();

        double[] medians = new double[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            addNumber(num, lowers, highers);
            rebalance(lowers, highers);
            medians[i] = getMedian(lowers, highers);
        }

        return medians;
    }

    static void addNumber(int num, PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
        if (lowers.size() == 0 || num < lowers.peek()) {
            lowers.add(num);
        } else {
            highers.add(num);
        }
    }

    static void rebalance(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
        PriorityQueue<Integer> biggerHeap = lowers.size() > highers.size() ? lowers : highers;
        PriorityQueue<Integer> smallerHeap = lowers.size() > highers.size() ? highers : lowers;

        if (biggerHeap.size() - smallerHeap.size() >= 2) {
            smallerHeap.add(biggerHeap.poll());
        }
    }

    static double getMedian(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers) {
        PriorityQueue<Integer> biggerHeap = lowers.size() > highers.size() ? lowers : highers;
        PriorityQueue<Integer> smallerHeap = lowers.size() > highers.size() ? highers : lowers;

        if (biggerHeap.size() == smallerHeap.size()) {
            return (double) (biggerHeap.peek() + smallerHeap.peek()) / 2;
        } else {
            return biggerHeap.peek();
        }
    }

}
