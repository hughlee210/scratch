package com.hlee.scratch;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private DoublyLinkedList cacheList;
    private Map<Integer, Node> cacheMap;
    private final int cacheSize;

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        this.cacheList = new DoublyLinkedList(cacheSize);
        this.cacheMap = new HashMap<Integer, Node>();
    }

    public void accessData(int dataKey) {
        Node node = null;
        if (cacheMap.containsKey(dataKey)) {
            // If dataKey is present in the cache, move the page to the start of list
            node = cacheMap.get(dataKey);
            cacheList.moveDataToHead(node);
        } else {
            // If the data is not present in the cache, add the data to the cache
            if (cacheList.currSize == cacheList.cacheSize) {
                // If cache is full, we will remove the tail from the cacheList
                // Remove it from map too
                cacheMap.remove(cacheList.tail.data);
            }
            node = cacheList.addDataToList(dataKey);
            cacheMap.put(dataKey, node);
        }
    }

    public void printCacheState() {
        cacheList.printCacheList();
        System.out.println();
    }

    public static void main(String[] args) {
        int cacheSize = 4;
        LRUCache cache = new LRUCache(cacheSize);
        System.out.println("acessing 4...");
        cache.accessData(4);
        cache.printCacheState();

        System.out.println("acessing 2...");
        cache.accessData(2);
        cache.printCacheState();

        System.out.println("acessing 1...");
        cache.accessData(1);
        cache.printCacheState();

        System.out.println("acessing 1...");
        cache.accessData(1);
        cache.printCacheState();

        System.out.println("acessing 4...");
        cache.accessData(4);
        cache.printCacheState();

        System.out.println("acessing 3...");
        cache.accessData(3);
        cache.printCacheState();

        System.out.println("acessing 7...");
        cache.accessData(7);
        cache.printCacheState();

        System.out.println("acessing 8...");
        cache.accessData(8);
        cache.printCacheState();

        System.out.println("acessing 3...");
        cache.accessData(3);
        cache.printCacheState();
    }
}

class DoublyLinkedList {

    final int cacheSize;
    int currSize;
    Node head;
    Node tail;

    public DoublyLinkedList(int size) {
        this.cacheSize = size;
        this.currSize = 0;
    }

    public void printCacheList() {
        if (head == null) {
            return;
        }
        Node node = head;
        while (node != null) {
            System.out.print(node);
            node = node.next;
        }
    }

    public Node addDataToList(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            currSize = 1;
            return newNode;
        } else if (currSize < cacheSize) {
            currSize++;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        // add new node to head
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
        return newNode;
    }

    public void moveDataToHead(Node node) {
        if (node == null || node == head) {
            return;
        }

        if (node == tail) {
            tail = tail.prev;
            tail.next = null;
        }

        node.prev.next = node.next;

        // if node is not tail
        if (node.next != null) {
            node.next.prev = node.prev;
        }

        // make the node as head
        node.prev = null;
        node.next = head;
        head.prev = node;
        head = node;
    }
}

class Node {
    int data;
    Node prev;
    Node next;

    public Node(int data) {
        this.data = data;
    }
    public String toString() {
        return data + "  ";
    }
}
