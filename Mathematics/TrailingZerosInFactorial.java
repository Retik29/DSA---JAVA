
// Trailing Zeros in Factorial — count factors of 5
// Formula: ⌊n/5⌋ + ⌊n/25⌋ + ⌊n/125⌋ + ...
// Time: O(log₅ n) | Space: O(1)
import java.util.*;

public class TrailingZerosInFactorial {

    // Count trailing zeros in n!
    static int countTrailingZeros(int n) {
        int count = 0;
        for (int p = 5; p <= n; p *= 5)
            count += n / p;
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        System.out.println("Trailing zeros in " + n + "! = " + countTrailingZeros(n));

        // Show breakdown
        System.out.print("Breakdown: ");
        StringBuilder sb = new StringBuilder();
        for (int p = 5; p <= n; p *= 5)
            sb.append(sb.length() > 0 ? " + " : "").append(n + "/" + p + "=" + n / p);
        System.out.println(sb);

        sc.close();
    }
}
