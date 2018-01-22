package com.hlee.scratch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeChange {

    public static void main(String[] args) {

        List<Integer> coinList = new java.util.LinkedList<>();
        coinList.add(1);
        coinList.add(5);
        coinList.add(10);
        coinList.add(25);

        int change = 63;
        long start = System.currentTimeMillis();
        System.out.println("coinList: " + coinList + ", change: " + change);
        System.out.println("minimum number of coins = " + mc(coinList, change));
        System.out.println("call count = " + callCount);
        System.out.println("update count = " + count);
        System.out.println("finished in " + (System.currentTimeMillis() - start) + " millisec...................");
        callCount = 0;
        count = 0;

        start = System.currentTimeMillis();
        System.out.println("coinList: " + coinList + ", change: " + change);
        System.out.println("minimum number of coins = " + mc_dyn(coinList, change));
        System.out.println("call count = " + callCount);
        System.out.println("update count = " + count);
        System.out.println("changeCoinNumMap: " + changeCoinNumMap);
        System.out.println("finished in " + (System.currentTimeMillis() - start) + " millisec...................");

        change = 4;
        coinList = new java.util.LinkedList<>();
        coinList.add(1);
        coinList.add(2);
        int numWays = numOfWaysMC(4, coinList);
        System.out.format("* Number of ways to make change for %s with %s = %s", change, coinList, numWays);

    }

    static int callCount = 0;
    static int count = 0;
    static Map<Integer, Integer> changeCoinNumMap = new HashMap<>();

    /**
     * Returns minimum number of coins that make the change.
     */
    static int mc(List<Integer> coinList, int change) {
        callCount++;
        if (coinList.contains(change)) {
            return 1;
        } else {
            int minNumCoins = change;
            int numCoins = 0;
            for (int coin : coinList) {
                if (coin <= change) {
                    numCoins = 1 + mc(coinList, change - coin);
                    if (numCoins < minNumCoins) {
                        minNumCoins = numCoins;
                        count++;
                    }
                }
            }
            return minNumCoins;
        }
    }

    /**
     * Returns minimum number of coins that make the change.
     */
    static int mc_dyn(List<Integer> coinList, int change) {
        callCount++;
        int minNumCoins = change;
        if (coinList.contains(change)) {
            changeCoinNumMap.put(change, 1);
            return 1;
        } else if (changeCoinNumMap.containsKey(change)) {
            return changeCoinNumMap.get(change);
        } else {
            int numCoins = 0;
            for (int coin : coinList) {
                if (coin <= change) {
                    numCoins = 1 + mc_dyn(coinList, change - coin);
                    if (numCoins < minNumCoins) {
                        minNumCoins = numCoins;
                        changeCoinNumMap.put(change, minNumCoins);
                        count++;
                    }
                }
            }
            return minNumCoins;
        }
    }

    static int numOfWaysMC(int change, List<Integer> coins) {
        if (change == 0) {
            return 1;
        } else {
            if (change < 0 || coins.isEmpty())
                return 0;
            else {
                int branch1 = numOfWaysMC(change - coins.get(0), coins);
                coins.remove(0);
                int branch2 = numOfWaysMC(change, coins);
                return branch1 + branch2;
            }
        }
    }

}
