package com.hlee.scratch.queue;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Queue<T> {

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
        throw new NoSuchElementException();
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

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Test cases
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void whenItemsAddedAndRemovedThenReturnsCorrectSize() {
        // setup
        Queue<Integer> queue = new Queue<Integer>();

        assertEquals(0, queue.size());

        queue.add(2);
        queue.add(1);
        assertEquals(2, queue.size());

        queue.remove();
        assertEquals(1, queue.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenRemovingFromEmptyQueueThrowNoSuchElementException() {
        // setup
        Queue<Integer> queue = new Queue<Integer>();

        queue.remove();
    }

    //////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {

        Queue<String> queue = new Queue<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");

        System.out.println("Queue size = " + queue.size());
        System.out.println("data dequeued = " + queue.remove());
        System.out.println("data dequeued = " + queue.remove());
        System.out.println("data dequeued = " + queue.remove());
        System.out.println("Queue size = " + queue.size());
    }

}
