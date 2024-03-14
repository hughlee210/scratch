package com.hlee.scratch.tree;

import java.util.*;
import java.util.LinkedList;
import java.util.Stack;

public class BinaryTree<T> {

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

        BinaryTree<Integer> bst = new BinaryTree<>();
        bst.root = node;

        System.out.print("<printInorderRecursive>: ");
        printInorderRecursive(bst.root);
        System.out.println("  count = " + count);
        count = 0;

        List<Integer> resultList = new ArrayList<>();
        System.out.print("<printInOrderR>: ");
        traverseInOrderR(bst.root, resultList);
        System.out.println("result list: " + resultList);

        System.out.println("\nIs this tree a BST? " + bst.isBst());

        List<Integer> list = new ArrayList<>();
        bst.inOrderRecur(bst.root, list);
        System.out.println("list converted from BST = " + list);

        System.out.print("\n<visitInOrder>: ");
        List inOrderList = bst.visitInOrderIterative(true);
        System.out.println("\nvisitInOrder result list = " + inOrderList);

        bfs(bst.root);

        TreeNode node1 = bst.root;
        invertTree(node1);
        System.out.println("inverted tree");

        bfs(node1);

        int targetSum = 8;
        System.out.println("\n<findSumPair_usingArray>: ");
        bst.findSumPair_usingArray(targetSum);

        System.out.println("<findSumPair_usingStack>: ");
        bst.findSumPair_usingStack(targetSum);
        ///////////////////////////////////////////////////////////

        int[] inOrderArr = new int[] { 4, 2, 5, 1, 6, 3 };
        int[] preOrderArr = new int[] { 1, 2, 4, 5, 3, 6 };

        TreeNode root = buildTree(inOrderArr, preOrderArr, 0, inOrderArr.length - 1);

