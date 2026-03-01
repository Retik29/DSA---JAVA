
/**
 * Kadane's Algorithm
 * 
 * Problem: Given an integer array 'arr', find the contiguous subarray 
 * (containing at least one number) which has the largest sum and return its sum.
 * 
 * Logic (Dynamic Programming):
 * 1. Foundations: At any index 'i', the maximum sum of a subarray ending at 'i' 
 *    is either the element arr[i] itself or (arr[i] + max sum ending at i-1).
 * 2. Formula: maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i])
 * 3. Result: The overall maximum (maxSoFar) is the maximum of all maxEndingHere values.
 * 
 * Complexity:
 * - Time Complexity : O(n) - Single pass through the array.
 * - Space Complexity: O(1) - Constant auxiliary space.
 * 
 * Bonus: This implementation also tracks the start and end indices of the result subarray.
 */
import java.util.*;

public class KadanesAlgorithm {

    /**
     * Calculates the maximum subarray sum and identifies the range.
     * 
     * @return An array containing {maxSum, startIndex, endIndex}
     */
    static int[] maxSubarraySum(int[] arr) {
        if (arr.length == 0)
            return new int[] { 0, -1, -1 };

        int maxSoFar = arr[0]; // Global maximum
        int maxEndingHere = arr[0]; // Maximum sum for subarray ending at current index

        int start = 0, end = 0; // Final indices
        int tempStart = 0; // Potential start index for a new subarray

        for (int i = 1; i < arr.length; i++) {
            // Decision: Start a new subarray at current element, or add it to the previous?
            if (arr[i] > maxEndingHere + arr[i]) {
                maxEndingHere = arr[i];
                tempStart = i; // Reset start index
            } else {
                maxEndingHere += arr[i];
            }

            // Update global maximum if current subarray is better
            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
            }
        }
        return new int[] { maxSoFar, start, end };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Kadane's Algorithm Interactive ---");
        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Processing
        int[] res = maxSubarraySum(arr);
        int maxSum = res[0];
        int startIdx = res[1];
        int endIdx = res[2];

        // Output results
        System.out.println("\n--- Result Summary ---");
        System.out.println("Input Array: " + Arrays.toString(arr));
        System.out.println("Maximum Subarray Sum: " + maxSum);

        if (startIdx != -1) {
            System.out.print("The Subarray is: [");
            for (int i = startIdx; i <= endIdx; i++) {
                System.out.print(arr[i] + (i == endIdx ? "" : ", "));
            }
            System.out.println("] (indices " + startIdx + " to " + endIdx + ")");
        }

        sc.close();
    }
}
