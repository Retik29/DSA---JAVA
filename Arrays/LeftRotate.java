
/**
 * Left Rotate Array by D Positions
 * 
 * Problem: Move the first D elements of an array to the end while maintaining relative order.
 * 
 * Algorithm: Reversal Algorithm
 * 1. Reverse the first D elements — arr[0...D-1]
 * 2. Reverse the remaining N-D elements — arr[D...N-1]
 * 3. Reverse the whole array — arr[0...N-1]
 * 
 * Example: arr = [1, 2, 3, 4, 5], d = 2
 * 1. Reverse first 2: [2, 1, 3, 4, 5]
 * 2. Reverse rest:  [2, 1, 5, 4, 3]
 * 3. Reverse all:   [3, 4, 5, 1, 2]
 * 
 * Time Complexity : O(n) - three reversal operations, each O(n).
 * Space Complexity: O(1) - in-place rotation.
 */
import java.util.*;

public class LeftRotate {

    /**
     * Rotates the array using the Reversal Algorithm.
     * 
     * @param arr The array to rotate
     * @param d   The number of positions to rotate left
     */
    static void leftRotate(int[] arr, int d) {
        int n = arr.length;
        if (n == 0)
            return;

        // Handle cases where d >= n
        d %= n;
        if (d == 0)
            return;

        // Step 1: Reverse first d elements
        reverse(arr, 0, d - 1);

        // Step 2: Reverse remaining elements
        reverse(arr, d, n - 1);

        // Step 3: Reverse the entire array
        reverse(arr, 0, n - 1);
    }

    /**
     * Utility method to reverse a portion of an array in-place.
     * 
     * @param a The array
     * @param i Starting index
     * @param j Ending index
     */
    private static void reverse(int[] a, int i, int j) {
        while (i < j) {
            int t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input array size
        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        // Input array elements
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        // Input rotation count
        System.out.print("Enter rotation count d: ");
        int d = sc.nextInt();

        System.out.println("Original: " + Arrays.toString(arr));

        // Perform rotation
        leftRotate(arr, d);

        // Output result
        System.out.println("Rotated left by " + d + ": " + Arrays.toString(arr));

        sc.close();
    }
}
