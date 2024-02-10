package com.hlee.scratch.twodimensionarray;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

    static int[][] DIRECTIONS = {
            {-1, 0}, // up
            {0, 1},  // right
            {1, 0},  // down
            {0, -1}, // left
    };

    /**
     * row  col  0  1  2  3  4
     * 0      [[ 1, 1, 1, 1, 0],
     * 1       [ 1, 1, 0, 1, 0],
     * 2       [ 1, 1, 0, 0, 1],
     * 3       [ 0, 0, 0, 1, 1]]
     *    there are two islands in the above case.
     *    lands are only connected horizontally or vertically
     *    0: water, 1: land
     *
     *    logic:
     *    1. search sequentially from top to bottom, left to right
     *      increment counter when value is 1 (land)
     *    2. from that position do BFS/DFS for adjacent lands (value: 1)
     *      flip their values to 0 so that connected lands are not counted again
     *      during next sequential search
     */

    public static void main(String[] args) {
        int[][] inputMatrix = {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 1, 1},
        };
        print(inputMatrix);

        int islandCount = getNumberOfIslandsBFS(inputMatrix);
        System.out.println("Number of islands (BFS) = " + islandCount);

        int[][] inputMatrix2 = {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 1, 1},
        };
        islandCount = getNumberOfIslandsDFS(inputMatrix2);
        System.out.println("Number of islands (DFS) = " + islandCount);
    }

    /**
     * Time complexity: O(N) where N is number of elements in the matrix = O(m x n)
     * O(N) for sequential search
     * O(N) for bfs to visit all 1's in case the matrix is all 1's.
     * so it's O(2N) and drop constant => O(N)
     * Space complexity: O(queue size) = O(max(m, n))
     */
    static int getNumberOfIslandsBFS(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int islandCount = 0;
        // do sequential search to check if there is a new island
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 1) {
                    islandCount++;
                    // flip to 0 because we don't want to add it to queue again while doing BFS
                    // also, to avoid increment the counter while continuing sequential search.
                    // and add it to queue to check its adjacent cells and if it's land flip to 0
                    bfs(matrix, row, col);
                }
            }
        }
        return islandCount;
    }

    private static void bfs(int[][] matrix, int row, int col) {
        if (matrix[row][col] == 1) {
            matrix[row][col] = 0;
            Integer[] startingPos = {row, col};
            Queue<Integer[]> queue = new LinkedList<>();
            queue.add(startingPos);
            while (!queue.isEmpty()) {
                Integer[] currPos = queue.poll();
                int currRow = currPos[0];
                int currCol = currPos[1];
                // will add adjacent positions to the queue if it's land
                for (int i = 0; i < DIRECTIONS.length; i++) {
                    int[] currDir = DIRECTIONS[i];
                    int nextRow = currRow + currDir[0];
                    int nextCol = currCol + currDir[1];
                    // bound check
                    if (nextRow < 0 || nextRow >= matrix.length || nextCol < 0 || nextCol >= matrix[0].length) {
                        continue;
                    }
                    if (matrix[nextRow][nextCol] == 1) {
                        matrix[nextRow][nextCol] = 0; // flip to 0
                        Integer[] nextPos = {nextRow, nextCol};
                        queue.add(nextPos);
                    }
                }
            }
        }
    }

    static int getNumberOfIslandsDFS(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int islandCount = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 1) {
                    islandCount++;
                    dfs(matrix, row, col);
                }
            }
        }
        return islandCount;
    }

    private static void dfs(int[][] matrix, int row, int col) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return;
        }
        if (matrix[row][col] == 1) {
            matrix[row][col] = 0;
            for (int i = 0; i < DIRECTIONS.length; i++) {
                int[] currDir = DIRECTIONS[i];
                dfs(matrix, row + currDir[0], col + currDir[1]);
            }
        }
    }

    static void print(int[][] matrix) {
        System.out.println("matrix: ");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + ", ");
            }
            System.out.println();
        }
    }

}
