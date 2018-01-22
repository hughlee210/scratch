package com.hlee.scratch.corejava.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionEx {

    public static void main(String[] args) {
        testSet();
        testIterRemove();
        testReference();
        testReference_mutableElement();
        testSetEquals();

        Set<String> mySet = new HashSet<>();
        mySet.add("elem1");
        mySet.add("elem2");
        mySet.add("elem3");
        System.out.println("before calling testMutateParam() method: set = " + mySet);
        testMutateParam(new HashSet<>(mySet)); // passing new hash set with the same elements in mySet
        System.out.println("after  calling testMutateParam() method: set = " + mySet);

        testCasting();

    }

    private static void testSet() {
        StringBuilder sb1 = new StringBuilder("string1");
        StringBuilder sb2 = new StringBuilder("string2");
        Set<StringBuilder> set1 = new HashSet<>();
        set1.add(sb1);
        set1.add(sb2);

        Set<StringBuilder> set2 = new HashSet<>(set1);

        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);

        for (StringBuilder sb : set2) {
            sb.append("_modified");
        }

        System.out.println("after modifying the elements in set2");
        // this affects the elements in set1 since they are actually the same
        // elements referenced by the elements in set2.

        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);
        System.out.println("-------------------");
    }

    private static void testIterRemove() {
        Map<String, String> map = new HashMap<>();
        map.put("test1", "val1");
        map.put("test2", "val2");
        map.put("test3", "val3");
        Set<String> valueSet = new HashSet<>(map.values());
        System.out.println("map = " + map);
        System.out.println("valueSet = " + valueSet);

        for (Iterator<String> iter = map.values().iterator(); iter.hasNext();) {
            if (iter.next().equals("val1")) {
                iter.remove();
            }
        }
        System.out.println("after removing val1");
        System.out.println("map = " + map + ", size = " + map.size());
        System.out.println("valueSet = " + valueSet + ", size = " + valueSet.size());

        for (Iterator<String> iter = valueSet.iterator(); iter.hasNext();) {
            if (iter.next().equals("val3")) {
                iter.remove();
            }
        }
        System.out.println("map = " + map);
        System.out.println("valueSet = " + valueSet);

        Set<String> valueSet2 = valueSet;
        System.out.println("valueSet : " + valueSet);
        System.out.println("valueSet2: " + valueSet2);

        for (Iterator<String> iter = valueSet.iterator(); iter.hasNext();) {
            if (iter.next().equals("val2")) {
                iter.remove();
            }
        }
        System.out.println("after removing val2 from valueSet");
        System.out.println("valueSet : " + valueSet);
        System.out.println("valueSet2: " + valueSet2);

        System.out.println("-------------------");
    }

    private static void testReference() {
        List<String> list1 = new ArrayList<>();
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        String s4 = "d";
        list1.add(s1);
        list1.add(s2);
        System.out.println("list1: " + list1);

        List<String> list2 = list1;
        list2.add(s3); // result = s3 is added to list1
        System.out.println("list1: " + list1);

        List<String> list3 = new ArrayList<>(list2);
        System.out.println("list2: " + list2);
        System.out.println("list3: " + list3);
        System.out.println("adding " + s4 + " to list3");
        list3.add(s4); // result = s4 is not added to list2
        System.out.println("list2: " + list2);
        System.out.println("list3: " + list3);
        
        System.out.println("modifying first element in list3");
        list3.set(0, "a1"); // modify first element in list3
        System.out.println("list2: " + list2);
        System.out.println("list3: " + list3);

        System.out.println("-------------------");
    }

    private static void testReference_mutableElement() {
        List<StringBuilder> list1 = new ArrayList<>();
        StringBuilder s1 = new StringBuilder("a");
        StringBuilder s2 = new StringBuilder("b");
        StringBuilder s3 = new StringBuilder("c");
        StringBuilder s4 = new StringBuilder("d");
        list1.add(s1);
        list1.add(s2);
        System.out.println("list1: " + list1);

        List<StringBuilder> list2 = list1;
        list2.add(s3); // result = s3 is added to list1
        System.out.println("list1: " + list1);

        List<StringBuilder> list3 = new ArrayList<>(list2);
        System.out.println("list2: " + list2);
        System.out.println("list3: " + list3);
        System.out.println("adding " + s4 + " to list3");
        list3.add(s4); // result = s4 is not added to list2
        System.out.println("list2: " + list2);
        System.out.println("list3: " + list3);

        System.out.println("modifying first element in list3");
        list3.set(0, list3.get(0).append("1")); // result = modifying first element in list3 results in the element updated in list2
        System.out.println("list2: " + list2);
        System.out.println("list3: " + list3);
        System.out.println("-------------------");
    }

    private static void testSetEquals() {

        String s1 = "a";
        String s2 = new String("a");
        String s3 = "c";
        System.out.println(s1 + " equals " + s2 + "? " + s1.equals(s2));
        System.out.println(s1 + " == " + s2 + "? " + (s1 == s2));
        System.out.println("s1 hashcode: " + s1.hashCode());
        System.out.println("s2 hashcode: " + s2.hashCode());

        Set<String> set1 = new HashSet<>();
        set1.add(s1);
        set1.add(s3);
        Set<String> set2 = new HashSet<>();
        set2.add(s1);
        set2.add(s3);
        System.out.println("set1: " + set1 + ", hashcode: " + set1.hashCode());
        System.out.println("set2: " + set2 + ", hashcode: " + set2.hashCode());
        System.out.println(set1 + " equals " + set2 + "? " + set1.equals(set2));
        System.out.println("-------------------");
    }

    private static void testMutateParam(Set<String> set) {
        System.out.println("  in testMutateParam() method set = " + set);
        System.out.println("   clearing the set");
        set.clear();
        System.out.println("  in testMutateParam() method set = " + set);
    }

    private static void testCasting() {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        Collection<String> coll = set;
        set = (Set<String>) coll;

        Map<String, String> map = new HashMap<>();
        map.put("test1", "val1");
        map.put("test2", "val2");
        map.put("test3", "val3");

        Collection<String> coll2 = map.values();
        Set<String> set2 = (Set<String>) coll2;

        System.out.println("collection coll = " + coll);
        System.out.println("set = " + set);

        System.out.println("coll2 = " + coll2);
        System.out.println("set2 = " + set2);

    }
}
