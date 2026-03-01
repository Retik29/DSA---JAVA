
/**
 * Palindrome Number Check
 * 
 * Problem: Determine if an integer 'n' is a palindrome (reads the same 
 * forwards and backwards).
 * 
 * Methods:
 * 1. Full Reverse Approach: 
 *    Reverse the entire number and compare it with the original. 
 *    Cons: Might cause integer overflow for large numbers.
 * 2. Half Reverse Approach: 
 *    Reverse only the half of the digits. For example, if we have 1221, 
 *    we reverse the last two digits (21 -> 12) and compare with the first 
 *    half (12).
 *    Pros: More efficient and avoids overflow.
 * 
 * Time Complexity : O(digits) or O(log10 n)
 * Space Complexity: O(1)
 */
import java.util.*;

public class PalindromeNumber {

    /**
     * Checks palindrome status by reversing the entire number.
     * 
     * @param n Input number
     * @return true if palindrome, false otherwise
     */
    static boolean isPalindromeReverse(int n) {
        // Negative numbers are not palindromes
        if (n < 0)
            return false;

        int original = n;
        long reversed = 0; // Use long to prevent overflow during intermediate steps

        while (n > 0) {
            reversed = reversed * 10 + (n % 10);
            n /= 10;
        }

        return (long) original == reversed;
    }

    /**
     * Checks palindrome status by reversing only half of the number.
     * 
     * @param n Input number
     * @return true if palindrome, false otherwise
     */
    static boolean isPalindromeHalf(int n) {
        // Special cases: negative numbers or numbers with trailing zeros (except 0
        // itself)
        if (n < 0 || (n % 10 == 0 && n != 0))
            return false;

        int halfReversed = 0;
        // Keep reversing until the original part is <= the reversed part
        while (n > halfReversed) {
            halfReversed = halfReversed * 10 + n % 10;
            n /= 10;
        }

        // For even-length numbers: n == halfReversed (e.g., 12 == 12)
        // For odd-length numbers: n == halfReversed / 10 (e.g., 1 == 1, middle digit 2
        // ignored)
        return n == halfReversed || n == halfReversed / 10;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter an integer: ");
        int n = sc.nextInt();

        // Output results
        System.out.println("--- Palindrome Analysis ---");
        System.out.println("Input Number: " + n);
        System.out.println("Method 1 (Full Reverse): " + (isPalindromeReverse(n) ? "YES" : "NO"));
        System.out.println("Method 2 (Half Reverse): " + (isPalindromeHalf(n) ? "YES" : "NO"));

        sc.close();
    }
}
