package com.hlee.scratch;

public class MultiLevelDoublyLinkedList<T> {

    static class ListNode<T> {
        T value;
        ListNode<T> next;
        ListNode<T> prev;
        ListNode<T> child;
    }

    ListNode<T> flatten(ListNode<T> head) {
        if (head == null)
            return null;
        ListNode<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.child == null) {
                currentNode = currentNode.next;
            } else {
                ListNode<T> tail = currentNode.child;
                while (tail.next != null) {
                    tail = tail.next;
                }

                tail.next = currentNode.next;
                if (tail.next != null) {
                    tail.next.prev = tail;
                }

                currentNode.next = currentNode.child;
                currentNode.next.prev = currentNode;
                currentNode.child = null;
            }
        }
        return head;
    }
}
