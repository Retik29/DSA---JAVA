
// Maximum Difference — max(arr[j] - arr[i]) where j > i
// Time: O(n) | Space: O(1)
import java.util.*;

public class MaximumDifference {

    static int maxDifference(int[] arr) {
        int minSoFar = arr[0], maxDiff = arr[1] - arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxDiff = Math.max(maxDiff, arr[i] - minSoFar);
            minSoFar = Math.min(minSoFar, arr[i]);
        }
        return maxDiff;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Maximum difference (arr[j]-arr[i], j>i): " + maxDifference(arr));

        sc.close();
    }
}
