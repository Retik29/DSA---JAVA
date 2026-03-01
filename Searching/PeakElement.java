
/**
 * Peak Element
 * 
 * Problem: Find a "peak" element in an array. A peak element is an element 
 * that is strictly greater than its neighbors. 
 * For corners: 
 * - arr[0] is peak if arr[0] > arr[1].
 * - arr[n-1] is peak if arr[n-1] > arr[n-2].
 * 
 * Algorithm: Modified Binary Search
 * 1. If arr[mid] < arr[mid+1], then there must be a peak on the right side.
 *    (Proof: Either the array keeps increasing until the end, making the last 
 *    element a peak, or it starts decreasing at some point, creating a peak).
 * 2. Otherwise, if arr[mid] >= arr[mid+1], a peak exists on the left side 
 *    (including mid).
 * 
 * Time Complexity : O(log n)
 * Space Complexity: O(1)
 */
import java.util.*;

public class PeakElement {

    /**
     * Finds the index of any one peak element.
     * 
     * @param arr The input array
     * @return Index of a peak element
     */
    static int findPeak(int[] arr) {
        int n = arr.length;
        if (n == 0)
            return -1;
        if (n == 1)
            return 0;

        int lo = 0;
        int hi = n - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            // Compare mid with the next element
            if (arr[mid] < arr[mid + 1]) {
                // If mid is less than mid+1, the peak must be to the right
                lo = mid + 1;
            } else {
                // If mid is greater than or equal to mid+1,
                // mid itself could be a peak or it's on the left
                hi = mid;
            }
        }

        // When lo == hi, we have found a peak element index
        return lo;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input gathering
        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        // Perform search
        int idx = findPeak(arr);

        // Output results
        System.out.println("--- Peak Element Finder ---");
        System.out.println("Array: " + Arrays.toString(arr));
        if (idx != -1) {
            System.out.println("One peak element is " + arr[idx] + " found at index " + idx + ".");
        }

        sc.close();
    }
}
