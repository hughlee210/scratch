package com.hlee.scratch.string;

import java.util.HashSet;
import java.util.Set;

public class NextClosestTime {

    public static void main(String[] args) {
//        String time = "19:34";
        String time = "23:59";
        String result = nextClosestTime(time);
        System.out.println("time              = " + time);
        System.out.println("next closest time = " + result);
    }

    /**
     * Time Complexity: O(1). try all 4^4 possible times and take the best one.
     * Space Complexity: O(1)
     */
    public static String nextClosestTime(String time) {
        int start = 60 * Integer.parseInt(time.substring(0, 2));
        start += Integer.parseInt(time.substring(3));
        int ans = start;
        int elapsed = 24 * 60; // minutes in 24 hours
        Set<Integer> allowed = new HashSet<>(); // allowed int digits
        for (char c : time.toCharArray()) {
            if (c != ':') {
                allowed.add(c - '0');
            }
        }
        System.out.println("allowed = " + allowed + ", start time = " + start);

        for (int h1 : allowed) {
            for (int h2 : allowed) {
                if (h1 * 10 + h2 < 24) { // check the hour part
                    for (int m1 : allowed) {
                        for (int m2 : allowed) {
                            if (m1 * 10 + m2 < 60) { // check the minute part
                                int cur = 60 * (h1 * 10 + h2) + (m1 * 10 + m2);
                                int candElapsed = Math.floorMod(cur - start, 24 * 60); // = Math.floorMod(cur - start, 24 * 60);
                                if (0 < candElapsed && candElapsed < elapsed) {
                                    ans = cur; // update the answer with current time
                                    elapsed = candElapsed; // update elapsed time
                                }
                            }
                        }
                    }
                }
            }
        }
        return String.format("%02d:%02d", ans / 60, ans % 60);
    }
}
