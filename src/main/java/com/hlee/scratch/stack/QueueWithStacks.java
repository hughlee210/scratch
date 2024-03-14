package com.hlee.scratch.stack;

import com.hlee.scratch.stack.Stack;

public class QueueWithStacks {

    Stack<Integer> in = new Stack<>();
    Stack<Integer> out = new Stack<>();

    // Time: O(1), Space: O(1)
    void enqueue(Integer val) {
        in.push(val);
    }

    // Time: Amortized O(1), worst case O(N)

    /**
     * Time complexity: Amortized O(1), worst case O(N)
     *  but when out stack is not empty, the algorithm has O(1) time complexity.
     *
     * Amortized analysis gives the average performance (over time) of each operation in the worst case.
     * The basic idea is that a worst case operation can alter the state in such a way that the worst
     * case cannot occur again for a long time, thus amortizing its cost.
     */
    Integer dequeue() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    // Time: O(N)
    Integer peek() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.peek();
    }

    // Time: O(1)
    boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }
}
