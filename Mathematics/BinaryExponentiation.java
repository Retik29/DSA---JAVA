
/**
 * Binary Exponentiation (Exponentiation by Squaring)
 * 
 * Problem: Compute (base^exp) efficiently.
 * 
 * Logic:
 * - A naive approach (multiplying base 'exp' times) takes O(n) time.
 * - Binary Exponentiation reduces this to O(log exp).
 * - Core Idea:
 *   If exp is even: base^exp = (base^2)^(exp/2)
 *   If exp is odd:  base^exp = base * (base^2)^((exp-1)/2)
 * 
 * Modular Exponentiation:
 * - Frequently used to calculate results within the range of large data types 
 *   using the property: (A * B) % M = ((A % M) * (B % M)) % M.
 * 
 * Time Complexity : O(log exp)
 * Space Complexity: O(1) iterative.
 */
import java.util.*;

public class BinaryExponentiation {

    /**
     * Calculates base^exp using iterative binary exponentiation.
     * 
     * @param base The base number
     * @param exp  The exponent
     * @return Result of base^exp
     */
    static long power(long base, long exp) {
        long result = 1;

        while (exp > 0) {
            // If exponent is odd, multiply result by current base
            if ((exp & 1) == 1) {
                result *= base;
            }
            // Square the base and halve the exponent
            base *= base;
            exp >>= 1; // Bitwise right shift is equivalent to dividing by 2
        }
        return result;
    }

    /**
     * Calculates (base^exp) % mod using binary exponentiation.
     */
    static long powerMod(long base, long exp, long mod) {
        long result = 1;
        base %= mod; // Handle base larger than mod

        while (exp > 0) {
            // If exponent is odd
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            // Square the base and halve the exponent
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter base and exponent: ");
        long base = sc.nextLong();
        long exp = sc.nextLong();

        // Standard Exponentiation
        System.out.println("--- Results ---");
        System.out.println(base + "^" + exp + " = " + power(base, exp));

        // Modular Exponentiation
        System.out.print("\nEnter modulus for (base^exp % mod): ");
        long mod = sc.nextLong();
        if (mod > 0) {
            System.out.println(base + "^" + exp + " % " + mod + " = " + powerMod(base, exp, mod));
        }

        sc.close();
    }
}
