
// Rotate Array Right by K positions — reversal algorithm
// Time: O(n) | Space: O(1)
import java.util.*;

public class rotateRightK {

    static void rotateRight(int[] arr, int k) {
        int n = arr.length;
        k %= n;
        reverse(arr, 0, n - 1); // reverse all
        reverse(arr, 0, k - 1); // reverse first k
        reverse(arr, k, n - 1); // reverse rest
    }

    private static void reverse(int[] a, int i, int j) {
        while (i < j) {
            int t = a[i];
            a[i++] = a[j];
            a[j--] = t;
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
        System.out.print("Enter k: ");
        int k = sc.nextInt();

        System.out.println("Original: " + Arrays.toString(arr));
        rotateRight(arr, k);
        System.out.println("Rotated right by " + k + ": " + Arrays.toString(arr));

        sc.close();
    }
}
