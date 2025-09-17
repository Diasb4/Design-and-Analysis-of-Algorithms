package com.example;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.metrics.OperationCounter;
import com.example.metrics.RecursionTracker;

public class QuickSortTest {
    @Test
    void testQuickSortMetrics() {
        int[] arr = { 5, 2, 9, 11, 1, 3 };
        OperationCounter counter = new OperationCounter();
        RecursionTracker tracker = new RecursionTracker();

        QuickSort.sort(arr, counter, tracker);

        assertArrayEquals(new int[] { 1, 2, 3, 5, 9, 11 }, arr);

        System.out.println("Comparisons = " + counter.getComparisons());
        System.out.println("Assignments = " + counter.getAssignments());
        System.out.println("Max depth   = " + tracker.getMaxDepth());
    }
}