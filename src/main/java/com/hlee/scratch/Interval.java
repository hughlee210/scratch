package com.hlee.scratch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    public static void main(String[] args) {
        //        For example, let the given set of intervals be {{1,3}, {2,4}, {5,7}, {6,8} }. 
        //        The intervals {1,3} and {2,4} overlap with each other, so they should be merged and become {1, 4}. 
        //        Similarly {5, 7} and {6, 8} should be merged and become {5, 8}

        List<Interval> list = new ArrayList<>();
        list.add(new Interval(2, 4));
        list.add(new Interval(1, 3));
        list.add(new Interval(5, 7));
        list.add(new Interval(6, 8));
        //System.out.println("original list: " + list);

        mergeIntervals(list);

    }

    static void mergeIntervals(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start.compareTo(o2.start);
            }
        });
        System.out.println("sorted list: " + intervals);

        List<Interval> output = new ArrayList<>();
        for (int i = 0; i < intervals.size(); i++) {
            Interval in1 = intervals.get(i);
            for (int j = i + 1; j < intervals.size(); j++) {
                Interval in2 = intervals.get(j);
                // check if they are overlapping
                if (in1.end >= in2.start) {
                    // overlapping
                    Interval mergedIn;
                    if (in1.end < in2.end) {
                        mergedIn = new Interval(in1.start, in2.end);
                    } else {
                        mergedIn = new Interval(in1.start, in1.end);
                    }
                    output.add(mergedIn);
                } else {
                    // not overlapping, so add in1 and in2 to output
                    output.add(in1);
                    output.add(in2);
                }
            }
        }

    }
}
