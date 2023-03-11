package com.hlee.scratch.dp;

import java.util.Arrays;

public class MinimumCostOfClimbingStairs {

    public static void main(String[] args) {

    }

    /**
     * For a given staircase, the i-th step is assigned a non-negative cost
     * indicated by a cost array.
     * Once you pay the cost for a step, you can either climb one or two steps.
     * Find the minimum cost to reach the top of the staircase. Your first step
     * can either be the first or second step.
     * <p>
     * cost = [20, 15, 30, 5] and Final step (cost = 0)
     * index  0   1   2   3       4
     *                     ____
     *                ____| FIN
     *           ____| 5
     *      ____| 30
     * ____| 15
     * | 20
     */
    int minCostClimbingStairs(int[] costArr) {
        int n = costArr.length; // number of steps
        // final stair's cost is 0, so the cost to reach to the top is minimum between
        // cost to reach one step below and two steps below
        return Math.min(minCost(n - 1, costArr), minCost(n - 2, costArr));
    }

    // Time complexity: O(2^N)
    // Space complexity: O(N)
    int minCost(int i, int[] costArr) {
        // i: step index
        if (i < 0) {
            return 0;
        }
        if (i == 0 || i == 1) {
            return costArr[i];
        }
        return costArr[i] + Math.min(minCost(i - 1, costArr), minCost(i - 2, costArr));
    }

    int minCostClimbingStairsDP(int[] costArr) {
        int n = costArr.length; // number of steps
        int[] dpCache = new int[costArr.length];
        Arrays.fill(dpCache, -1);
        return Math.min(minCostDP(n - 1, costArr, dpCache), minCostDP(n - 2, costArr, dpCache));
    }

    // Time Complexity: O(N)
    // Space Complexity: O(N)
    int minCostDP(int i, int[] costArr, int[] dpCache) {
        // i: step index = index of costArr
        if (i < 0) {
            return 0;
        }
        if (i == 0 || i == 1) {
            return costArr[i];
        }
        if (dpCache[i] > -1) {
            return dpCache[i];
        }
        dpCache[i] = costArr[i] + Math.min(minCostDP(i - 1, costArr, dpCache), minCostDP(i - 2, costArr, dpCache));
        return dpCache[i];
    }

    // bottom up approach
    int minCostClimbingStairsDPIterative(int[] costArr) {
        int n = costArr.length;
//        int[] dpCache = new int[costArr.length];
//        for (int i = 0; i < n; i++) {
//            if (i < 2) {
//                dpCache[i] = costArr[i];
//            } else {
//                dpCache[i] = costArr[i] + Math.min(dpCache[i - 1], dpCache[i - 2]);
//            }
//        }
//        return Math.min(dpCache[n - 1], dpCache[n - 2]);

        // optimized: space complexity is now O(1) from O(N)
        int dpOne = costArr[0];
        int dpTwo = costArr[1];
        for (int i = 2; i < n; i++) {
            int current = costArr[i] + Math.min(dpOne, dpTwo);
            dpOne = dpTwo;
            dpTwo = current;
        }
        return Math.min(dpOne, dpTwo);
    }
}
