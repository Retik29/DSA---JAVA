
/**
 * Subset Sum Problem
 * 
 * Problem: Given a set of non-negative integers and a target 'sum', determine 
 * if there is a subset of the given set with sum equal to the target.
 * 
 * Logic (Bottom-Up DP):
 * 1. State: dp[i][j] is a boolean representing if a sum 'j' can be achieved 
 *    using a subset of the first 'i' elements of the array.
 * 
 * 2. Base Cases:
 *    - If sum is 0 (j=0), it's always possible (by an empty subset) -> dp[i][0] = true.
 *    - If array is empty (i=0) and sum is not 0, it's impossible -> dp[0][j] = false.
 * 
 * 3. Transitions:
 *    For each element arr[i-1] at sum 'j':
 *    - Exclusion: If we can form sum 'j' using 'i-1' elements, we can also form it with 'i'.
 *      dp[i][j] = dp[i-1][j]
 *    - Inclusion: If current element arr[i-1] <= j, we check if sum (j - arr[i-1]) 
 *      was possible with 'i-1' elements.
 *      dp[i][j] = dp[i][j] || dp[i-1][j - arr[i-1]]
 * 
 * Complexity:
 * - Time Complexity : O(n * sum) - Loop through elements (n) and target sum (sum).
 * - Space Complexity: O(n * sum) - To store the DP table.
 */
import java.util.*;

public class SubsetSum {

    /**
     * Efficient DP implementation of Subset Sum.
     */
    static boolean hasSubsetSum(int[] arr, int targetSum) {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][targetSum + 1];

        // Base case: sum 0 is always possible (empty subset)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= targetSum; j++) {
                // Choice 1: Exclude the current element
                dp[i][j] = dp[i - 1][j];

                // Choice 2: Include the current element (if it doesn't exceed current sum j)
                if (j >= arr[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        return dp[n][targetSum];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Subset Sum Problem (DP) ---");
        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements (non-negative): ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter target sum: ");
        int target = sc.nextInt();

        // Execution
        boolean exists = hasSubsetSum(arr, target);

        // Output results
        System.out.println("\n--- Summary ---");
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target Sum: " + target);
        if (exists) {
            System.out.println("Result: YES, a subset with sum " + target + " exists.");
        } else {
            System.out.println("Result: NO, no such subset exists.");
        }

        sc.close();
    }
}
