package com.hlee.scratch.corejava;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StreamEx {
    public static void main(String[] args) {

        String[] arr = { " string1 ", "    string2     ", " string3" };
        List<String> list = Arrays.asList(arr).stream().map(s -> s.trim()).collect(Collectors.toList());

        System.out.println(Arrays.asList(arr));
        System.out.println(list);

        List<String> sourceList = Collections.emptyList();
        list = sourceList.stream().map(s -> s.trim()).collect(Collectors.toList());
        System.out.println(list);
    }
}
