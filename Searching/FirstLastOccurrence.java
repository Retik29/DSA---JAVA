
/**
 * First and Last Occurrence in a Sorted Array
 * 
 * Problem: Given a sorted array, find the first and last indices of a target element.
 * 
 * Algorithm: Modified Binary Search
 * 1. To find the First Occurrence:
 *    - When target == arr[mid], record indices and move 'hi' to mid - 1 (search left half).
 * 2. To find the Last Occurrence:
 *    - When target == arr[mid], record indices and move 'lo' to mid + 1 (search right half).
 * 
 * Time Complexity : O(log n) - Two binary search operations.
 * Space Complexity: O(1)     - Constant space used.
 */
import java.util.*;

public class FirstLastOccurrence {

    /**
     * Finds the index of the first occurrence of the target.
     * 
     * @param arr    The sorted input array
     * @param target The value to search for
     * @return Index of the first occurrence, or -1 if not found
     */
    static int findFirst(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;
        int res = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] == target) {
                res = mid; // Record current index
                hi = mid - 1; // Search in the left side to find an earlier occurrence
            } else if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return res;
    }

    /**
     * Finds the index of the last occurrence of the target.
     * 
     * @param arr    The sorted input array
     * @param target The value to search for
     * @return Index of the last occurrence, or -1 if not found
     */
    static int findLast(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;
        int res = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] == target) {
                res = mid; // Record current index
                lo = mid + 1; // Search in the right side to find a later occurrence
            } else if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter sorted array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.print("Enter " + n + " sorted elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.print("Enter target to search: ");
        int target = sc.nextInt();

        // Calculate occurrences
        int first = findFirst(arr, target);
        int last = findLast(arr, target);

        // Output results
        System.out.println("--- Occurrence Finder ---");
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("First occurrence index: " + first);
        System.out.println("Last occurrence index:  " + last);

        // Count of occurrences
        int count = (first == -1) ? 0 : (last - first + 1);
        System.out.println("Total count of " + target + " is: " + count);

        sc.close();
    }
}
