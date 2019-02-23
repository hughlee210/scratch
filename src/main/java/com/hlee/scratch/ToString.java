package com.hlee.scratch;

import java.util.ArrayList;
import java.util.List;

public class ToString {

    public static void main(String[] args) {

        listToString();

        arrayToString();
    }

    private static void arrayToString() {
        String[] strArray = { "item 1", "item 2", "item 3" };
        System.out.println("strArray: " + strArray);
    }

    private static void listToString() {
        List<String> list = new ArrayList<>();
        list.add("item 1");
        list.add("item 2");
        list.add("item 3");
        System.out.println("list: " + list);
    }
}
