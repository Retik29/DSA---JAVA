
// First and Last Occurrence in Sorted Array — Binary Search
// Time: O(log n) | Space: O(1)
import java.util.*;

public class FirstLastOccurrence {

    static int findFirst(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1, res = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) {
                res = mid;
                hi = mid - 1;
            } // go left
            else if (arr[mid] < target)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return res;
    }

    static int findLast(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1, res = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) {
                res = mid;
                lo = mid + 1;
            } // go right
            else if (arr[mid] < target)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return res;
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

        int first = findFirst(arr, target), last = findLast(arr, target);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("First occurrence: " + first + ", Last: " + last);
        System.out.println("Count: " + (first == -1 ? 0 : last - first + 1));

        sc.close();
    }
}
