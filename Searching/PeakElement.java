
// Peak Element — find element greater than its neighbors
// Time: O(log n) | Space: O(1)
import java.util.*;

public class PeakElement {

    static int findPeak(int[] arr) {
        int lo = 0, hi = arr.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] < arr[mid + 1])
                lo = mid + 1; // peak is on right
            else
                hi = mid; // peak is on left or mid
        }
        return lo;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        int idx = findPeak(arr);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Peak element: " + arr[idx] + " at index " + idx);

        sc.close();
    }
}
