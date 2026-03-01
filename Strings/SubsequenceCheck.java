
/**
 * Subsequence Check
 * 
 * Problem: Check if string 's1' is a subsequence of string 's2'.
 * A subsequence is derived by deleting zero or more characters from original string 
 * without changing the order of remaining characters.
 * 
 * Time Complexity : O(n + m) - where n and m are lengths of the two strings.
 * Space Complexity: O(1)     - no extra space used.
 */
import java.util.*;

public class SubsequenceCheck {

    /**
     * Iterative approach using two pointers.
     * 
     * @param s1 The potential subsequence
     * @param s2 The main string
     * @return true if s1 is a subsequence of s2, false otherwise
     */
    static boolean isSubsequence(String s1, String s2) {
        int i = 0; // Pointer for s1
        int j = 0; // Pointer for s2

        // Traverse both strings
        while (i < s1.length() && j < s2.length()) {
            // If characters match, move the pointer for s1
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
            }
            // Always move the pointer for s2
            j++;
        }

        // If s1 pointer reached the end, all characters were found in order
        return (i == s1.length());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter main string (s2): ");
        String s2 = sc.nextLine();
        System.out.print("Enter potential subsequence (s1): ");
        String s1 = sc.nextLine();

        // Check and print result
        if (isSubsequence(s1, s2)) {
            System.out.println("Result: '" + s1 + "' is a subsequence of '" + s2 + "'.");
        } else {
            System.out.println("Result: '" + s1 + "' is NOT a subsequence of '" + s2 + "'.");
        }

        sc.close();
    }
}
