package com.hlee.scratch.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    // Time complexity: O(n)
    // Space complexity: O(1)
    boolean hasCycle(Node head) {
        if (head == null) {
            return false;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // Time: O(n)
    // Space: O(n)
    boolean hasCycleUsingHashSet(Node head) {
        if (head == null) {
            return false;
        }
        Set<Node> seenNodes = new HashSet<>();
        Node curr = head;
        while (curr != null) {
            if (seenNodes.contains(curr)) {
                return true;
            }
            seenNodes.add(curr);
            curr = curr.next;
        }
        return false;
    }

}
