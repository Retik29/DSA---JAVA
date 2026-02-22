
// K-th element of two sorted arrays
// Time Complexity: O(log(min(n, m))) | Space Complexity: O(1)
import java.util.*;

public class KthElementOfTwoSortedArrays {

    static long kthElement(int arr1[], int arr2[], int n, int m, int k) {
        if (n > m)
            return kthElement(arr2, arr1, m, n, k);

        int low = Math.max(0, k - m), high = Math.min(k, n);

        while (low <= high) {
            int cut1 = (low + high) / 2;
            int cut2 = k - cut1;

            int l1 = (cut1 == 0) ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int l2 = (cut2 == 0) ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int r1 = (cut1 == n) ? Integer.MAX_VALUE : arr1[cut1];
            int r2 = (cut2 == m) ? Integer.MAX_VALUE : arr2[cut2];

            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            } else if (l1 > r2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of first sorted array: ");
        int n = sc.nextInt();
        int[] arr1 = new int[n];
        System.out.print("Enter elements: ");
        for (int i = 0; i < n; i++)
            arr1[i] = sc.nextInt();

        System.out.print("Enter size of second sorted array: ");
        int m = sc.nextInt();
        int[] arr2 = new int[m];
        System.out.print("Enter elements: ");
        for (int i = 0; i < m; i++)
            arr2[i] = sc.nextInt();

        System.out.print("Enter k: ");
        int k = sc.nextInt();

        System.out.println("The " + k + "-th element is: " + kthElement(arr1, arr2, n, m, k));

        sc.close();
    }
}
