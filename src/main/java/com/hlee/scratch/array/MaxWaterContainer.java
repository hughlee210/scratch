package com.hlee.scratch.array;

public class MaxWaterContainer {

    public static void main(String[] args) {
        int[] heights = {7, 1, 2, 3, 9};
        System.out.println("heights: " + heights);

        int maxArea = getMaxWaterContainerArea_bruteForce(heights);
        System.out.println("maxArea: " + maxArea);

        maxArea = getMaxWaterContainerArea_optimal(heights);
        System.out.println("maxArea: " + maxArea);
    }

    // return area of max water container
    // area = height * width = min(a, b) * (bi - ai)
    // Time: O(N^2)
    // Space: O(1)
    private static int getMaxWaterContainerArea_bruteForce(int[] heights) {
        int maxArea = 0;
        for (int p1 = 0; p1 < heights.length; p1++) {
            for (int p2 = p1 + 1; p2 < heights.length; p2++) {
                int height = Math.min(heights[p1], heights[p2]);
                int width = p2 - p1;
                int area = height * width;
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    // Time: O(N)
    // Space: O(1)
    private static int getMaxWaterContainerArea_optimal(int[] heights) {
        int p1 = 0, p2 = heights.length - 1, maxArea = 0;
        while (p1 < p2) {
            int height = Math.min(heights[p1], heights[p2]);
            int width = p2 - p1;
            int area = height * width;
            maxArea = Math.max(maxArea, area);
            if (heights[p1] <= heights[p2]) {
                p1++;
            } else {
                p2--;
            }
        }
        return maxArea;
    }
}
