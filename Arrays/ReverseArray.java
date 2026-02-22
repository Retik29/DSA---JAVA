
// Reverse Array — two pointers
// Time: O(n) | Space: O(1)
import java.util.*;

public class ReverseArray {

    static void reverse(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            int t = arr[i];
            arr[i++] = arr[j];
            arr[j--] = t;
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

        System.out.println("Original: " + Arrays.toString(arr));
        reverse(arr);
        System.out.println("Reversed: " + Arrays.toString(arr));

        sc.close();
    }
}
