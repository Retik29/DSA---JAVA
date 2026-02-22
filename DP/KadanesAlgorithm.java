
// Kadane's Algorithm — Maximum Subarray Sum
// Time: O(n) | Space: O(1)
import java.util.*;

public class KadanesAlgorithm {

    // Returns {maxSum, startIndex, endIndex}
    static int[] maxSubarraySum(int[] arr) {
        int maxSoFar = arr[0], maxHere = arr[0];
        int start = 0, end = 0, tempStart = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxHere + arr[i]) {
                maxHere = arr[i];
                tempStart = i;
            } else
                maxHere += arr[i];
            if (maxHere > maxSoFar) {
                maxSoFar = maxHere;
                start = tempStart;
                end = i;
            }
        }
        return new int[] { maxSoFar, start, end };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        int[] res = maxSubarraySum(arr);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Max subarray sum: " + res[0]);
        System.out.println("Subarray: " + Arrays.toString(Arrays.copyOfRange(arr, res[1], res[2] + 1)));

        sc.close();
    }
}
