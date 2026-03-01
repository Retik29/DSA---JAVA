
/**
 * String Permutations (Backtracking)
 * 
 * Problem: Given a string, generate all possible rearrangements (permutations) 
 * of its characters.
 * 
 * Logic (Swap-Based Backtracking):
 * 1. Concept: For each position 'idx' in the string, we swap the character at 
 *    'idx' with every character from 'idx' to 'n-1'.
 * 2. Recursion: After each swap, we fix the character at 'idx' and recurse for 'idx + 1'.
 * 3. Backtracking: After the recursive call returns, we swap the characters BACK 
 *    to their original positions. This ensures the array is restored to its 
 *    original state before the next iteration of the loop.
 * 4. Handling Duplicates: To avoid duplicate permutations in strings with 
 *    repeated characters, we use a 'HashSet' at each level of recursion to 
 *    track characters already placed at the current 'idx'.
 * 
 * Complexity:
 * - Time Complexity : O(n! * n) - Total n! permutations, each taking O(n) to build the string.
 * - Space Complexity: O(n) - Maximum depth of the recursion stack.
 */
import java.util.*;

public class StringPermutations {

    /**
     * Recursive function to generate permutations via swapping.
     * 
     * @param arr    character array of the string.
     * @param idx    current index to fix.
     * @param result list to store discovered permutations.
     */
    static void permute(char[] arr, int idx, List<String> result) {
        // Base Case: If we've reached the end, add the current arrangement
        if (idx == arr.length) {
            result.add(new String(arr));
            return;
        }

        // Use a set to handle duplicate characters at this position
        Set<Character> usedAtCurrentIdx = new HashSet<>();

        for (int i = idx; i < arr.length; i++) {
            // Only proceed if this character hasn't been placed at this index yet
            if (usedAtCurrentIdx.add(arr[i])) {
                // 1. Swap character at 'idx' with character at 'i'
                swap(arr, idx, i);

                // 2. Recurse for the next index
                permute(arr, idx + 1, result);

                // 3. Backtrack: Swap back to restore original state
                swap(arr, idx, i);
            }
        }
    }

    /**
     * Utility method to swap two characters in an array.
     */
    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- String Permutations (Backtracking) ---");
        System.out.print("Enter a string: ");
        String s = sc.nextLine();

        List<String> result = new ArrayList<>();
        permute(s.toCharArray(), 0, result);

        // Output results
        System.out.println("\nTotal unique permutations found: " + result.size());
        System.out.println("Result Set:");
        for (String p : result) {
            System.out.println(p);
        }

        sc.close();
    }
}
