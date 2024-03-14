package com.hlee.scratch.array;

public class BestTimeToBuyStock {

    public static void main(String[] args) {

    }

    /**
     * We need to find out the maximum difference (which will be the maximum profit)
     * between two numbers in the given array. Also, the second number (selling price)
     * must be larger than the first one (buying price).
     *
     * In formal terms, we need to find max(prices[j] − prices[i]) for every i and j such that j > i
     *
     * Time complexity: O(n^2). Loop runs n(n - 1)/2 times.
     * Space complexity: O(1)
     */
    static int maxProfit_bf(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }

    /**
     * prices[] = {7, 1, 5, 3, 6, 4}
     */
    static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

}
