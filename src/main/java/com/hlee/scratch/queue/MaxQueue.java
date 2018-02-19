package com.hlee.scratch.queue;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MaxQueue<T extends Number> {

    private static class Node {
        Integer value;
        Integer maxUptoCurrentNode; // to keep track of max value of nodes in the same stack
        Node next;

        public Node(Integer value, Integer currentMax) {
            this.value = value;
            this.maxUptoCurrentNode = currentMax;
        }
    }

    private static class Stack {
        Node top;

        public void push(Node node) {
            node.next = top;
            top = node;
        }

        public Node peek() {
            if (top != null) {
                return top;
            }
            return null;
        }

        public Node pop() {
            if (top != null) {
                Node node = top;
                top = top.next;
                return node;
            }
            throw new NoSuchElementException();
        }

        public boolean isEmpty() {
            return top == null;
        }
    }
    /////////////////////////////////////////////////////////////////////////

    Stack in = new Stack();
    Stack out = new Stack();
    int size;
    Integer currentMax = Integer.MIN_VALUE;

    public void add(Integer value) {

        if (value > currentMax) {
            currentMax = value;
        }

        // keep track of current max in the node item along with node value
        in.push(new Node(value, currentMax));

        size++;
    }

    public Integer remove() {
        if (out.isEmpty()) {
            if (in.isEmpty()) {
                throw new NoSuchElementException();
            }
            // move items from 'in' to 'out' stack
            moveFromInToOut();
        }
        size--;
        return out.pop().value;
    }

    public int size() {
        return size;
    }

    private void moveFromInToOut() {
        while (!in.isEmpty()) {
            // when moving items to out stack recalculate max
            if (out.isEmpty()) {
                // reset max value
                currentMax = Integer.MIN_VALUE;
            }
            Node node = in.pop();
            if (node.value > currentMax) {
                currentMax = node.value;
            }
            node.maxUptoCurrentNode = currentMax;
            out.push(node);
        }
    }

    public Integer getMaxValue() {
        if (!out.isEmpty()) {
            Node node = out.peek();
            return node != null ? node.maxUptoCurrentNode : null;
        } else {
            Node node = in.peek();
            return node != null ? node.maxUptoCurrentNode : null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Test cases
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void whenItemsAddedAndRemovedThenReturnsCorrectSize() {
        // setup
        MaxQueue<Integer> queue = new MaxQueue<Integer>();

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
        MaxQueue<Integer> queue = new MaxQueue<Integer>();

        queue.remove();
    }

    @Test
    public void whenMaxValueThenReturnsCorrectMax() {
        // setup
        MaxQueue<Integer> queue = new MaxQueue<Integer>();

        assertEquals(null, queue.getMaxValue());

        queue.add(2);
        queue.add(10);
        queue.add(7);

        assertEquals(10, queue.getMaxValue().intValue());

        queue.remove();
        assertEquals(10, queue.getMaxValue().intValue());

        queue.remove();
        assertEquals(7, queue.getMaxValue().intValue());

    }

}
