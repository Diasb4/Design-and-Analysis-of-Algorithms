package com.example.util;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Point {
        public final double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double findClosestPair(Point[] points) {
        Point[] px = points.clone();
        Point[] py = points.clone();

        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));

        return closestPairRec(px, py);
    }

    private static double closestPairRec(Point[] px, Point[] py) {
        int n = px.length;
        if (n <= 3) {
            return bruteForce(px);
        }

        int mid = n / 2;
        Point midPoint = px[mid];

        Point[] Qx = Arrays.copyOfRange(px, 0, mid);
        Point[] Rx = Arrays.copyOfRange(px, mid, n);

        Point[] Qy = new Point[mid];
        Point[] Ry = new Point[n - mid];
        int li = 0, ri = 0;
        for (Point p : py) {
            if (p.x <= midPoint.x && li < Qy.length)
                Qy[li++] = p;
            else
                Ry[ri++] = p;
        }

        double dl = closestPairRec(Qx, Qy);
        double dr = closestPairRec(Rx, Ry);

        double d = Math.min(dl, dr);

        return Math.min(d, stripClosest(py, midPoint.x, d));
    }

    private static double stripClosest(Point[] py, double midX, double d) {
        Point[] strip = Arrays.stream(py)
                .filter(p -> Math.abs(p.x - midX) < d)
                .toArray(Point[]::new);

        double min = d;
        for (int i = 0; i < strip.length; i++) {
            // Проверяем только до 7 следующих точек
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                min = Math.min(min, dist(strip[i], strip[j]));
            }
        }
        return min;
    }

    // --- brute force для маленьких массивов ---
    private static double bruteForce(Point[] points) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                min = Math.min(min, dist(points[i], points[j]));
            }
        }
        return min;
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
