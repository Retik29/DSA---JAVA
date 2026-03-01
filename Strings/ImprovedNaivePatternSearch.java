
/**
 * Improved Naive Pattern Search (for Distinct Pattern Characters)
 * 
 * Problem: Find all occurrences of a 'pattern' in a 'text'.
 * Constraint: All characters in the pattern must be DISTINCT.
 * 
 * Algorithm: Optimized Naive Search
 * 1. Start comparing the pattern with text at index 'i'.
 * 2. If 'j' characters match and then a mismatch occurs:
 *    - Because all characters in the pattern are distinct, we know that none of the 
 *      already matched characters can match the starting character of the pattern.
 *    - Therefore, we can safely skip 'j' positions instead of just 1.
 * 3. This reduces the time complexity from O((n-m+1)*m) to O(n).
 * 
 * Time Complexity : O(n) - due to the smart skipping.
 * Space Complexity: O(1).
 */
import java.util.*;

public class ImprovedNaivePatternSearch {

    /**
     * Optimized search for distinct pattern characters.
     * 
     * @param txt The main text string
     * @param pat The pattern with distinct characters
     */
    static void search(String txt, String pat) {
        int n = txt.length();
        int m = pat.length();

        // Increment i dynamically based on matching length j
        for (int i = 0; i <= n - m;) {
            int j;
            // Check for match
            for (j = 0; j < m; j++) {
                if (pat.charAt(j) != txt.charAt(i + j))
                    break;
            }

            if (j == m) {
                System.out.println("Pattern found at index " + i);
            }

            // Key Optimization:
            if (j == 0) {
                // If no character matched, move to next index
                i++;
            } else {
                // If 'j' characters matched, we can skip 'j' indices
                // because all characters in pattern are distinct.
                i = i + j;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String txt = sc.nextLine();

        // This optimization only works if the pattern has distinct characters!
        System.out.print("Enter pattern (must have DISTINCT characters): ");
        String pat = sc.nextLine();

        System.out.println("--- Search Results ---");
        search(txt, pat);

        sc.close();
    }
}
