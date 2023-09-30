package com.hlee.scratch.linkedlist;

public class MergeSortedLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.add(2);
        listNode.add(4);
        listNode.print();

    }

    static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }

        public void add(int value) {
            ListNode tail = this.next;
            while (tail != null) {
                tail = tail.next;
            }

            tail = new ListNode(value);
        }

        public void print() {
            System.out.print(value + "->");
            if (next == null) {
                System.out.println((Object) null);
            } else {
                next.print();
            }
        }
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
    static ListNode mergeTwoSortedLinkedList_recur(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.value < l2.value) {
            l1.next = mergeTwoSortedLinkedList_recur(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoSortedLinkedList_recur(l1, l2.next);
            return l2;
        }
    }

    /**
     * Iterator through both lists, compare head nodes one by one
     * connect the smaller one first and repeat the iteration.
     *
     * Time complexity: O(n + m)
     * Space complexity: O(1) no scaling space needed
     */
    static ListNode mergeTwoSortedLinkedList(ListNode l1, ListNode l2) {
        // keep unchanging reference to node ahead of the return node
        ListNode preHead = new ListNode(-1);
        ListNode curr = preHead;
        while (l1 != null && l2 != null) {
            if (l1.value <= l2.value) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        // at least one of l1 and l2 can still have nodes at this point,
        // so, connect the non-null list to the end of the merged list.
        curr.next = l1 == null ? l2 : l1;
        return preHead.next;
    }

}
