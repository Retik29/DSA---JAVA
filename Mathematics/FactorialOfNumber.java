
/**
 * Factorial of a Number
 * 
 * Problem: Calculate the product of all positive integers less than or 
 * equal to 'n'. (n! = n * (n-1) * ... * 1)
 * 
 * Approaches:
 * 1. Iterative Approach (using long):
 *    Efficient for small 'n'. However, 20! is the largest factorial 
 *    that fits in a 64-bit 'long'.
 * 2. BigInteger Approach:
 *    Necessary for 'n > 20' to avoid overflow. BigInteger can handle 
 *    arbitrarily large integer values.
 * 
 * Time Complexity : O(n)
 * Space Complexity: O(1) for iterative, O(digits) for BigInteger storage.
 */
import java.util.*;
import java.math.BigInteger;

public class FactorialOfNumber {

    /**
     * Calculates factorial using 'long'.
     * 
     * @param n Input number
     * @return Factorial value (valid up to n=20)
     */
    static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Calculates factorial using 'BigInteger' to handle large results.
     * 
     * @param n Input number
     * @return Large factorial as BigInteger
     */
    static BigInteger factorialBig(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input gathering
        System.out.print("Enter a number (n) to find n!: ");
        int n = sc.nextInt();

        // Output results
        System.out.println("--- Factorial Calculation ---");

        if (n <= 20) {
            System.out.println(n + "! (Standard long): " + factorial(n));
        } else {
            System.out.println(n + "! (Standard long): [OVERFLOW - Use BigInteger]");
        }

        System.out.println(n + "! (BigInteger):    " + factorialBig(n));

        sc.close();
    }
}
