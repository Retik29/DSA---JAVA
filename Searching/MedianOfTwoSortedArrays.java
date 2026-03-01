
/**
 * Median of Two Sorted Arrays
 * 
 * Problem: Find the median of two sorted arrays in O(log(min(N, M))) time.
 * 
 * Algorithm: Binary Search on Partitions
 * 1. Ensure 'a' is the smaller array to minimize binary search range.
 * 2. Partition both arrays into two halves (left and right) such that:
 *    - The total number of elements on the left is (N + M + 1) / 2.
 *    - All elements in the left partition are <= all elements in the right partition.
 * 3. We binary search for the correct partition point 'i' in array 'a'.
 * 4. The partition point 'j' in array 'b' is derived as ((N + M + 1) / 2) - i.
 * 5. Check if the partition is correct:
 *    - maxLeftA <= minRightB AND maxLeftB <= minRightA.
 * 6. If maxLeftA > minRightB, move the partition in 'a' to the left (hi = i - 1).
 * 7. If maxLeftB > minRightA, move the partition in 'a' to the right (lo = i + 1).
 * 
 * Time Complexity : O(log(min(n, m)))
 * Space Complexity: O(1)
 */
import java.util.*;

public class MedianOfTwoSortedArrays {

    /**
     * Finds the median of two sorted arrays.
     * 
     * @param a First sorted array
     * @param b Second sorted array
     * @return The median as a double
     */
    static double findMedian(int[] a, int[] b) {
        // Optimization: Ensure binary search is performed on the smaller array
        if (a.length > b.length)
            return findMedian(b, a);

        int n = a.length;
        int m = b.length;
        int lo = 0;
        int hi = n;

        while (lo <= hi) {
            // Partition index for array 'a'
            int i = (lo + hi) / 2;
            // Partition index for array 'b' (ensures left side has half total elements)
            int j = (n + m + 1) / 2 - i;

            // Handle boundary cases for partition A
            int maxLeftA = (i == 0) ? Integer.MIN_VALUE : a[i - 1];
            int minRightA = (i == n) ? Integer.MAX_VALUE : a[i];

            // Handle boundary cases for partition B
            int maxLeftB = (j == 0) ? Integer.MIN_VALUE : b[j - 1];
            int minRightB = (j == m) ? Integer.MAX_VALUE : b[j];

            // Check if we have found the correct partition
            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                // If total size is even
                if ((n + m) % 2 == 0) {
                    return (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2.0;
                } else {
                    // If total size is odd, the max of the left side is the median
                    return Math.max(maxLeftA, maxLeftB);
                }
            } else if (maxLeftA > minRightB) {
                // Too many elements from 'a' on the left side, search left
                hi = i - 1;
            } else {
                // Too few elements from 'a' on the left side, search right
                lo = i + 1;
            }
        }
        throw new IllegalArgumentException("Input arrays are not sorted.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input array 1
        System.out.print("Enter size of array 1: ");
        int n = sc.nextInt();
        int[] a = new int[n];
        System.out.print("Enter " + n + " sorted elements: ");
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();

        // Input array 2
        System.out.print("Enter size of array 2: ");
        int m = sc.nextInt();
        int[] b = new int[m];
        System.out.print("Enter " + m + " sorted elements: ");
        for (int i = 0; i < m; i++)
            b[i] = sc.nextInt();

        // Output results
        System.out.println("--- Median Finder ---");
        System.out.println("Array 1: " + Arrays.toString(a));
        System.out.println("Array 2: " + Arrays.toString(b));

        try {
            System.out.println("Median: " + findMedian(a, b));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}
