
/**
 * Naive Pattern Search (Brute Force)
 * 
 * Problem: Find all starting indices of a 'pattern' string in a 'text' string.
 * 
 * Algorithm: Simple Sliding Window
 * 1. Slide the pattern over the text one character at a time from index '0' to 'n-m'.
 * 2. At each position 'i', check if the characters of the pattern match 
 *    the characters of the text starting from 'i'.
 * 3. Use an inner loop to compare all characters of the pattern.
 * 4. If all characters match, record the starting position 'i'.
 * 
 * Time Complexity : O((n - m + 1) * m) - Worst case (e.g., text: "AAAAAA", pat: "AAA").
 * Space Complexity: O(1) - No extra space used except for the result list.
 */
import java.util.*;

public class NaivePatternSearch {

    /**
     * Searches for all instances of the pattern in the text.
     * 
     * @param text The main text string
     * @param pat  The pattern to search for
     * @return A list of indices where the pattern starts
     */
    static List<Integer> naiveSearch(String text, String pat) {
        List<Integer> positions = new ArrayList<>();
        int n = text.length();
        int m = pat.length();

        if (m == 0 || m > n)
            return positions;

        // Slide the pattern window one by one
        for (int i = 0; i <= n - m; i++) {
            int j = 0;

            // For current index i, check for pattern match
            while (j < m && text.charAt(i + j) == pat.charAt(j)) {
                j++;
            }

            // If the whole pattern was traversed successfully, a match is found
            if (j == m) {
                positions.add(i);
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

        // Perform search
        List<Integer> pos = naiveSearch(text, pat);

        // Output results
        if (pos.isEmpty()) {
            System.out.println("Result: Pattern not found in the text.");
        } else {
            System.out.println("Result: Pattern found at positions: " + pos);
            System.out.println("Total occurrences found: " + pos.size());
        }

        sc.close();
    }
}
