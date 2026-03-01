
/**
 * Anagram Search (Pattern Searching)
 * 
 * Problem: Check if any permutation (anagram) of a 'pattern' exists as a substring in 'text'.
 * 
 * Algorithm: Sliding Window with Frequency Arrays
 * 1. Maintain two frequency arrays: one for the pattern and one for the current window in text.
 * 2. Initially, fill both arrays for the first 'm' characters (where m is pattern length).
 * 3. Slide the window one character at a time:
 *    - Check if the two frequency arrays are identical.
 *    - If yes, an anagram of the pattern is found.
 *    - Update the window array by adding the new character and removing the leftmost character.
 * 
 * Time Complexity : O(n * CHAR_SIZE) -> O(n) since CHAR_SIZE (256) is constant.
 * Space Complexity: O(CHAR_SIZE) -> O(1).
 */
import java.util.*;

public class AnagramSearch {

    /**
     * Searches for any anagram of 'pat' within 'txt'.
     * 
     * @param txt The main text string
     * @param pat The pattern to search for
     * @return true if an anagram of pattern is found in text, else false
     */
    static boolean isAnagramSearch(String txt, String pat) {
        int n = txt.length();
        int m = pat.length();

        // Base case: pattern cannot be longer than text
        if (m > n)
            return false;

        // Frequency arrays for current window (ct) and pattern (cp)
        int[] ct = new int[256];
        int[] cp = new int[256];

        // Process the first window of size 'm'
        for (int i = 0; i < m; i++) {
            ct[txt.charAt(i)]++;
            cp[pat.charAt(i)]++;
        }

        // Slide the window from index 'm' to 'n'
        for (int i = m; i < n; i++) {
            // If frequency arrays match, we found an anagram
            if (areSame(ct, cp))
                return true;

            // Update the sliding window: add current char, remove leftmost char
            ct[txt.charAt(i)]++;
            ct[txt.charAt(i - m)]--;
        }

        // Check the last window
        return areSame(ct, cp);
    }

    /**
     * Utility method to compare two frequency arrays.
     */
    static boolean areSame(int[] a, int[] b) {
        for (int i = 0; i < 256; i++) {
            if (a[i] != b[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String txt = sc.nextLine();
        System.out.print("Enter pattern: ");
        String pat = sc.nextLine();

        // Perform search and output result
        if (isAnagramSearch(txt, pat)) {
            System.out.println("Result: An anagram of '" + pat + "' EXISTS in the text.");
        } else {
            System.out.println("Result: NO anagram of '" + pat + "' was found in the text.");
        }

        sc.close();
    }
}
