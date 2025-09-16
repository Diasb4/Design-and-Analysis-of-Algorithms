package com.example.metrics;    

public class OperationCounter {
    private long comparisons = 0;
    private long assignments = 0;

    public void incComparisons() { comparisons++; }
    public void incAssignments() { assignments++; }

    public long getComparisons() { return comparisons; }
    public long getAssignments() { return assignments; }

    public void reset() {
        comparisons = 0;
        assignments = 0;
    }
}
