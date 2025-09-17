package com.example;

import com.example.metrics.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    @Test
    void testMergeSortMetrics() {
        int[] arr = { 5, 2, 9, 1, 3 };
        OperationCounter counter = new OperationCounter();
        RecursionTracker tracker = new RecursionTracker();

        MergeSort.sort(arr, counter, tracker);

        assertArrayEquals(new int[] { 1, 2, 3, 5, 9 }, arr);

        System.out.println("Comparisons = " + counter.getComparisons());
        System.out.println("Assignments = " + counter.getAssignments());
        System.out.println("Max depth   = " + tracker.getMaxDepth());
    }
}
