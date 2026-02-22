
/**
 * Two Sum Problem using HashMap
 * Find indices/pairs in array that add up to a target.
 * Time: O(n) | Space: O(n)
 */
import java.util.*;

public class TwoSum {

    // Returns indices of two numbers that sum to target, or {-1,-1} if none
    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int comp = target - nums[i];
            if (map.containsKey(comp))
                return new int[] { map.get(comp), i };
            map.put(nums[i], i);
        }
        return new int[] { -1, -1 };
    }

    // Counts all pairs (i,j) where i<j and nums[i]+nums[j] == target
    static int countPairs(int[] nums, int target) {
        Map<Integer, Integer> freq = new HashMap<>();
        int count = 0;
        for (int num : nums) {
            count += freq.getOrDefault(target - num, 0);
            freq.merge(num, 1, Integer::sum);
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] nums = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();

        System.out.print("Enter target sum: ");
        int target = sc.nextInt();

        int[] result = twoSum(nums, target);
        System.out.println("Array:   " + Arrays.toString(nums));
        System.out.println("Target:  " + target);
        System.out.println("Indices: " + Arrays.toString(result));
        System.out.println("Pair count with sum " + target + ": " + countPairs(nums, target));

        sc.close();
    }
}
