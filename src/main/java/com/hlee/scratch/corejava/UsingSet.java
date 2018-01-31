package com.hlee.scratch.corejava;

import java.util.HashSet;
import java.util.Set;

class UsingSet {

    String value;

    public UsingSet(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    //    @Override
    //    public int hashCode() {
    //        int hash = value.hashCode();
    //        System.out.println("hashcode called" + hash);
    //        return hash;
    //    }

    public static void main(String args[]) {

        Set s = new HashSet();

        s.add(new UsingSet("A"));
        s.add(new UsingSet("b"));
        s.add(new UsingSet("a"));
        s.add(new UsingSet("b"));
        s.add(new UsingSet("a"));

        s.add(new Integer(1));
        s.add(new Integer(1));

        System.out.println("s = " + s);


    }
}