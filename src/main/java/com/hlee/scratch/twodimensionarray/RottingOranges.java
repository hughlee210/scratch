package com.hlee.scratch.twodimensionarray;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {

    /**
     * 2: Rotten orange
     * 1: Fresh orange
     * 0: Empty cell
     * {
     *     {2, 1, 1, 0, 0},
     *     {1, 1, 0, 0, 2},
     *     {0, 1, 1, 1, 1},
     *     {0, 1, 0, 0, 1}
     * }
     *
     *
     * {
     *     {2, 0, 1, 0, 0},
     *     {1, 1, 0, 0, 2},
     *     {0, 1, 1, 1, 1},
     *     {0, 1, 0, 0, 1}
     * }
     *
     *
     */

    public static void main(String[] args) {
        int[][] input = {
                {2, 1, 1, 0, 0},
                {1, 1, 0, 0, 2},
                {0, 1, 1, 1, 1},
                {0, 1, 0, 0, 1}
        };
        RottingOranges theObj = new RottingOranges();
        int minutes = theObj.getMinutesToRotOranges(input);
        System.out.println("minutes rotting oranges: " + minutes);

        int[][] input2 = {
                {2, 0, 1, 0, 0},
                {1, 1, 0, 0, 2},
                {0, 1, 1, 1, 1},
                {0, 1, 0, 0, 1}
        };
        minutes = theObj.getMinutesToRotOranges(input2);
        System.out.println("minutes rotting oranges: " + minutes);
    }

    static int[][] DIRECTIONS = {
            {-1, 0}, // up
            {0, 1},  // right
            {1, 0},  // down
            {0, -1}, // left
    };

    int getMinutesToRotOranges(int[][] matrix) {
        // sequential order:
        // - count fresh oranges
        // - put rotten oranges into queue
        // BFS:
        // - use queue size to track minutes
        // - process rotting oranges
        //   - rot adjacent fresh oranges
        //   - push into queue
        //   - decrement fresh orange count

        final int ROTTEN = 2;
        final int FRESH = 1;
        final int EMPTY = 0;

        int freshOranges = 0;
        Queue<Integer[]> queue = new LinkedList<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                // count fresh oranges
                if (matrix[row][col] == FRESH) {
                    freshOranges++;
                }
                // put rotten oranges into queue
                if (matrix[row][col] == ROTTEN) {
                    Integer[] rottenPos = {row, col};
                    queue.add(rottenPos);
                }
            }
        }

        int currentQueueSize = queue.size();
        int minutes = 0;
        while (queue.size() > 0) {
            if (currentQueueSize == 0) {
                // 0 means adjacent fresh oranges got rotten
                minutes++;
                // update currentQueueSize for the next iteration
                currentQueueSize = queue.size();
            }

            Integer[] currentRottenOrangePos = queue.poll();
            currentQueueSize--;
            int currentRow = currentRottenOrangePos[0];
            int currentCol = currentRottenOrangePos[1];
            for (int i = 0; i < DIRECTIONS.length; i++) {
                int[] currentDir = DIRECTIONS[i];
                int nextRow = currentRow + currentDir[0];
                int nextCol = currentCol + currentDir[1];
                if (nextRow < 0 || nextRow >= matrix.length || nextCol < 0 || nextCol >= matrix[0].length) {
                    continue;
                }
                // if next orange is fresh, rot it and decrement fresh count and put it into queue.
                if (matrix[nextRow][nextCol] == FRESH) {
                    matrix[nextRow][nextCol] = ROTTEN;
                    freshOranges--;
                    Integer[] nextPos = {nextRow, nextCol};
                    queue.add(nextPos);
                }
            }
        }
        return freshOranges > 0 ? -1 : minutes;
    }
}
