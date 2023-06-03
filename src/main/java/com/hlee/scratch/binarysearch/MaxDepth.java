package com.hlee.scratch.binarysearch;

public class MaxDepth {

    /**
     * Time complexity: O(N) - worst
     * Space complexity: O(N) - worst
     */
    static int maxDepth(Node<Integer> node, int currentDepth) {
        if (node == null) {
            return currentDepth;
        }
        currentDepth++;
        return Math.max(maxDepth(node.left, currentDepth), maxDepth(node.right, currentDepth));
    }

    static class Node<T> {

        T value;
        Node<T> left;
        Node<T> right;
    }
}
