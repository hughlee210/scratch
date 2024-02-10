package com.hlee.scratch.twodimensionarray;

public class WallsAndGates {
    /**
     * Given a 2D array containing -1's (walls), 0's (gates) and INF's (empty room).
     * Fill each empty room with the number of steps to the nearest gate.
     * If it is impossible to reach a gate, leave INF as the value.
     * INF is equal to max integer value.
     *
     * {
     *     {INF, -1,   0,  INF},
     *     {INF, INF, INF,  -1},
     *     {INF, -1,  INF,  -1},
     *     {0,   -1,  INF, INF}
     * }
     *
     * {
     *     {INF, -1,   0, INF},
     *     {-1,  INF, INF, -1},
     *     {INF, -1, INF,  -1},
     *     { 0,  -1, INF, INF}
     * }
     */

    public static void main(String[] args) {

    }

    final static int WALL = -1;
    final static int GATE = 0;
    final static int EMPTY = Integer.MAX_VALUE;
    final static int[][] DIRECTIONS = {
            {-1, 0}, // up
            {0, 1},  // right
            {1, 0},  // down
            {0, -1}, // left
    };

    // fill INF (empty room) with minimum steps to Gate
    int[][] wallsAndGates(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {     // T: O(N)
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == GATE) {
                    dfs(matrix, row, col, 0); // S: O(N) stack
                }
            }
        }
        return matrix;
    }

    void dfs(int[][] matrix, int row, int col, int currentStep) {
        // base case
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length ||
                currentStep > matrix[row][col]) {
            return;
        }

        matrix[row][col] = currentStep;

        for (int i = 0; i < DIRECTIONS.length; i++) {
            int[] currentDir = DIRECTIONS[i];
            dfs(matrix, row + currentDir[0], col + currentDir[1], currentStep + 1);
        }
    }

}
