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
     * index   0   1   2   3     4
     *                          ____
     *                     ____| FIN
     *                ____| 5
     *           ____| 30
     *      ____| 15
     *     | 20
     */
    int minCostClimbingStairs(int[] costArr) {
        // TOP DOWN approach
        int n = costArr.length; // number of steps
        // final stair's cost is 0, so the cost to reach to the top is minimum between
        // cost to reach one step below and two steps below
        return Math.min(minCost(n - 1, costArr), minCost(n - 2, costArr));
    }

    // Time complexity: O(2^N)
    // Space complexity: O(N), not O(2^n) because call stack is used for single branch
    //      O(depth of recursion tree)
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
        int[] dp = new int[costArr.length];
        Arrays.fill(dp, -1);
        return Math.min(minCostDP(n - 1, costArr, dp), minCostDP(n - 2, costArr, dp));
    }

    // Time Complexity: O(N)
    // Space Complexity: O(N)
    int minCostDP(int i, int[] costArr, int[] dp) {
        // i: step index = index of costArr
        if (i < 0) {
            return 0;
        }
        if (i == 0 || i == 1) {
            return costArr[i];
        }
        if (dp[i] > -1) {
            return dp[i];
        }
        dp[i] = costArr[i] + Math.min(minCostDP(i - 1, costArr, dp), minCostDP(i - 2, costArr, dp));
        return dp[i];
    }

    // bottom up approach (iterative / tabulation)
    // Time complexity: O(N)
    // Space complexity: O(N)
    int minCostClimbingStairsDPIterative(int[] costArr) {
        int n = costArr.length;
        int[] dp = new int[costArr.length];
        for (int i = 0; i < n; i++) {
            if (i < 2) {
                dp[i] = costArr[i];
            } else {
                dp[i] = costArr[i] + Math.min(dp[i - 1], dp[i - 2]);
            }
        }
        return Math.min(dp[n - 1], dp[n - 2]);
    }

    // bottom up approach (iterative / tabulation)
    int minCostClimbingStairsDPIterativeOptimal(int[] cost) {
        int n = cost.length;
        int[] dp = new int[cost.length];
        // optimized: space complexity is now O(1) from O(N)
        int dpOne = cost[0];
        int dpTwo = cost[1];
        for (int i = 2; i < n; i++) {
            int current = cost[i] + Math.min(dpOne, dpTwo);
            dpOne = dpTwo;
            dpTwo = current;
        }
        return Math.min(dpOne, dpTwo);
    }
}
