
// 0/1 Knapsack Problem - Dynamic Programming
// Time Complexity: O(n * W) | Space Complexity: O(n * W)
import java.util.*;

public class Knapsack01 {
    static int solveKnapsack(int[] val, int[] wt, int W, int n) {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    dp[i][w] = 0;
                else if (wt[i - 1] <= w)
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }
        return dp[n][W];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();
        int[] val = new int[n];
        int[] wt = new int[n];

        System.out.print("Enter values: ");
        for (int i = 0; i < n; i++)
            val[i] = sc.nextInt();
        System.out.print("Enter weights: ");
        for (int i = 0; i < n; i++)
            wt[i] = sc.nextInt();

        System.out.print("Enter knapsack capacity: ");
        int W = sc.nextInt();

        System.out.println("Maximum value in knapsack: " + solveKnapsack(val, wt, W, n));
        sc.close();
    }
}
