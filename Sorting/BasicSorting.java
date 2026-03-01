
/**
 * Basic Sorting Algorithms
 * 
 * Includes three fundamental O(n²) sorting algorithms:
 * 1. Bubble Sort
 * 2. Selection Sort
 * 3. Insertion Sort
 * 
 * Time Complexity : O(n²) for all (on average and worst case).
 * Space Complexity: O(1) - All are in-place sorting algorithms.
 */
import java.util.*;

public class BasicSorting {

    /**
     * Bubble Sort: Repeatedly steps through the list, compares adjacent elements
     * and swaps them if they are in the wrong order.
     * Optimization: Uses a 'swapped' flag to exit early if the array is already
     * sorted.
     */
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            // Last i elements are already in place
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap adjacent elements
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                    swapped = true;
                }
            }
            // If no elements were swapped, the array is already sorted
            if (!swapped)
                break;
        }
    }

    /**
     * Selection Sort: Dividies the input list into two parts: the sublist of items
     * already sorted and the sublist of items remaining to be sorted.
     * Core Idea: Repeatedly find the minimum element from the unsorted part
     * and put it at the beginning.
     */
    static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx])
                    minIdx = j;
            }
            // Swap the found minimum element with the first element of unsorted part
            int t = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = t;
        }
    }

    /**
     * Insertion Sort: Builds the final sorted array one item at a time.
     * Core Idea: Take one element and insert it into its correct position
     * in the already sorted left part of the array.
     */
    static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // Current element to be inserted
            int j = i - 1;

            // Move elements of arr[0..i-1] that are greater than key
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            // Place key at its correct position
            arr[j + 1] = key;
        }
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

        System.out.println("--- Original Array ---");
        System.out.println(Arrays.toString(arr));

        // Create copies to demonstrate each sorting algorithm
        int[] a1 = arr.clone();
        int[] a2 = arr.clone();
        int[] a3 = arr.clone();

        // Perform sorts
        bubbleSort(a1);
        selectionSort(a2);
        insertionSort(a3);

        // Output results
        System.out.println("\n--- Sorting Results ---");
        System.out.println("Bubble Sort:    " + Arrays.toString(a1));
        System.out.println("Selection Sort: " + Arrays.toString(a2));
        System.out.println("Insertion Sort: " + Arrays.toString(a3));

        sc.close();
    }
}
