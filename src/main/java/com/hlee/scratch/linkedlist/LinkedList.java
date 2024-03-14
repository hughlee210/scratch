package com.hlee.scratch.linkedlist;

public class LinkedList<T> {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.print();

        list.head = reverse(list.head);
        list.print();

        printReverse(list.head);

        list.clear();
        list.print();

        list.add(6);
        list.print();

        System.out.println("==============================================");

        LinkedList<Integer> l1 = new LinkedList<>();
        l1.push(3);
        l1.push(4);
        l1.push(2);
        System.out.print("list1: ");
        l1.print();

        LinkedList<Integer> l2 = new LinkedList<>();
        l2.push(4);
        l2.push(6);
        l2.push(5);
        System.out.print("list2: ");
        l2.print();

        Node<Integer> result = addTwoNumbers(l1.head, l2.head);
        System.out.print("sum = ");
        print(result);

    }

    static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;
        Node<T> child;

        public Node(T value) {
            this(value, null);
        }

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<T> head;

    // add to tail
    public void add(T e) {
        Node<T> newNode = new Node<>(e);
        if (head == null) {
            head = newNode;
            return;
        }

        Node<T> node = head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = newNode;
    }

    /**
     * add a node to the front of list
     * @param e
     */
    public void push(T e) {
        Node<T> newNode = new Node<>(e, null);
        newNode.next = head;
        head = newNode;
    }

    public void print() {
        System.out.print("Printing the list: ");
        if (head == null) {
            System.out.println(head);
            return;
        }
        Node<T> node = head;
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    static void print(Node<Integer> head) {
        System.out.print("Printing nodes: ");
        if (head == null) {
            System.out.println(head);
            return;
        }
        Node<Integer> node = head;
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    static Node<Integer> reverse(Node<Integer> head) {
        if (head == null) {
            return null;
        }
        Node<Integer> prev = null;
        Node<Integer> curr = head;
        while (curr != null) {
            // we will update current node's next pointer to point to previous node
            // so, we will need to store the next node otherwise we will lose the pointer to next node
            Node<Integer> next = curr.next;
            curr.next = prev; // update next pointer
            // now advance prev and curr pointers
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * [1]->[2]->[3]->[4]->[5]->[6]-> null
     *            M         N
     *  to be
     * [1]->[2]->[5]->[4]->[3]->[6]-> null
     */
    static Node reverseBetween(Node head, int m, int n) {
        int currentPos = 1;
        Node currentNode = head, start = head; // start node is the node just before M
        while (currentPos < m) { // to move start node just before M and currentNode at M
            start = currentNode;
            currentNode = currentNode.next;
            currentPos++;
        }
        Node newList = null, tail = currentNode; // currentNode at M will end up as tail
        while (currentPos >= m && currentPos <= n) { // boundary between m and n
            Node next = currentNode.next;
            currentNode.next = newList;
            newList = currentNode;
            currentNode = next;
            currentPos++;
        }
        start.next = newList;
        tail.next = currentNode; // currentNode is at n+1 position

        if (m > 1) {
            return head;
        } else {
            return newList;
        }
    }

//    private void reverseRecur(LinkedList.Node<T> node) {
//        if (node == null)
//            return;
//        if (node.next == null) { // base case; reached the last node, so this node become the head
//            head = node;
//            return;
//        }
//        reverseRecur(node.next);
//        node.next.next = node; // change the next pointer to previous node
//        node.next = null; // cut the existing pointer
//    }
//
//    public void reverseRecursively() {
//        reverseRecur(head);
//    }
//
//    public void printReversely() {
//        System.out.print("Printing in reverse order: ");
//        printReverse(head);
//        System.out.println();
//    }

    // remove a node from head and return the value removed
    public T remove() {
        if (head == null)
            return null;
        T value = head.value;
        Node<T> next = head.next;
        head.value = null;
        head.next = null;
        head = next;
        return value;
    }

    public void clear() {
        T itemRemoved;
        while ((itemRemoved = remove()) != null) {
            System.out.println(itemRemoved + " removed from the head.");
        }
        System.out.println("- cleared the list.");
    }

    static void printReverse(Node<Integer> node) {
        if (node == null) { // base case
            return;
        }
        printReverse(node.next);
        // after reaching to the last node print the number
        System.out.print(node.value + " ");
    }

    static boolean hasLoop(Node<Integer> head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next; // move it 1 hop
            fast = fast.next.next; // move it 2 hops
            if (slow == fast) { // fast caught up to slow, so there is a loop
                return true;
            }
        }
        return false; // fast reached null, so list terminates
    }

    static Node findCycleStartNode(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next; // move it 1 hop
            fast = fast.next.next; // move it 2 hops
            if (slow == fast) { // fast caught up to slow, so there is a loop
                break;
            }
        }
        if (fast == null || fast.next == null) { // fast reached null, so list terminates
            return null;
        }

        Node p1 = head, p2 = slow; // p2 can be either slow or fast
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    // find middle node of non-circular list
    static Node findMiddleNode(Node head) {
        if (head == null) {
            return null;
        }
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * null<-[1]<->[2]<->[3]-> null
     *              |
     *      null <-[4]<->[5]<->[6]-> null
     *                    |
     *            null <-[7]-> null
     */
    Node flattenMultiLevelList(Node head) {
        if (head == null) {
            return null;
        }
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.child == null) { // child is null, so move to next
                currentNode = currentNode.next;
            } else { // has child node
                Node tail = currentNode.child;
                while (tail.next != null) { // move tail to the end
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

    // time: O(max(m,n))
    // space: O(max(m,n)) + 1 at most
    static Node<Integer> addTwoNumbers(Node<Integer> head1, Node<Integer> head2) {
        Node<Integer> dummyHead = new Node<>(0); // create a dummy head node to keep the head of result
        Node<Integer> a = head1, b = head2, curr = dummyHead;
        int carry = 0, x, y, sum;
        while (a != null || b != null) {
            x = (a != null) ? a.value : 0;
            y = (b != null) ? b.value : 0;
            sum = carry + x + y;
            carry = sum / 10;
            curr.next = new Node<>(sum % 10);

            if (a != null)
                a = a.next;
            if (b != null)
                b = b.next;
            curr = curr.next;
        }
        if (carry > 0) {
            curr.next = new Node<>(carry);
        }
        return dummyHead.next;
    }

}
