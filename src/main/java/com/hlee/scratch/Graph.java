package com.hlee.scratch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {

    public static void main(String[] args) {

        List<Node> nodes = createTestNodes();
        Node node = nodes.get(0);
        dfs(node);
        System.out.println("call count = " + count + ", loop count = " + loopCnt);
        resetNodes(nodes);

        dfsIterative(node);
        resetNodes(nodes);

        bfs(node);
        resetNodes(nodes);
    }

    static List<Node> createTestNodes() {
        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        node.adjacentNodes.add(node2);
        node.adjacentNodes.add(node3);
        node2.adjacentNodes.add(node4);
        node2.adjacentNodes.add(node5);
        node2.adjacentNodes.add(node6);
        node3.adjacentNodes.add(node7);
        node3.adjacentNodes.add(node8);
        node7.adjacentNodes.add(node9);
        node8.adjacentNodes.add(node10);
        List<Node> list = new ArrayList<>();
        list.add(node);
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

    static void resetNodes(List<Node> nodes) {
        for (Node node : nodes) {
            node.visited = false;
        }
    }

    static int count = 0;
    static int loopCnt = 0;

    static class Node {
        boolean visited;
        int value;
        List<Node> adjacentNodes = new ArrayList<>();

        public Node(int value) {
            this.value = value;
        }
    }

    static void dfs(Node node) {
        count++;
        if (node == null)
            return;
        visit(node);
        for (Node n : node.adjacentNodes) {
            loopCnt++;
            if (!n.visited)
                dfs(n);
        }
        System.out.println();
    }

    static void dfsIterative(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            if (!n.visited) {
                visit(n);
                for (int i = n.adjacentNodes.size() - 1; i >= 0; i--) {
                    stack.push(n.adjacentNodes.get(i));
                }
            }
        }
        System.out.println();
    }

    static void bfs(Node node) {
        Queue<Node> q = new LinkedList<>();
        visit(node);
        q.add(node); // add to the end of queue
        while (!q.isEmpty()) {
            Node root = q.poll();
            for (Node n : root.adjacentNodes) {
                if (!n.visited) {
                    visit(n);
                    q.add(n);
                }
            }
        }
        System.out.println();
    }

    private static void visit(Node root) {
        System.out.print(root.value + ", ");
        root.visited = true;
    }
}
