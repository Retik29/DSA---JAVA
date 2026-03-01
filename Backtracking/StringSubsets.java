
/**
 * String Subsets / Power Set (Backtracking)
 * 
 * Problem: Given a string, generate all possible subsets (subsequences).
 * For a string of length 'n', there are 2^n possible subsets.
 * 
 * Logic (Recursive Backtracking):
 * 1. Choice: At each index 'idx', we have two options for the character at that position:
 *    - Option 1 (Skip/Exclude): Move to the next index without adding the character 
 *      to the current subset string.
 *    - Option 2 (Pick/Include): Add the current character to the current subset string 
 *      and then move to the next index.
 * 
 * 2. Recursion Tree: This branching creates a binary recursion tree with a 
 *    depth equal to the string length. The leaves of this tree represent 
 *    all possible subsets.
 * 
 * Complexity:
 * - Time Complexity : O(2^n) - Each element leads to two recursive branches.
 * - Space Complexity: O(n) - Maximum depth of the recursion stack.
 */
import java.util.*;

public class StringSubsets {

    /**
     * Recursive function to explore both include/exclude paths for each character.
     * 
     * @param s       The input string.
     * @param idx     The current character index being considered.
     * @param current The subset string built so far.
     * @param result  List to store all discovered subsets.
     */
    static void generateSubsets(String s, int idx, String current, List<String> result) {
        // Base Case: We've made a decision for every character in the string
        if (idx == s.length()) {
            // Represent empty string as "" for clarity
            result.add(current.isEmpty() ? "\"\"" : current);
            return;
        }

        // --- Branch 1: EXCLUDE the current character ---
        generateSubsets(s, idx + 1, current, result);

        // --- Branch 2: INCLUDE the current character ---
        generateSubsets(s, idx + 1, current + s.charAt(idx), result);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- String Subsets (Power Set) Generator ---");
        System.out.print("Enter string: ");
        String s = sc.nextLine();

        List<String> result = new ArrayList<>();
        // Note: Using an empty string for the initial subset 'current'
        generateSubsets(s, 0, "", result);

        // Output results
        System.out.println("\nTotal subsets (2^" + s.length() + "): " + result.size());
        System.out.println("Result Set:");
        for (String subset : result) {
            System.out.println(subset);
        }

        sc.close();
    }
}
