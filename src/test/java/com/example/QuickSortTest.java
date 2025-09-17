package com.example;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.metrics.OperationCounter;
import com.example.metrics.RecursionTracker;

public class QuickSortTest {
    @Test
    void testQuickSortMetrics() {
        int[] arr = { 5, 2, 9, 1, 3 };
        OperationCounter counter = new OperationCounter();
        RecursionTracker tracker = new RecursionTracker();

        QuickSort.sort(arr, counter, tracker);

        assertArrayEquals(new int[] { 1, 2, 3, 5, 9 }, arr);
        assertEquals(7, counter.getComparisons());
        assertEquals(12, counter.getAssignments());
        assertEquals(3, tracker.getMaxDepth());

        System.out.println("Comparisons = " + counter.getComparisons());
        System.out.println("Assignments = " + counter.getAssignments());
        System.out.println("Max depth   = " + tracker.getMaxDepth());
    }
}