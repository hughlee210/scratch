package com.hlee.scratch.dp;

public class CoinChangeBruteForce {
    public static int coinChangeWaysBruteForce(int[] coins, int amount) {
        return coinChangeWaysHelper(coins, amount);
    }

    private static int coinChangeWaysHelper(int[] coins, int amount) {
        if (amount == 0) return 1;
        if (amount < 0) return 0;

        int ways = 0;
        for (int coin : coins) {
            ways += coinChangeWaysHelper(coins, amount - coin);
        }

        return ways;
    }

    /**
     * 5 = 1 1 1 1 1
     * 5 = 1 1 1 2
     *   = 1 1 2 1
     *   = 1 2 1 1
     *   = 2 1 1 1
     * 5 = 1 2 2
     *   = 2 1 2
     *   = 2 2 1
     * 5 = 5
     *
     */
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;
        System.out.println("Number of ways (Brute Force): " + coinChangeWaysBruteForce(coins, amount));
    }
}