package com.hlee.scratch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomNumberWithFreq {

    public static void main(String[] args) {

        test_usingArrays();
        //        test_usingClass();
    }

    static int getRandom(int start, int end) {
        Random rand = new Random();
        return start + rand.nextInt(end - start + 1);
    }

    static void test_usingArrays() {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            int[] array = { 12, 25, 17, 48 };
            int[] freq = { 1, 6, 2, 1 };
            int randElem = getRandomInArray(array, freq);
            System.out.println(i + ".random element = " + randElem);

            if (countMap.containsKey(randElem))
                countMap.put(randElem, countMap.get(randElem) + 1);
            else
                countMap.put(randElem, 1);
        }
        System.out.println("countMap = " + countMap);
    }

    static int[] calledCounts;
    static boolean[] freqReached;

    static int getRandomInArray(int[] arr, int[] freq) {
        if (arr == null || arr.length == 0 || freq == null || freq.length == 0)
            return -1;
        if (calledCounts == null) {
            calledCounts = new int[freq.length];
            freqReached = new boolean[freq.length];
        }
        while (true) {
            int index = getRandom(0, arr.length - 1); // get random position of the array
            if (calledCounts[index] < freq[index]) {
                calledCounts[index]++;
                return arr[index]; // return random element
            } else {
                freqReached[index] = true;
                // if freq is reached for all elements, return -1
                for (Boolean reached : freqReached) {
                    if (!reached) {
                        return getRandomInArray(arr, freq);
                    }
                }
                return -1;
            }
        }
    }

    static int notCallableCount = 0;

    static int getRandomInArray_usingClass(List<MyNumber> list) {
        if (list == null || list.size() == 0)
            return -1;
        int randIndex = getRandom(0, list.size() - 1);
        MyNumber myNum = list.get(randIndex);
        if (myNum.isCallable()) {
            myNum.calledCount++;
            return myNum.number;
        } else {
            notCallableCount++;
            if (notCallableCount >= totalFreq) {
                System.out.println("No more callable numbers left!");
                return -1;
            } else {
                return getRandomInArray_usingClass(list);
            }
        }
    }

    static int totalFreq = 0;

    static void test_usingClass() {
        int[] array = { 12, 25, 17, 48 };
        int[] freq = { 1, 6, 2, 1 };
        List<MyNumber> numList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            numList.add(new MyNumber(array[i], freq[i]));
            totalFreq += freq[i];
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            int randElem = getRandomInArray_usingClass(numList);
            System.out.println(i + ".random element = " + randElem);

            if (countMap.containsKey(randElem))
                countMap.put(randElem, countMap.get(randElem) + 1);
            else
                countMap.put(randElem, 1);
        }
        System.out.println("countMap = " + countMap);
    }

}

class MyNumber {
    int number;
    int freq;
    volatile int calledCount;

    public MyNumber(int number, int freq) {
        this.number = number;
        this.freq = freq;
    }

    public boolean isCallable() {
        return calledCount < freq;
    }
}
