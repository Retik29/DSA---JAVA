
/**
 * 0/1 Knapsack Problem (Dynamic Programming)
 * 
 * Problem: Given weights and values of N items, put these items in a knapsack 
 * of capacity W to get the maximum total value. You cannot break items (0 or 1).
 * 
 * Logic (Bottom-Up DP):
 * 1. State: dp[i][w] represents the maximum value obtained using the first 'i' items 
 *    with a maximum weight capacity of 'w'.
 * 
 * 2. Base Case: 
 *    If items (i) = 0 or weight (w) = 0, dp[i][w] = 0.
 * 
 * 3. Transitions:
 *    For each item 'i' at capacity 'w':
 *    - If weight of current item (wt[i-1]) is less than or equal to 'w':
 *      Choice 1: Include item -> val[i-1] + dp[i-1][w - wt[i-1]]
 *      Choice 2: Exclude item -> dp[i-1][w]
 *      dp[i][w] = max(Choice 1, Choice 2)
 *    - Else (item is too heavy):
 *      dp[i][w] = dp[i-1][w]
 * 
 * Complexity:
 * - Time Complexity : O(n * W) - Nested loops for items and capacity.
 * - Space Complexity: O(n * W) - To store the DP table.
 */
import java.util.*;

public class Knapsack01 {

    /**
     * Solves the 0/1 Knapsack problem using a 2D DP table.
     */
    static int solveKnapsack(int[] values, int[] weights, int capacity, int n) {
        int[][] dp = new int[n + 1][capacity + 1];

        // Build table dp[][] in bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                // Base Case: no items or no capacity
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                }
                // Case 1: Current item can fit into the current capacity
                else if (weights[i - 1] <= w) {
                    // Max of (including the current item, excluding the current item)
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]],
                            dp[i - 1][w]);
                }
                // Case 2: Current item's weight > current capacity
                else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- 0/1 Knapsack Solver ---");
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] values = new int[n];
        int[] weights = new int[n];

        System.out.print("Enter the values of " + n + " items: ");
        for (int i = 0; i < n; i++)
            values[i] = sc.nextInt();

        System.out.print("Enter the weights of " + n + " items: ");
        for (int i = 0; i < n; i++)
            weights[i] = sc.nextInt();

        System.out.print("Enter knapsack weight capacity (W): ");
        int W = sc.nextInt();

        // Calculate result
        int maxValue = solveKnapsack(values, weights, W, n);

        System.out.println("\n--- Summary ---");
        System.out.println("Max weight allowed: " + W);
        System.out.println("Maximum total value achieved: " + maxValue);

        sc.close();
    }
}
