package com.hlee.scratch.corejava.intf;

public interface MyInterface {

    void regularMethod();

    default void defaultMeth() {
        System.out.println("Default method called");
        privateHelperMethod();
        privateHelperMethod2();
    }

    private void privateHelperMethod() {
        System.out.println("Private Helper Method");
    }

    private void privateHelperMethod2() {
        System.out.println("Private Helper Method 2");
    }
}
