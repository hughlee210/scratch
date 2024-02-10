package com.hlee.scratch.twodimensionarray;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {

    /**
     * Given a 2D array containing 0's (empty cell), 1's (fresh orange) and 2's (rotten orange).
     * Every minute, all fresh orange immediately adjacent (4 directions) to rotten oranges will rot.
     * How many minutes must pass until all oranges are rotten?
     *
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
     * below case returns -1
     * {
     *     {2, 0, 1, 0, 0},
     *     {1, 1, 0, 0, 2},
     *     {0, 1, 1, 1, 1},
     *     {0, 1, 0, 0, 1}
     * }
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

    static int ROTTEN = 2;
    static int FRESH = 1;
    static int EMPTY = 0;

    /*
    // search sequentially from top to bottom, left to right
    // - count fresh oranges
    // - put rotten oranges positions into queue
    // BFS:
    // - use queue size to track minutes
    // - process rotting oranges
    //   - rot adjacent fresh oranges
    //   - push into queue
    //   - decrement fresh orange count
        T: O(N)
        S: O(N) queue size can be O(n) when all oranges are rotten
     */
    int getMinutesToRotOranges(int[][] matrix) {
        int freshOranges = 0;
        Queue<Integer[]> queue = new LinkedList<>();
        for (int row = 0; row < matrix.length; row++) {     // O(N)
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

        // now we have initial rotten oranges in the queue
        // record the current queue size to track minutes
        // when each queue element is processed, the queue size will be decremented
        // when the queue size become 0, that means one level for the rotten oranges
        // is processed.
        int currentQueueSize = queue.size();
        int minutes = 0;
        while (!queue.isEmpty()) {      // O(N)
            if (currentQueueSize == 0) {
                // 0 means adjacent fresh oranges (one level) got rotten
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
