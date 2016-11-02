package com.hlee.scratch;

public class LinkedList<T> {

    private static class Node<T> {
        T item;
        Node<T> next;

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    private Node<T> head;

    public void add(T e) {
        final Node<T> newNode = new Node<T>(e, null);
        if (head == null) {
            head = newNode;
            return;
        }

        Node<T> node = head;
        while (node.next != null)
            node = node.next;
        node.next = newNode;
    }

    public void print() {
        if (head == null) {
            System.out.println(head);
            return;
        }
        Node<T> node = head;
        while (node != null) {
            System.out.print(node.item + " ");
            node = node.next;
        }
        System.out.println();
    }

    public void reverseList() {
        // practice reverse method

    }

    public void reverse() {
        if (head == null || head.next == null)
            return;
        Node<T> currNode = head;
        Node<T> nextNode = head.next;
        Node<T> temp = null;
        head.next = null;
        while (nextNode != null) {
            temp = nextNode.next;
            nextNode.next = currNode;
            currNode = nextNode;
            nextNode = temp;
        }
        head = currNode;
    }

    private void reverseRecur_ex(Node<T> node) {
        // practice

    }

    private void reverseRecur(Node<T> node) {
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

    public T remove() {
        if (head == null)
            return null;
        T item = head.item;
        Node<T> next = head.next;
        head.item = null;
        head.next = null;
        head = next;
        return item;
    }

    public void clear() {
        T itemRemoved;
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

    private static boolean hasLoop(Node node) {
        if (node == null)
            return false;
        Node slow = node;
        Node fast = node;
        while (fast != null && fast.next != null) {
            slow = slow.next; // 1 hop
            fast = fast.next.next; // 2 hops
            if (slow == fast) // fast caught up to slow, so there is a loop
                return true;
        }
        return false; // fast reached null, so the list terminates
    }

    private static Node findMiddleNode(Node head) {
        if (head == null)
            return null;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
