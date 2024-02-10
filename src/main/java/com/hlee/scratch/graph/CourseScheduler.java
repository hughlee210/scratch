package com.hlee.scratch.graph;

import java.util.*;

/**
 * There are a total of n courses to take, labeled from 0 to n-1.
 * Some courses have prerequisite courses. This is expressed as a pair
 * i.e. [1, 0] which indicates you must take course 0 before taking
 * course 1.
 * Given the total number of courses and an array of prerequisite pairs,
 * return if it is possible to finish all courses.
 *
 * can be solved either with BFS or DFS
 *
 *  n = 6       6 courses: 0, 1, 2, 3, 4, 5
 *  prerequisites:
 *  {
 *      {1, 0},
 *      {2, 1},
 *      {2, 5},
 *      {0, 3},
 *      {4, 3},
 *      {3, 5},
 *      {4, 5}
 *  }
 */
public class CourseScheduler {

    public static void main(String[] args) {
        int[][] prerequisites = {
                {1, 0},
                {2, 1},
                {2, 5},
                {0, 3},
                {4, 3},
                {3, 5},
                {4, 5}
        };
        int n = 6;
        boolean canFinish = canFinish(n, prerequisites);
        System.out.println("can finish = " + canFinish);
    }

    /**
     * Time complexity: O(P + N^3)
     * Space complexity: O(N^2) for adjList
     */
    static boolean canFinish(int n, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) { // T: O(p)
            int[] pair = prerequisites[i];
            adjList.get(pair[1]).add(pair[0]);
        }
        boolean[] seen = new boolean[n];
        Arrays.fill(seen, false);
        for (int v = 0; v < n; v++) { // n times
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < adjList.get(v).size(); i++) { // n times
                queue.add(adjList.get(v).get(i));
            }
            while (!queue.isEmpty()) { // n times
                Integer current = queue.poll();
                seen[current] = true;
                if (current == v) {
                    return false;
                }
                List<Integer> adjacent = adjList.get(current);
                for (int i = 0; i < adjacent.size(); i++) {  // n times
                    Integer next = adjacent.get(i);
                    if (!seen[next]) {
                        queue.add(next);
                    }
                }
            }
        }
        return true;
    }
}
