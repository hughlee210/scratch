package com.hlee.scratch;

public class QueueWithStacks {

    Stack<Integer> in = new Stack<>();
    Stack<Integer> out = new Stack<>();

    // Time: O(1), Space: O(1)
    void enqueue(Integer val) {
        this.in.push(val);
    }

    // Time: O(N)
    Integer dequeue() {
        if (this.out.size() == 0) {
            while (this.in.size() > 0) {
                this.out.push(this.in.pop());
            }
        }
        return this.out.pop();
    }

    // Time: O(N)
    Integer peek() {
        if (this.out.size() == 0) {
            while (this.in.size() > 0) {
                this.out.push(this.in.pop());
            }
        }
        return this.out.peek();
    }

    // Time: O(1)
    boolean isEmpty() {
        return this.in.size() == 0 && this.out.size() == 0;
    }
}
