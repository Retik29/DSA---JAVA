
// Binary Search — Iterative and Recursive
// Time: O(log n) | Space: O(1) iterative, O(log n) recursive
import java.util.*;

public class BinarySearch {

    // Iterative
    static int binarySearch(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target)
                return mid;
            else if (arr[mid] < target)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return -1;
    }

    // Recursive
    static int binarySearchRec(int[] arr, int lo, int hi, int target) {
        if (lo > hi)
            return -1;
        int mid = lo + (hi - lo) / 2;
        if (arr[mid] == target)
            return mid;
        return (arr[mid] < target) ? binarySearchRec(arr, mid + 1, hi, target)
                : binarySearchRec(arr, lo, mid - 1, target);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter sorted array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " sorted elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.print("Enter target: ");
        int target = sc.nextInt();

        int idx = binarySearch(arr, target);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target " + target + " found at index: " + idx);

        sc.close();
    }
}
