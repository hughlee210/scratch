package com.hlee.scratch.tree;

public class MaxDepth {

    public static void main(String[] args) {

        // ...4
        // .2...6
        // 1 3 5 7

        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(2);
        node.right = new TreeNode(6);
        node.left.left = new TreeNode(1);
        node.left.right = new TreeNode(3);
        node.right.left = new TreeNode(5);
        node.right.right = new TreeNode(7);

        int maxDepth = maxDepth(node, 0);
        System.out.println("max depth = " + maxDepth);
    }

    /**
     * Time complexity: O(N) - worst
     * Space complexity: O(N) - worst
     */
    static int maxDepth(TreeNode node, int currentDepth) {
        if (node == null) {
            return currentDepth;
        }
        currentDepth++;
        return Math.max(maxDepth(node.left, currentDepth), maxDepth(node.right, currentDepth));
    }

}
