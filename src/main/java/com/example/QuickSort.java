package com.example;

import java.util.Random;
import com.example.metrics.OperationCounter;
import com.example.metrics.RecursionTracker;

public class QuickSort {
    private static final Random rand = new Random();

    public static void sort(int[] arr, OperationCounter counter, RecursionTracker tracker) {
        quickSort(arr, 0, arr.length - 1, counter, tracker);
    }

    private static void quickSort(int[] arr, int left, int right,
            OperationCounter counter, RecursionTracker tracker) {
        while (left < right) {
            tracker.enter();

            int pivotIndex = left + rand.nextInt(right - left + 1);
            int pivot = arr[pivotIndex];
            swap(arr, pivotIndex, right, counter);

            int partitionIndex = partition(arr, left, right, pivot, counter);

            if (partitionIndex - left < right - partitionIndex) {
                quickSort(arr, left, partitionIndex - 1, counter, tracker);
                left = partitionIndex + 1;
            } else {
                quickSort(arr, partitionIndex + 1, right, counter, tracker);
                right = partitionIndex - 1;
            }

            tracker.exit();
        }
    }

    private static int partition(int[] arr, int left, int right, int pivot, OperationCounter counter) {
        int i = left - 1;
        for (int j = left; j < right; j++) {
            counter.incComparisons();
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j, counter);
            }
        }
        swap(arr, i + 1, right, counter);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j, OperationCounter counter) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            counter.incAssignments();
            counter.incAssignments();
            counter.incAssignments();
        }
    }
}
