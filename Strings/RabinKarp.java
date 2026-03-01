
/**
 * Rabin-Karp Algorithm
 * 
 * Problem: Find all occurrences of a 'pattern' string in a 'text' string using hashing.
 * 
 * Key Concept: Rolling Hash
 * Instead of recomputing the hash for every window from scratch, Rabin-Karp computes 
 * the next hash using the previous hash in O(1) time.
 * 
 * Formula for next hash: 
 * txtHash = (BASE * (txtHash - text.charAt(i) * h) + text.charAt(i + m)) % PRIME
 * 
 * Time Complexity : O(n + m) on average, O(n * m) in the worst case (many collisions).
 * Space Complexity: O(1).
 */
import java.util.*;

public class RabinKarp {

    // BASE is the number of characters in the input alphabet (e.g., 256 for ASCII)
    // PRIME is a large prime number used to reduce the size of the hash value
    static final int BASE = 256, PRIME = 101;

    /**
     * Searches for the pattern in the text using the Rabin-Karp rolling hash
     * strategy.
     * 
     * @param text The main text String
     * @param pat  The pattern String to find
     * @return A list of starting indices where the pattern is found
     */
    static List<Integer> search(String text, String pat) {
        List<Integer> positions = new ArrayList<>();
        int n = text.length();
        int m = pat.length();

        if (m > n || m == 0)
            return positions;

        // h = BASE^(m-1) % PRIME
        // This is used for removing the leading character from the current hash
        long h = 1;
        for (int i = 0; i < m - 1; i++)
            h = (h * BASE) % PRIME;

        // Initial hashes for pattern and the first window of text
        long patHash = 0;
        long txtHash = 0;
        for (int i = 0; i < m; i++) {
            patHash = (BASE * patHash + pat.charAt(i)) % PRIME;
            txtHash = (BASE * txtHash + text.charAt(i)) % PRIME;
        }

        // Slide the window over the text
        for (int i = 0; i <= n - m; i++) {
            // If hash values match, then only check for actual character match
            // This is to handle potential hash collisions
            if (patHash == txtHash) {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pat.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match)
                    positions.add(i);
            }

            // Compute hash for the next window of text
            if (i < n - m) {
                // Remove leading digit, add trailing digit
                txtHash = (BASE * (txtHash - text.charAt(i) * h) + text.charAt(i + m)) % PRIME;

                // We might get negative value of txtHash, converting it to positive
                if (txtHash < 0)
                    txtHash += PRIME;
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
