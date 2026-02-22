
// Quick Sort — Divide & Conquer using Lomuto partition
// Time: O(n log n) avg, O(n²) worst | Space: O(log n)
import java.util.*;

public class QuickSort {

    static void quickSort(int[] arr, int lo, int hi) {
        if (lo >= hi)
            return;
        int pi = partition(arr, lo, hi);
        quickSort(arr, lo, pi - 1);
        quickSort(arr, pi + 1, hi);
    }

    // Lomuto partition: pivot = last element
    static int partition(int[] arr, int lo, int hi) {
        int pivot = arr[hi], i = lo - 1;
        for (int j = lo; j < hi; j++)
            if (arr[j] < pivot) {
                i++;
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        i++;
        int t = arr[i];
        arr[i] = arr[hi];
        arr[hi] = t;
        return i;
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
        quickSort(arr, 0, n - 1);
        System.out.println("Sorted:   " + Arrays.toString(arr));

        sc.close();
    }
}
