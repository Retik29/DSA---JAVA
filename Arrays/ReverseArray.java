
/**
 * Reverse Array
 * 
 * Problem: Reverse the order of elements in an array.
 * 
 * Algorithm: Two-Pointer Approach
 * 1. Initialize two pointers: 'i' at the start (0) and 'j' at the end (n-1).
 * 2. Swap elements at 'i' and 'j'.
 * 3. Increment 'i' and decrement 'j'.
 * 4. Continue until 'i' and 'j' meet or cross each other.
 * 
 * Time Complexity : O(n) - Single pass through half the array.
 * Space Complexity: O(1) - In-place reversal.
 */
import java.util.*;

public class ReverseArray {

    /**
     * Reverses the array in-place using two pointers.
     * 
     * @param arr The array to reverse
     */
    static void reverse(int[] arr) {
        int i = 0; // Pointer at the beginning
        int j = arr.length - 1; // Pointer at the end

        while (i < j) {
            // Swap elements at i and j
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;

            // Move pointers towards the middle
            i++;
            j--;
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

        System.out.println("Original: " + Arrays.toString(arr));

        // Perform reversal
        reverse(arr);

        // Output result
        System.out.println("Reversed: " + Arrays.toString(arr));

        sc.close();
    }
}
