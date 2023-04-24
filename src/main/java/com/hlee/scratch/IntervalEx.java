package com.hlee.scratch;

import java.util.*;
import java.util.LinkedList;
import java.util.Stack;

public class IntervalEx {

    public static void main(String[] args) {
        //    For example, let the given set of intervals be {{1,3}, {2,4}, {5,7}, {6,8}}.
        //    The intervals {1,3} and {2,4} overlap with each other, so they should be merged and become {1, 4}.
        //    Similarly {5, 7} and {6, 8} should be merged and become {5, 8}

        test2();
//        test3();
    }

    static void test2() {
        IntervalEx[] input = {
                new IntervalEx(3, 5),
                new IntervalEx(1, 7),
                new IntervalEx(8, 12)
        };

        System.out.println("original list: " + Arrays.deepToString(input));

        mergeIntervals(input);

        mergeIntervals_improvedSpace(input);
    }

    static void test3() {
        List<IntervalEx> list = new ArrayList<>();
        list.add(new IntervalEx(1, 4));
        list.add(new IntervalEx(3, 5));

        IntervalEx[] input = {
                new IntervalEx(1, 4),
                new IntervalEx(3, 5)
        };

        System.out.println("original list: " + list);

        mergeIntervals(input);
    }

    private int start;
    private int end;

    public IntervalEx(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[").append(start);
        sb.append(", ").append(end);
        sb.append(']');
        return sb.toString();
    }

    /**
     * Time complexity: O(nlogn) for sorting
     * Space complexity: O(n)
     */
    static void mergeIntervals(IntervalEx[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return;
        }

        Arrays.sort(intervals, (o1, o2) -> o1.start - o2.start);

//        Stack<IntervalEx> stack = new Stack<>();
//        // push the first interval to stack
//        stack.push(intervals[0]);

        List<IntervalEx> result = new LinkedList<>();
        result.add(intervals[0]);

        // start from the next interval and merge if necessary
        for (int i = 1; i < intervals.length; i++) {
            // get interval from stack top
//            IntervalEx top = stack.peek();
            IntervalEx top = result.get(result.size() - 1);

            // if current interval is not overlapping with stack top
            // push it into the stack
            if (top.end < intervals[i].start) {
                //stack.push(intervals[i]);
                result.add(intervals[i]);
            } else if (top.end < intervals[i].end) {
                top.end = intervals[i].end;
                //stack.pop();
                //stack.push(top);
                result.remove(result.size() - 1);
                result.add(top);
            }
        }

        System.out.println("Merged intervals: ");
//        while (!stack.isEmpty()) {
//            IntervalEx interval = stack.pop();
//            System.out.println(interval);
//        }

        System.out.println(result);
    }

    /**
     * Time complexity: O(nlogn) for sorting
     * Space complexity: O(1)
     */
    static void mergeIntervals_improvedSpace(IntervalEx[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return;
        }

        Arrays.sort(intervals, (o1, o2) -> o1.start - o2.start);

        int prevIndex = 0;
        // start from the next interval and merge if necessary
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[prevIndex].end < intervals[i].start) {
                // if current interval is not overlapping with previous interval
                prevIndex++;
                intervals[prevIndex] = intervals[i];
            } else if (intervals[prevIndex].end < intervals[i].end){
                // overlapping case. merge previous and current interval
                intervals[prevIndex].end = intervals[i].end;
                //intervals[index].end = Math.max(intervals[index].end, intervals[i].end);
            }
        }

        // now intervals[0..index] stores the merged intervals
        System.out.println("Merged intervals: ");
        for (int i = 0; i <= prevIndex; i++) {
            System.out.print(intervals[i] + ", ");
            if (i == prevIndex) {
                System.out.println();
            }
        }

        System.out.println("Merged intervals (full list): ");
        System.out.println(Arrays.deepToString(intervals));
    }

}
