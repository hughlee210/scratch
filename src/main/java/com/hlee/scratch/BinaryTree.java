package com.hlee.scratch;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree<T> {

    public static void main(String[] args) {

        // ...4
        // .2...6
        // 1 3 5 7

        BinaryTree<Integer> bst = new BinaryTree<Integer>();
        Node<Integer> node = new Node<Integer>(4);
        node.left = new Node<Integer>(2);
        node.right = new Node<Integer>(6);
        node.left.left = new Node<Integer>(1);
        node.left.right = new Node<Integer>(3);
        node.right.left = new Node<Integer>(5);
        node.right.right = new Node<Integer>(7);
        bst.root = node;

        System.out.print("<printInorderRecursive>: ");
        printInorderRecursive(bst.root);
        System.out.println("  count = " + count);
        count = 0;

        System.out.println("Is this tree a BST? " + bst.isBst());

        List<Integer> list = new ArrayList<>();
        bst.inOrderRecur(bst.root, list);
        System.out.println("list converted from BST = " + list);

        System.out.print("\n<visitInOrder>: ");
        bst.visitInOrder(true);

        int targetSum = 8;
        System.out.println("\n<findSumPair_usingArray>: ");
        bst.findSumPair_usingArray(targetSum);

        System.out.println("<findSumPair_usingStack>: ");
        bst.findSumPair_usingStack(targetSum);
        ///////////////////////////////////////////////////////////

        int[] inOrderArr = new int[] { 4, 2, 5, 1, 6, 3 };
        int[] preOrderArr = new int[] { 1, 2, 4, 5, 3, 6 };

        Node root = buildTree(inOrderArr, preOrderArr, 0, inOrderArr.length - 1);

        // building the tree by printing inorder traversal
        System.out.println("Inorder traversal of constructed tree is : ");
        printInorderRecursive(root);
    }

    static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    Node<T> root;

    static int count = 0;

    boolean isBst() {
        return isBst((Node<Integer>) this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // time complexity: O(N)
    // space: O(1) if Function Call Stack size is not considered, otherwise O(N)
    boolean isBst(Node<Integer> node, int min, int max) {
        // empty tree is BST
        if (node == null)
            return true;

        // false if node value is not between min and max
        if (node.value < min || node.value > max)
            return false;

        // check sub trees recursively with updated min max range
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
    static Node buildTree(int[] inorderArr, int[] preorderArr, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;

        // Pick value from preorder traversal array using preIndex,
        // create a parent node with the value, and increment preIndex.
        Node<Integer> node = new Node<>(preorderArr[preIndex++]);

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
    public static void printInorderRecursive(Node node) {
        if (node == null)
            return;
        printInorderRecursive(node.left);
        System.out.print(node.value + " ");
        count++;
        printInorderRecursive(node.right);
    }

    public static void printPreOrderRecursive(Node node) {
        if (node == null)
            return;
        System.out.print(node.value + " ");
        printPreOrderRecursive(node.left);
        printPreOrderRecursive(node.right);
    }

    // ...4
    // .2...6
    // 1 3 5 7
    /**
     * Using stack. similar to DFS.
     * time: O(N), space: O(height of tree) = O(logN) if BST
     */
    public List<T> visitInOrder(boolean print) {
        List<T> list = new ArrayList<T>(); // store value in visited order
        Node<T> node = root;
        Stack<Node<T>> parentStack = new Stack<>();
        while (!parentStack.isEmpty() || node != null) {
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

    void inOrderRecur(Node<T> node, List<T> list) {
        if (node == null)
            return;
        inOrderRecur(node.left, list);
        list.add(node.value);
        inOrderRecur(node.right, list);
    }

    List<T> inOrderIter(Node<T> root) {
        List<T> list = new ArrayList<>();
        Node<T> node = root;
        Stack<Node<T>> parentStack = new Stack<>();
        while (!parentStack.isEmpty() || node != null) {
            if (node != null) {
                parentStack.push(node);
                node = node.left;
            } else {
                node = parentStack.pop();
                list.add(node.value);
                node = node.right;
            }
        }
        return list;
    }

    List<T> inOrderIter_ex(Node<T> root) {
        // exercise
        List<T> list = new ArrayList<>();
        Node<T> node = root;
        Stack<Node<T>> parentStack = new Stack<>();
        while (!parentStack.isEmpty() || node != null) {
            if (node != null) {
                parentStack.push(node);
                node = node.left;
            } else {
                node = parentStack.pop();
                list.add(node.value);
                node = node.right;
            }
        }
        return list;
    }

    // time complexity: O(N), space: O(N)
    public void findSumPair_usingArray(int sum) {
        // assume the tree is BST, so converted list using in-order traversal is sorted in ascending order
        List<Integer> list = (List<Integer>) visitInOrder(false); // time: O(N), space: O(logN) + O(N) for list 
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

    // time: O(n), space: O(n)
    void findSumPair_usingArray_ex(int sum) {
        // Exercise
        // given root of BST, obtain a sorted list/array using in-order traversal of BST
        List<Integer> list = (List<Integer>) inOrderIter(this.root); // time: O(N), space: O(logN) + O(N) for list
        int l = 0, r = list.size() - 1;
        while (l < r) {
            if (list.get(l) + list.get(r) == sum) {
                System.out.println("found a pair for sum " + sum + " = " + list.get(l) + " + " + list.get(r));
                l++;
                r--;
            } else if (list.get(l) + list.get(r) < sum) {
                l++;
            } else {
                r--;
            }
        }
    }
    
    // time: O(N), space: O(N)
    void findSumPair_usingSortedList_ex(int sum) {
        // Exercise
        // given root of BST, obtain a sorted list using in-order traversal of BST
        List<Integer> list = (List<Integer>) inOrderIter(this.root);
        int l = 0, r = list.size() - 1;
        while (l < r) {
            if (list.get(l) + list.get(r) == sum) {
                System.out.println("found a pair for sum " + sum + " = " + list.get(l) + " + " + list.get(r));
                l++;
                r--;
            } else if (list.get(l) + list.get(r) < sum) {
                l++;
            } else {
                r--;
            }
        }
    }

    // time complexity: O(N), space: O(logN)
    public void findSumPair_usingStack(int sum) {
        // assume the tree is BST
        Stack<Node<Integer>> stack1 = new Stack<>();
        Stack<Node<Integer>> stack2 = new Stack<>();
        boolean stop1 = false, stop2 = false;
        int val1 = 0, val2 = 0;
        Node<Integer> node1 = (Node<Integer>) root;
        Node<Integer> node2 = (Node<Integer>) root;
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

    void findSumPair_usingStack_ex(int sum) {
        // assume the tree is BST
        Node<Integer> node1 = (Node<Integer>) this.root;
        Node<Integer> node2 = (Node<Integer>) this.root;
        Stack<Node<Integer>> stack1 = new Stack<>();
        Stack<Node<Integer>> stack2 = new Stack<>();
        int value1 = 0, value2 = 0;
        boolean stop1 = false, stop2 = false;
        while (true) {
            // find leftmost node in normal in-order traversal
            while (!stop1) {
                if (node1 != null) {
                    stack1.push(node1);
                    node1 = node1.left;
                } else {
                    if (!stack1.isEmpty()) {
                        node1 = stack1.pop();
                        value1 = node1.value;
                        node1 = node1.right;
                    }
                    stop1 = true;
                }
            }

            // find rightmost node in reverse in-order traversal
            while (!stop2) {
                if (node2 != null) {
                    stack2.push(node2);
                    node2 = node2.right;
                } else {
                    if (!stack2.isEmpty()) {
                        node2 = stack2.pop();
                        value2 = node2.value;
                        node2 = node2.left;
                    }
                    stop2 = true;
                }
            }

            if (value1 >= value2)
                break;

            if (value1 + value2 == sum)
                System.out.println("found a pair for sum " + sum + " = " + value1 + " + " + value2);
            else if (value1 + value2 < sum)
                stop1 = false;
            else
                stop2 = false;
        }
    }


}
