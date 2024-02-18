package com.hlee.scratch.dp;

import java.util.Arrays;

/**
 * subtract each coin from the amount. there are n ways to branching down from the amount.
 */
public class CoinChangeCountWays2 {

    public static void main(String[] args) {

        int[] coins = {1, 2, 5};
        int amount = 5;

//        int[] coins = {2, 3, 5};
//        int amount = 6;

        COUNT = 0;
        int numWays = countWays_bruteForce2(coins, 0, amount);
        System.out.printf("top-down recursive brute force 2: number of combinations = %d\n", numWays);
        System.out.println("call count = " + COUNT);
        System.out.println("=======================================================================");

        numWays = countWays_bruteForce3(coins, amount); // counting the same combinations with different order as well
        System.out.printf("top-down recursive brute force 3: number of combinations = %d\n", numWays);
        System.out.println("call count = " + COUNT);
        System.out.println("=======================================================================");

        numWays = coinChangeWays_memo_ai(coins, amount); // counting the same combinations with different order as well
        System.out.printf("top-down coinChangeWays_ai      : number of combinations = %d\n", numWays);
        System.out.println("call count = " + COUNT);
        System.out.println("=======================================================================");

        numWays = countWays_recursiveWithMemo(coins, amount);
        System.out.printf("top-down recursive with dp     : number of combinations = %d\n", numWays);
        System.out.println("=======================================================================");

        numWays = countWays_iterativeWithTabulation(coins, amount);
        System.out.printf("botton-up iterative with dp    : number of combinations = %d\n", numWays);
        System.out.println("=======================================================================");

        numWays = countWays1D_tabulation(coins, amount);
        System.out.printf("botton-up iterative with dp 1D array: number of combinations = %d\n", numWays);
        System.out.println("=======================================================================");

        numWays = countWays2D_tabulation(coins, amount);
        System.out.printf("botton-up iterative with dp 2D array: number of combinations = %d\n", numWays);
        System.out.println("=======================================================================");

    }

    /**
     * You are given coins of different denominations and a target amount of money.
     * Write a function to compute the number of combinations that make up that amount.
     * <p>
     * coins = {1, 2}
     * amount = 4
     * <p>
     * we are trying to find a combination of amount 4. what we will do?
     * we would do 4 minus 2, and then again minus 2, and that gives us 0
     * now we know 2 plus 2 equals 4, but since we did it backwards we got zero
     * to validate that it was a combination we could also do 4 minus 1 minus 1 minus 1 minus 1
     * to get us to 0 once we get to 0 we know we can stop. that's how we validate it.
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
     *              1    0    0   -1      0  -1    -1   -2
     *
     *           0  -1
     *
     *  4 = 1 + 1 + 1 + 1
     *  4 = 1 + 1 + 2  or 1 + 2 + 1 or 2 + 1 + 1
     *  4 = 2 + 2
     *  so, there is 3 ways to make the amount 4
     *
     * [another example]
     *
     *  coins = {1, 2, 3}
     *  amount = 3
     *                                     3
     *                          /-1        |-2        \-3
     *                      2              1             0
     *                 /-1  |-2  \-3   /-1 |-2 \-3
     *                1     0          0
     *              /-1
     *             0
     *  3 = 1 + 1 + 1
     *  3 = 1 + 2 or 2 + 1
     *  3 = 3
     *  so, there is 3 ways to make the amount 3
     *
     * Top-down recursive approach
     *
     * Time complexity: O(N^amount) where N is number of coins as for each denomination(coin), we calculate
     *     the number of ways to form all the amount.
     * Space complexity: call stack = worst case O(N+amount)
     *
     */
    static int countWays_bruteForce2(int[] coins, int startIndex, int amount) {
        //System.out.println("called coinCountWays: coins " + Arrays.toString(coins) + ", startIndex " + startIndex + ", amount " + amount);
        COUNT++;
        if (amount == 0) {
            //System.out.println("found 1 way");
            return 1; // 1 way to make 0 amount
        }
        if (amount < 0) {
            //System.out.println("found NO way");
            return 0; // no way to make the amount
        }

        int ways = 0;
        for (int i = startIndex; i < coins.length; i++) {
            ways = ways + countWays_bruteForce2(coins, i, amount - coins[i]);
            //System.out.println("ways so far = " + ways);
        }
        //System.out.println("===== returning ways " + ways);
        return ways;
    }

    static int COUNT = 0;

    // this seems like including the ways of permutation
    // meaning that it counts 1,2 and 2,1 as two ways
    public static int countWays_bruteForce3(int[] coins, int amount) {
        return coinChangeWaysHelper_bf(coins, amount);
    }

