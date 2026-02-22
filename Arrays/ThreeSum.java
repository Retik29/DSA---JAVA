
// Three Sum (3SUM) - Find all unique triplets that sum to zero
// Time Complexity: O(n^2) | Space Complexity: O(1) (ignoring output list)
import java.util.*;

public class ThreeSum {

    static List<List<Integer>> findThreeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for the first element
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int left = i + 1, right = nums.length - 1, target = -nums[i];

                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                        // Skip duplicates for left and right
                        while (left < right && nums[left] == nums[left + 1])
                            left++;
                        while (left < right && nums[right] == nums[right - 1])
                            right--;

                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        List<List<Integer>> triplets = findThreeSum(arr);
        System.out.println("Original Array: " + Arrays.toString(arr));
        System.out.println("Triplets that sum to zero: " + triplets);

        sc.close();
    }
}
