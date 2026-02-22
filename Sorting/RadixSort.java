
// Radix Sort — sort digit by digit using counting sort as subroutine
// Time: O(d × (n + b)) where d = digits, b = base | Space: O(n + b)
import java.util.*;

public class RadixSort {

    // Counting sort by a specific digit (exp = 1, 10, 100, ...)
    static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n], count = new int[10];
        for (int num : arr)
            count[(num / exp) % 10]++;
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1]; // prefix sum
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[--count[digit]] = arr[i];
        }
        System.arraycopy(output, 0, arr, 0, n);
    }

    static void radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(0);
        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(arr, exp);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " non-negative elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println("Original: " + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("Sorted:   " + Arrays.toString(arr));

        sc.close();
    }
}
