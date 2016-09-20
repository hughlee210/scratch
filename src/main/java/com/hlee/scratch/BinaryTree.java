package com.hlee.scratch;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree<T> {

	public static void main(String[] args) {

		// 4
		// 2 6
		// 1 3 5 7

		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		Node<Integer> node = new Node<Integer>(4);
		node.left = new Node<Integer>(2);
		node.right = new Node<Integer>(6);
		node.left.left = new Node<Integer>(1);
		node.left.right = new Node<Integer>(3);
		node.right.left = new Node<Integer>(5);
		node.right.right = new Node<Integer>(7);
		bt.root = node;

		System.out.print("<printInorderRecursive>: ");
		printInorderRecursive(node);
		System.out.println();

		System.out.print("<visitInOrder>: ");
		bt.visitInOrder(true);
		System.out.println();

		int targetSum = 8;
		System.out.println("<findSumPair_usingArray>: ");
		bt.findSumPair_usingArray(targetSum);

		System.out.println("<findSumPair_usingStack>: ");
		bt.findSumPair_usingStack(targetSum);

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

	public static void printInorderRecursive(Node node) {
		if (node == null)
			return;
		printInorderRecursive(node.left);
		System.out.print(node.value + " ");
		printInorderRecursive(node.right);
	}

	public List<T> visitInOrder(boolean print) {
		List<T> list = new ArrayList<T>();
		Node<T> node = root;
		Stack<Node<T>> parentStack = new Stack<BinaryTree.Node<T>>();
		while (!parentStack.isEmpty() || node != null) {
			if (node != null) {
				parentStack.push(node);
				node = node.left;
			} else {
				node = parentStack.pop();
				if (print)
					System.out.print(node.value + " ");
				list.add(node.value);
				node = node.right;
			}
		}
		return list;
	}

	// time complexity: O(N), space: O(N)
	public void findSumPair_usingArray(int sum) {
		List<Integer> list = (List<Integer>) visitInOrder(false); // time: O(N)
		int l = 0;
		int r = list.size() - 1;
		// time: O(N/2)
		while (l < r) {
			if (list.get(l) + list.get(r) == sum) {
				System.out.println("found a pair for sum: " + list.get(l)
						+ " + " + list.get(r) + " = " + sum);
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
		Stack<Node<Integer>> stack1 = new Stack<BinaryTree.Node<Integer>>();
		Stack<Node<Integer>> stack2 = new Stack<BinaryTree.Node<Integer>>();
		boolean stop1 = false, stop2 = false;
		int val1 = 0, val2 = 0;
		Node<Integer> node1 = (Node<Integer>) root;
		Node<Integer> node2 = (Node<Integer>) root;
		while (true) {
			// find next node in normal in-order traversal
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

			// find next node in Reverse in-order traversal
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
				System.out.println("using stack - Found a pair for sum: "
						+ val1 + " + " + val2 + " = " + sum);
				stop1 = stop2 = false;
			} else if (val1 + val2 < sum)
				stop1 = false;
			else
				stop2 = false;

		}
	}

}
