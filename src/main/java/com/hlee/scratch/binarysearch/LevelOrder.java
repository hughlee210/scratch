package com.hlee.scratch.binarysearch;

import java.util.*;

public class LevelOrder {

    /**
     * 3
     * /    \
     * 6       1
     * /  \       \
     * 9    2       4
     * /
     * 5
     * /
     * 8
     * <p>
     * level order output: {[3], [6, 1], [9, 2, 4], [5], [8]}
     *
     * Time complexity: O(N) touches every element
     * Space complexity: O(max queue size) = O(N/2) = O(N) worst case
     */
    static List<List<Integer>> levelOrder(Node<Integer> root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>(); // space: O(N)
        Queue<Node<Integer>> queue = new LinkedList<>(); // space: O(N/2) = O(N)
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelQueueSize = queue.size();
            int count = 0;
            List<Integer> currentLevelValues = new ArrayList<>();
            while (count < levelQueueSize) {
                Node<Integer> currentNode = queue.poll();
                currentLevelValues.add(currentNode.value);
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
                count++;
            }
            result.add(currentLevelValues);
        }
        return result;
    }

    static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;
    }
}
