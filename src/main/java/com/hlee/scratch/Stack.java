package com.hlee.scratch;

public class Stack<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.valueOf(1));
        stack.push(Integer.valueOf(2));
        stack.push(Integer.valueOf(3));

        System.out.println("stack size = " + stack.size());
        System.out.println("top = " + stack.peek());
        Integer data = null;
        while ((data = stack.pop()) != null) {
            System.out.println("data popped = " + data);
        }

        System.out.println("stack size = " + stack.size());
    }

    private Node<T> top;

    public void push(T data) {
        Node<T> node = new Node<T>(data);
        node.next = top;
        top = node;
    }

    public T peek() {
        if (top != null) {
            return top.data;
        }
        return null;
    }

    public T pop() {
        if (top != null) {
            T data = top.data;
            top = top.next;
            return data;
        }
        return null;
    }

    public int size() {
        int count = 0;
        Node<T> node = top;
        while (node != null) {
            node = node.next;
            count++;
        }
        return count;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
