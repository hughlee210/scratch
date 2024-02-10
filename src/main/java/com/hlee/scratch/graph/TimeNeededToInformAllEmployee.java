package com.hlee.scratch.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * A company has n employees with unique IDs from 0 to n-1.
 * The head of the company has the ID headId.
 *  n = 8, 8 employees: 0,1,2,3,4,5,6,7
 *  headID = 4
 *
 *  You will receive a managers array where managers[i] is the ID
 *  of the manager for employee i. Each employee has one direct manager.
 *  The company head has no manager (managers[headID] = -1). It's guaranteed
 *  the subordination relationships will have a tree structure.
 *
 *  managers =  [2, 2, 4, 6, -1, 4, 4, 5]
 *
 *  empId = index 0, 1, 2, 3, 4, 5, 6, 7
 *
 *  informTime = [0, 0, 4, 0, 7, 3, 6, 0]
 *
 *                               4 (7)
 *                            /    |    \
 *                        5 (3)  2 (4)   6 (6)
 *                      /      /   \      \
 *                    7      0      1      3
 *
 *  total time taken to inform all = 13
 *
 *  adjacency list: index will be manager id (direction is manger to its subordinates)
 */
public class TimeNeededToInformAllEmployee {

    public static void main(String[] args) {
        int[] manangers = new int[]{2, 2, 4, 6, -1, 4, 4, 5};
        int[] informTimes = new int[]{0, 0, 4, 0, 7, 3, 6, 0};
        int numOfMinutes = numOfMinutesToInformAll(manangers.length, 4, manangers, informTimes);
        System.out.println("number of minutes to inform all employess: " + numOfMinutes);
    }

    static int numOfMinutesToInformAll(int n, int headId, int[] managers, int[] informTime) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int e = 0; e < n; e++) {  // O(N)
            adjList.add(new ArrayList<>()); // initialize
        }
        // for each employee, find the manger and add the employee to the manager's subordinates
        for (int e = 0; e < n; e++) { // O(N)
            int manager = managers[e];
            if (manager == -1) {
                continue;
            }
            adjList.get(manager).add(e); // T: O(N), S: O(N)
        }
        return dfs(headId, adjList, informTime);
    }

    static int dfs(int currEmpId, List<List<Integer>> adjList, int[] informTime) {
        if (adjList.get(currEmpId).size() == 0) { // base case = no subordinate
            return 0;
        }
        // explore all of its subordinates
        int max = 0; // max inform time of subordinates
        List<Integer> subordinates = adjList.get(currEmpId);
        for (int i = 0; i < subordinates.size(); i++) {
            max = Math.max(max, dfs(subordinates.get(i), adjList, informTime));
        }
        return max + informTime[currEmpId];
    }

}
