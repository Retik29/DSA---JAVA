
/**
 * Trailing Zeros in Factorial
 * 
 * Problem: Find the number of zeros at the end of 'n!' (n factorial).
 * 
 * Logic:
 * - A trailing zero is created by a factor of 10.
 * - 10 = 2 * 5.
 * - In n!, factors of 2 are always more frequent than factors of 5.
 * - Thus, counting trailing zeros is equivalent to counting factors of 5 in n!.
 * 
 * Formula:
 * Count = floor(n/5) + floor(n/25) + floor(n/125) + ...
 * - floor(n/5) counts numbers with at least one factor of 5.
 * - floor(n/25) counts numbers with at least two factors of 5 (adding the second factor).
 * - And so on for higher powers of 5.
 * 
 * Time Complexity : O(log5 n)
 * Space Complexity: O(1)
 */
import java.util.*;

public class TrailingZerosInFactorial {

    /**
     * Calculates the number of trailing zeros in n!.
     * 
     * @param n Input number
     * @return Number of trailing zeros
     */
    static int countTrailingZeros(int n) {
        int count = 0;
        // Keep dividing n by powers of 5 and summing the quotients
        for (int p = 5; p <= n; p *= 5) {
            count += n / p;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input gathering
        System.out.print("Enter an integer (n) to find zeros in n!: ");
        int n = sc.nextInt();

        // Perform calculation
        int result = countTrailingZeros(n);

        // Output results
        System.out.println("--- Trailing Zero Analysis ---");
        System.out.println(n + "! has " + result + " trailing zeros.");

        // Visual breakdown of the formula
        System.out.print("Mathematical Breakdown: ");
        StringBuilder sb = new StringBuilder();
        for (int p = 5; p <= n; p *= 5) {
            if (sb.length() > 0)
                sb.append(" + ");
            sb.append("floor(").append(n).append("/").append(p).append(")=").append(n / p);
        }
        System.out.println(sb.length() > 0 ? sb : "None (n < 5)");

        sc.close();
    }
}
