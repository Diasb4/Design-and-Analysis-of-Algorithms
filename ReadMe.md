Assignment 1 Report

Architecture notes
All algorithms were implemented with counters for comparisons, assignments, and recursion depth. Recursion depth was kept safe by design: QuickSort and Select always recurse on the smaller side, MergeSort naturally has logarithmic depth, and Closest Pair also splits by halves. Allocations were minimized. For example, MergeSort uses a reusable buffer and Closest Pair only performs one initial sort. A command line interface was added to run algorithms and export results to CSV for later plotting.

Recurrence analysis

MergeSort
The recurrence is T(n) = 2T(n/2) + O(n). By the Master Theorem this is Case 2, so the result is Theta(n log n). For small n a cut-off to insertion sort was used to reduce overhead.


QuickSort (randomized pivot)
The expected recurrence is T(n) = T(alpha n) + T((1-alpha)n) + O(n) where alpha is random. The expected running time is Theta(n log n). The worst case is Theta(n^2), but randomized pivots make it very unlikely. By always recursing into the smaller partition the recursion depth stays about O(log n).

Deterministic Select (Median of Medians)
The algorithm splits into groups of 5, finds the median of medians, and recurses only into the needed side. The recurrence is T(n) = T(n/5) + T(7n/10) + O(n). By Akra–Bazzi the result is Theta(n). The method guarantees linear time but has larger constants compared to randomized QuickSelect.

Closest Pair of Points
The algorithm sorts by x, divides, and then checks the strip by y ordering. The recurrence is T(n) = 2T(n/2) + O(n). This matches Master Theorem Case 2, so the result is Theta(n log n). Constant factors depend on the implementation of the strip check and the initial sorting.

Plots
![Depth](https://github.com/Diasb4/Design-and-Analysis-of-Algorithms/raw/main/images/image.png)
![Time](https://github.com/Diasb4/Design-and-Analysis-of-Algorithms/raw/main/images/image2.png)
![Ops](https://github.com/Diasb4/Design-and-Analysis-of-Algorithms/raw/main/images/image3.png)
Graphs will include time versus n and recursion depth versus n. Discussion will mention constant factors like cache behavior and garbage collection.

Summary
MergeSort and Closest Pair followed the expected Theta(n log n) growth. QuickSort showed average Theta(n log n) performance with bounded recursion depth. Deterministic Select worked in Theta(n) time but was slower in practice than randomized QuickSelect because of higher constants. In general the measurements agreed with theory, and the differences can be explained by constant factors and low-level system effects.