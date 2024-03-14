package com.hlee.scratch.linkedlist;

public class MergeSortedLinkedList {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(4);
        list.print();

        LinkedList list2 = new LinkedList();
        list2.add(3);
        list2.add(6);
        list2.add(10);
        list2.print();

        ListNode merged = mergeTwoSortedList_iter(list.head, list2.head);
        print(merged);


    }

    static class LinkedList {
        private ListNode head;

        public void add(int value) {
            if (head == null) {
                head = new ListNode(value);
                return;
            }
            ListNode node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new ListNode(value);
        }

        public void print() {
            if (head == null) {
                System.out.println(head);
                return;
            }
            ListNode node = head;
            while (node != null) {
                System.out.print(node.value + " -> ");
                node = node.next;
            }
            System.out.println("null");
        }
    }

    static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    static void print(ListNode node) {
        if (node == null) {
            System.out.println(node);
            return;
        }
        while (node != null) {
            System.out.print(node.value + " -> ");
            node = node.next;
        }
        System.out.println("null");
    }

    /**
     * We can recursively define the result of a merge operation on two lists as the following:
     * if (list1[0] < list2[0])
     *   list1[0] + merge(list1[1:], list2)
     * else
     *   list2[0] + merge(list1, list2[1:]
     *  The smaller of the two lists' heads plus the result of a merge on the rest of the elements.
     *
     * Time complexity: O(n + m)
     *   Because each recursive call increments the pointer to l1 or l2 by one,
     *   there will be exactly one call to the method per element in each list.
     * Space complexity: O(n + m)
     *   The first call to the method does not return until the ends of both l1 and l2
     *   have been reached, so n + m stack frames consume O(n + m) space.
     */
    static ListNode mergeTwoSortedList_recur(ListNode n1, ListNode n2) {
        if (n1 == null) {
            return n2;
        } else if (n2 == null) {
            return n1;
        } else if (n1.value < n2.value) {
            n1.next = mergeTwoSortedList_recur(n1.next, n2);
            return n1;
        } else {
            n2.next = mergeTwoSortedList_recur(n1, n2.next);
            return n2;
        }
    }

    /**
     * Iterator through both lists, compare head nodes one by one
     * connect the smaller one first and repeat the iteration.
     *
     * Time complexity: O(n + m)
     * Space complexity: O(1) no scaling space needed
     */
    static ListNode mergeTwoSortedList_iter(ListNode n1, ListNode n2) {
        // keep unchanging reference to node ahead of the return node
        ListNode preHead = new ListNode(-1);
        ListNode curr = preHead;
        while (n1 != null && n2 != null) {
            if (n1.value <= n2.value) {
                curr.next = n1; // add n1 node to next
                n1 = n1.next; // move to next node
            } else {
                curr.next = n2; // add n2 node to next
                n2 = n2.next; // move to next node
            }
            curr = curr.next;
        }

        // at least one of l1 and l2 can still have nodes at this point,
        // so, connect the non-null list to the end of the merged list.
        curr.next = n1 == null ? n2 : n1;
        return preHead.next;
    }

}
