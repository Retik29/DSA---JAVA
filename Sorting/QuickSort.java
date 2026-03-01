
/**
 * Quick Sort
 * 
 * Algorithm: Divide and Conquer
 * 1. Pick an element as 'pivot' (this implementation uses the Lomuto partition scheme 
 *    where the last element is picked as the pivot).
 * 2. Partition: Rearrange the array so that all elements smaller than the pivot 
 *    are on its left, and all elements larger than the pivot are on its right.
 * 3. Recursively apply the same steps to the left and right sub-arrays.
 * 
 * Time Complexity : O(n log n) average, O(n²) worst case.
 * Space Complexity: O(log n) - average stack space for recursion.
 */
import java.util.*;

public class QuickSort {

    /**
     * Main recursive function that implements Quick Sort.
     * 
     * @param arr Array to be sorted
     * @param lo  Starting index
     * @param hi  Ending index
     */
    static void quickSort(int[] arr, int lo, int hi) {
        // Base case: range has 0 or 1 element
        if (lo >= hi)
            return;

        // Partition the array and get the pivot index
        int pi = partition(arr, lo, hi);

        // Recursively sort elements before and after partition
        quickSort(arr, lo, pi - 1);
        quickSort(arr, pi + 1, hi);
    }

    /**
     * Lomuto Partition Scheme.
     * This function takes the last element as pivot, places the pivot element
     * at its correct position in sorted array, and places all smaller
     * (than pivot) to left of pivot and all greater elements to right of pivot.
     * 
     * @return Index of the pivot after partitioning
     */
    static int partition(int[] arr, int lo, int hi) {
        int pivot = arr[hi]; // Choosing the last element as pivot
        int i = lo - 1; // Index of smaller element

        for (int j = lo; j < hi; j++) {
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }

        // Swap the pivot element with the element at i+1
        i++;
        int t = arr[i];
        arr[i] = arr[hi];
        arr[hi] = t;

        return i; // Return the position of the pivot
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

        System.out.println("--- Quick Sort ---");
        System.out.println("Original: " + Arrays.toString(arr));

        // Sorting
        quickSort(arr, 0, n - 1);

        System.out.println("Sorted:   " + Arrays.toString(arr));

        sc.close();
    }
}
