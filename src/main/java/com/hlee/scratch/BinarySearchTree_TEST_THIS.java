package com.hlee.scratch;

import java.util.Stack;

public class BinarySearchTree_TEST_THIS {

	public static void main(String[] args) {

		BinarySearchTree_TEST_THIS bst = new BinarySearchTree_TEST_THIS();
		bst.insert(5);
		bst.insert(2);
		bst.insert(3);
		bst.insert(4);
		bst.insert(6);
		bst.insert(7);
		bst.insert(8);
		bst.insert(9);
		bst.insert(1);

		bst.inOrder(bst.root);
		System.out.println("--- end of printing inOrder");
		bst.inOrderIterative(bst.root);
		System.out.println("--- end of printing inOrderIterative");
		bst.preOrder(bst.root);
		System.out.println("--- end of printing preOrder");
		bst.preOrderIter(bst.root);
		System.out.println("--- end of printing preOrderIterative");

		bst.lookup(3);
		bst.lookup(9);
		bst.lookup(300);
		
		int k = 4;
		Node result = bst.inOrderIterativeKthSmallest(bst.root, k);
		System.out.println();
		System.out.println(k + "th smallest node value: " + (result==null? "null" : result.data));
		
		System.out.println("find " + k + "th smallest using inOrder recursive");
		bst.inOrderKthSmallest(bst.root, k); // this method iterates thru all nodes even after finding the answer
		System.out.println("===============");
		
		int nodeSize = bst.size(bst.root);
		System.out.println("root node size = " + nodeSize);
		
		System.out.println("find " + k + "th smallest using size of left subtree and binary search");
		int dataFound = bst.findKthSmallest(bst.root, k); // using size of left subtree
		System.out.println("data found: " + dataFound);

		// find node pair sum target number
		bst.findPairSum();
		
		
	}
	
	private static int SMALLEST_COUNT = 0;

	// Root node pointer. Will be null for an empty tree. 
	private Node root; 

	private static class Node { 
		int data; 
		Node left; 
		Node right; 

		public Node(int data) { 
			this.data = data; 
			left = null; 
			right = null; 
		} 
	} 

	public BinarySearchTree_TEST_THIS() { 
	} 

	/** 
	   Inserts the given data into the binary tree. 
	   Uses a recursive helper. 
	 */ 
	public void insert(int data) { 
		root = insert(root, data); 
	} 

	/** 
	   Recursive insert -- given a node pointer, recur down and 
	   insert the given data into the tree. Returns the new 
	   node pointer (the standard way to communicate 
	   a changed pointer back to the caller). 
	 */ 
	private Node insert(Node node, int data) { 
		if (node == null) { 
			node = new Node(data); 
		} 
		else { 
			if (data <= node.data) { 
				node.left = insert(node.left, data); 
			} 
			else { 
				node.right = insert(node.right, data); 
			} 
		} 
		return node; // in any case, return the new pointer to the caller 
	} 

	// see http://en.wikipedia.org/wiki/Tree_traversal#Iterative_Traversal
	//
	public void inOrder(Node node) {
		if (node == null)
			return;
		inOrder(node.left);
		System.out.print(node.data + ", "); // visit the node
		inOrder(node.right);		
	}
	
	public void inOrderIterative(Node node) {
        Stack<Node> parentStack = new Stack<>();
		while (!parentStack.isEmpty() || node != null) {
			if (node != null) {
				parentStack.push(node);
				node = node.left;
			}
			else {
				node = parentStack.pop();
				System.out.print(node.data + ", "); // visit the node
				node = node.right;
			}
		}
	}
	
	public void preOrder(Node node) {
		if (node == null)
			return;
		System.out.print(node.data + ", "); // visit the node
		preOrder(node.left);
		preOrder(node.right);
	}
	
	public void preOrderIter(Node node) {
        Stack<Node> parentStack = new Stack<>();
		//parentStack.push(null);
		Node top = node;
		while (top != null) {
			System.out.print(top.data + ", ");
			if (top.right != null)
				parentStack.push(top.right);
			if (top.left != null)
				parentStack.push(top.left);
			
			if (!parentStack.isEmpty())
				top = parentStack.pop();
			else
				top = null;
		}
	}
	
	public void inOrderKthSmallest(Node node, int k) {
		if (node == null)
			return;

		inOrderKthSmallest(node.left, k);

		SMALLEST_COUNT++; // increment the counter when visiting the node
		
		if (SMALLEST_COUNT == k)
			System.out.print(k + "th smallest = " + node.data + ", "); // visit the node
		else
			System.out.print(node.data + ", ");

		inOrderKthSmallest(node.right, k);		
	}

	// Using stack pop count
	public Node inOrderIterativeKthSmallest(Node node, int k) {
        Stack<Node> parentStack = new Stack<>();
		int popCount = 0;
		while (!parentStack.isEmpty() || node != null) {
			if (node != null) {
				parentStack.push(node);
				node = node.left;
			}
			else {
				node = parentStack.pop();
				popCount++;
				System.out.print(node.data + ", "); // visit the node
				if (popCount == k)
					return node;
				
				node = node.right;
			}
		}
		
		return null;
	}
	
	// Good
	public int size(Node node) {
		System.out.print("* ");
		if (node == null)
			return 0;
		else
			return size(node.left) + 1 + size(node.right);
	}
	
	// Good
	public int findKthSmallest(Node node, int k) {
		if (node == null)
			return -1;
		
		int leftSize = size(node.left);
		if (k == leftSize + 1) // current node's size = leftSize + 1
			return node.data;
		else if (k < leftSize + 1)
			return findKthSmallest(node.left, k);
		else
			return findKthSmallest(node.right, k - (leftSize + 1));
	}
	

	//TODO
	/**
		Breadth-first
		
		levelorder(root)
		  q = empty queue
		  q.enqueue(root)
		  while not q.empty do
		    node := q.dequeue()
		    visit(node)
		    if node.left �� null then
		      q.enqueue(node.left)
		    if node.right �� null then
		      q.enqueue(node.right)
	 */
	
	/** 
	   Returns true if the given target is in the binary tree. 
	   Uses a recursive helper. 
	 */ 
	public boolean lookup(int data) { 
		return lookup(root, data); 
	} 

	/** 
	   Recursive lookup  -- given a node, recur down searching for the given data.
	   Time complexity: O(log n) or O(n) depending upon how balanced the tree is 
	 */ 
	private boolean lookup(Node node, int data) { 
		if (node == null) {
			System.out.println("data " + data + " NOT found");
			return false; 
		}

		if (data == node.data) { 
			System.out.println("data " + data + " found!");
			return true; 
		} 
		else if (data < node.data) { 
			return lookup(node.left, data); 
		} 
		else { 
			return lookup(node.right, data); 
		} 
	}
	
	private int[] sortedArr;
	private int arrIndex; 
	
	public void findPairSum() {
		if (root == null)
			return;
		
		sortedArr = new int[size(root)];
		arrIndex = 0;
		// populate the integer array. time: O(n)
		inOrder(root, sortedArr);
		
		for (int i = 0; i < sortedArr.length; i++)
			System.out.print(sortedArr[i] + ", ");
		System.out.println(" <-- sortedArr");
		
	}
	
	public void inOrder(Node node, int[] arr) {
		if (node == null || arr == null)
			return;
		inOrder(node.left, arr);
		arr[arrIndex++] = node.data;
		inOrder(node.right, arr);
	}
}
