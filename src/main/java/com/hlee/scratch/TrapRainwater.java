package com.hlee.scratch;

import java.util.Arrays;

public class TrapRainwater {

    public static void main(String[] args) {
        int[] heights = {0, 1, 0, 2, 1, 0, 3, 1, 0, 1, 2};
        int totalWater = getTrappedRainwater_bruteForce(heights);
        System.out.println("heights: " + Arrays.toString(heights));
        System.out.println("using brute force: totalWater = " + totalWater);

        totalWater = getTrappedRainwater_optimal(heights);
        System.out.println("using optimal: totalWater = " + totalWater);
    }

    // Time complexity: O(N^2)
    // Space complexity: O(1)
    static int getTrappedRainwater_bruteForce(int[] heights) {
        int totalWater = 0;
        for (int p = 0; p < heights.length; p++) {
            int leftP = p, rightP = p, maxLeft = 0, maxRight = 0;
            while (leftP >= 0) {
                maxLeft = Math.max(maxLeft, heights[leftP]);
                leftP--;
            }
            while (rightP < heights.length) {
                maxRight = Math.max(maxRight, heights[rightP]);
                rightP++;
            }
            int currentWater = Math.min(maxLeft, maxRight) - heights[p];
            if (currentWater >= 0) {
                totalWater += currentWater;
            }
        }
        return totalWater;
    }

    // 1. Identify pointer with lesser value
    // 2. Is this pointer value greater than or equal to max on that side
    //    if yes -> update max on that side
    //    if no  -> get water for pointer value, add to total
    // 3. move pointer inwards
    // 4. repeat for other pointer
    //
    // Time complexity: O(N)
    // Space complexity: O(1)
    // int[] heights = {0, 1, 0, 2, 1, 0, 3, 1, 0, 1, 2};
    static int getTrappedRainwater_optimal(int[] heights) {
        int totalWater = 0;
        int leftP = 0, rightP = heights.length - 1, maxLeft = 0, maxRight = 0;
        while (leftP < rightP) {
            if (heights[leftP] <= heights[rightP]) {
                if (heights[leftP] >= maxLeft) {
                    maxLeft = heights[leftP];
                } else {
                    totalWater += maxLeft - heights[leftP];
                }
                leftP++;
            } else {
                if (heights[rightP] >= maxRight) {
                    maxRight = heights[rightP];
                } else {
                    totalWater += maxRight - heights[rightP];
                }
                rightP--;
            }
        }
        return totalWater;
    }
}
