package com.hlee.scratch.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinChangeMinimumNumber {

    /**
     *  Constraints:
     *     -  1 <= coins.length <= 12
     *     -  1 <= coins[i] <= 2^31 - 1
     *     -  0 <= amount <= 10^4
     * <p>
     * Thoughts Before Coding
     *     - For each of the 'amount'
     *         - We have up to 'n' choices, where 'n' is the number of coin denominators
     *         - If we pick a coin 'c', where 'c <= amount'
     *             - Then we need to find the minimum number of coins 'x' for 'amount - c'
     *             - Then we can increment 'x' by 1 to account for the selected coin 'c'
     *         - We will want to pick the value 'c' that will give us the minimum number
     *           of coins
     * <p>
     * Answer
     *     - Implement the recursive approach
     *         - What parameter do we need?
     *             - coins, input coins
     *             - amount
     *         - What is the base case?
     *             - If 'amount' is equal to 0
     *                 - Return 0
     *         - In each of the recursive call
     *             - Create a variable 'minCoins' to keep track of the minimum number of
     *               coins for 'amount', initially '10000 + 1'
     *             - Iterate through the 'coins', denoted as 'c'
     *                 - If 'c' is greater than 'amount'
     *                     - continue to next iteration
     *                 - Recursively find the number of coins 'x' if we pick 'c'
     *                     - amount => amount - c
     *                 - Update 'minCoins' if 'x + 1' is smaller
     *         - Return 'minCoins'
     *     - If the recursive result is less than or equal to '10000' (Max number)
     *         - Return 'minCoins'
     *     - Else
     *         - Return -1
     * <p>
     * Brute force solution:
     *  Time complexity: O(n^a), where 'n' is the length of the input array 'coins' and 'a' is the input amount.
     *     - O(n^a), each recursive call, we have up to 'n' choices and a depth of 'a'
     * Space complexity: O(a) where 'a' is the input amount. recursive call stack memory for the depth 'a' of tree
     * <p>
     * DP solution:
     * Time Complexity: O(N * amount) as for each denomination, we calculate the number of ways to form all the amounts
     *  (i.e. from 1 to a given amount).
     * Space Complexity: O(amount) as extra space for storing the max possible ways for each amount that has been used.
     */

    public static void main(String[] args) {

        int[] coins = {1, 2, 5};
        int amount = 11;

        coinChange_bruteForce(coins, amount);

        coinChange_dp(coins, amount);

        testMinCoins_greedy(coins, amount);

        System.out.println("====================================");

        int[] coins2 = {1, 5, 6, 9};
        int amount2 = 11;

        coinChange_bruteForce(coins2, amount2);

        coinChange_dp(coins2, amount2);

        testMinCoins_greedy(coins2, amount2);

    }

    private static final int MAX_AMOUNT = 10000;
    static int counter = 0;

    static int coinChange_bruteForce(int[] coins, int amount) {
        counter = 0;
        int minCoins = minCoins(coins, amount);
        int result = minCoins < Integer.MAX_VALUE ? minCoins : -1;

        System.out.printf("- brute force: coins: %s, amount: %d, min number of coins = %d\n",
                Arrays.toString(coins), amount, result);
        System.out.println("call count: " + counter);

        return result;
    }

    /**
     * Minimum number of coins for 'amount' can be computed using the below
     * recursive formula
     *
     * If 'amount' == 0, then 0 coins required.
     * If 'amount' > 0,
     *      minCoins(coins[0..m-1], amount) = min {1 + minCoins(amount - coins[i]}
     *                                          where i varies from 0 to m-1
     *                                          and coins[i] <= amount
     *
     * Time complexity: O(n^a), where 'n' is the length of the input array 'coins'
     *                  and 'a' is the input amount.
     *          - O(n^a), each recursive call, we have up to 'n' choices and a depth of 'a'
     * Space complexity: O(a) where 'a' is the input amount.
     *                  recursive call stack memory for the depth 'a' of tree
     */
    static int minCoins(int[] coins, int amount) {
        if (amount == 0) { // base case: 0 coins needed to make amount 0
            return 0;
        }
        int minCoins = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            // try every coin that is smaller than or equal to 'amount'
            if (coins[i] <= amount) {
                int subResult = minCoins(coins, amount - coins[i]); // amount - every coin
                if (subResult != Integer.MAX_VALUE && (1 + subResult) < minCoins) {
                    minCoins = subResult + 1;
                }
            }
        }
        return minCoins;
    }

    private static int coinChange_dp(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        int minCoins = minCoinsDP(coins, amount, memo);
        int result = minCoins < Integer.MAX_VALUE ? minCoins : -1;

        System.out.printf("- Using DP   : coins: %s, amount: %d, min number of coins = %d\n",
                Arrays.toString(coins), amount, result);
        System.out.println("call count: " + counter);

        return result;
    }

    /**
     * Time complexity: O(a * n) where 'a' is the input amount
     *                           and 'n' is the number of coins
     *      - O(a * n), there are 'a' possible states with 'amount'
     *      and in each of the recursive call we iterate through all the coins
     * Space complexity: O(a) where 'a' is the input amount.
     *                   recursive call stack memory
     */
    static int minCoinsDP(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            return 0;
        }
        if (memo[amount] != 0) {
            return memo[amount];
        }
        int minCoins = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= amount) {
                int subResult = minCoinsDP(coins, amount - coins[i], memo);
                if (subResult != Integer.MAX_VALUE && 1 + subResult < minCoins) {
                    minCoins = subResult + 1;
                    memo[amount] = minCoins;
                }
            }
        }
        return minCoins;
    }

    /**
     * 1. Sort the array of coins.
     * 2. Initialize ans as empty
     * 3. Find the largest denomination that is smaller than remaining amount and
     *    while it is smaller than the remaining amount:
     *    - Add found denomination to ans.
     *    - Subtract value of found denomination from amount
     * 4. If amount becomes 0, then print ans.
     *
     * this greedy approach does not work for all denominations meaning
     * it does not always find the minimum number.
     */
    static int findMinCoins_greedy(int[] coins, int amount) {
        // assume coins is sorted in ascending order
        List<Integer> ans = new ArrayList<>();
        // iterate through all coins
        for (int i = coins.length - 1; i >= 0; i--) {
            // find coins and add to ans
            while (coins[i] <= amount) {
                amount = amount - coins[i];
                ans.add(coins[i]);
            }
        }
        // print result
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(" " + ans.get(i));
        }
        System.out.println();
        return ans.size();
    }

    static void testMinCoins_greedy(int[] coins, int amount) {
        counter = 0;
        int minCoins = findMinCoins_greedy(coins, amount);
        System.out.printf("- Using greedy   : coins: %s, amount: %d, min number of coins = %d\n", Arrays.toString(coins), amount, minCoins);
        System.out.println("call count: " + counter);
    }
}
