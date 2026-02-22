
// Dutch National Flag — sort 0s, 1s, 2s in single pass
// Time: O(n) | Space: O(1)
import java.util.*;

public class DutchNationalFlag {

    // Three pointers: low, mid, high
    static void sort012(int[] arr) {
        int lo = 0, mid = 0, hi = arr.length - 1;
        while (mid <= hi) {
            if (arr[mid] == 0) {
                int t = arr[lo];
                arr[lo++] = arr[mid];
                arr[mid++] = t;
            } else if (arr[mid] == 1)
                mid++;
            else {
                int t = arr[mid];
                arr[mid] = arr[hi];
                arr[hi--] = t;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements (0s, 1s, 2s): ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println("Original: " + Arrays.toString(arr));
        sort012(arr);
        System.out.println("Sorted:   " + Arrays.toString(arr));

        sc.close();
    }
}
