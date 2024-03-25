package com.hlee.scratch.corejava.designpattern;

public class SingletonDoubleLocking {

    // 'volatile' to ensure that changes made to it are visible to all threads
    private static volatile SingletonDoubleLocking instance;

    private SingletonDoubleLocking() {
        // Private constructor to prevent instantiation from other classes.
    }

    /**
     * The first check (if (instance == null)) verifies if the instance is null
     * without synchronization. This is an optimization to avoid the overhead of
     * synchronization once the instance is created.
     *
     * If the instance is null, the thread enters a synchronized block
     * (synchronized on the Singleton.class object) to create the instance.
     * The second check (if (instance == null)) is performed inside this synchronized
     * block to ensure thread safety.
     */
    public static SingletonDoubleLocking getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new SingletonDoubleLocking();
                }
            }
        }
        return instance;
    }
}