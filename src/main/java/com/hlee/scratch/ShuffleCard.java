package com.hlee.scratch;

import java.util.Arrays;
import java.util.Random;

public class ShuffleCard {

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
        System.out.println("array before shuffle: " + Arrays.toString(arr));

        for (int i = 0; i < 10; i++) {
            shuffle(arr);
            System.out.println("array after shuffle : " + Arrays.toString(arr));
        }

    }

    static void shuffle(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            swap(arr, i, rand.nextInt(arr.length));
        }
    }

    static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
