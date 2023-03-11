package com.hlee.scratch.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphTraversal {

    public static void main(String[] args) {

        List<Node> nodes = createTestNodes();
        Node node = nodes.get(0);
        dfs(node);
        System.out.println("dfs call count = " + count + ", loop count = " + loopCnt);

        System.out.println("dfsIterative");
        dfsIterative(node);

        System.out.println("bfs");
        bfs(node);

        System.out.println("bfsRecur");
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        List<Integer> list = new ArrayList<>();
        bfsRecur(queue, list);
        System.out.println("\nresult list: " + list);
    }

    /**
     *                 1
     *              /     \
     *             2       3
     *           / | \    / \
      *         4  5  6  7  8
     *                  /   |
     *                 9    10
     * @return
     */
    static List<Node> createTestNodes() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        node1.adjacentNodes.add(node2);
        node1.adjacentNodes.add(node3);
        node2.adjacentNodes.add(node4);
        node2.adjacentNodes.add(node5);
        node2.adjacentNodes.add(node6);
        node3.adjacentNodes.add(node7);
        node3.adjacentNodes.add(node8);
        node7.adjacentNodes.add(node9);
        node8.adjacentNodes.add(node10);
        List<Node> list = new ArrayList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        list.add(node6);
        list.add(node7);
        list.add(node8);
        list.add(node9);
        list.add(node10);
        return list;
    }

    static int count = 0;
    static int loopCnt = 0;

    static class Node {
        int value;
        List<Node> adjacentNodes = new ArrayList<>();

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     *             1
     *           /    \
     *         2        3
     *       / | \     / \
     *      4  5  6   7   8
     *               /     \
     *              9       10  
     */
    static void dfs(Node node) {
        count++;
        if (node == null)
            return;
        visit(node);
        for (Node n : node.adjacentNodes) {
            loopCnt++;
            dfs(n);
        }
        System.out.println();
    }

    // DFS using stack; similar to pre-order
    static void dfsIterative(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            visit(n);
            // to visit adjacent nodes from left to right
            for (int i = n.adjacentNodes.size() - 1; i >= 0; i--) {
                stack.push(n.adjacentNodes.get(i));
            }
        }
        System.out.println();
    }

    /**
     *             1
     *           /    \
     *         2        3
     *       / | \     / \
     *      4  5  6   7   8
     *               /     \
     *              9       10  
     */
    // BFS using queue; level order search
    static void bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node); // add to the end of queue
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            visit(currentNode);
            for (Node n : currentNode.adjacentNodes) {
                queue.add(n); // add n to the queue to process its adjacent nodes (next level nodes) later
            }
        }
        System.out.println();
    }

    static void bfsRecur(Queue<Node> queue, List<Integer> list) {
        if (queue.isEmpty())
            return;
        Node currentNode = queue.poll();
        list.add(currentNode.value);
        visit(currentNode);
        for (Node n : currentNode.adjacentNodes) {
            queue.add(n);
        }
        bfsRecur(queue, list);
    }

    private static void visit(Node root) {
        System.out.print(root.value + ", ");
    }
}
