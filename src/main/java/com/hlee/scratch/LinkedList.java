package com.hlee.scratch;

public class LinkedList<E> {

	private static class Node<E> {
		E item;
		Node<E> next;

		public Node(E item, Node<E> next) {
			this.item = item;
			this.next = next;
		}
	}

	private Node<E> head;

	public void add(E e) {
		final Node<E> newNode = new Node<E>(e, null);
		if (head == null) {
			head = newNode;
			return;
		}

		Node<E> node = head;
		while (node.next != null)
			node = node.next;
		node.next = newNode;
	}

	public void print() {
		if (head == null) {
			System.out.println(head);
			return;
		}
		Node<E> node = head;
		while (node != null) {
			System.out.print(node.item + " ");
			node = node.next;
		}
		System.out.println();
	}

	public void reverse() {
		if (head == null || head.next == null)
			return;
		Node<E> currNode = head;
		Node<E> nextNode = head.next;
		Node<E> temp = null;
		head.next = null;
		while (nextNode != null) {
			temp = nextNode.next;
			nextNode.next = currNode;
			currNode = nextNode;
			nextNode = temp;
		}
		head = currNode;
	}

	private void reverseRecur(Node<E> node) {
		if (node == null)
			return;
		if (node.next == null) {
			head = node;
			return;
		}

		reverseRecur(node.next);
		node.next.next = node; // change the next pointer to previous node
		node.next = null; // cut the existing pointer
	}

	public void reverseRecursely() {
		reverseRecur(head);
	}

	public E remove() {
		if (head == null)
			return null;
		E item = head.item;
		Node<E> next = head.next;
		head.item = null;
		head.next = null;
		head = next;
		return item;
	}

	public void clear() {
		E itemRemoved;
		while ((itemRemoved = remove()) != null) {
			System.out.println(itemRemoved + " removed");
		}
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(5);
		list.print();

		list.reverseRecursely();
		list.print();

		list.clear();
		list.print();

		list.reverse();
		list.print();

		list.add(6);
		list.print();

		list.reverse();
		list.print();

	}

	private static boolean hasCircle(Node<Integer> node) {
		if (node == null)
			return false;
		Node slow = node;
		Node fast = node;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		if (fast == null || fast.next == null)
			return false;
		return slow == fast;
	}

}
