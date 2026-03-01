
/**
 * Palindrome Number Check
 * 
 * Problem: A palindrome number is a number that stays the same when its 
 * digits are reversed.
 * 
 * Logic: 
 * 1. Store the original number in a temporary variable.
 * 2. Reverse the digits of the number using the property: rev = rev * 10 + (num % 10).
 * 3. Compare the reversed number with the original number.
 * 
 * Time Complexity : O(digits) or O(log10 n)
 * Space Complexity: O(1)
 */
import java.util.*;

public class Palindrome {

    /**
     * Checks if a number is a palindrome.
     * 
     * @param num Input number
     * @return true if palindrome, false otherwise
     */
    static boolean isPalindrome(int num) {
        // Negative numbers are usually not considered palindromes
        if (num < 0)
            return false;

        int temp = num;
        int reversed = 0;

        while (temp != 0) {
            int lastDigit = temp % 10;
            reversed = reversed * 10 + lastDigit;
            temp /= 10;
        }

        return num == reversed;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input gathering
        System.out.print("Enter an integer: ");
        int num = sc.nextInt();

        // Output results
        System.out.println("--- Palindrome Result ---");
        if (isPalindrome(num)) {
            System.out.println(num + " is a palindrome.");
        } else {
            System.out.println(num + " is NOT a palindrome.");
        }

        sc.close();
    }
}
