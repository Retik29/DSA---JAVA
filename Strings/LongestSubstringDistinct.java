
/**
 * Longest Substring with Distinct Characters
 * 
 * Problem: Find the length of the longest substring that contains all unique characters.
 * Example: "abcabcbb" -> "abc" has length 3.
 * 
 * Algorithm: Optimized Sliding Window
 * 1. Use an array 'lastIndex' to store the last seen index of each character (initialized to -1).
 * 2. maintain two pointers: 'i' (start of the current window) and 'j' (end of the window).
 * 3. As we move 'j' from 0 to n-1:
 *    - If str[j] has been seen before at an index 'k', we update 'i' 
 *      to max(i, k + 1) to ensure all characters in the window [i...j] are unique.
 *    - Update the maximum length found so far (res = max(res, j - i + 1)).
 *    - Store the current index 'j' of the character in 'lastIndex'.
 * 
 * Time Complexity : O(n) - Single pass through the string.
 * Space Complexity: O(1) - Constant space for the lastIndex array (256 characters).
 */
import java.util.*;

public class LongestSubstringDistinct {

    /**
     * Calculates the length of the longest substring with distinct characters.
     * 
     * @param str The input string
     * @return Length of the longest unique substring
     */
    static int longestSubstring(String str) {
        int n = str.length();
        int res = 0; // Stores the result

        // Array to store the last occurrence of each character
        int[] lastIndex = new int[256];
        Arrays.fill(lastIndex, -1);

        int i = 0; // Left pointer of the sliding window
        for (int j = 0; j < n; j++) {
            // Update the window start 'i'
            // If the character was seen inside the current window, move 'i'
            i = Math.max(i, lastIndex[str.charAt(j)] + 1);

            // Current window size is j - i + 1
            res = Math.max(res, j - i + 1);

            // Store current index of character
            lastIndex[str.charAt(j)] = j;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter string: ");
        String str = sc.nextLine();

        // Perform calculation and output result
        int result = longestSubstring(str);
        System.out.println("Result: Length of the longest substring with distinct characters is " + result + ".");

        sc.close();
    }
}
