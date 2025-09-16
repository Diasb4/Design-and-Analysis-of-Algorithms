# Algorithms Report

## Overview
This project implements and analyzes four core algorithms in Java: **MergeSort, QuickSort, Deterministic Select (Median-of-Medians), and Closest Pair of Points in 2D**.  
The focus is both on theoretical guarantees (recurrence relations and asymptotic bounds) and on practical performance aspects such as recursion depth, memory allocations, and cache behavior.

---

## Implemented Algorithms

### 1. MergeSort (Divide & Conquer)
- **Features:**
  - Linear merge with a reusable buffer.
  - Small-n cutoff using insertion sort for efficiency.
- **Recurrence:**  
  T(n) = 2T(n/2) + Θ(n).  
  By Master Theorem (Case 2), complexity is Θ(n log n).
- **Notes:** Reusable buffer reduces memory allocations and improves cache performance.

### 2. QuickSort (Robust Version)
- **Features:**
  - Randomized pivot selection to avoid worst-case scenarios.
  - Recurses only on the smaller partition, iterates over the larger one → O(log n) stack usage.
- **Recurrence (average case):**  
  T(n) = T(n/2) + T(n/2) + Θ(n) → Θ(n log n).
- **Notes:** Often faster than MergeSort in practice due to in-place partitioning and better cache locality.

### 3. Deterministic Select (Median-of-Medians)
- **Features:**
  - Groups of 5, median of medians pivot selection.
  - In-place partitioning.
  - Recursion only on the necessary side (prefer the smaller side).
- **Recurrence:**  
  T(n) ≤ T(n/5) + T(7n/10) + Θ(n) → Θ(n).
- **Notes:** Guarantees linear time but slower constants compared to randomized selection.

### 4. Closest Pair of Points (2D)
- **Features:**
  - Sort points by x-coordinate, recursive divide.
  - Merge step checks the “strip” around the division line using y-sorted order.
  - Uses the 7–8 neighbor scan method in the strip.
- **Recurrence:**  
  T(n) = 2T(n/2) + Θ(n) → Θ(n log n).
- **Notes:** Efficient geometric algorithm; practical performance depends on sorting and memory layout.

---

## Architecture Notes
- Recursion depth is controlled by preferring smaller partitions (QuickSort, Select).  
- Buffer reuse in MergeSort avoids repeated memory allocation.  
- Small-n cutoff in MergeSort improves constant factors by using insertion sort.  
- Closest Pair combines sorting with geometric checks, balancing divide-and-conquer with local scans.

---

## Experimental Plots
- **Time vs n:** Confirms asymptotic complexity; QuickSort usually fastest.  
- **Depth vs n:** QuickSort stack bounded by O(log n); MergeSort depth also O(log n).  
- **Constant factors:** Cache effects, garbage collection, and insertion sort cutoff strongly influence real runtimes.

---

## Summary
- Theoretical results (Θ bounds) align with measurements, though constant factors cause differences in speed.  
- QuickSort performs best on average inputs.  
- MergeSort is stable and predictable.  
- Deterministic Select ensures linear time but is slower in practice.  
- Closest Pair matches its O(n log n) bound and illustrates efficient use of geometry with divide-and-conquer.
