
// Activity Selection Problem - Greedy Algorithm
// Time Complexity: O(n log n) | Space Complexity: O(n)
import java.util.*;

public class ActivitySelection {
    static class Activity {
        int start, end;

        Activity(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static void selectActivities(int[] start, int[] end) {
        int n = start.length;
        Activity[] activities = new Activity[n];
        for (int i = 0; i < n; i++)
            activities[i] = new Activity(start[i], end[i]);

        Arrays.sort(activities, Comparator.comparingInt(a -> a.end));

        System.out.println("Selected Activities:");
        int lastEnd = -1;
        int count = 0;
        for (Activity activity : activities) {
            if (activity.start >= lastEnd) {
                System.out.println("Activity: " + activity.start + " - " + activity.end);
                lastEnd = activity.end;
                count++;
            }
        }
        System.out.println("Total activities: " + count);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of activities: ");
        int n = sc.nextInt();
        int[] start = new int[n];
        int[] end = new int[n];

        System.out.print("Enter start times: ");
        for (int i = 0; i < n; i++)
            start[i] = sc.nextInt();
        System.out.print("Enter end times: ");
        for (int i = 0; i < n; i++)
            end[i] = sc.nextInt();

        selectActivities(start, end);
        sc.close();
    }
}
