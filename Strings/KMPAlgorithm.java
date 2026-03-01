
/**
 * Knuth-Morris-Pratt (KMP) Algorithm
 * 
 * Problem: Find all occurrences of a 'pattern' string in a 'text' string.
 * Unlike the naive approach which takes O(n*m), KMP avoids redundant comparisons by 
 * using information from previous matches.
 * 
 * Key Concept: LPS Array (Longest Prefix Suffix)
 * lps[i] stores the length of the longest proper prefix of pat[0...i] that is also a suffix of pat[0...i].
 * 
 * Time Complexity : O(n + m) - where n is text length and m is pattern length.
 * Space Complexity: O(m)     - for the LPS array.
 */
import java.util.*;

public class KMPAlgorithm {

    /**
     * Precomputes the LPS array for the given pattern.
     * 
     * @param pat The pattern string
     * @return The LPS array
     */
    static int[] computeLPS(String pat) {
        int m = pat.length();
        int[] lps = new int[m];
        int len = 0; // Length of previous longest prefix suffix
        int i = 1;

        // lps[0] is always 0
        lps[0] = 0;

        while (i < m) {
            if (pat.charAt(i) == pat.charAt(len)) {
                // Characters match, extend the prefix-suffix length
                len++;
                lps[i] = len;
                i++;
            } else {
                // Characters don't match
                if (len != 0) {
                    // Try to find a smaller prefix suffix from the lps array
                    len = lps[len - 1];
                } else {
                    // No prefix suffix found
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    /**
     * Searches for the pattern in the text using the KMP strategy.
     * 
     * @param text The main text String
     * @param pat  The pattern String to find
     * @return A list of starting indices where the pattern is found
     */
    static List<Integer> search(String text, String pat) {
        List<Integer> positions = new ArrayList<>();
        int n = text.length();
        int m = pat.length();

        if (m == 0)
            return positions;

        // Precompute the LPS array
        int[] lps = computeLPS(pat);

        int i = 0; // index for text
        int j = 0; // index for pat

        while (i < n) {
            if (text.charAt(i) == pat.charAt(j)) {
                // Match found at current character, move both pointers
                i++;
                j++;
            }

            if (j == m) {
                // Entire pattern matched
                positions.add(i - j);
                // Continue searching for the next occurrence
                j = lps[j - 1];
            } else if (i < n && text.charAt(i) != pat.charAt(j)) {
                // Mismatch after j matches
                if (j != 0) {
                    // Skip characters using LPS array to avoid redundant work
                    j = lps[j - 1];
                } else {
                    // Start from next character in text
                    i++;
                }
            }
        }
        return positions;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input text and pattern
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter pattern: ");
        String pat = sc.nextLine();

        // Optional: Show LPS array for debugging/clarity
        System.out.println("LPS array: " + Arrays.toString(computeLPS(pat)));

        // Find occurrences
        List<Integer> pos = search(text, pat);

        // Output results
        if (pos.isEmpty()) {
            System.out.println("Pattern not found in the text.");
        } else {
            System.out.println("Pattern found at positions: " + pos);
        }

        sc.close();
    }
}
