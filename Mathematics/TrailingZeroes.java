
/**
 * Trailing Zeroes in Factorial
 * 
 * Problem: Find the number of trailing zeroes in the value of n!.
 * 
 * Logic:
 * - A trailing zero is produced by a factor of 10.
 * - Factors of 10 are formed by multiplying 2 and 5 (2 * 5 = 10).
 * - In n!, there are always more factors of 2 than factors of 5.
 * - Therefore, the number of trailing zeroes is equal to the number of 
 *   factors of 5 in the prime factorization of n!.
 * 
 * Formula (Legendre's Formula):
 * Trailing Zeroes = floor(n/5) + floor(n/25) + floor(n/125) + ...
 * We continue until 5^k > n.
 * 
 * Time Complexity : O(log5 n) - Very efficient as we check powers of 5.
 * Space Complexity: O(1)
 */
import java.util.*;

public class TrailingZeroes {

    /**
     * Calculates the number of trailing zeroes in n!
     * 
     * @param n Input number
     * @return Count of trailing zeroes
     */
    static int trailingZeros(int n) {
        int count = 0;
        // Count factors of 5, 25, 125...
        for (int i = 5; i <= n; i *= 5) {
            count += n / i;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input gathering
        System.out.print("Enter a number (n): ");
        int n = sc.nextInt();

        // Output results
        int result = trailingZeros(n);
        System.out.println("--- Trailing Zeroes Summary ---");
        System.out.println("Number: " + n + "!");
        System.out.println("Count of trailing zeroes: " + result);

        // Logical hint
        if (n >= 5) {
            System.out.println("Hint: Every power of 5 contributes to the trailing zeroes.");
        }

        sc.close();
    }
}
