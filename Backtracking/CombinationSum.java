
// Combination Sum - Find all unique combinations that sum to target
// Time Complexity: O(2^target) | Space Complexity: O(target)
import java.util.*;

public class CombinationSum {

    static void findCombinations(int index, int[] candidates, int target, List<Integer> current,
            List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (index == candidates.length || target < 0) {
            return;
        }

        // Include the candidate
        current.add(candidates[index]);
        findCombinations(index, candidates, target - candidates[index], current, result);

        // Exclude the candidate (backtrack)
        current.remove(current.size() - 1);
        findCombinations(index + 1, candidates, target, current, result);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of candidates: ");
        int n = sc.nextInt();
        int[] candidates = new int[n];
        System.out.print("Enter " + n + " candidates: ");
        for (int i = 0; i < n; i++)
            candidates[i] = sc.nextInt();

        System.out.print("Enter target sum: ");
        int target = sc.nextInt();

        List<List<Integer>> result = new ArrayList<>();
        findCombinations(0, candidates, target, new ArrayList<>(), result);

        System.out.println("Combinations: " + result);

        sc.close();
    }
}
