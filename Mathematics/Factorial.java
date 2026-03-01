
/**
 * Factorial of a Number (Iterative vs Recursive)
 * 
 * Problem: Calculate n! (the product of all integers from 1 to n).
 * 
 * Approaches:
 * 1. Iterative: Use a simple for-loop.
 *    - Pros: Efficient space (O(1)), no risk of stack overflow.
 *    - Cons: slightly less 'mathematical' in appearance.
 * 2. Recursive: Function calls itself with (n-1).
 *    - Pros: Elegant and maps directly to the mathematical definition.
 *    - Cons: Consumes O(n) stack space. For large 'n', it will throw a 
 *      StackOverflowError.
 * 
 * Time Complexity : O(n) for both.
 * Space Complexity: O(1) for iterative, O(n) for recursive (call stack).
 */
import java.util.*;

public class Factorial {

    /**
     * Calculates factorial using a loop.
     */
    static long factIterative(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Calculates factorial using recursion.
     */
    static long factRecursive(int n) {
        // Base case: 0! and 1! are 1
        if (n <= 1)
            return 1;
        // Recursive step: n! = n * (n-1)!
        return n * factRecursive(n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input gathering
        System.out.print("Enter an integer (n): ");
        int n = sc.nextInt();

        // Output results
        System.out.println("--- Factorial Comparison ---");
        System.out.println(n + "! (Iterative): " + factIterative(n));
        System.out.println(n + "! (Recursive): " + factRecursive(n));

        sc.close();
    }
}
