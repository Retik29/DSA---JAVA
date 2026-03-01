
/**
 * Search in Rotated Sorted Array
 * 
 * Problem: Given a sorted array that has been rotated at some pivot, 
 * find the index of a target element.
 * Example: [4, 5, 6, 7, 0, 1, 2], target = 0 -> returns 4.
 * 
 * Algorithm: Modified Binary Search
 * 1. At any 'mid', at least one of the two halves ([lo...mid] or [mid...hi]) 
 *    must be sorted.
 * 2. If the LEFT half is sorted (arr[lo] <= arr[mid]):
 *    - Check if target is in the range [arr[lo], arr[mid]).
 *    - If yes, move hi = mid - 1. Else, move lo = mid + 1.
 * 3. If the RIGHT half is sorted (arr[mid] <= arr[hi]):
 *    - Check if target is in the range (arr[mid], arr[hi]].
 *    - If yes, move lo = mid + 1. Else, move hi = mid - 1.
 * 
 * Time Complexity : O(log n)
 * Space Complexity: O(1)
 */
import java.util.*;

public class SearchRotatedArray {

    /**
     * Searches for the target in a rotated sorted array.
     * 
     * @param arr    The rotated sorted array
     * @param target The value to search for
     * @return Index of target, or -1 if not found
     */
    static int search(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            // Step 1: Identify which half is sorted
            if (arr[lo] <= arr[mid]) {
                // Left half [lo...mid] is sorted
                // Step 2: Check if target resides in the sorted left half
                if (target >= arr[lo] && target < arr[mid]) {
                    hi = mid - 1; // Target is in left half
                } else {
                    lo = mid + 1; // Target is in right half
                }
            } else {
                // Right half [mid...hi] is sorted
                // Step 3: Check if target resides in the sorted right half
                if (target > arr[mid] && target <= arr[hi]) {
                    lo = mid + 1; // Target is in right half
                } else {
                    hi = mid - 1; // Target is in left half
                }
            }
        }
        return -1; // Target not found
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input gathering
        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.print("Enter " + n + " elements (rotated sorted, e.g., 4 5 6 0 1 2): ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.print("Enter target to search: ");
        int target = sc.nextInt();

        // Perform search
        int index = search(arr, target);

        // Output results
        System.out.println("--- Rotated Search ---");
        System.out.println("Array: " + Arrays.toString(arr));
        if (index != -1) {
            System.out.println("Target " + target + " found at index: " + index);
        } else {
            System.out.println("Target " + target + " was NOT found in the array.");
        }

        sc.close();
    }
}
