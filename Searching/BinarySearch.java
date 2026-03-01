
/**
 * Binary Search
 * 
 * Problem: Search for a target value in a SORTED array.
 * If the target is found, return its index; otherwise, return -1.
 * 
 * Key Logic: 
 * Binary search follows the Divide and Conquer strategy. It compares the target 
 * value with the middle element of the array. If they are not equal, the half 
 * in which the target cannot lie is eliminated, and the search continues on 
 * the remaining half.
 * 
 * Time Complexity : O(log n) - Search range is halved in each step.
 * Space Complexity: O(1) for iterative, O(log n) for recursive (due to call stack).
 */
import java.util.*;

public class BinarySearch {

    /**
     * Iterative Binary Search implementation.
     * 
     * @param arr    The sorted input array
     * @param target The value to search for
     * @return Index of the target, or -1 if not found
     */
    static int binarySearch(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            // Calculate mid index. (lo + hi) / 2 might cause overflow for large indices.
            // lo + (hi - lo) / 2 is a safer way to calculate the midpoint.
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] == target) {
                return mid; // Target found
            } else if (arr[mid] < target) {
                // If target is greater, ignore the left half
                lo = mid + 1;
            } else {
                // If target is smaller, ignore the right half
                hi = mid - 1;
            }
        }
        return -1; // Target not found
    }

    /**
     * Recursive Binary Search implementation.
     * 
     * @param arr    The sorted input array
     * @param lo     Starting index
     * @param hi     Ending index
     * @param target The value to search for
     * @return Index of the target, or -1 if not found
     */
    static int binarySearchRec(int[] arr, int lo, int hi, int target) {
        // Base case: range is empty
        if (lo > hi)
            return -1;

        int mid = lo + (hi - lo) / 2;

        if (arr[mid] == target) {
            return mid;
        }

        // Recursive calls on the relevant half
        if (arr[mid] < target) {
            return binarySearchRec(arr, mid + 1, hi, target);
        } else {
            return binarySearchRec(arr, lo, mid - 1, target);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input gathering
        System.out.print("Enter sorted array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.print("Enter " + n + " sorted elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.print("Enter target to search: ");
        int target = sc.nextInt();

        // Perform search
        int idx = binarySearch(arr, target);

        // Output results
        System.out.println("--- Binary Search Result ---");
        System.out.println("Array: " + Arrays.toString(arr));
        if (idx != -1) {
            System.out.println("Target " + target + " found at index: " + idx);
        } else {
            System.out.println("Target " + target + " was NOT found in the array.");
        }

        sc.close();
    }
}
