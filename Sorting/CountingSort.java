
// Counting Sort — non-comparison sort for integers in known range
// Time: O(n + k) where k = range | Space: O(k)
import java.util.*;

public class CountingSort {

    static void countingSort(int[] arr) {
        if (arr.length == 0)
            return;
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;

        int[] count = new int[range];
        for (int num : arr)
            count[num - min]++; // count occurrences
        int idx = 0;
        for (int i = 0; i < range; i++) // place back
            while (count[i]-- > 0)
                arr[idx++] = i + min;
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
        countingSort(arr);
        System.out.println("Sorted:   " + Arrays.toString(arr));

        sc.close();
    }
}
