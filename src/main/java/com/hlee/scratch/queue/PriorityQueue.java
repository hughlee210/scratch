package com.hlee.scratch.queue;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PriorityQueue {

    private List<Integer> heap = new LinkedList<>();
//    private Comparator<Integer> comparator;

    public PriorityQueue() {

    }

    public int size() {
        return this.heap.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int peek() {
        return this.heap.get(0); // return top of the priority queue
    }

    /**
     * return index of parent
     */
    private int parentIndex(int index) {
       return (index - 1) / 2;
    }

    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }

    private void swap(int i, int j) {
        Integer temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private int compare(Integer i, Integer j) {
        return Integer.compare(heap.get(i), heap.get(j));
    }

    /**
     *                    15
     *                 /      \
     *             10           5
     *          /      \
     *         7       3
     *
     *      [15, 10, 5, 7, 3 ]
     */

    public int add(int value) {
        this.heap.add(value); // add to the end
        this.siftUp(); // compare with parent and bubble up if greater than parent
        return this.size();
    }

    private void siftUp() {
        int nodeIndex = this.size() - 1; // index of last node which newly added node is at
        while (nodeIndex > 0 && this.compare(nodeIndex, parentIndex(nodeIndex)) > 0) {
            this.swap(nodeIndex, parentIndex(nodeIndex));
            nodeIndex = parentIndex(nodeIndex);
        }
    }

    public int poll() {
        if (this.size() > 1) {
            this.swap(0, this.size() - 1); // swap first and last - move the top node to last
        }
        int poppedValue = this.heap.remove(this.heap.size() - 1); //
        this.siftDown(); // compare with children and bubble down if less than child
        return poppedValue;
    }
    private void siftDown() {
        int nodeIndex = 0;
        while ((leftChildIndex(nodeIndex) < this.size() && compare(leftChildIndex(nodeIndex), nodeIndex) > 0) ||
                (rightChildIndex(nodeIndex) < this.size() && compare(rightChildIndex(nodeIndex), nodeIndex) > 0)) {
            int greaterNodeIndex = rightChildIndex(nodeIndex) < this.size() &&
                    this.compare(rightChildIndex(nodeIndex), leftChildIndex(nodeIndex)) > 0 ?
                    rightChildIndex(nodeIndex) : leftChildIndex(nodeIndex);
            this.swap(greaterNodeIndex, nodeIndex);
            nodeIndex = greaterNodeIndex;
        }
    }

    @Override
    public String toString() {
        return this.heap.toString();
    }
}
