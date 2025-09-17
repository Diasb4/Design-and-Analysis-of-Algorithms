package com.example.metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MetricsLogger {
    private final String fileName;

    public MetricsLogger(String fileName) {
        this.fileName = fileName;
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            pw.println("algorithm,n,time,comparisons,assignments,maxDepth");
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize CSV file", e);
        }
    }

    public void log(String algorithm, int n, long timeMillis,
            OperationCounter counter, RecursionTracker tracker) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, true))) {
            pw.printf("%s,%d,%d,%d,%d,%d%n",
                    algorithm, n, timeMillis,
                    counter.getComparisons(),
                    counter.getAssignments(),
                    tracker.getMaxDepth());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write CSV line", e);
        }
    }
}
