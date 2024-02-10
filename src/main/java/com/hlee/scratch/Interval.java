package com.hlee.scratch;

import java.util.*;

public class Interval {

    public static void main(String[] args) {
        //    For example, let the given set of intervals be {{1,3}, {2,4}, {5,7}, {6,8}}.
        //    The intervals {1,3} and {2,4} overlap with each other, so they should be merged and become {1, 4}.
        //    Similarly, {5, 7} and {6, 8} should be merged and become {5, 8}

        test1();

        test2();

        test3();
    }

    private static void test1() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(2, 4));
        list.add(new Interval(1, 7));
        list.add(new Interval(1, 3));
        list.add(new Interval(5, 7));
        list.add(new Interval(6, 8));
        list.add(new Interval(1, 3));
        list.add(new Interval(1, 2));
        list.add(new Interval(9, 12));
        list.add(new Interval(10, 15));
        System.out.println("original list: " + list);

        List<Interval> result = merge(list);
        System.out.println("result list: " + result);

        mergeIntervals_inPlace(list.toArray(new Interval[0]));
        System.out.println("===================================");
    }

    static void test2() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(3, 5));
        list.add(new Interval(1, 7));
        list.add(new Interval(8, 12));
        System.out.println("original list: " + list);

        List<Interval> result = merge(list);
        System.out.println("result list: " + result);

        mergeIntervals_inPlace(list.toArray(new Interval[0]));
        System.out.println("===================================");
    }

    static void test3() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 4));
        list.add(new Interval(3, 5));
        System.out.println("original list: " + list);

        List<Interval> result = merge(list);
        System.out.println("result list: " + result);

        mergeIntervals_inPlace(list.toArray(new Interval[0]));
        System.out.println("===================================");
    }

    /**
     * Time complexity: O(NlogN) for sorting
     * Space complexity: O(N)
     */
    static List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty())
            return Collections.emptyList();
        // Time complexity: O(NlogN)
        intervals.sort((o1, o2) -> o1.start - o2.start);
        System.out.println("sorted list: " + intervals);

        List<Interval> result = new ArrayList<>();
        Interval prev = intervals.get(0);
        // Time: O(N)
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (prev.end < curr.start) {
                // current interval is not connected to the previous one, so add previous one to result
                result.add(prev);
                prev = curr;
            } else {
                // current interval is connected to prev, so merge to prev by updating prev.end
                prev.end = Math.max(prev.end, curr.end);
            }
        }
        result.add(prev);
        return result;
    }

    /**
     * Time complexity: O(nlogn) for sorting
     * Space complexity: O(1)
     */
    static void mergeIntervals_inPlace(Interval[] intervals) {
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
            } else {
                // overlapping case. merge previous and current interval
                //intervals[prevIndex].end = intervals[i].end;
                intervals[prevIndex].end = Math.max(intervals[prevIndex].end, intervals[i].end);
            }
        }

        System.out.print("Improved version: ");
        for (int i = 0; i < intervals.length; i++) {
            if (i <= prevIndex) {
                System.out.print(intervals[i] + ", ");
                if (i == prevIndex) {
                    System.out.println();
                }
            } else {
                // nullify the elements after last merged element
                // intervals[i] = null;
                System.out.println("***** i (" + i + ") > prevIndex (" + prevIndex + ")");
            }
        }
        System.out.println("Merged intervals          : " + Arrays.deepToString(intervals));

        // nullify the elements after merged elements
        for (int i = prevIndex + 1; i < intervals.length; i++) {
            intervals[i] = null;
        }
        System.out.println("Merged intervals with null: " + Arrays.deepToString(intervals));
    }

    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Interval))
            return false;

        Interval that = (Interval) obj;
        if (this == that)
            return true;

        return Objects.equals(start, that.start) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

}
