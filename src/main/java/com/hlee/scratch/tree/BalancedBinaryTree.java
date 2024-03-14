package com.hlee.scratch.tree;

/**
 * Tree T is balanced if and only if height difference of left child and right child,
 * and the subtrees at each child are also balanced.
 * is within 1.
 *
 * isBalanced(root):
 *     if (root == NULL):
 *         return true
 *     if (abs(height(root.left) - height(root.right)) > 1):
 *         return false
 *     else:
 *         return isBalanced(root.left) && isBalanced(root.right)
 */
public class BalancedBinaryTree {

    /**
     * null node height: -1
     * single node height: 0
     */
    int height(TreeNode root) {
        if (root == null) {
            return -1;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    /**
     * Given a binary tree, determine if it is height-balanced.
     * Approach 1: Top-down recursion
     *
     * Time complexity: O(NlogN) height method, O(N), will be called tree depth times, O(logN)
     *                  O(N^2) worst case if the tree is skewed (depth: N)
     * Space complexity: O(N) recursion stack may contain all nodes if the tree is skewed
     */
    boolean isBalanced(TreeNode root) {
        // an empty tree satisfies the definition of a balanced tree
        if (root == null) {
            return true;
        }

        // check if subtrees have height difference within 1 and
        // subtrees are balanced.
        return Math.abs(height(root.left) - height(root.right)) <= 1
                && isBalanced(root.left) && isBalanced(root.right);
    }
}
