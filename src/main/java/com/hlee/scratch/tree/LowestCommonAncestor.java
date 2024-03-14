package com.hlee.scratch.tree;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 *
 * According to the definition of LCA on wikipedia: The lowest common ancestor is defined between two nodes
 * p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a
 * descendant of itself).
 */
public class LowestCommonAncestor {

    /**
     * 1. Start traversing the tree from the root node.
     * 2. If both the nodes p and q are in the right subtree, then continue the search with
     *    right subtree starting step 1.
     * 3. If both the nodes p and q are in the left subtree, then continue the search with
     *    left subtree starting step 1.
     * 4. If both step 2 and step 3 are not true, this means we have found the node which is
     *    common to node p's and q's subtrees, and hence we return this common node as the LCA.
     *
     * Time complexity: O(n) worst case we might be visiting all the nodes
     * Space complexity: O(n) recursion stack would be n since skewed tree height could be n.
     */
    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // value of current/parent node
        int parentVal = root.value;
        // value of p
        int pVal = p.value;
        // value of q
        int qVal = q.value;

        if (pVal > parentVal && qVal > parentVal) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (pVal < parentVal && qVal < parentVal) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // found the split point, i.e. the LCA
            return root;
        }
    }

    /**
     * The steps taken are also similar to approach 1 (recursive). The only difference is
     * instead of recursively calling the function, we traverse down the tree iteratively.
     * This is possible without using a stack or recursion since we don't need to backtrace
     * to find the LCA node. In essence of it the problem is iterative, it just wants us
     * to find the split point. The point from where p and q won't be part of the same subtree
     * or when one is the parent of the other.
     *
     * Time complexity: O(n) in worst case we might be visiting all the nodes in the BST.
     * Space complexity: O(1)
     */
    static TreeNode lowestCommonAncestor_iter(TreeNode root, TreeNode p, TreeNode q) {
        // value of p
        int pVal = p.value;
        // value of q
        int qVal = q.value;

        // start from the root node
        TreeNode node = root;

        // traverse the tree
        while (node != null) {
            // value of ancestor/parent node
            int parentVal = node.value;
            if (pVal > parentVal && qVal > parentVal) {
                node = node.right;
            } else if (pVal < parentVal && qVal < parentVal) {
                node = node.left;
            } else {
                // found the split point
                return node;
            }
        }
        return null;
    }

}
