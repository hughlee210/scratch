package com.hlee.scratch.dp;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 */
public class ClimbingStairs {

    public int climbStairs(int n) {
        return climbStairs(0, n);
    }

    /**
     * Time complexity: O(2^n) size of recursion tree will be 2^n.
     * Space complexity: O(n) the depth of the recursion tree can go upto n.
     */
    private int climbStairs(int i, int n) {
        if (i == n) {
            return 1;
        }
        if (i > n) {
            return 0;
        }
        return climbStairs(i + 1, n) + climbStairs(i + 2, n);
    }

    public int climbStairs_memo(int n) {
        int[] memo = new int[n + 1];
        return climbStairs_memo(0, n, memo);
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    private int climbStairs_memo(int i, int n, int[] memo) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climbStairs_memo(i + 1, n, memo) + climbStairs_memo(i + 2, n, memo);
        return memo[i];
    }

    /**
     * Time complexity: O(n) single loop up to n
     * Space complexity: O(n) dp array of size n
     */
    public int climbStairs_dp(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public int climbStairs_dpOptimized(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = second + first;
            first = second;
            second = third;
        }
        return second; // or third; they are the same
    }

}
