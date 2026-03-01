
/**
 * Merge Sort
 * 
 * Algorithm: Divide and Conquer
 * 1. Divide: Split the array into two halves.
 * 2. Conquer: Recursively sort each half until you reach base cases (1 element).
 * 3. Combine: Merge the two sorted halves back into a single sorted array.
 * 
 * Time Complexity : O(n log n) - In all cases (best, average, worst).
 * Space Complexity: O(n) - Requires extra space for the temporary merge array.
 */
import java.util.*;

public class MergeSort {

    /**
     * Main recursive function that implements Merge Sort.
     * 
     * @param arr Array to be sorted
     * @param l   Left index (start)
     * @param r   Right index (end)
     */
    static void mergeSort(int[] arr, int l, int r) {
        // Base case: 0 or 1 element in the range
        if (l >= r)
            return;

        // Find the midpoint
        int mid = l + (r - l) / 2;

        // Recursive calls to sort the left and right halves
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);

        // Merge the sorted halves
        merge(arr, l, mid, r);
    }

    /**
     * Merges two sorted sub-arrays into a single sorted range.
     * Sub-array 1 is arr[l..mid]
     * Sub-array 2 is arr[mid+1..r]
     */
    static void merge(int[] arr, int l, int mid, int r) {
        // Create a temporary array to store the merged result
        int[] temp = new int[r - l + 1];
        int i = l; // Pointer for left sub-array
        int j = mid + 1; // Pointer for right sub-array
        int k = 0; // Pointer for temp array

        // Compare elements and add the smaller one to temp
        while (i <= mid && j <= r) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // Add remaining elements from the left sub-array (if any)
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Add remaining elements from the right sub-array (if any)
        while (j <= r) {
            temp[k++] = arr[j++];
        }

        // Copy the sorted temp array back into the original array starting at index l
        System.arraycopy(temp, 0, arr, l, temp.length);
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

        System.out.println("--- Merge Sort ---");
        System.out.println("Original: " + Arrays.toString(arr));

        // Sorting
        mergeSort(arr, 0, n - 1);

        System.out.println("Sorted:   " + Arrays.toString(arr));

        sc.close();
    }
}
