package com.hlee.scratch.corejava.intf;

public class MyClass implements MyInterface {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.defaultMeth();
        myClass.regularMethod();
    }

    @Override
    public void regularMethod() {
        System.out.println("Implemented regular method");
    }

    @Override
    public void defaultMeth() {
        System.out.println("Overridden default method");
        MyInterface.super.defaultMeth();
    }
}
