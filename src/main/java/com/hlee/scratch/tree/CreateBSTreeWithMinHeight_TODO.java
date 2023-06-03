package com.hlee.scratch.tree;

public class CreateBSTreeWithMinHeight_TODO {

	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		createMinHeightBST(arr);
	}

	public static TreeNode createMinHeightBST(int[] arr) {
		return createMinHeightBST(arr, 0, arr.length -1 );
	}

	// Time: O(n)
	public static TreeNode createMinHeightBST(int[] arr, int l, int r) {
        // assume the arr is sorted in ascending order
		System.out.println("method called");
        if (l > r)
			return null;
        int mid = l + (r - l) / 2; // (l + r) / 2;
		TreeNode node = new TreeNode(arr[mid]);
		node.left = createMinHeightBST(arr, l, mid - 1);
		node.right = createMinHeightBST(arr, mid + 1, r);
		return node;
	}

	static class TreeNode {
		TreeNode left;
		TreeNode right;
		int data;
		public TreeNode(int data) {
			this.data = data;
		}
	}
}
