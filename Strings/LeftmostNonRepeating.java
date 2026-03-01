
/**
 * Leftmost Non-Repeating Character
 * 
 * Problem: Find the index of the first character in a string that does not repeat.
 * Example: "geeksforgeeks" -> 'f' is the first non-repeating character.
 * 
 * Algorithm: Two-Pass Frequency Approach
 * 1. First Pass: Traverse the string and store the frequency of each character in an array.
 * 2. Second Pass: Traverse the string again and check the frequency of each character 
 *    in the frequency array. The first character with frequency '1' is the answer.
 * 
 * Time Complexity : O(n) - Two linear traversals of the string.
 * Space Complexity: O(1) - Constant space for the frequency array (256 characters).
 */
import java.util.*;

public class LeftmostNonRepeating {

    /**
     * Finds the index of the leftmost non-repeating character.
     * 
     * @param s The input string
     * @return Index of the leftmost non-repeating char, or -1 if none exist.
     */
    static int leftmostNonRepeating(String s) {
        // Frequency array for ASCII characters
        int[] count = new int[256];

        // Step 1: Count frequency of each character
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        // Step 2: Find the first character with frequency 1
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i)] == 1) {
                return i;
            }
        }

        // No non-repeating character found
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String s = sc.nextLine();

        // Perform calculation
        int idx = leftmostNonRepeating(s);

        // Output result
        if (idx == -1) {
            System.out.println("Result: No non-repeating character found.");
        } else {
            System.out.println(
                    "Result: Leftmost non-repeating character is '" + s.charAt(idx) + "' at index " + idx + ".");
        }

        sc.close();
    }
}
