
// Left Rotate Array by D positions
// Time: O(n) reversal, O(n×d) naive | Space: O(1) reversal
import java.util.*;

public class LeftRotate {

    // Reversal algorithm — O(n) time, O(1) space
    static void leftRotate(int[] arr, int d) {
        int n = arr.length;
        d %= n;
        reverse(arr, 0, d - 1);
        reverse(arr, d, n - 1);
        reverse(arr, 0, n - 1);
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
        System.out.print("Enter rotation count d: ");
        int d = sc.nextInt();

        System.out.println("Original: " + Arrays.toString(arr));
        leftRotate(arr, d);
        System.out.println("Rotated left by " + d + ": " + Arrays.toString(arr));

        sc.close();
    }
}
