package com.hlee.scratch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class QueueTest<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    Node<T> first;
    Node<T> last;

    public void add(T data) {
        if (first == null) {
            first = last = new Node<T>(data);
        } else {
            last.next = new Node<T>(data);
            last = last.next;
        }
    }

    public T remove() {
        if (first != null) {
            T data = first.data;
            first = first.next;
            return data;
        }
        return null;
    }

    public int size() {
        int count = 0;
        Node<T> node = first;
        while (node != null) {
            node = node.next;
            count++;
        }
        return count;
    }

    @Test
    public void whenAfterAddThenReturnsCorrectSize() {
        // setup
        QueueTest<Integer> queue = new QueueTest<Integer>();
        assertEquals(0, queue.size());

    }

    //    public static void main(String[] args) {
    //
    //        QueueTest<String> queue = new QueueTest<>();
    //        queue.add("a");
    //        queue.add("b");
    //        queue.add("c");
    //
    //        System.out.println("Queue size = " + queue.size());
    //        System.out.println("data dequeued = " + queue.remove());
    //        System.out.println("data dequeued = " + queue.remove());
    //        System.out.println("data dequeued = " + queue.remove());
    //        System.out.println("Queue size = " + queue.size());
    //    }

}
