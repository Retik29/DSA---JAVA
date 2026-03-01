
/**
 * Palindrome Check using Recursion
 * 
 * Problem: Check if a given string is a palindrome (reads the same forwards and backwards).
 * Example: "madam" is a palindrome.
 * 
 * Algorithm: Recursive Two-Pointer
 * 1. Base Case: If the string has 0 or 1 character left (l >= r), it's a palindrome.
 * 2. Recursive Step: Compare characters at the current left (l) and right (r) positions.
 *    - If they match, call the function recursively for the inner substring (l+1, r-1).
 *    - If they don't match, it's not a palindrome.
 * 
 * Time Complexity : O(n) - where n is the length of the string.
 * Space Complexity: O(n) - due to the recursion stack.
 */
import java.util.*;

public class PalindromeString {

    /**
     * Recursive function to check for palindrome.
     * 
     * @param s The processed string
     * @param l Left pointer
     * @param r Right pointer
     * @return true if palindrome, false otherwise
     */
    static boolean isPalindrome(String s, int l, int r) {
        // Base case: Pointers have crossed or met
        if (l >= r)
            return true;

        // Recursive step: Compare ends and recurse for inner part
        return (s.charAt(l) == s.charAt(r)) && isPalindrome(s, l + 1, r - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter a string: ");
        // Preliminary processing: lower case and remove non-alphanumeric
        String s = sc.nextLine().toLowerCase().replaceAll("[^a-z0-9]", "");

        // Perform calculation
        boolean result = isPalindrome(s, 0, s.length() - 1);

        // Output results
        System.out.println("Processing: Resulting string is \"" + s + "\"");
        System.out.println("Is it a palindrome? " + (result ? "YES" : "NO"));

        sc.close();
    }
}
