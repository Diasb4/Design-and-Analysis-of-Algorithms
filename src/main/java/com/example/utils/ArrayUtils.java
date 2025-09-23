package com.example.util;

import java.util.Random;

import com.example.metrics.OperationCounter;

public class ArrayUtils {

    private static final Random random = new Random();

    public static void swap(int[] arr, int i, int j, OperationCounter counter) {
        if (i == j)
            return;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        counter.incAssignments();
        counter.incAssignments();
        counter.incAssignments();
    }

    // Перемешивает массив
    public static void shuffle(int[] arr, OperationCounter counter) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static int partition(int[] arr, int left, int right, int pivotValue, OperationCounter counter) {
        int storeIndex = left;
        for (int i = left; i <= right; i++) {
            counter.incComparisons();
            if (arr[i] < pivotValue) {
                swap(arr, i, storeIndex, counter);
                storeIndex++;
            }
        }
        return storeIndex - 1;
    }

    // Проверка массива на null или пустоту
    public static boolean isNullOrEmpty(int[] arr) {
        return arr == null || arr.length == 0;
    }
}
