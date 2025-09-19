package com.example;

import java.util.Arrays;

import com.example.metrics.OperationCounter;
import com.example.metrics.RecursionTracker;
import com.example.util.ArrayUtils;

public class DeterministicSelect {

    public static int select(int[] arr, int k, OperationCounter counter, RecursionTracker tracker) {
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Index out of range");
        }
        tracker.enter();
        int result = selectHelper(arr, 0, arr.length - 1, k, counter, tracker);
        tracker.exit();
        return result;
    }

    private static int selectHelper(int[] arr, int left, int right, int k,
            OperationCounter counter,
            RecursionTracker tracker) {
        if (left == right) {
            return arr[left];
        }

        int pivot = medianOfMedians(arr, left, right, counter);

        int pivotIndex = ArrayUtils.partition(arr, left, right, pivot);

        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return selectHelper(arr, left, pivotIndex - 1, k, counter, tracker);
        } else {
            return selectHelper(arr, pivotIndex + 1, right, k, counter, tracker);
        }
    }

    private static int medianOfMedians(int[] arr, int left, int right, OperationCounter counter) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }

        int numMedians = (int) Math.ceil((double) n / 5);
        int[] medians = new int[numMedians];

        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);

            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }

        return medianOfMedians(medians, 0, medians.length - 1, counter);
    }
}
