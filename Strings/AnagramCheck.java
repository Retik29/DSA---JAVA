
/**
 * Anagram Check
 * 
 * Problem: Check if two strings s1 and s2 are anagrams.
 * Two strings are anagrams if they contain the same characters with the same frequency, 
 * just in a different order.
 * 
 * Algorithm: Frequency Count Approach
 * 1. If lengths are different, they cannot be anagrams.
 * 2. Use an array (size 256 or 26 for 'a'-'z') to store frequencies.
 * 3. Traverse s1: increment count for each character.
 * 4. Traverse s2: decrement count for each character.
 * 5. If all counts in the array are zero, the strings are anagrams.
 * 
 * Time Complexity : O(n) - where n is the length of strings.
 * Space Complexity: O(1) - constant space (256/26 for the count array).
 */
import java.util.*;

public class AnagramCheck {

    /**
     * Checks if two strings are anagrams using a single frequency array.
     * 
     * @param s1 First string
     * @param s2 Second string
     * @return true if anagrams, false otherwise
     */
    static boolean isAnagram(String s1, String s2) {
        // Base case: different lengths cannot be anagrams
        if (s1.length() != s2.length())
            return false;

        // Assuming ASCII or just 'a'-'z' (extended to 256 for general ASCII)
        int[] count = new int[256];

        // Increment for s1, decrement for s2
        for (int i = 0; i < s1.length(); i++) {// Traverse both strings simultaneously
            count[s1.charAt(i)]++;// Increment count for s1 character.
            count[s2.charAt(i)]--;// Decrement count for s2 character.// This way, if s1 and s2 are anagrams, all counts will return to zero.
        }

        // Check if all counts returned to zero
        for (int c : count) {
            if (c != 0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input strings
        System.out.print("Enter first string: ");
        String s1 = sc.nextLine().toLowerCase().replaceAll("\\s", "");
        System.out.print("Enter second string: ");
        String s2 = sc.nextLine().toLowerCase().replaceAll("\\s", "");

        // Output result
        if (isAnagram(s1, s2)) {
            System.out.println("Result: \"" + s1 + "\" and \"" + s2 + "\" are anagrams.");
        } else {
            System.out.println("Result: \"" + s1 + "\" and \"" + s2 + "\" are NOT anagrams.");
        }

        sc.close();
    }
}
