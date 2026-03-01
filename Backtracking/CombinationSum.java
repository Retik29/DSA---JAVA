
/**
 * Combination Sum (Backtracking)
 * 
 * Problem: Given an array of distinct integers 'candidates' and a 'target', 
 * find all unique combinations where the candidates sum to the target. 
 * You may use the same number an unlimited number of times.
 * 
 * Logic (Backtracking Strategy):
 * 1. Choices: At each index, we have two primary choices:
 *    - Pick the current element: Decrease the target and recurse on the SAME index 
 *      (since reuse is allowed).
 *    - Skip the current element: Recurse on the NEXT index with the original target.
 * 
 * 2. Backtracking: After exploring the "pick" path, we must remove the element 
 *    we just added (the state restoration) so that the "skip" path can explore 
 *    CLEANLY from where it started.
 * 
 * 3. Base Cases:
 *    - If target becomes 0: We found a valid combination! Add it to the result list.
 *    - If target < 0 or index == length: The current path is invalid; return.
 * 
 * Complexity:
 * - Time Complexity : O(2^target) - worst case exponential recursion.
 * - Space Complexity: O(target) - Maximum depth of the recursion stack.
 */
import java.util.*;

public class CombinationSum {

    /**
     * Recursive backtracking function to generate combinations.
     */
    static void findCombinations(int index, int[] candidates, int target,
            List<Integer> currentPath, List<List<Integer>> result) {
        // Base Case 1: Target achieved
        if (target == 0) {
            result.add(new ArrayList<>(currentPath)); // Create a copy of current path
            return;
        }

        // Base Case 2: Out of bounds or target exceeded
        if (index == candidates.length || target < 0) {
            return;
        }

        // --- Choice 1: Include the current candidate ---
        // Add to path
        currentPath.add(candidates[index]);
        // Recurse: Note we stay at the same 'index' because we can reuse elements
        findCombinations(index, candidates, target - candidates[index], currentPath, result);

        // --- Backtrack ---
        // Crucial: remove the last element to restore state for the next recursive
        // branch
        currentPath.remove(currentPath.size() - 1);

        // --- Choice 2: Exclude the current candidate ---
        // Move to the next index without reducing the target
        findCombinations(index + 1, candidates, target, currentPath, result);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Combination Sum Finder ---");
        System.out.print("Number of candidate elements: ");
        int n = sc.nextInt();

        int[] candidates = new int[n];
        System.out.print("Enter " + n + " unique candidates: ");
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }

        System.out.print("Enter target sum: ");
        int target = sc.nextInt();

        // Execution
        List<List<Integer>> result = new ArrayList<>();
        findCombinations(0, candidates, target, new ArrayList<>(), result);

        // Output results
        System.out.println("\n--- Unique Combinations ---");
        if (result.isEmpty()) {
            System.out.println("No combinations found for target " + target);
        } else {
            for (List<Integer> combo : result) {
                System.out.println(combo);
            }
        }

        sc.close();
    }
}
