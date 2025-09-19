package com.example;

import com.example.metrics.OperationCounter;
import com.example.metrics.RecursionTracker;

public class MergeSort {
    private static final int INSERTION_SORT_THRESHOLD = 2;

    public static void sort(int[] arr,
            OperationCounter counter,
            RecursionTracker tracker) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int[] buffer = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, buffer, counter, tracker);
    }

    private static void mergeSort(int[] arr, int l, int r,
            int[] buffer,
            OperationCounter counter,
            RecursionTracker tracker) {
        tracker.enter();

        if (r - l + 1 <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, l, r, counter);
            tracker.exit();
            return;
        }

        int m = (l + r) >>> 1;
        mergeSort(arr, l, m, buffer, counter, tracker);
        mergeSort(arr, m + 1, r, buffer, counter, tracker);
        merge(arr, l, m, r, buffer, counter);

        tracker.exit();
    }

    private static void merge(int[] arr, int l, int m, int r,
            int[] buffer,
            OperationCounter counter) {
        int i = l, j = m + 1, k = l;
        while (i <= m && j <= r) {
            counter.incComparisons();
            if (arr[i] <= arr[j]) {
                buffer[k++] = arr[i++];
                counter.incAssignments();
            } else {
                buffer[k++] = arr[j++];
                counter.incAssignments();
            }
        }
        while (i <= m) {
            buffer[k++] = arr[i++];
            counter.incAssignments();
        }
        while (j <= r) {
            buffer[k++] = arr[j++];
            counter.incAssignments();
        }
        for (int t = l; t <= r; t++) {
            arr[t] = buffer[t];
            counter.incAssignments();
        }
    }

    private static void insertionSort(int[] arr, int l, int r,
            OperationCounter counter) {
        for (int i = l + 1; i <= r; i++) {
            int key = arr[i];
            counter.incAssignments();
            int j = i - 1;
            while (j >= l) {
                counter.incComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    counter.incAssignments();
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
            counter.incAssignments();
        }
    }
}