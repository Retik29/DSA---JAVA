
/**
 * Check if a number is Power of Two
 * 
 * Problem: Determine if a given integer 'n' is a power of two (2, 4, 8, 16...).
 * 
 * Bitwise Concept:
 * - A number that is a power of two has EXACTLY one set bit in its binary 
 *   representation.
 * - Example: 8 (1000), 16 (10000).
 * - Property: If n is a power of 2, then (n & (n - 1)) will be 0.
 * - Why? (n - 1) flips all bits from the rightmost set bit inclusive.
 *   Example 8: 1000 & (0111) = 0000.
 * 
 * Time Complexity : O(1)
 * Space Complexity: O(1)
 */
import java.util.*;

public class PowerOfTwo {

    /**
     * Efficiently checks if n is a power of two.
     * 
     * @param n Input integer
     * @return true if power of two, false otherwise
     */
    static boolean isPowerOfTwo(int n) {
        // Must be positive and have exactly one set bit
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter a number to check: ");
        int n = sc.nextInt();

        // Perform check
        boolean result = isPowerOfTwo(n);

        // Output results
        System.out.println("--- Power of Two Check ---");
        System.out.println("Number: " + n + " (" + Integer.toBinaryString(n) + ")");
        System.out.println("Is it a power of two? " + (result ? "YES" : "NO"));

        if (result) {
            // Find the exponent
            System.out.println("Explanation: " + n + " = 2^" + Integer.numberOfTrailingZeros(n));
        }

        sc.close();
    }
}
