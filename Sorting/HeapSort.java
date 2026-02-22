
// Heap Sort — build max heap, then extract max repeatedly
// Time: O(n log n) | Space: O(1) in-place
import java.util.*;

public class HeapSort {

    // Heapify subtree rooted at index i (max heap)
    static void heapify(int[] arr, int n, int i) {
        int largest = i, left = 2 * i + 1, right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest])
            largest = left;
        if (right < n && arr[right] > arr[largest])
            largest = right;
        if (largest != i) {
            int t = arr[i];
            arr[i] = arr[largest];
            arr[largest] = t;
            heapify(arr, n, largest);
        }
    }

    static void heapSort(int[] arr) {
        int n = arr.length;
        // Build max heap (bottom-up)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        // Extract max one by one
        for (int i = n - 1; i > 0; i--) {
            int t = arr[0];
            arr[0] = arr[i];
            arr[i] = t; // swap root with end
            heapify(arr, i, 0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println("Original: " + Arrays.toString(arr));
        heapSort(arr);
        System.out.println("Sorted:   " + Arrays.toString(arr));

        sc.close();
    }
}
