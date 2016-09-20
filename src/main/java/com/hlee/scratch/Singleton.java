package com.hlee.scratch;

import java.io.Serializable;

public class Singleton implements Serializable {

	// public static final Singleton INSTANCE = new Singleton();

	private static final Singleton INSTANCE = new Singleton();

	private transient Integer count;

	private Singleton() {
		count = 0;
	}

	public static Singleton getInstance() {
		return INSTANCE;
	}

	private Object readResolve() {
		return INSTANCE;
	}

}
