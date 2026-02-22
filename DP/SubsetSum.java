
// Subset Sum — check if subset with given sum exists
// Time: O(n × sum) DP, O(2^n) recursive | Space: O(n × sum) DP
import java.util.*;

public class SubsetSum {

    // Recursive check
    static boolean hasSubsetSum(int[] arr, int n, int sum) {
        if (sum == 0)
            return true;
        if (n == 0)
            return false;
        if (arr[n - 1] > sum)
            return hasSubsetSum(arr, n - 1, sum);
        return hasSubsetSum(arr, n - 1, sum) || hasSubsetSum(arr, n - 1, sum - arr[n - 1]);
    }

    // DP approach — O(n × sum) time
    static boolean hasSubsetSumDP(int[] arr, int sum) {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (int i = 0; i <= n; i++)
            dp[i][0] = true; // sum 0 always possible

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j]; // exclude
                if (j >= arr[i - 1])
                    dp[i][j] |= dp[i - 1][j - arr[i - 1]]; // include
            }
        return dp[n][sum];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.print("Enter target sum: ");
        int sum = sc.nextInt();

        System.out.println("Array: " + Arrays.toString(arr) + ", Sum: " + sum);
        System.out.println("Subset with sum exists (DP)? " + hasSubsetSumDP(arr, sum));

        sc.close();
    }
}
