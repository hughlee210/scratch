package com.hlee.scratch.twodimensionarray;

import java.util.Arrays;

public class FloodFill {

    public static void main(String[] args) {
        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        System.out.println("input = \n" + Arrays.deepToString(image));

        floodFill(image, 1, 1, 2);

        System.out.println("output = \n" + Arrays.deepToString(image));
    }

    static int[][] DIRECTIONS = {
            {-1, 0}, // UP
            {0, 1},  // RIGHT
            {1, 0},  // DOWN
            {0, -1}  // LEFT
    };
    static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if (color != newColor) {
            dfs(image, sr, sc, color, newColor);
        }
        return image;
    }

    /**
     * Time complexity: O(n) where n is the number of cells. might need to visit every cell
     * Space complexity: O(n) for call stack size
     *
     */
    static void dfs(int[][] image, int r, int c, int color, int newColor) {
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length) {
            return;
        }
        if (image[r][c] == color) {
            image[r][c] = newColor;
            for (int[] dir : DIRECTIONS) {
                dfs(image, r + dir[0], c + dir[1], color, newColor);
            }
        }
    }
}
