
// Basic Sorting — Bubble, Selection, Insertion Sort
// Time: O(n²) each | Space: O(1)
import java.util.*;

public class BasicSorting {

    // Bubble Sort: repeatedly swap adjacent if out of order
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                    swapped = true;
                }
            if (!swapped)
                break; // already sorted
        }
    }

    // Selection Sort: find min and place at correct position
    static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min])
                    min = j;
            int t = arr[i];
            arr[i] = arr[min];
            arr[min] = t;
        }
    }

    // Insertion Sort: insert each element at correct position in sorted part
    static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i], j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
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

        System.out.println("Original:  " + Arrays.toString(arr));

        int[] a1 = arr.clone(), a2 = arr.clone(), a3 = arr.clone();
        bubbleSort(a1);
        selectionSort(a2);
        insertionSort(a3);

        System.out.println("Bubble:    " + Arrays.toString(a1));
        System.out.println("Selection: " + Arrays.toString(a2));
        System.out.println("Insertion: " + Arrays.toString(a3));

        sc.close();
    }
}
