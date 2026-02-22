
// Median of Two Sorted Arrays — Binary Search
// Time: O(log(min(n,m))) | Space: O(1)
import java.util.*;

public class MedianOfTwoSortedArrays {

    static double findMedian(int[] a, int[] b) {
        // Ensure binary search on smaller array
        if (a.length > b.length)
            return findMedian(b, a);
        int n = a.length, m = b.length, lo = 0, hi = n;

        while (lo <= hi) {
            int i = (lo + hi) / 2; // partition in a
            int j = (n + m + 1) / 2 - i; // partition in b

            int maxLeftA = (i == 0) ? Integer.MIN_VALUE : a[i - 1];
            int minRightA = (i == n) ? Integer.MAX_VALUE : a[i];
            int maxLeftB = (j == 0) ? Integer.MIN_VALUE : b[j - 1];
            int minRightB = (j == m) ? Integer.MAX_VALUE : b[j];

            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                if ((n + m) % 2 == 0)
                    return (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2.0;
                return Math.max(maxLeftA, maxLeftB);
            } else if (maxLeftA > minRightB)
                hi = i - 1;
            else
                lo = i + 1;
        }
        throw new IllegalArgumentException("Arrays are not sorted");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array 1: ");
        int n = sc.nextInt();
        int[] a = new int[n];
        System.out.print("Enter " + n + " sorted elements: ");
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();

        System.out.print("Enter size of array 2: ");
        int m = sc.nextInt();
        int[] b = new int[m];
        System.out.print("Enter " + m + " sorted elements: ");
        for (int i = 0; i < m; i++)
            b[i] = sc.nextInt();

        System.out.println("Array 1: " + Arrays.toString(a));
        System.out.println("Array 2: " + Arrays.toString(b));
        System.out.println("Median: " + findMedian(a, b));

        sc.close();
    }
}
