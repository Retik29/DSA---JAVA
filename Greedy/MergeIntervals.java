
/**
 * Merge Overlapping Intervals
 * 
 * Problem: Given a collection of intervals, merge all overlapping intervals.
 * Example: Input [[1,3], [2,6], [8,10]] -> Output [[1,6], [8,10]]
 * 
 * Logic (Greedy Strategy):
 * 1. Sorting: Sort all intervals based on their Start Time.
 * 2. Iteration:
 *    - Maintain a 'currentInterval' (starting with the first one).
 *    - For each 'nextInterval' in the sorted list:
 *      - If it overlaps with 'currentInterval' (i.e., nextInterval.start <= currentInterval.end):
 *        Merge them -> update currentInterval.end = max(currentInterval.end, nextInterval.end)
 *      - Else:
 *        No overlap -> add 'nextInterval' to the result and make it the new 'currentInterval'.
 * 
 * Why Sort by Start Time? 
 * Sorting ensures that we only need to compare the current interval with the 
 * next one in line to decide on a merge.
 * 
 * Complexity:
 * - Time Complexity : O(n log n) - primarily for sorting.
 * - Space Complexity: O(n) - to store the result list.
 */
import java.util.*;

public class MergeIntervals {

    /**
     * Merges overlapping intervals in O(n log n) time.
     */
    static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        // Step 1: Sort intervals by start time using a Comparator
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> mergedResult = new ArrayList<>();

        // Step 2: Initialize current interval with the first one
        int[] currentInterval = intervals[0];
        mergedResult.add(currentInterval);

        for (int[] nextInterval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = nextInterval[0];
            int nextEnd = nextInterval[1];

            // If there is an overlap
            if (currentEnd >= nextStart) {
                // Update the end of current interval to the maximum of both
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // No overlap: add the new interval to the list and move pointer
                currentInterval = nextInterval;
                mergedResult.add(currentInterval);
            }
        }

        // Convert List back to 2D Array
        return mergedResult.toArray(new int[mergedResult.size()][]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Merge Overlapping Intervals ---");
        System.out.print("Enter number of intervals: ");
        int n = sc.nextInt();

        int[][] intervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            System.out.print("Interval " + (i + 1) + " (start end): ");
            intervals[i][0] = sc.nextInt();
            intervals[i][1] = sc.nextInt();
        }

        // Execution
        int[][] result = merge(intervals);

        // Result Output
        System.out.println("\n--- Merged Intervals ---");
        for (int[] interval : result) {
            System.out.print(Arrays.toString(interval) + " ");
        }
        System.out.println();

        sc.close();
    }
}
