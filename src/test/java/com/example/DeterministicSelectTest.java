package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.metrics.OperationCounter;
import com.example.metrics.RecursionTracker;

public class DeterministicSelectTest {

    @Test
    void testSelectMiddle() {
        int[] arr = { 7, 2, 1, 6, 8, 5, 3, 4 };
        OperationCounter counter = new OperationCounter();
        RecursionTracker tracker = new RecursionTracker();

        int result = DeterministicSelect.select(arr, 3, counter, tracker);
        assertEquals(4, result);

        System.out.println("Comparisons = " + counter.getComparisons());
        System.out.println("Assignments = " + counter.getAssignments());
        System.out.println("Max depth   = " + tracker.getMaxDepth());
        System.out.println();
    }

    @Test
    void testSelectMin() {
        int[] arr = { 7, 2, 1, 6, 8, 5, 3, 4 };
        OperationCounter counter = new OperationCounter();
        RecursionTracker tracker = new RecursionTracker();

        int result = DeterministicSelect.select(arr, 0, counter, tracker);
        assertEquals(1, result);

        System.out.println("Comparisons = " + counter.getComparisons());
        System.out.println("Assignments = " + counter.getAssignments());
        System.out.println("Max depth   = " + tracker.getMaxDepth());
        System.out.println();
    }

    @Test
    void testSelectMax() {
        int[] arr = { 7, 2, 1, 6, 8, 5, 3, 4 };
        OperationCounter counter = new OperationCounter();
        RecursionTracker tracker = new RecursionTracker();

        int result = DeterministicSelect.select(arr, arr.length - 1, counter, tracker);
        assertEquals(8, result);

        System.out.println("Comparisons = " + counter.getComparisons());
        System.out.println("Assignments = " + counter.getAssignments());
        System.out.println("Max depth   = " + tracker.getMaxDepth());
    }
}
