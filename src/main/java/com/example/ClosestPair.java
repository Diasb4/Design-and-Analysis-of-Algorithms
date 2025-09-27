package com.example;

import java.util.Arrays;
import java.util.Comparator;

import com.example.metrics.OperationCounter;
import com.example.metrics.RecursionTracker;

public class ClosestPair {

    public static class Point {
        public final double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double findClosestPair(Point[] points, OperationCounter counter, RecursionTracker tracker) {
        Point[] px = points.clone();
        Point[] py = points.clone();

        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));

        return closestPairRec(px, py, counter, tracker);
    }

    private static double closestPairRec(Point[] px, Point[] py,
            OperationCounter counter, RecursionTracker tracker) {
        tracker.enter();
        int n = px.length;
        if (n <= 3) {
            double res = bruteForce(px, counter);
            tracker.exit();
            return res;
        }

        int mid = n / 2;
        Point midPoint = px[mid];

        Point[] Qx = Arrays.copyOfRange(px, 0, mid);
        Point[] Rx = Arrays.copyOfRange(px, mid, n);

        Point[] Qy = new Point[mid];
        Point[] Ry = new Point[n - mid];
        int li = 0, ri = 0;
        for (Point p : py) {
            counter.incComparisons();
            if (p.x <= midPoint.x && li < Qy.length) {
                Qy[li++] = p;
                counter.incAssignments();
            } else {
                Ry[ri++] = p;
                counter.incAssignments();
            }
        }

        double dl = closestPairRec(Qx, Qy, counter, tracker);
        double dr = closestPairRec(Rx, Ry, counter, tracker);

        counter.incComparisons();
        double d = Math.min(dl, dr);

        double stripResult = stripClosest(py, midPoint.x, d, counter);
        counter.incComparisons();
        double res = Math.min(d, stripResult);

        tracker.exit();
        return res;
    }

    private static double stripClosest(Point[] py, double midX, double d, OperationCounter counter) {
        Point[] strip = Arrays.stream(py)
                .filter(p -> {
                    counter.incComparisons();
                    return Math.abs(p.x - midX) < d;
                })
                .toArray(Point[]::new);

        double min = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                counter.incComparisons();
                double newDist = dist(strip[i], strip[j], counter);
                counter.incComparisons();
                if (newDist < min) {
                    min = newDist;
                    counter.incAssignments();
                }
            }
        }
        return min;
    }

    private static double bruteForce(Point[] points, OperationCounter counter) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double d = dist(points[i], points[j], counter);
                counter.incComparisons();
                if (d < min) {
                    min = d;
                    counter.incAssignments();
                }
            }
        }
        return min;
    }

    private static double dist(Point a, Point b, OperationCounter counter) {
        counter.incAssignments();
        counter.incAssignments(); 
        counter.incComparisons();
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
