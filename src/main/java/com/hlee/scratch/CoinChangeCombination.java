package com.hlee.scratch;

public class CoinChangeCombination {

    public static void main(String[] args) {
//        int[] coins = {1, 2, 3, 5, 10, 20, 50, 100, 1000};
//        int amount = 283;

        int[] coins = {1, 2, 3};
        int amount = 3;

        long start = System.currentTimeMillis();
        int numCombos = coinCombos(coins, 0, amount);
        long end = System.currentTimeMillis();
        System.out.printf("brute force top-down recursive: time taken = %d milliseconds, number of combinations = %d\n", (end - start), numCombos);

        // form dp array
        int[][] dp = new int[coins.length][amount + 1];
        start = System.currentTimeMillis();
        numCombos = coinCombos_dp_recur(coins, 0, amount, dp);
        end = System.currentTimeMillis();
        System.out.printf("top-down recursive with dp    : time taken = %d milliseconds, number of combinations = %d\n", (end - start), numCombos);

        start = System.currentTimeMillis();
        numCombos = coinCombos_dp_iterative(coins, amount);
        end = System.currentTimeMillis();
        System.out.printf("botton-up iterative with dp   : time taken = %d milliseconds, number of combinations = %d\n", (end - start), numCombos);

        start = System.currentTimeMillis();
        int ways = countWaysRecursive(coins, 0, amount);
        end = System.currentTimeMillis();
        System.out.printf("top-down recursive            : time taken = %d milliseconds, number of combinations = %d\n", (end - start), ways);

    }

    /**
     * Given an infinite supply of coins of different coins,
     * find the number of ways to make change for a value 'amount'
     *
     *  coins = {2, 3, 5}
     *  amount = 7      {2, 2, 3}, {2, 5}
     *
     *  There are two options: coin selected or not selected
     *      Option 1 - pick a coin
     *      Option 2 - discard a coin
     *
     *                                               7 {2,3,5}
     *                                   / (pick)                     \ (discard)
     *                             5 {2,3,5}                           7 {3,5}
     *                      /                  \                     /           \
     *                3 {2,3,5}               5 {3,5}           4 {3,5}          7 {5}
     *                  /       \            /       \             /   \         /    \
     *            1 {2,3,5}   3 {3,5}    2 {3,5}    5 {5}     1 {3,5}  4 {5}   2 {5}  7 {}
     *            /    \       /  \       /         /  \
     *   -1 {2,3,5}  1 {3,5}  0  3 {5}  -1         0
     *
     */
    private static int countWaysRecursive(int[] coins, int startIndex, int amount) {
        // base case for n == 0 and n < 0
        if (amount == 0) {
            return 1; // 1 way
        }
        if (amount < 0) {
            return 0; // no ways
        }
        if (startIndex == coins.length) {
            return 0;
        }

        return countWaysRecursive(coins, startIndex, amount - coins[startIndex]) // option 1 (pick coin)
                + countWaysRecursive(coins, startIndex + 1, amount); // option 2 (discard coin)
    }

    /**
     * You are given coins of different denominations and a total of money.
     * Write a function to compute the number of combinations that make up that amount.
     * <p>
     * coins = {1, 2}
     * amount = 4
     * <p>
     * we are trying to find a combination of 4. what we will do?
     * we would do 4 minus 2 and then again minus 2 and that gives us 0
     * now we know 2 plus 2 equals 4, but since we did it backwards we got zero
     * to validate that it was a combination we could also do 4 minus 1 minus 1 minus 1 minus 1
     * to get us to 0 ance we get to 0 we know we can stop. that's how we validate it.
     * that sounds like a base case to me recursively.
     * coins = {1, 2}
     * amount = 4
     * 4-1 = 3
     * 3-1 = 2
     * 2-1 = 1 ->
     * 1-1 = 0 -> 1
     *
     *                                4
     *
     *                     3                     2
     *
     *                 2        1           1        0
     *
     *              1    0    0         0
     *
     *
     * Top-down recursive approach
     *
     * Time complexity: O(N^amount) where N is number of coins as for each denomination(coin), we calculate
     *     the number of ways to form all the amount.
     * Space complexity: call stack
     *
     *  coins = {1, 2, 3}
     *  amount = 3
     *                                     3
     *                       /-1           |-2        \-3
     *                      2              1             0
     *                 /-1  |-2  \-3   /-1 |-2 \-3
     *                1     0          0
     */
    static int coinCombos(int[] coins, int startIndex, int amount) {
        if (amount == 0) {
            return 1; // we have combination
        }
        if (amount < 0) {
            return 0; // no combination
        }

        int ways = 0;
        for (int i = startIndex; i < coins.length; i++) {
            ways = ways + coinCombos(coins, i,amount - coins[i]);
        }
        return ways;
    }

    /**
     * Top-down recursive approach with dp array
     * Time complexity: O(N * amount) where N is number of coins
     * Space complexity: O(N * amount)
     */
    static int coinCombos_dp_recur(int[] coins, int startIndex, int amount, int[][] dp) {
        if (amount == 0) {
            return 1; // we have combination
        }
        if (amount < 0) {
            return 0; // no combination
        }

        // lookup in dp array
        if (dp[startIndex][amount] != 0) {
            return dp[startIndex][amount];
        }

        int ways = 0;
        for (int i = startIndex; i < coins.length; i++) {
            ways = ways + coinCombos_dp_recur(coins, i, amount - coins[i], dp);
        }
        dp[startIndex][amount] = ways;
        return dp[startIndex][amount];
    }

    /**
     *  coins = {2, 3, 5}
     *  amount = 8
     *
     *  number of ways to make change
     *   j (a)  0     1     2     3     4     5     6     7     8
     * i (c) -----------------------------------------------
     *   2      1     0     1     0     1     0     1     0     1
     *   3      1     0     1     0+1   1+0   0+1   1+1   0+1   1+1
     *   5      1     0     1     1     1     1+1   2+0   1+1   2+1
     *
     * if (coin value > a), then just copy the above value.
     * else, 1) exclude the coin, 2) include the coin, 3) add 1) and 2)
     *
     * Time Complexity: O(N * amount) as for each denomination, we calculate the number of ways to form all the amounts
     *      (i.e. from 1 to a given amount).
     * Space Complexity: O(amount) as extra space for storing the max possible ways for each amount that has been used.
     */
    static int coinCombos_dp_iterative(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // 1 way to make change 0: use 0 coin
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j < dp.length; j++) {
                if (coins[i] <= j) {
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
        }
        return dp[dp.length - 1];
    }

}