        // building the tree by printing inorder traversal
        System.out.println("Inorder traversal of constructed tree is : ");
        printInorderRecursive(root);


    }

    TreeNode root;

    static int count = 0;

    boolean isBst() {
        return isBst(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // time complexity: O(N)
    // space: O(1) if Function Call Stack size is not considered,
    // otherwise O(N) worst case, O(h) = O(logN) for complete tree
    boolean isBst(TreeNode node, int min, int max) {
        // empty tree is BST
        if (node == null)
            return true;

        // false if node value is not between min and max
        if (node.value < min || node.value > max)
            return false;

        // check subtrees recursively with updated min max range
        return isBst(node.left, min, node.value - 1) && isBst(node.right, node.value + 1, max);
    }

    static int preIndex = 0;

    // int[] inOrderArr  = new int[] { 4, 2, 5, 1, 6, 3 };
    // int[] preOrderArr = new int[] { 1, 2, 4, 5, 3, 6 };
    //
    // Construct Tree from given Inorder and Preorder traversals
    //
    //    In a Preorder sequence, leftmost element is the root of the tree. So we know ‘A’ is root for given sequences.
    // By searching ‘A’ in Inorder sequence, we can find out all elements on left side of ‘A’ are in left subtree
    // and elements on right are in right subtree. So we know below structure now.
    //
    //          1
    //        /   \
    //      /       \
    //    4 2 5     6 3
    //
    //  We recursively follow above steps and get the following tree.
    //
    //            1
    //          /   \
    //        /       \
    //       2         3
    //      / \       /
    //     /    \    /
    //    4      5  6
    //
    //  Algorithm: buildTree()
    //  1) Pick an element from Preorder. Increment a Preorder Index Variable (preIndex in below code) to pick next element in next recursive call.
    //  2) Create a new tree node tNode with the data as picked element.
    //  3) Find the picked element’s index in Inorder. Let the index be inIndex.
    //  4) Call buildTree for elements before inIndex and make the built tree as left subtree of tNode.
    //  5) Call buildTree for elements after inIndex and make the built tree as right subtree of tNode.
    //  6) return tNode.
    //
    // Time Complexity: O(n^2). Worst case occurs when tree is left skewed.
    // Example Preorder and Inorder traversals for worst case are {A, B, C, D} and {D, C, B, A}.
    //
    // O(NlogN) if the tree is balanced
    // O(N^2) if the tree a linked list
    //
    // https://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
    // https://stackoverflow.com/questions/20922969/time-complexity-of-construction-of-a-binary-tree-from-inorder-and-preorder-trave
    //
    static TreeNode buildTree(int[] inorderArr, int[] preorderArr, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;

        // Pick value from preorder traversal array using preIndex,
        // create a parent node with the value, and increment preIndex.
        TreeNode node = new TreeNode(preorderArr[preIndex++]);

        // if this node has no children then return the node
        if (inStart == inEnd)
            return node;

        // Else find the index of this value in inorder traversal array
        int inIndex = search(inorderArr, node.value, inStart, inEnd);

        // inIndex is the index of parent index that divides the inorder array
        // into left and right sub trees
        // using index in inorder traversal, construct left and right sub trees
        //
        node.left = buildTree(inorderArr, preorderArr, inStart, inIndex - 1);
        node.right = buildTree(inorderArr, preorderArr, inIndex + 1, inEnd);

        return node;
    }

    /*
     * Function to find index of value in arr[start...end]
     * The function assumes that value is present in the array
     */
    static int search(int[] arr, int value, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * time: O(N), space: O(height of tree)
     * The space complexity of this traversal seems to be O(h) where h is height of tree.
     * The best way to think about space complexity of recursive functions is
     * (# of stack frames)*(space per stack frame). In your case, in worst case,
     * you can have h stack frames. With each stack frame having O(1) space.
     * So total space complexity is O(h).
     */
    public static void printInorderRecursive(TreeNode node) {
        if (node == null)
            return;
        printInorderRecursive(node.left);
        System.out.print(node.value + " ");
        count++;
        printInorderRecursive(node.right);
    }

    static void traverseInOrderR(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        traverseInOrderR(node.left, list);
        list.add(node.value);
        traverseInOrderR(node.right, list);
    }

    public static void printPreOrderRecursive(TreeNode node) {
        if (node == null)
            return;
        System.out.print(node.value + " ");
        printPreOrderRecursive(node.left);
        printPreOrderRecursive(node.right);
    }

    // ...4
    // .2...6
    // 1 3 5 7
    // stack:
    // print: 1, 2, 3, 4, 5, 6, 7
    /**
     * Using stack. similar to DFS.
     * time: O(N), space: O(height of tree) = O(logN) if BST
     */
    public List<Integer> visitInOrderIterative(boolean print) {
        List<Integer> list = new ArrayList<>(); // store value in visited order
        TreeNode node = root;
        Stack<TreeNode> parentStack = new Stack<>();
        while (!parentStack.isEmpty() || node != null) { // exit if stack is empty and node is null
            if (node != null) {
                // push the node to visit later after visiting its left child
                parentStack.push(node);
                node = node.left;
            } else { // means no more child
                node = parentStack.pop();
                if (print)
                    System.out.print(node.value + " ");
                // after visiting the node move to right child
                list.add(node.value);
                node = node.right;
            }
        }
        return list;
    }

    void inOrderRecur(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        inOrderRecur(node.left, list);
        list.add(node.value);
        inOrderRecur(node.right, list);
    }

    List<Integer> inOrderIter(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode node = root;
        Stack<TreeNode> parentStack = new Stack<>();
        while (!parentStack.isEmpty() || node != null) {
            if (node != null) {
                parentStack.push(node); // push the node to visit later after visiting its left child
                node = node.left;
            } else { // means no more child
                node = parentStack.pop();
                list.add(node.value);
                node = node.right; // after visiting the node move to right child
            }
        }
        return list;
    }

    // time complexity: O(N), space: O(N)
    public void findSumPair_usingArray(int sum) {
        // assume the tree is BST, so converted list using in-order traversal is sorted in ascending order
        List<Integer> list = visitInOrderIterative(false); // time: O(N), space: O(logN) + O(N) for list
        // now we have sorted list
        int l = 0;
        int r = list.size() - 1;
        // time: O(N/2)
        while (l < r) {
            if (list.get(l) + list.get(r) == sum) {
                System.out.println("found a pair for sum: " + list.get(l) + " + " + list.get(r) + " = " + sum);
                l++;
                r--;
            } else if (list.get(l) + list.get(r) < sum) {
                l++;
            } else {
                r--;
            }
        }
        // if the tree is not BST and the list is not sorted,
        // then we can use HashMap to find sum pairs
    }

    // time complexity: O(N), space: O(logN)
    public void findSumPair_usingStack(int sum) {
        // assume the tree is BST
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        boolean stop1 = false, stop2 = false;
        int val1 = 0, val2 = 0;
        TreeNode node1 = root;
        TreeNode node2 = root;
        while (true) {
            // find next node in normal in-order traversal (starting from leftmost node)
            // time: O(N/2), space: O(logN/2)
            while (!stop1) {
                if (node1 != null) {
                    stack1.push(node1);
                    node1 = node1.left;
                } else {
                    if (!stack1.isEmpty()) {
                        node1 = stack1.pop();
                        val1 = node1.value;
                        node1 = node1.right;
                    }
                    stop1 = true;
                }
            }

            // find next node in Reverse in-order traversal (starting from rightmost node)
            // time: O(N/2), space: O(logN/2)
            while (!stop2) {
                if (node2 != null) {
                    stack2.push(node2);
                    node2 = node2.right;
                } else {
                    if (!stack2.isEmpty()) {
                        node2 = stack2.pop();
                        val2 = node2.value;
                        node2 = node2.left;
                    }
                    stop2 = true;
                }
            }

            if (val1 >= val2)
                break;

            if (val1 + val2 == sum) {
                System.out.println("using stack - Found a pair for sum: " + val1 + " + " + val2 + " = " + sum);
                stop1 = stop2 = false;
            } else if (val1 + val2 < sum)
                stop1 = false;
            else
                stop2 = false;
        }
    }

    void dfs(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        // preorder traversal
        list.add(node.value);
        if (node.left != null)
            dfs(node.left, list);
        if (node.right != null)
            dfs(node.right, list);
    }

    static void bfs(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            list.add(currentNode.value);
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
        System.out.println("\nBFS list: " + list);
    }

    void bfsRecur(Queue<TreeNode> queue, List<Integer> list) {
        if (queue.isEmpty()) {
            return;
        }
        TreeNode currentNode = queue.poll();
        list.add(currentNode.value);
        if (currentNode.left != null) {
            queue.add(currentNode.left);
        }
        if (currentNode.right != null) {
            queue.add(currentNode.right);
        }
        bfsRecur(queue, list);
    }

    /**
     *                3
     *               / \
     *             6     1
     *           /  \     \
     *          9   2      4
     *           \
     *            5
     *           /
     *          8
     *
     *  output: [[3], [6,1], [9,2,4], [5], [8]]
     */
    List<List<Integer>> bfsLevelOrderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int queueLength = queue.size();
            int processingCount = 0;
            List<Integer> currentLevelValues = new ArrayList<>();
            while (processingCount < queueLength) {
                TreeNode currentNode = queue.poll();
                currentLevelValues.add(currentNode.value);
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
                processingCount++;
            }
            result.add(currentLevelValues);
        }
        return result;
    }

    // ...4
    // .2...6
    // 1 3 5 7

    static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode rightInverted = invertTree(root.right);
        TreeNode leftInverted = invertTree(root.left);
        root.left = rightInverted;
        root.right = leftInverted;
        return root;
    }

    /**
     * using BFS, we need to swap the left and right child of all nodes in the tree.
     * So, we create a queue to store nodes whose left and right child have not been
     * swapped yet. Initially, only the root is in the queue. As long as the queue is
     * not empty, remove the next node from the queue, swap its children, and add the
     * children to the queue. Null nodes are not added to the queue. Eventually, the
     * queue will be empty and all the children swapped, and we return the original
     * root.
     *
     * Time complexity: O(n) since each node in the tree is visited/added to the queue
     *      only once, the time complexity is O(n) where n is the number of nodes
     * Space complexity: O(n) since worst case, the queue will contain all nodes in one
     *      level of the binary tree. For full binary tree, the leaf level has n/2 = O(n)
     *      leaves.
     *
     */
    static TreeNode invertTreeIter(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode currNode = queue.poll();
            TreeNode temp = currNode.left;
            currNode.left = currNode.right;
            currNode.right = temp;
            if (currNode.left != null) {
                queue.add(currNode.left);
            }
            if (currNode.right != null) {
                queue.add(currNode.right);
            }
        }
        return root;
    }
}
