
/**
 * Greatest Common Divisor (GCD) - Euclidean Algorithm
 * 
 * Problem: Find the largest positive integer that divides both 'a' and 'b'.
 * 
 * Algorithm: Euclidean Algorithm
 * - The fundamental idea is that GCD(a, b) = GCD(b, a % b).
 * - This process continues until the remainder becomes 0.
 * - The last non-zero remainder (or the value of 'a' when 'b' is 0) is the GCD.
 * 
 * Features:
 * 1. Iterative: More memory efficient as it avoids the recursion stack.
 * 2. Recursive: Elegant one-line implementation based on the mathematical definition.
 * 
 * Time Complexity : O(log(min(a, b)))
 * Space Complexity: O(log(min(a, b))) for recursive, O(1) for iterative.
 */
import java.util.*;

public class GCDEuclid {

    /**
     * Efficiently calculates GCD using iterative Euclidean approach.
     * 
     * @param a First integer
     * @param b Second integer
     * @return Greatest Common Divisor
     */
    static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        // Loop until one of the numbers becomes zero
        while (b != 0) {
            int temp = b;
            b = a % b; // Remainder becomes new b
            a = temp; // Old b becomes new a
        }
        return a;
    }

    /**
     * Calculates GCD using recursive Euclidean approach.
     * 
     * @param a First integer
     * @param b Second integer
     * @return Greatest Common Divisor
     */
    static int gcdRecursive(int a, int b) {
        // Base case: if b is 0, the GCD is a
        // Recurse: GCD(a, b) = GCD(b, a % b)
        return (b == 0) ? Math.abs(a) : gcdRecursive(b, a % b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input gathering
        System.out.print("Enter two integers (a and b): ");
        int a = sc.nextInt();
        int b = sc.nextInt();

        // Perform calculation
        int result = gcd(a, b);

        // Output result
        System.out.println("--- GCD Result ---");
        System.out.println("The GCD of " + a + " and " + b + " is: " + result);

        sc.close();
    }
}
