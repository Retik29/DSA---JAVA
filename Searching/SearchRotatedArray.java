
// Search in Rotated Sorted Array — Modified Binary Search
// Time: O(log n) | Space: O(1)
import java.util.*;

public class SearchRotatedArray {

    static int search(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target)
                return mid;

            if (arr[lo] <= arr[mid]) { // left half sorted
                if (target >= arr[lo] && target < arr[mid])
                    hi = mid - 1;
                else
                    lo = mid + 1;
            } else { // right half sorted
                if (target > arr[mid] && target <= arr[hi])
                    lo = mid + 1;
                else
                    hi = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements (rotated sorted): ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.print("Enter target: ");
        int target = sc.nextInt();

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target " + target + " found at index: " + search(arr, target));

        sc.close();
    }
}
