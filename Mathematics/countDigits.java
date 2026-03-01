
/**
 * Count Digits in a Number
 * 
 * Problem: Count how many digits are present in a given integer 'n'.
 * 
 * Methods:
 * 1. Iterative Method: 
 *    Continuously divide the number by 10 until it becomes 0, counting 
 *    the steps.
 *    Complexity: O(digits)
 * 2. Logarithmic Method: 
 *    Use the mathematical property that the number of digits in 'n' is 
 *    equal to floor(log10(n)) + 1.
 *    Complexity: O(1) - (effectively, though log calculation has its own complexity).
 * 3. String Method: 
 *    Convert the number to a string and find its length.
 *    Complexity: O(digits)
 * 
 * Time Complexity : O(digits) or O(log10 n)
 * Space Complexity: O(1)
 */
import java.util.*;

public class countDigits {

    /**
     * Counts digits using an iterative division approach.
     * 
     * @param n The input number
     * @return Number of digits
     */
    static int countIterative(long n) {
        // Handle negative and zero cases
        n = Math.abs(n);
        if (n == 0)
            return 1;

        int count = 0;
        while (n > 0) {
            count++;
            n /= 10; // Remove the last digit
        }
        return count;
    }

    /**
     * Counts digits using logarithmic formula.
     * 
     * @param n The input number
     * @return Number of digits
     */
    static int countLog(long n) {
        n = Math.abs(n);
        // log10(n) + 1 gives the count of digits
        if (n == 0)
            return 1;
        return (int) Math.log10(n) + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input gathering
        System.out.print("Enter an integer: ");
        long n = sc.nextLong();

        // Output results
        System.out.println("--- Digit Count Summary ---");
        System.out.println("Input Number: " + n);
        System.out.println("Method 1 (Iterative): " + countIterative(n));
        System.out.println("Method 2 (Logarithmic): " + countLog(n));
        System.out.println("Method 3 (String length): " + String.valueOf(Math.abs(n)).length());

        sc.close();
    }
}
