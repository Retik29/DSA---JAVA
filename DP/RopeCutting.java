
// Rope Cutting — max pieces of lengths a, b, or c from rope of length n
// Time: O(3^n) without memo | Space: O(n)
import java.util.*;

public class RopeCutting {

    // Recursive: try cutting a, b, c and take max
    static int maxPieces(int n, int a, int b, int c) {
        if (n == 0)
            return 0;
        if (n < 0)
            return -1;
        int res = Math.max(maxPieces(n - a, a, b, c),
                Math.max(maxPieces(n - b, a, b, c), maxPieces(n - c, a, b, c)));
        return (res == -1) ? -1 : res + 1;
    }

    // DP approach — O(n) time, O(n) space
    static int maxPiecesDP(int n, int a, int b, int c) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (i >= a && dp[i - a] != -1)
                dp[i] = Math.max(dp[i], dp[i - a] + 1);
            if (i >= b && dp[i - b] != -1)
                dp[i] = Math.max(dp[i], dp[i - b] + 1);
            if (i >= c && dp[i - c] != -1)
                dp[i] = Math.max(dp[i], dp[i - c] + 1);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter rope length n: ");
        int n = sc.nextInt();
        System.out.print("Enter cut lengths (a b c): ");
        int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();

        System.out.println("Max pieces (DP): " + maxPiecesDP(n, a, b, c));

        sc.close();
    }
}
