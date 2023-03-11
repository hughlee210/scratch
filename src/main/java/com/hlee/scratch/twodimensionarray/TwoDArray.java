package com.hlee.scratch.twodimensionarray;

import java.util.*;

public class TwoDArray {

    /**
     * row  col  0  1  2  3  4
     * 0      [[ 1, 2, 3, 4, 5],
     * 1       [ 6, 7, 8, 9,10],
     * 2       [11,12,13,14,15],
     * 3       [16,17,18,19,20]]
     */

    public static void main(String[] args) {
        TwoDArray matrix = new TwoDArray();
        int[][] inputMatrix = {
                {1,   2,  3,  4,  5},
                {6,   7,  8,  9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
        };
        matrix.print(inputMatrix);
        System.out.println(Arrays.deepToString(inputMatrix));

        matrix.traverseDFS(inputMatrix);

        matrix.traverseBFS(inputMatrix);

        matrix.visitDFS(inputMatrix);

        matrix.visitBFS(inputMatrix);
    }

    List<Integer> traverseDFS(int[][] matrix) {
        int[][] directions = {
                {-1, 0}, // up
                {0, 1}, // right
                {1, 0}, // down
                {0, -1} // left
        };
        boolean[][] seen = new boolean[matrix.length][matrix[0].length]; // default value: false
        System.out.println(Arrays.deepToString(seen));

        List<Integer> values = new ArrayList<>();
        dfs(matrix, 0, 0, seen, directions, values);
        System.out.println("result of traverseDFS: " + values);
        return values;
    }

    void dfs(int[][] matrix, int row, int col, boolean[][] seen, int[][] directions, List<Integer> values) {
        // base case to get out of recursion
        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length || seen[row][col]) {
            return;
        }
        values.add(matrix[row][col]);
        seen[row][col] = true;
        for (int i = 0; i < directions.length; i++) {
            int[] currentDir = directions[i];
            dfs(matrix, row + currentDir[0], col + currentDir[1], seen, directions, values);
        }
    }

    List<Integer> traverseBFS(int[][] matrix) {
        int[][] directions = {
                {-1, 0}, // up
                {0, 1}, // right
                {1, 0}, // down
                {0, -1} // left
        };
        boolean[][] seen = new boolean[matrix.length][matrix[0].length];
        System.out.println("seen: " + Arrays.deepToString(seen));

        List<Integer> values = new ArrayList<>();
        Queue<Integer[]> queue = new LinkedList<>();
        Integer[] startPos = {0, 0};
        queue.add(startPos);
        while (queue.size() > 0) {
            Integer[] currentPos = queue.poll();
            int row = currentPos[0];
            int col = currentPos[1];
            if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length || seen[row][col]) {
                continue;
            }
            seen[row][col] = true;
            values.add(matrix[row][col]);
            for (int i = 0; i < directions.length; i++) {
                int[] currentDir = directions[i];
                Integer[] newPos = {row + currentDir[0], col + currentDir[1]};
                queue.add(newPos);
            }
        }
        System.out.println("seen: " + Arrays.deepToString(seen));
        System.out.println("result of traverseBFS: " + values);
        return values;
    }

    void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + ", ");
            }
            System.out.println();
        }
    }

    ///////////
    List<Integer> visitDFS(int[][] matrix) {
        int[][] directions = {
                {-1, 0},
                {0, 1},
                {1, 0},
                {0, -1},
        };

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        List<Integer> values = new ArrayList<>();
        dfs_ex(matrix, 0, 0, visited, directions, values);
        System.out.println("result of visitDFS: " + values);
        return values;
    }

    void dfs_ex(int[][] matrix, int row, int col, boolean[][] seen, int[][] directions, List<Integer> values) {
        // base case to return
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || seen[row][col]) {
            return;
        }
        seen[row][col] = true;
        values.add(matrix[row][col]);
        for (int i = 0; i < directions.length; i++) {
            int[] currentDir = directions[i];
            dfs_ex(matrix, row + currentDir[0], col + currentDir[1], seen, directions, values);
        }
    }

    List<Integer> visitBFS(int[][] matrix) {
        int[][] directions = {
                {-1, 0}, // up
                {0, 1}, // right
                {1, 0}, // down
                {0, -1}, // left
        };

        List<Integer> values = new ArrayList<>();
        boolean[][] seen = new boolean[matrix.length][matrix[0].length];
        Queue<Integer[]> queue = new LinkedList<>();
        Integer[] startPos = {0, 0};
        queue.add(startPos);
        while (queue.size() > 0) {
            Integer[] currentPos = queue.poll();
            int row = currentPos[0];
            int col = currentPos[1];
            // check the range
            if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || seen[row][col]) {
                continue;
            }
            seen[row][col] = true;
            values.add(matrix[row][col]);
            // add adjacent cell to queue
            for (int i = 0; i < directions.length; i++) {
                int[] currentDir = directions[i];
                Integer[] nextPos = {row + currentDir[0], col + currentDir[1]};
                queue.add(nextPos);
            }
        }
        System.out.println("result of visitBFS: " + values);
        return values;
    }
}
