
/**
 * Activity Selection Problem (Greedy Algorithm)
 * 
 * Problem: Given 'n' activities with their start and end times, select the maximum 
 * number of activities that can be performed by a single person, assuming that 
 * a person can only work on a single activity at a time.
 * 
 * Logic (Greedy Strategy):
 * 1. Sorting: Sort all activities according to their Finishing Times.
 * 2. Selection: 
 *    - Always pick the first activity (as it finishes earliest, leaving maximum 
 *      room for others).
 *    - For the remaining activities, if the start time is greater than or equal 
 *      to the finish time of the previously selected activity, select it.
 * 
 * Why Finish Time? 
 * Sorting by finish time ensures we are greedily picking the activity that finishes 
 * as early as possible, thus maximizing the time available for subsequent activities.
 * 
 * Complexity:
 * - Time Complexity : O(n log n) - primarily due to sorting.
 * - Space Complexity: O(n) - to store activity objects.
 */
import java.util.*;

public class ActivitySelection {

    /**
     * Internal class to represent an activity.
     */
    static class Activity {
        int start, end;

        Activity(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * Selects and prints the maximum number of non-overlapping activities.
     */
    static void selectActivities(int[] start, int[] end) {
        int n = start.length;
        Activity[] activities = new Activity[n];

        // Combine start and end times into objects for sorting
        for (int i = 0; i < n; i++) {
            activities[i] = new Activity(start[i], end[i]);
        }

        // Greedy Key: Sort activities based on finish (end) time
        Arrays.sort(activities, Comparator.comparingInt(a -> a.end));

        System.out.println("\n--- Selected Activities ---");
        int lastFinishTime = -1;
        int count = 0;

        for (Activity activity : activities) {
            // If the activity starts after or when the previous one ends
            if (activity.start >= lastFinishTime) {
                System.out.println("Activity: [" + activity.start + " to " + activity.end + "]");
                lastFinishTime = activity.end;
                count++;
            }
        }
        System.out.println("Total non-overlapping activities selected: " + count);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Activity Selection Solver ---");
        System.out.print("Enter number of activities: ");
        int n = sc.nextInt();

        int[] start = new int[n];
        int[] end = new int[n];

        System.out.print("Enter " + n + " start times: ");
        for (int i = 0; i < n; i++)
            start[i] = sc.nextInt();

        System.out.print("Enter " + n + " end times: ");
        for (int i = 0; i < n; i++)
            end[i] = sc.nextInt();

        // Process selection
        selectActivities(start, end);

        sc.close();
    }
}