    private static int coinChangeWaysHelper_bf(int[] coins, int amount) {
        if (amount == 0) return 1;
        if (amount < 0) return 0;

        int ways = 0;
        for (int coin : coins) {
            ways += coinChangeWaysHelper_bf(coins, amount - coin);
        }
        return ways;
    }

    // this seems like including the ways of permutation
    // meaning that it counts 1,2 and 2,1 as two ways
    public static int coinChangeWays_memo_ai(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -1);
        return coinChangeWaysHelper_memo_ai(coins, amount, memo);
    }

    private static int coinChangeWaysHelper_memo_ai(int[] coins, int amount, int[] memo) {
        if (amount == 0) return 1;
        if (amount < 0) return 0;
        if (memo[amount] != -1) return memo[amount];

        int ways = 0;
        for (int coin : coins) {
            ways += coinChangeWaysHelper_memo_ai(coins, amount - coin, memo);
        }

        memo[amount] = ways;
        return ways;
    }

    public static int countWays_recursiveWithMemo(int[] coins, int amount) {
        System.out.println("Input: coins = " + Arrays.toString(coins) + ", amount = " + amount);
        int[][] dp = new int[coins.length][amount + 1];
        int result = countWaysHelper_recursiveWithMemo(coins, 0, amount, dp);
        System.out.println("DP memo array = " + Arrays.deepToString(dp));
        System.out.println("Result: dp[0][" + amount + "] = " + dp[0][amount]);
        return result;
    }

    /**
     * Top-down recursive approach with dp array
     * Time complexity: O(N * amount) where N is number of coins
     * Space complexity: O(N * amount)
     */
    static int countWaysHelper_recursiveWithMemo(int[] coins, int startIndex, int amount, int[][] dp) {
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
            ways = ways + countWaysHelper_recursiveWithMemo(coins, i, amount - coins[i], dp);
        }
        dp[startIndex][amount] = ways;
        return dp[startIndex][amount];
    }

    /**
     *  coins = {2, 3, 5}
     *  amount = 6
     *
     *  number of ways to make change
     *   j (a)  0     1     2     3     4     5     6
     * i (c) -------------------------------------------
     *   2      1     0     1     0     1     0     1
     *   3      1     0     1     0+1   1+0   0+1   1+1
     *   5      1     0     1     1     1     1+1   2+0
     *
     * if (coin value > a), then just copy the above value.
     * else, 1) exclude the coin, 2) include the coin, 3) add 1) and 2)
     *
     * Time Complexity:
     *      O(N * amount) because for each denomination, we calculate the number of ways to form
     *      all the amounts (i.e. from 1 to a given amount).
     * Space Complexity:
     *      O(amount) as extra space for storing the max possible ways for each amount that has been used.
     * !!!!!
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * NOTE: it becomes easy to understand if you step through the code line by line with the above table
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    static int countWays_iterativeWithTabulation(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // 1 way to make change 0: use 0 coin
        for (int coin : coins) {
            for (int j = 1; j < dp.length; j++) { // can use j <= amount
                // dp index j and dp value at j is the same as current amount
                if (coin <= j) {
                    dp[j] = dp[j] + dp[j - coin];
                }
            }
        }
        System.out.println("DP array = " + Arrays.toString(dp));
        return dp[dp.length - 1]; // = dp[amount]; dp value at last index
    }

    /**
     * ChatGPT
     * show me the Java solution for count ways of coin change problem using DP tabulation with
     * 1D array and 2D array. explain about the difference between using 1D array and 2D array.
     * Also, explain the algorithm, and the time and space complexity.
     * //
     * Difference between 1D and 2D Arrays:
     * 1D Array:
     *   Represents the number of ways to make change for each amount directly.
     *   More memory-efficient as only a single array is used.
     * 2D Array:
     *   Represents the number of ways to make change for each amount using the first i coins.
     *   Provides more information about the combinations of coins used.
     */
    static int countWays1D_tabulation(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // Base case: there is one way to make change for amount 0

        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }
        System.out.println("DP array = " + Arrays.toString(dp));
        return dp[amount];
    }

    /**
     * ChatGPT
     * show me the Java solution for count ways of coin change problem using DP tabulation with
     * 1D array and 2D array. explain about the difference between using 1D array and 2D array.
     * Also, explain the algorithm, and the time and space complexity.
     */
    static int countWays2D_tabulation(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1; // Base case: there is one way to make change for amount 0

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j]; // Exclude the current coin

                if (j >= coins[i - 1]) {
                    dp[i][j] += dp[i][j - coins[i - 1]]; // Include the current coin
                }
            }
        }
        System.out.println("DP array = " + Arrays.deepToString(dp));
        return dp[coins.length][amount];
    }

}
