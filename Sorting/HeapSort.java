
/**
 * Heap Sort
 * 
 * Algorithm: Comparison-based sorting based on Binary Heap data structure.
 * 
 * Steps:
 * 1. Build a Max Heap from the input data. (In a max heap, the root 
 *    is the largest element).
 * 2. At this point, the largest item is stored at the root of the heap. 
 *    Replace it with the last item of the heap followed by reducing the size 
 *    of the heap by 1. Finally, heapify the root of the tree.
 * 3. Repeat step 2 while the size of the heap is greater than 1.
 * 
 * Time Complexity : O(n log n) - O(n) to build heap + O(n log n) to extract.
 * Space Complexity: O(1)       - In-place sorting.
 */
import java.util.*;

public class HeapSort {

    /**
     * Heapify a subtree rooted with node 'i' which is an index in arr[].
     * 'n' is size of heap.
     */
    static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest])
            largest = left;

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest])
            largest = right;

        // If largest is not root, swap and continue heapifying
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    /**
     * Main function that implements Heap Sort.
     */
    static void heapSort(int[] arr) {
        int n = arr.length;

        // Step 1: Build max heap (rearrange array)
        // We start from the last non-leaf node and heapify each node
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // Step 2: One by one extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end (arr[0] is the maximum)
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
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

        System.out.println("--- Heap Sort ---");
        System.out.println("Original: " + Arrays.toString(arr));

        // Sorting
        heapSort(arr);

        System.out.println("Sorted:   " + Arrays.toString(arr));

        sc.close();
    }
}
