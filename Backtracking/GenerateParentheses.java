
/**
 * Generate Valid Parentheses (Backtracking)
 * 
 * Problem: Given 'n' pairs of parentheses, write a function to generate all 
 * combinations of well-formed parentheses.
 * 
 * Logic (Recursive Backtracking):
 * 1. Base Case: If the length of the current string is 2 * n, we've used all 
 *    parentheses and found a valid combination.
 * 
 * 2. Constraints for Validity:
 *    - Rule 1 (Open): We can add an opening parenthesis '(' if we have used 
 *      fewer than 'n' opening parentheses so far (open < n).
 *    - Rule 2 (Close): We can add a closing parenthesis ')' ONLY if the count 
 *      of closing parentheses is less than the count of opening parentheses 
 *      (close < open). This ensures that every ')' has a preceding unmatched '('.
 * 
 * Complexity:
 * - Time Complexity : O(4^n / √n) - related to the n-th Catalan Number.
 * - Space Complexity: O(n) - Maximum depth of the recursion stack.
 */
import java.util.*;

public class GenerateParentheses {

    /**
     * Recursive function to generate valid parentheses combinations.
     * 
     * @param result  List to store valid combinations.
     * @param current The current build of the parentheses string.
     * @param open    count of opening parentheses used.
     * @param close   count of closing parentheses used.
     * @param n       total number of pairs.
     */
    static void generate(List<String> result, String current, int open, int close, int n) {
        // Base Case: Current string reached target length
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        // Branch 1: Add Open Parenthesis
        if (open < n) {
            generate(result, current + "(", open + 1, close, n);
        }

        // Branch 2: Add Close Parenthesis
        if (close < open) {
            generate(result, current + ")", open, close + 1, n);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Well-Formed Parentheses Generator ---");
        System.out.print("Enter number of pairs (n): ");
        int n = sc.nextInt();

        List<String> result = new ArrayList<>();
        generate(result, "", 0, 0, n);

        // Output results
        System.out.println("\nTotal valid combinations: " + result.size());
        System.out.println("Result Set:");
        for (String s : result) {
            System.out.println(s);
        }

        sc.close();
    }
}
