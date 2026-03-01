
/**
 * K-th Element of Two Sorted Arrays
 * 
 * Problem: Find the k-th element in the combined sorted version of two sorted arrays.
 * 
 * Algorithm: Optimized Binary Search (Partitioning Method)
 * 1. Ensure arr1 is the smaller array to minimize the binary search range.
 * 2. We want to pick 'k' elements from both arrays combined.
 * 3. Range for number of elements to pick from arr1 (low/high):
 *    - low: We must pick at least (k - m) elements from arr1 if k > m.
 *    - high: We can pick at most n elements from arr1, but also at most k elements.
 * 4. We partition both arrays (cut1 for arr1, cut2 for arr2) such that cut1 + cut2 = k.
 * 5. Check if the partition is valid:
 *    - L1 <= R2 and L2 <= R1 (where L/R are elements around the cut).
 * 
 * Time Complexity : O(log(min(n, m)))
 * Space Complexity: O(1)
 */
import java.util.*;

public class KthElementOfTwoSortedArrays {

    /**
     * Finds the k-th smallest element of Two Sorted Arrays.
     * 
     * @param arr1 First sorted array
     * @param arr2 Second sorted array
     * @param n    Size of arr1
     * @param m    Size of arr2
     * @param k    Target k-th position (1-indexed)
     * @return The k-th element
     */
    static long kthElement(int arr1[], int arr2[], int n, int m, int k) {
        // Optimization: Binary search on the smaller array
        if (n > m)
            return kthElement(arr2, arr1, m, n, k);

        // Search boundaries for number of elements taken from arr1
        // We can't take more than n elements, and we can't take more than k.
        // If k > m, we MUST take at least (k-m) from arr1.
        int low = Math.max(0, k - m);
        int high = Math.min(k, n);

        while (low <= high) {
            // Pick 'cut1' elements from arr1
            int cut1 = (low + high) / 2;
            // The remaining 'cut2' elements must be picked from arr2
            int cut2 = k - cut1;

            // Elements to the left and right of the cuts
            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int r1 = (cut1 == n) ? Integer.MAX_VALUE : arr1[cut1];
            int r2 = (cut2 == m) ? Integer.MAX_VALUE : arr2[cut2];

            // Check if the partition is correct (all left elements <= all right elements)
            if (l1 <= r2 && l2 <= r1) {
                // The k-th element will be the maximum of the elements on the left side
                return Math.max(l1, l2);
            } else if (l1 > r2) {
                // Too many elements from arr1, move cut1 to the left
                high = cut1 - 1;
            } else {
                // Too few elements from arr1, move cut1 to the right
                low = cut1 + 1;
            }
        }
        return -1; // Should not reach here for valid input
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input arr1
        System.out.print("Enter size of first sorted array: ");
        int n = sc.nextInt();
        int[] arr1 = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr1[i] = sc.nextInt();

        // Input arr2
        System.out.print("Enter size of second sorted array: ");
        int m = sc.nextInt();
        int[] arr2 = new int[m];
        System.out.print("Enter " + m + " elements: ");
        for (int i = 0; i < m; i++)
            arr2[i] = sc.nextInt();

        // Input k
        System.out.print("Enter k (to find k-th smallest element): ");
        int k = sc.nextInt();

        // Output result
        System.out.println("--- K-th Element Result ---");
        long result = kthElement(arr1, arr2, n, m, k);
        System.out.println("The " + k + "-th combined element is: " + result);

        sc.close();
    }
}
