
// Merge Overlapping Intervals
// Time Complexity: O(n log n) due to sorting | Space Complexity: O(n) for result
import java.util.*;

public class MergeIntervals {

    static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            if (currentEnd >= nextStart) {
                // Overlap, update the end time
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // No overlap, add interval to list
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of intervals: ");
        int n = sc.nextInt();
        int[][] intervals = new int[n][2];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter start and end for interval " + (i + 1) + ": ");
            intervals[i][0] = sc.nextInt();
            intervals[i][1] = sc.nextInt();
        }

        int[][] result = merge(intervals);
        System.out.println("Merged Intervals:");
        for (int[] interval : result) {
            System.out.print(Arrays.toString(interval) + " ");
        }
        System.out.println();

        sc.close();
    }
}
