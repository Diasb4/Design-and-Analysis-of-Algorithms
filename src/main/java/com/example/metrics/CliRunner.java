package com.example.metrics;

import com.example.metrics.OperationCounter;
import com.example.metrics.RecursionTracker;
import com.example.MergeSort;
import com.example.QuickSort;
import com.example.DeterministicSelect;
import com.example.ClosestPair;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class CliRunner {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("Usage: java -jar demo.jar <algorithm> <n> [csvFile]");
            System.err.println("Algorithms: mergesort, quicksort, select, closestpair");
            System.exit(1);
        }

        String algo = args[0].toLowerCase();
        int n = Integer.parseInt(args[1]);
        String csvFile = args.length >= 3 ? args[2] : "results.csv";

        Random rand = new Random();
        int[] arr = rand.ints(n, 0, 1_000_000).toArray();

        OperationCounter counter = new OperationCounter();
        RecursionTracker tracker = new RecursionTracker();

        long start = System.nanoTime();
        switch (algo) {
            case "mergesort":
                MergeSort.sort(arr.clone(), counter, tracker);
                break;
            case "quicksort":
                QuickSort.sort(arr.clone(), counter, tracker);
                break;
            case "select":
                int k = n / 2;
                DeterministicSelect.select(arr.clone(), k, counter, tracker);
                break;
            case "closestpair":
                ClosestPair.Point[] pts = new ClosestPair.Point[n];
                for (int i = 0; i < n; i++) {
                    double x = rand.nextDouble() * 100000;
                    double y = rand.nextDouble() * 100000;
                    pts[i] = new ClosestPair.Point(x, y);
                }
                ClosestPair.findClosestPair(pts, counter, tracker);
                break;

            default:
                System.err.println("Unknown algorithm: " + algo);
                System.exit(1);
        }
        long end = System.nanoTime();

        long timeMs = (end - start) / 1_000_000;

        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile, true))) {
            pw.printf("%s,%d,%d,%d,%d%n",
                    algo,
                    n,
                    timeMs,
                    counter.getAssignments() + counter.getComparisons(),
                    tracker.getMaxDepth());
        }

        System.out.printf("Algorithm=%s, n=%d, time=%dms, ops=%d, depth=%d%n",
                algo, n, timeMs,
                counter.getAssignments() + counter.getComparisons(),
                tracker.getMaxDepth());
    }
}
