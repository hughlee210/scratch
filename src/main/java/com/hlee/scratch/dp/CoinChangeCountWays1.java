package com.hlee.scratch.dp;

import java.util.Arrays;

/**
 * using two options for each coin:
 * 1. use the coin and decrease the amount
 * 2. skip the coin and move to the next coin with the same amount
 */
public class CoinChangeCountWays1 {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;

//        int[] coins = {2, 3, 5};
//        int amount = 6;

        COUNT = 0;
        int ways = countWays_bruteForce1(coins, 0, amount);
        System.out.printf("top-down recursive brute force 1: number of combinations = %d\n", ways);
        System.out.println("call count = " + COUNT);
        System.out.println("=======================================================================");

        int numCombos = countWays_recursiveWithMemo(coins, amount);
        System.out.printf("top-down recursive with memo   : number of combinations = %d\n", numCombos);
        System.out.println("=======================================================================");

        numCombos = countWays_recursiveWithMemo_2(coins, amount);
        System.out.printf("top-down recursive with memo 2 : number of combinations = %d\n", numCombos);
        System.out.println("=======================================================================");

        numCombos = countWays_iterativeWithTabulation(coins, amount);
        System.out.printf("botton-up iterative with dp    : number of combinations = %d\n", numCombos);
        System.out.println("=======================================================================");
    }

    /**
     * Given an infinite supply of coins of different coins,
     * find the number of ways to make change for a value 'amount'
     *  coins = {2, 3, 5},  amount = 7,  ways: {2, 2, 3}, {2, 5}
     *
     *  There are two options: coin selected or not selected
     *  [Option 1] - pick a coin,    [Option 2] - discard a coin
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
     *   Time complexity: of this brute force solution is exponential. This is because,
     *   for each coin, we have two recursive calls, leading to a binary tree of recursive
     *   calls. The height of the tree is equal to the number of coins, and the branching factor
     *   is 2. Therefore, the time complexity is O(2^N), where N is the number of coins.
     *
     *   Space complexity: is determined by the maximum depth of the recursion stack, which
     *   is equal to the number of coins. In worst case, the space complexity is O(N), where
     *   N is the number of coins.
     */
    private static int countWays_bruteForce1(int[] coins, int i, int amount) {
        //System.out.println("called countWays_bruteForce: coins " + Arrays.toString(coins) + ", coin index " + i + ", amount " + amount);
        COUNT++;
        // base case for amount == 0, or amount < 0, or coin index == number of coin (there is no more coin)
        if (amount == 0) {
            return 1; // 1 way
        }
        if (amount < 0 || i == coins.length) {
            return 0; // no ways
        }

        int result = countWays_bruteForce1(coins, i, amount - coins[i]) // option 1 (pick coin to use coin)
                + countWays_bruteForce1(coins, i + 1, amount); // option 2 (discard coin to not use coin)
        //System.out.println("===== Returning countWays_bruteForce: result = " + result);
        return result;
    }

    static int COUNT = 0;

    /**
     * [Explanation of the Algorithm]:
     * 1. The countWays method initializes a memoization table (memo) with dimensions coins.length x (amount + 1).
     *    It then calls the helper method countWaysHelper to perform the actual computation.
     * 2. The countWaysHelper method is a recursive function that explores two options at each step:
     *  Option 1: Use the current coin (coins[currentIndex]) if it does not exceed the remaining amount.
     *  Option 2: Skip the current coin and move to the next one (currentIndex + 1).
     * 3. The base cases handle situations where the remaining amount becomes zero (indicating a successful combination)
     *    or when there are no more coins to consider.
     * 4. Memoization is used to store and retrieve previously computed results. Before computing the result
     *    for a specific combination of parameters, the method checks if the result is already memoized. If so,
     *    it returns the memoized result, avoiding redundant calculations.
     * <br><br>
     * - Time Complexity:
     *      The time complexity is O(n * m), where n is the number of coins and m is the target amount.
     *      This is because each cell in the memoization table is computed once, and there are n * m cells.
     * - Space Complexity:
     *      The space complexity is also O(n * m) due to the memoization table. Additionally, the recursive call
     *      stack contributes to the space complexity, but memoization helps avoid redundant recursive calls.
     *      In summary, the algorithm uses dynamic programming with memoization to efficiently count the number
     *      of ways to make change, considering the options of using or skipping each coin at each step.
     */
    public static int countWays_recursiveWithMemo(int[] coins, int amount) {
        int[][] memo = new int[coins.length][amount + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return countWaysHelper_memo(coins, 0, amount, memo);
    }

    private static int countWaysHelper_memo(int[] coins, int i, int remainingAmount, int[][] memo) {
        // Base cases
        if (remainingAmount == 0) {
            return 1; // One way to make change (no remaining amount)
        }
        if (i == coins.length) { // i = current index of coins array
            return 0; // No more coins to consider
        }
        // Check if the result is already memoized
        if (memo[i][remainingAmount] != -1) {
            return memo[i][remainingAmount];
        }

        // Option 1: Use the current coin
        int waysWithCurrentCoin = 0;
        if (coins[i] <= remainingAmount) {
            waysWithCurrentCoin = countWaysHelper_memo(coins, i, remainingAmount - coins[i], memo);
        }

        // Option 2: Skip the current coin
        int waysWithoutCurrentCoin = countWaysHelper_memo(coins, i + 1, remainingAmount, memo);

        // Total ways by combining both options
        int totalWays = waysWithCurrentCoin + waysWithoutCurrentCoin;

        // Memoize the result
        memo[i][remainingAmount] = totalWays;

        return totalWays;
    }

    public static int countWays_recursiveWithMemo_2(int[] coins, int amount) {
        int[][] memo = new int[coins.length][amount + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return countWaysHelper_memo_2(coins, 0, amount, memo);
    }

    // similar to above, less line of code
    private static int countWaysHelper_memo_2(int[] coins, int index, int amount, int[][] memo) {
        // Base cases
        if (amount == 0) return 1;
        if (amount < 0 || index == coins.length) return 0;

        // Check if the result is already memoized
        if (memo[index][amount] != -1) return memo[index][amount];

        // Include the coin + Exclude the coin
        int include = countWaysHelper_memo_2(coins, index, amount - coins[index], memo);
        int exclude = countWaysHelper_memo_2(coins, index + 1, amount, memo);

        // Memoize the result
        memo[index][amount] = include + exclude;
        return memo[index][amount];
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

}
