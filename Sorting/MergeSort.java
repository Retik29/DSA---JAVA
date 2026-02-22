
// Merge Sort — Divide & Conquer
// Time: O(n log n) | Space: O(n)
import java.util.*;

public class MergeSort {

    static void mergeSort(int[] arr, int l, int r) {
        if (l >= r)
            return;
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    // Merge two sorted halves [l..mid] and [mid+1..r]
    static void merge(int[] arr, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int i = l, j = mid + 1, k = 0;
        while (i <= mid && j <= r)
            temp[k++] = (arr[i] <= arr[j]) ? arr[i++] : arr[j++];
        while (i <= mid)
            temp[k++] = arr[i++];
        while (j <= r)
            temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, l, temp.length);
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
        mergeSort(arr, 0, n - 1);
        System.out.println("Sorted:   " + Arrays.toString(arr));

        sc.close();
    }
}
