
/**
 * Longest Common Subsequence (LCS)
 * 
 * Problem: Given two strings, find the length of the longest subsequence present in both of them.
 * A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous.
 * 
 * Logic (Bottom-Up DP):
 * 1. State: dp[i][j] represents the length of LCS for strings s1[0...i-1] and s2[0...j-1].
 * 
 * 2. Base Case:
 *    If either string is empty (i=0 or j=0), result is 0.
 * 
 * 3. Transitions:
 *    - If s1.charAt(i-1) == s2.charAt(j-1): 
 *      We found a matching character! -> dp[i][j] = 1 + dp[i-1][j-1]
 *    - Else (Characters don't match):
 *      We take the maximum of excluding trailing char from s1 or s2.
 *      dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 * 
 * Complexity:
 * - Time Complexity : O(m * n) - Nested loops for string lengths m and n.
 * - Space Complexity: O(m * n) - To store the DP table.
 */
import java.util.*;

public class LongestCommonSubsequence {

    /**
     * Calculates length of LCS and prints the LCS string.
     */
    static int solveLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Step 1: Fill the DP table
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1]; // Match found
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // No match
                }
            }
        }

        // Step 2: Backtrack to find the actual LCS string
        StringBuilder lcsStr = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcsStr.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        if (lcsStr.length() > 0) {
            System.out.println("Actual LCS: " + lcsStr.reverse().toString());
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Longest Common Subsequence (LCS) ---");
        System.out.print("Enter first string: ");
        String s1 = sc.next();
        System.out.print("Enter second string: ");
        String s2 = sc.next();

        int length = solveLCS(s1, s2);
        System.out.println("Length of LCS: " + length);

        sc.close();
    }
}
