package com.hlee.scratch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Interval {

    Integer start;
    Integer end;

    public Interval(Integer start, Integer end) {
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

    public static void main(String[] args) {
        //    For example, let the given set of intervals be {{1,3}, {2,4}, {5,7}, {6,8}}.
        //    The intervals {1,3} and {2,4} overlap with each other, so they should be merged and become {1, 4}.
        //    Similarly {5, 7} and {6, 8} should be merged and become {5, 8}

//        test1();

//        test2();

        test3();
    }

    private static void test1() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(2, 4));
        list.add(new Interval(1, 3));
        list.add(new Interval(5, 7));
        list.add(new Interval(6, 8));
        list.add(new Interval(1, 3));
        list.add(new Interval(9, 12));
        list.add(new Interval(10, 15));
        System.out.println("original list: " + list);

        List<Interval> result = merge(list);
        System.out.println("result list: " + result);
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
        System.out.println("===================================");
    }

    static void test3() {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 4));
        list.add(new Interval(3, 5));
        System.out.println("original list: " + list);

        List<Interval> result = merge(list);
        System.out.println("result list: " + result);
        System.out.println("===================================");
    }

    static List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        if (intervals == null || intervals.size() == 0)
            return result;

        // Time complexity: O(NlogN)
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                if (i1.start != i2.start)
                    return i1.start - i2.start;
                else
                    return i1.end - i2.end;
            }
        });
        System.out.println("sorted list: " + intervals);

        Interval prev = intervals.get(0);
        // Time: O(N)
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (curr.start > prev.end) {
                // current interval is not connected to the previous one, so add previous one to result
                result.add(prev);
                System.out.println("***adding " + prev);
                prev = curr;
            } else {
                Interval merged = new Interval(prev.start, Math.max(prev.end, curr.end));
                prev = merged;
                System.out.println("***merged: " + merged);
            }
        }
        result.add(prev);
        System.out.println("***last adding " + prev);
        return result;
    }
}
