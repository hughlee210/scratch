package com.hlee.scratch;

public class LinkedList<T> {

    static class Node<T> {
        T item;
        Node<T> next;

        public Node(T item) {
            this(item, null);
        }

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    private Node<T> head;

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.print();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.print();

        list.printReversely();
        list.print();

        list.reverseRecursively();
        list.print();

        list.clear();
        list.print();

        list.reverse();
        list.print();

        list.add(6);
        list.print();

        list.reverse();
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

    // add to tail
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
            System.out.print(node.item + " ");
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
            System.out.print(node.item + " ");
            node = node.next;
        }
        System.out.println();
    }

    public void reverse_ex() {
        // practice reverse method
        if (head == null || head.next == null)
            return;
        Node<T> currNode = head;
        Node<T> nextNode = head.next;
        Node<T> tempNode = null;
        head.next = null;
        while (nextNode != null) {
            tempNode = nextNode.next; // store next pointer of the next node before updating the pointer.
            nextNode.next = currNode;
            currNode = nextNode;
            nextNode = tempNode;
        }
        head = currNode;
    }

    public void reverse() {
        if (head == null || head.next == null)
            return;
        Node<T> currNode = head;
        Node<T> nextNode = head.next;
        Node<T> temp = null;
        head.next = null;
        while (nextNode != null) {
            temp = nextNode.next; // store the next pointer of the next node before update the pointer
            nextNode.next = currNode;
            currNode = nextNode;
            nextNode = temp;
        }
        head = currNode;
    }

    private void reverseRecur_ex(Node<T> node) {
        // practice
        if (node == null)
            return;
        if (node.next == null) {
            head = node;
            return;
        }
        reverseRecur_ex(node.next);
        node.next.next = node; // change the next pointer to previous node
        node.next = null; // cut the existing pointer        
    }

    private void reverseRecur(Node<T> node) {
        if (node == null)
            return;
        if (node.next == null) { // reached the last node, so this node become the head
            head = node;
            return;
        }
        reverseRecur(node.next);
        node.next.next = node; // change the next pointer to previous node
        node.next = null; // cut the existing pointer
    }

    public void reverseRecursively() {
        reverseRecur(head);
    }

    public void printReversely() {
        System.out.print("Printing in reverse order: ");
        printReverse(head);
        System.out.println();
    }

    private void printReverse(Node<T> node) {
        if (node == null)
            return;

        printReverse(node.next);

        // after everything else is printed 
        System.out.print(node.item + " ");
    }

    // remove a node from head and return the value removed
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
            System.out.println(itemRemoved + " removed from the head.");
        }
        System.out.println("- cleared the list.");
    }

    // time: O(max(m,n))
    // space: O(max(m,n)) + 1 at most
    static Node<Integer> addTwoNumbers(Node<Integer> head1, Node<Integer> head2) {
        Node<Integer> dummyHead = new Node<>(0); // create a dummy head node to keep the head of result
        Node<Integer> a = head1, b = head2, curr = dummyHead;
        int carry = 0, x, y, sum;
        while (a != null || b != null) {
            x = (a != null) ? a.item : 0;
            y = (b != null) ? b.item : 0;
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
