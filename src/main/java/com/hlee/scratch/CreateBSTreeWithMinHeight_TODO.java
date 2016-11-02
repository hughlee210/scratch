package com.hlee.scratch;

public class CreateBSTreeWithMinHeight_TODO {

	public static void main(String[] args) {		
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		createMinHeightBST(arr);	
	}
	
	public static TreeNode createMinHeightBST(int[] arr) {
		return createMinHeightBST(arr, 0, arr.length -1 );
	}

	// Time: O(n)
	public static TreeNode createMinHeightBST(int[] arr, int start, int end) {
		System.out.println("method called");
		if (end < start)
			return null;
		int mid = (start + end) / 2;
		TreeNode node = new TreeNode(arr[mid]);
		node.left = createMinHeightBST(arr, start, mid - 1);
		node.right = createMinHeightBST(arr, mid + 1, end);
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
