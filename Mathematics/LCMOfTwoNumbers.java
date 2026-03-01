
/**
 * Least Common Multiple (LCM)
 * 
 * Problem: Find the smallest positive integer that is divisible by 
 * both 'a' and 'b'.
 * 
 * Logic: Mathematical Formula
 * - There is a fundamental relationship: a * b = GCD(a, b) * LCM(a, b).
 * - Therefore, LCM(a, b) = (a * b) / GCD(a, b).
 * - Optimization: To avoid possible integer overflow during (a * b), we divide 
 *   one of the numbers by the GCD first: LCM(a, b) = (a / GCD(a, b)) * b.
 * 
 * Time Complexity : O(log(min(a, b))) - Due to the Euclidean GCD calculation.
 * Space Complexity: O(1) iterative.
 */
import java.util.*;

public class LCMOfTwoNumbers {

    /**
     * Efficiently calculates GCD using iterative Euclidean approach.
     */
    static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Calculates LCM using the optimized GCD-based formula.
     * 
     * @param a First number
     * @param b Second number
     * @return Least Common Multiple
     */
    static long lcm(long a, long b) {
        // LCM is 0 if either number is 0
        if (a == 0 || b == 0)
            return 0;

        // Use formula: (a / gcd) * b
        return Math.abs((a / gcd(a, b)) * b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input gathering
        System.out.print("Enter two integers (a and b): ");
        long a = sc.nextLong();
        long b = sc.nextLong();

        // Perform calculations
        long gcdValue = gcd(a, b);
        long lcmValue = lcm(a, b);

        // Output results
        System.out.println("--- LCM & GCD Summary ---");
        System.out.println("Numbers: " + a + ", " + b);
        System.out.println("GCD: " + gcdValue);
        System.out.println("LCM: " + lcmValue);

        // Verification of property: a * b = gcd * lcm
        System.out.println("\nVerification:");
        System.out.println("Property Check: GCD * LCM = " + (gcdValue * lcmValue));
        System.out.println("Property Check: |a * b|   = " + Math.abs(a * b));

        sc.close();
    }
}
