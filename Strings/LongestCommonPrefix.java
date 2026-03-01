
/**
 * Longest Common Prefix (LCP)
 * 
 * Problem: Find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * 
 * Algorithm: Horizontal Scanning
 * 1. Assume the first string as the initial 'prefix'.
 * 2. Iterate through the rest of the strings in the array.
 * 3. For each string, check if it starts with the current 'prefix'.
 * 4. If not, shorten the 'prefix' by removing the last character and repeat the check.
 * 5. If 'prefix' becomes empty, then there is no common prefix among the strings.
 * 
 * Time Complexity : O(S) - where S is the sum of all characters in all strings.
 * Space Complexity: O(1) - constant extra space used.
 */
import java.util.*;

public class LongestCommonPrefix {

    /**
     * Finds the LCP using the horizontal scanning method.
     * 
     * @param strs Array of strings
     * @return The longest common prefix
     */
    static String getLongestCommonPrefix(String[] strs) {
        // Base case: empty array
        if (strs == null || strs.length == 0)
            return "";

        // Start with the first string as the potential prefix
        String prefix = strs[0];

        // Compare prefix with every other string
        for (int i = 1; i < strs.length; i++) {
            // Keep shortening the prefix until it is found at the start of strs[i]
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);

                // If prefix becomes empty, no common prefix exists
                if (prefix.isEmpty())
                    return "";
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of strings
        System.out.print("Enter number of strings: ");
        int n = sc.nextInt();
        String[] strs = new String[n];

        // Input strings
        System.out.println("Enter " + n + " strings:");
        for (int i = 0; i < n; i++)
            strs[i] = sc.next();

        // Calculate and print LCP
        String lcp = getLongestCommonPrefix(strs);
        System.out.println("Result: Longest Common Prefix is " + (lcp.isEmpty() ? "(None)" : "'" + lcp + "'"));

        sc.close();
    }
}
