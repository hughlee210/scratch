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
     *    logic: search sequentially from top to bottom, left to right
     *    increment counter when value is 1
     *    from that position do dfs/bfs for adjacent lands (value: 1)
     *    flip their values to 0 so that connected lands are not counted again
     *    during next sequential search
     */

    public static void main(String[] args) {
        int[][] inputMatrix = {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 1, 1},
        };
        print(inputMatrix);

        NumberOfIslands theObj = new NumberOfIslands();
        int numberOfIslands = theObj.getNumberOfIslands(inputMatrix);
        System.out.println(String.format("number of islands: %s", numberOfIslands));

        int[][] inputMatrix2 = {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 1, 1},
        };
        numberOfIslands = theObj.findNumberOfIslands(inputMatrix2);
        System.out.println(String.format("find number of islands: %s", numberOfIslands));
    }

    int getNumberOfIslands(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int islandCount = 0;
        // do sequential search to check if there is a new island
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 1) {
                    islandCount++;
                    Queue<Integer[]> queue = new LinkedList<>();
                    Integer[] startPos = {row, col};
                    queue.add(startPos);
                    // flip to 0 because we don't want to add it to queue again while doing BFS
                    // also, to avoid increment the counter while continuing sequential search
                    matrix[row][col] = 0;
                    while (queue.size() > 0) {
                        Integer[] currentPos = queue.poll();
                        int currentRow = currentPos[0];
                        int currentCol = currentPos[1];
                        // will add adjacent positions to the queue if it's land
                        for (int i = 0; i < DIRECTIONS.length; i++) {
                            int[] currentDir = DIRECTIONS[i];
                            int nextRow = currentRow + currentDir[0];
                            int nextCol = currentCol + currentDir[1];
                            if (nextRow < 0 || nextRow >= matrix.length || nextCol < 0 || nextCol >= matrix[0].length) {
                                continue;
                            }
                            if (matrix[nextRow][nextCol] == 1) {
                                Integer[] nextPos = {nextRow, nextCol};
                                queue.add(nextPos);
                                matrix[nextRow][nextCol] = 0; // flip to 0
                            }
                        }
                    }
                }
            }
        }
        return islandCount;
    }

    int findNumberOfIslands(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int islandCount = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 1) {
                    islandCount++;
                    Queue<Integer[]> queue = new LinkedList<>();
                    Integer[] startPos = {row, col};
                    queue.add(startPos);
                    matrix[row][col] = 0; // flip to 0 not to be counted again
                    while (queue.size() > 0) {
                        Integer[] currentPos = queue.poll();
                        int currentRow = currentPos[0];
                        int currentCol = currentPos[1];
                        for (int i = 0; i < DIRECTIONS.length; i++) {
                            int[] currentDir = DIRECTIONS[i];
                            int nextRow = currentRow + currentDir[0];
                            int nextCol = currentCol + currentDir[1];
                            Integer[] nextPos = {nextRow, nextCol};
                            // check the range
                            if (nextRow < 0 || nextRow >= matrix.length || nextCol < 0 || nextCol >= matrix[0].length) {
                                continue;
                            }
                            if (matrix[nextRow][nextCol] == 1) {
                                queue.add(nextPos);
                                matrix[nextRow][nextCol] = 0; // flip to 0
                            }
                        }
                    }
                }
            }
        }
        return islandCount;
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
