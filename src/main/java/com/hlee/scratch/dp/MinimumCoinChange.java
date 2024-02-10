package com.hlee.scratch.dp;

import java.util.Arrays;

public class MinimumCoinChange {

    public static void main(String[] args) {

        int[] coins = {1, 2, 5};
        int amount = 11;
        minCoins_bruteForce(coins, amount);

        minCoins_withMemo(coins, amount);
    }

    static void minCoins_bruteForce(int[] coins, int amount) {
        int minCoins = minCoins(coins, amount);
        int result = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        System.out.printf("coins = %s, amount = %d\n", Arrays.toString(coins), amount);
        System.out.println("minCoins (brute force) = " + result);
    }

    /**
     * Time complexity: O(amount^N) each problem can be divided into N denominations
     * Space complexity: depth of recursion tree = O(amount)
     */
    static int minCoins(int[] coins, int amount) {
        if (amount == 0) { // base case: 0 coins needed to make amount 0
            return 0;
        }
        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= amount) {
                int subResult = minCoins(coins, amount - coin);
                if (subResult != Integer.MAX_VALUE) {
                    minCoins = Math.min(minCoins, subResult + 1); // subResult + 1 use of coin
                }
            }
        }
        return minCoins;
    }

    static void minCoins_withMemo(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -1);
        int minCoins = minCoinsDPMemo(coins, amount, memo);
        int result = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        System.out.printf("coins = %s, amount = %d\n", Arrays.toString(coins), amount);
        System.out.println("minCoins (with memoization) = " + result);
    }

    /**
     * Time complexity: O(a * n) where 'a' is the input amount
     *                           and 'n' is the number of coins
     *      - O(a * n), there are 'a' possible states with 'amount'
     *      and in each of the recursive call we iterate through all the coins
     * Space complexity: O(a) where 'a' is the input amount.
     *                   recursive call stack memory
     */
    static int minCoinsDPMemo(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            return 0;
        }
        if (memo[amount] != -1) {
            return memo[amount];
        }
        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= amount) {
                int subResult = minCoinsDPMemo(coins, amount - coin, memo);
                if (subResult != Integer.MAX_VALUE) {
                    minCoins = Math.min(minCoins, subResult + 1); // + 1 use of coin
                }
            }
        }
        // memoize the result before returning
        memo[amount] = minCoins;
        return minCoins;
    }

    /**
     * In this implementation, the minCoins function takes an array of coin
     * denominations (coins) and the target amount (amount). It uses a dynamic
     * programming approach to fill in the dp array where dp[i] represents the
     * minimum number of coins needed to make change for amount i.
     *
     * The outer loop iterates over each coin, and the inner loop updates the
     * dp array based on the current coin denomination. Finally, the result is
     * obtained from dp[amount], and -1 is returned if it's not possible to make
     * change for the given amount.
     *
     * Time Complexity:
     * The time complexity of this dynamic programming solution is O(amount * n),
     * where 'amount' is the target amount for which we want to make change,
     * and 'n' is the number of coin denominations. The nested loops iterate
     * through each coin denomination and each possible amount, filling in
     * the dynamic programming array.
     *
     * Space Complexity:
     * The space complexity is O(amount), as we use a 1D array (dp) to store
     * the minimum number of coins needed for each amount up to the target amount.
     * The space required is proportional to the target amount.
     */
    static int minCoinsDPIterative(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

}
