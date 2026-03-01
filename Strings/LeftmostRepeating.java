
/**
 * Leftmost Repeating Character
 * 
 * Problem: Find the index of the character that repeats and whose first occurrence 
 * is at the smallest index (leftmost).
 * Example: "abccbd" -> 'b' is the leftmost repeating character because its first 
 * occurrence (index 1) is earlier than 'c' (index 2).
 * 
 * Algorithm: Single-Pass Reverse Traversal
 * 1. Initialize 'res' as -1.
 * 2. Maintain a boolean 'visited' array to keep track of characters encountered.
 * 3. Traverse the string from RIGHT to LEFT:
 *    - If a character is already visited, update 'res' to the current index 'i'.
 *    - Otherwise, mark the character as visited.
 * 4. By traversing backwards, the last time we update 'res' will be at the 
 *    leftmost occurrence of a repeating character.
 * 
 * Time Complexity : O(n) - Single linear traversal.
 * Space Complexity: O(1) - Constant space for the visited array.
 */
import java.util.*;

public class LeftmostRepeating {

    /**
     * Finds the index of the leftmost repeating character.
     * 
     * @param str The input string
     * @return Index of the leftmost repeating char, or -1 if none exist.
     */
    static int leftmostRepeating(String str) {
        boolean[] visited = new boolean[256];
        int res = -1;

        // Traverse from right to left
        for (int i = str.length() - 1; i >= 0; i--) {
            char currentChar = str.charAt(i);

            if (visited[currentChar]) {
                // If already seen, this is a repeating character
                // As we move left, 'res' will eventually store the leftmost index
                res = i;
            } else {
                // Mark the character as seen
                visited[currentChar] = true;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter string: ");
        String str = sc.nextLine();

        // Perform calculation
        int index = leftmostRepeating(str);

        // Output result
        if (index == -1) {
            System.out.println("Result: No repeating character found.");
        } else {
            System.out.println(
                    "Result: Leftmost repeating character is '" + str.charAt(index) + "' at index " + index + ".");
        }

        sc.close();
    }
}
