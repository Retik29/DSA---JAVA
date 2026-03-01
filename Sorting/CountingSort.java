
/**
 * Counting Sort
 * 
 * Algorithm: Non-comparison based sorting.
 * 1. Find the maximum and minimum elements in the array to determine the range.
 * 2. Create a frequency array 'count' to record how many times each value appears.
 * 3. Iterate through the frequency array and place the elements back into 
 *    the original array in sorted order.
 * 
 * Constraints: Best suited for integers when the range (k) is not significantly 
 * larger than the number of elements (n).
 * 
 * Time Complexity : O(n + k) - where n is number of elements and k is the range.
 * Space Complexity: O(k)     - for the frequency array.
 */
import java.util.*;

public class CountingSort {

    /**
     * Implements Counting Sort for a given array of integers.
     * 
     * @param arr The array to be sorted
     */
    static void countingSort(int[] arr) {
        if (arr.length == 0)
            return;

        // Step 1: Find the range of elements
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;

        // Step 2: Create a frequency array to count occurrences
        int[] count = new int[range];
        for (int num : arr) {
            count[num - min]++; // Offset by min to handle negative numbers or large offsets
        }

        // Step 3: Iterate through count array and place elements back into arr
        int idx = 0;
        for (int i = 0; i < range; i++) {
            // While the count for this element is > 0
            while (count[i]-- > 0) {
                arr[idx++] = i + min; // Restore the value (i + offset)
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println("--- Counting Sort ---");
        System.out.println("Original: " + Arrays.toString(arr));

        // Sorting
        countingSort(arr);

        System.out.println("Sorted:   " + Arrays.toString(arr));

        sc.close();
    }
}
