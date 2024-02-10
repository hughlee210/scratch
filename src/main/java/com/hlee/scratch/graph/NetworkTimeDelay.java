package com.hlee.scratch.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Dijkstra's shortest path
 *
 */
public class NetworkTimeDelay {

    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(100);
        minHeap.add(1);
        minHeap.add(9);
        minHeap.add(12);
        minHeap.add(5);

        System.out.println("minHeap = " + minHeap);
        System.out.println("get top = " + minHeap.poll());
        System.out.println("minHeap = " + minHeap);
        System.out.println("get top = " + minHeap.poll());
        System.out.println("minHeap = " + minHeap);
        System.out.println("get top = " + minHeap.poll());
        System.out.println("minHeap = " + minHeap);
        System.out.println("get top = " + minHeap.poll());
        System.out.println("minHeap = " + minHeap);
        System.out.println("get top = " + minHeap.poll());
        System.out.println("minHeap = " + minHeap);
        System.out.println("===========================================");

        com.hlee.scratch.queue.PriorityQueue minHeap2 = new com.hlee.scratch.queue.PriorityQueue();
        minHeap2.add(100);
        minHeap2.add(1);
        minHeap2.add(9);
        minHeap2.add(12);
        minHeap2.add(5);

        System.out.println("minHeap2 = " + minHeap2);
        System.out.println("get top = " + minHeap2.poll());
        System.out.println("minHeap2 = " + minHeap2);
        System.out.println("get top = " + minHeap2.poll());
        System.out.println("minHeap2 = " + minHeap2);
        System.out.println("get top = " + minHeap2.poll());
        System.out.println("minHeap2 = " + minHeap2);
        System.out.println("get top = " + minHeap2.poll());
        System.out.println("minHeap2 = " + minHeap2);
        System.out.println("get top = " + minHeap2.poll());
        System.out.println("minHeap2 = " + minHeap2);
        System.out.println("===========================================");

        int[][] times = {
                {1, 2, 9},
                {1, 4, 2},
                {2, 5, 1},
                {4, 2, 4},
                {4, 5, 6},
                {3, 2, 3},
                {5, 3, 7},
                {3, 1, 5},
        };

        System.out.println("times[][] array: ");
        for (int i = 0; i < times.length; i++) {
            System.out.println(Arrays.toString(times[i]));
        }
        System.out.println("---------------------------");

        int timeTakenToSendSignalToAllNodes = networkDelayTime(times, 5, 1);
        System.out.println("time taken to send signal to all nodes = " + timeTakenToSendSignalToAllNodes);
    }

    /**
     *
     * times[][] = {
     *  [1, 2, 9],
     *  [1, 4, 2],
     *  [2, 5, 1],
     *  [4, 2, 4],
     *  [4, 5, 6]
     *  [3, 2, 3],
     *  [5, 3, 7],
     *  [3, 1, 5]
     * }
     * [source vertex value, target vertex value, distance]
     *
     * vertex index = vertex value - 1
     *
     * Adjacency list keeps neighboring vertex index and weight
     * vertex index 0: [1, 9], [3, 2],
     * vertex index 1: [4, 1],
     * vertex index 2: [1, 3], [0, 5],
     * vertex index 3: [1, 4], [4, 6],
     * vertex index 4: [2, 7],
     *
     * k = 1 (starting vertex)
     * n = 5 (number of vertex)
     *
     * To store shortest distances
     * distances[] = {
     *     Inf,
     *     Inf,
     *     Inf,
     *     Inf,
     *     Inf
     * }
     *
     * Send a signal from node k, return how long it takes for all nodes
     * to receive the signal. Return -1 if it's impossible.
     *
     * note: Dijkstra's algorithm does not work for negative weight nor negative cycle
     */
    static int networkDelayTime(int[][] times, int n, int k) {
        int[] distances = new int[n]; // to store shortest distances
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k - 1] = 0; // distance from k to k is 0. distances array is 0 based. so use k-1
        List<List<Integer[]>> adjList = new ArrayList<>();
        for (int i = 0; i < distances.length; i++) {
            adjList.add(new ArrayList<>());
        }

//        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // vertex value is integer
        com.hlee.scratch.queue.PriorityQueue minHeap = new com.hlee.scratch.queue.PriorityQueue();
        minHeap.add(k - 1); //

        // populate adjacency list from the data in times[][]
        for (int i = 0; i < times.length; i++) {
            int source = times[i][0];
            int target = times[i][1];
            int weight = times[i][2];
            Integer[] targetAndWeight = {target - 1, weight};
            adjList.get(source - 1).add(targetAndWeight); // adjList is index 0 based
        }

        while (!minHeap.isEmpty()) {
            System.out.println(minHeap);
            int currentVertex = minHeap.poll();
            System.out.println("removed from minHeap: " + currentVertex);
            List<Integer[]> neighbors = adjList.get(currentVertex);
            for (int i = 0; i < neighbors.size(); i++) {
                Integer[] neighborTargetAndWeight = neighbors.get(i);
                int targetVertex = neighborTargetAndWeight[0];
                int weight = neighborTargetAndWeight[1];
                if (distances[currentVertex] + weight < distances[targetVertex]) {
                    distances[targetVertex] = distances[currentVertex] + weight;
                    minHeap.add(targetVertex);
                    System.out.println("added " + targetVertex);
                }
            }
        }

        System.out.println("distances: " + Arrays.toString(distances));
        int maxDistance = Arrays.stream(distances).max().getAsInt();
        return maxDistance == Integer.MAX_VALUE ? -1 : maxDistance;
    }

    static void printAdjList(List<List<Integer[]>> adjList) {
        // below is just printing logic
        System.out.println("Adjacency list");
        for (int i = 0; i < adjList.size(); i++) {
            List<Integer[]> neighbors = adjList.get(i); // list of [taret_index, weight]
            System.out.print("vertex index " + i + ": ");
            for (int j = 0; j < neighbors.size(); j++) {
                Integer[] targetAndWeight = neighbors.get(j);
                System.out.print(Arrays.deepToString(targetAndWeight) + ",");
            }
            System.out.println();
        }
        System.out.println("-----end of printing Adjacency list");
    }
}
