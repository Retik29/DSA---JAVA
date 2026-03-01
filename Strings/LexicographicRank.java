
/**
 * Lexicographic Rank of a String
 * 
 * Problem: Find the rank of a given string among all its permutations when 
 * sorted lexicographically (alphabetically).
 * Constraint: Assumes all characters in the string are DISTINCT.
 * 
 * Algorithm:
 * 1. Calculate total possible permutations for length N: N!
 * 2. For each position 'i' in the string:
 *    - Count how many characters are smaller than str[i] among the remaining characters.
 *    - Let this count be 'countSmaller'.
 *    - Add (countSmaller * (N - 1 - i)!) to the rank.
 * 3. Update the available characters and continue.
 * 
 * Efficiency Improvement:
 * Use a frequency array and cumulative count (prefix sum) to find 'countSmaller' in O(1).
 * 
 * Time Complexity : O(n * CHAR_SIZE) -> O(n) since CHAR_SIZE (256) is constant.
 * Space Complexity: O(CHAR_SIZE) -> O(1).
 */
import java.util.*;

public class LexicographicRank {

    /**
     * Utility method to calculate factorial of n.
     */
    static long factorial(int n) {
        long res = 1;
        for (int i = 2; i <= n; i++)
            res *= i;
        return res;
    }

    /**
     * Calculates the lexicographic rank of the string.
     * 
     * @param str The input string (distinct characters)
     * @return Lexicographic rank (1-indexed)
     */
    static int findRank(String str) {
        int n = str.length();
        long mul = factorial(n);
        int rank = 1; // Start from rank 1
        int[] count = new int[256];

        // Step 1: Count frequency of each character
        for (int i = 0; i < n; i++)
            count[str.charAt(i)]++;

        // Step 2: Compute cumulative count (prefix sums)
        // count[i] will store number of characters smaller than or equal to index 'i'
        for (int i = 1; i < 256; i++)
            count[i] += count[i - 1];

        // Step 3: Iterate through the string to calculate rank contribution
        for (int i = 0; i < n; i++) {
            // Factorial for remaining positions: (n - 1 - i)!
            mul /= (n - i);

            // Number of characters smaller than str[i] that haven't been used yet
            rank += count[str.charAt(i) - 1] * mul;

            // Reduce the count for current character and all characters after it
            // as the current character is now 'used'
            for (int j = str.charAt(i); j < 256; j++)
                count[j]--;
        }
        return rank;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter string (must have DISTINCT characters): ");
        String str = sc.nextLine();

        // Perform calculation and output result
        System.out.println("Lexicographic rank: " + findRank(str));

        sc.close();
    }
}
