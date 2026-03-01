
/**
 * Rope Cutting Problem
 * 
 * Problem: Given a rope of length 'n', find the maximum number of pieces 
 * you can cut it into, such that each piece has a length of either 'a', 'b', or 'c'.
 * If no such cut is possible, return -1.
 * 
 * Logic (Bottom-Up DP):
 * 1. State: dp[i] represents the maximum number of pieces for a rope of length 'i'.
 * 
 * 2. Base Case: 
 *    dp[0] = 0 (A rope of length 0 can have 0 pieces).
 *    Initialize all other dp[i] with -1 (indicating not reachable/cuttable).
 * 
 * 3. Transitions:
 *    For each length 'i' from 1 to 'n':
 *    Check if cut 'a', 'b', or 'c' can be made:
 *    - If (i >= a) and (dp[i-a] is reachable): update dp[i] = max(dp[i], dp[i-a] + 1)
 *    - Repeat for lengths 'b' and 'c'.
 * 
 * Complexity:
 * - Time Complexity : O(n) - Single loop from 1 to n.
 * - Space Complexity: O(n) - To store the DP array.
 */
import java.util.*;

public class RopeCutting {

    /**
     * Efficient DP implementation to find max pieces.
     */
    static int solveRopeCutting(int n, int a, int b, int c) {
        int[] dp = new int[n + 1];

        // Initialize with -1 (representing invalid/not possible)
        Arrays.fill(dp, -1);

        // Base case: length 0 results in 0 pieces
        dp[0] = 0;

        // Iterate through all possible lengths till 'n'
        for (int i = 1; i <= n; i++) {
            // Try cutting piece of length 'a'
            if (i >= a && dp[i - a] != -1) {
                dp[i] = Math.max(dp[i], dp[i - a] + 1);
            }

            // Try cutting piece of length 'b'
            if (i >= b && dp[i - b] != -1) {
                dp[i] = Math.max(dp[i], dp[i - b] + 1);
            }

            // Try cutting piece of length 'c'
            if (i >= c && dp[i - c] != -1) {
                dp[i] = Math.max(dp[i], dp[i - c] + 1);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Rope Cutting Max Pieces (DP) ---");
        System.out.print("Enter total rope length (n): ");
        int n = sc.nextInt();

        System.out.print("Enter three allowed cut lengths (a b c): ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        // Execution
        int result = solveRopeCutting(n, a, b, c);

        // Output formatting
        System.out.println("\n--- Summary ---");
        if (result == -1) {
            System.out.println("Result: It is NOT possible to cut the rope of length " + n +
                    " into pieces of lengths " + a + ", " + b + ", or " + c + ".");
        } else {
            System.out.println("Result: Maximum number of pieces possible: " + result);
        }

        sc.close();
    }
}
