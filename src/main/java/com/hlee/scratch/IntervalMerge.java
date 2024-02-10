package com.hlee.scratch;

import java.util.*;

public class IntervalMerge {

    public static void main(String[] args) {
        //    For example, let the given set of intervals be {{1,3}, {2,4}, {5,7}, {6,8}}.
        //    The intervals {1,3} and {2,4} overlap with each other, so they should be merged and become {1, 4}.
        //    Similarly, {5, 7} and {6, 8} should be merged and become {5, 8}

        test2();

        testMerge();
    }

    static void test2() {
        int[][] intervals = {
                {3, 5},
                {2, 5},
                {1, 7},
                {8, 12},
        };
        System.out.println("before merge: " + Arrays.deepToString(intervals));
        mergeIntervals_inPlace(intervals);
        System.out.println("after merge : " + Arrays.deepToString(intervals));

    }

    static void testMerge() {
        int[][] intervals = {
                {3, 5},
                {1, 7},
                {8, 12}
        };
        List<int[]> result = mergeIntervals(intervals);
        System.out.println("result = " + Arrays.deepToString(result.toArray()));
        System.out.println("input after merge = " + Arrays.deepToString(intervals));
    }

    /**
     * Time complexity: O(nlogn) for sorting
     * Space complexity: O(n)
     */
    static List<int[]> mergeIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return Collections.emptyList();
        // Time complexity: O(NlogN)
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        System.out.println("sorted = " + Arrays.deepToString(intervals));

        List<int[]> result = new ArrayList<>();
        int[] prev = intervals[0];
        // Time: O(N)
        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if (prev[1] < curr[0]) {
                // current interval is not connected to prev, so add prev to the result.
                result.add(prev);
                prev = curr;
            } else {
                // current interval is connected to prev, so merge the two intervals
                prev[1] = Math.max(prev[1], curr[1]);
            }
        }
        result.add(prev); // add the last interval
        return result;
    }

    /**
     * Time complexity: O(nlogn) for sorting
     * Space complexity: O(1)
     */
    static void mergeIntervals_inPlace(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return;
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        int prevIndex = 0;
        // start from the next interval and merge if necessary
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[prevIndex][1] < intervals[i][0]) {
                // if current interval is not overlapping with previous interval
                prevIndex++;
                intervals[prevIndex] = intervals[i];
            } else {
                // overlapping case. merge previous and current interval
                //intervals[prevIndex].end = intervals[i].end;
                intervals[prevIndex][1] = Math.max(intervals[prevIndex][1], intervals[i][1]);
            }
        }

        // nullify the elements after the last merged element
        for (int i = prevIndex + 1; i < intervals.length; i++) {
            intervals[i] = null;
        }
        System.out.println("Merged      : " + Arrays.deepToString(intervals));
    }

}
