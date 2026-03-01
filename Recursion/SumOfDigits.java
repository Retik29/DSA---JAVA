
/**
 * Sum of Digits using Recursion
 * 
 * Problem:
 * 1. Calculate the sum of all individual digits of a number.
 * 2. Calculate the Digital Root (repeatedly summing digits until a single digit is obtained).
 * 
 * Algorithm:
 * - Sum of Digits: (n % 10) gives the last digit, while (n / 10) removes it.
 *   Sum = last digit + recursive call for the remaining digits.
 * - Digital Root: If the result of sumOfDigits is >= 10, repeat the process.
 * 
 * Time Complexity : O(log10 n) - Number of recursive calls equals number of digits.
 * Space Complexity: O(log10 n) - Due to the recursion stack.
 */
import java.util.*;

public class SumOfDigits {

    /**
     * Calculates the sum of digits of n.
     * 
     * @param n Input number
     * @return Sum of digits
     */
    static int sumOfDigits(int n) {
        // Handle negative numbers
        n = Math.abs(n);

        // Base case: 0
        if (n == 0)
            return 0;

        // Recursive step: last digit + sum of remaining digits
        return (n % 10) + sumOfDigits(n / 10);
    }

    /**
     * Calculates the Digital Root of a number.
     * Digital root is the recursive sum of digits until a single-digit number.
     * 
     * @param n Input number
     * @return Single-digit digital root
     */
    static int digitalRoot(int n) {
        n = Math.abs(n);

        // Base case: if single digit, return it
        if (n < 10)
            return n;

        // Recurse using the sum of digits
        return digitalRoot(sumOfDigits(n));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter an integer: ");
        int n = sc.nextInt();

        // Perform calculations
        int sum = sumOfDigits(n);
        int root = digitalRoot(n);

        // Output results
        System.out.println("--- Digits Summary ---");
        System.out.println("Input Number:  " + n);
        System.out.println("Sum of Digits: " + sum);
        System.out.println("Digital Root:  " + root);

        sc.close();
    }
}
