package com.example;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.metrics.OperationCounter;
import com.example.metrics.RecursionTracker;

public class EmptyArrayTest {
    @Test
    void testEmptyArray() {
        int[] arr = {};
        OperationCounter counter = new OperationCounter();
        RecursionTracker tracker = new RecursionTracker();

        MergeSort.sort(arr, counter, tracker);

        assertArrayEquals(new int[] {}, arr);
        assertEquals(0, counter.getComparisons());
        assertEquals(0, counter.getAssignments());
        assertEquals(0, tracker.getMaxDepth());

        System.out.println("Comparisons = " + counter.getComparisons());
        System.out.println("Assignments = " + counter.getAssignments());
        System.out.println("Max depth   = " + tracker.getMaxDepth());
    }
}
