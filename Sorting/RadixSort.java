
/**
 * Radix Sort
 * 
 * Algorithm: Non-comparison based integer sorting.
 * 1. Find the maximum element to know the number of digits.
 * 2. Sort the array digit by digit starting from the Least Significant Digit 
 *    (LSD) to the Most Significant Digit (MSD).
 * 3. Use a stable sort (like Counting Sort) as a subroutine to sort 
 *    based on each digit.
 * 
 * Time Complexity : O(d * (n + k)) - where d is number of digits, n is 
 *                   number of elements, and k is the range (base 10).
 * Space Complexity: O(n + k)       - for the output array and count array.
 */
import java.util.*;

public class RadixSort {

    /**
     * A stable sorting subroutine (Counting Sort) to sort based on the digit
     * represented by 'exp' (1, 10, 100, ...).
     * 
     * @param arr The array to sort
     * @param exp Current digit's place value (10^i)
     */
    static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n]; // Store the sorted result for this digit
        int[] count = new int[10]; // Frequency array for digits 0-9

        // Count occurrences of each digit at current exp place
        for (int num : arr) {
            int digit = (num / exp) % 10;
            count[digit]++;
        }

        // Cumulative sum of counts to find actual positions of digits in output
        // This makes the sorting stable.
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array in backward direction to maintain stability
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[--count[digit]] = arr[i];
        }

        // Copy the sorted output back into the original array
        System.arraycopy(output, 0, arr, 0, n);
    }

    /**
     * Main function that implements Radix Sort.
     */
    static void radixSort(int[] arr) {
        // Step 1: Find the maximum number to know number of digits
        int max = Arrays.stream(arr).max().orElse(0);

        // Step 2: Do counting sort for every digit.
        // Note that instead of passing digit number, exp is passed.
        // exp is 10^i where i is current digit number.
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.print("Enter " + n + " non-negative integers: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println("--- Radix Sort ---");
        System.out.println("Original: " + Arrays.toString(arr));

        // Sorting
        radixSort(arr);

        System.out.println("Sorted:   " + Arrays.toString(arr));

        sc.close();
    }
}
