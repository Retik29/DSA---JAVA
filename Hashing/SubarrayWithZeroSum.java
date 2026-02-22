
// Subarray with Zero Sum — Prefix sum + HashSet
// Time: O(n) | Space: O(n)
import java.util.*;

public class SubarrayWithZeroSum {

    // Check if subarray with zero sum exists
    static boolean hasZeroSumSubarray(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        seen.add(0); // empty prefix
        int sum = 0;
        for (int num : arr) {
            sum += num;
            if (seen.contains(sum))
                return true;
            seen.add(sum);
        }
        return false;
    }

    // Find longest subarray with zero sum
    static int longestZeroSum(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum))
                maxLen = Math.max(maxLen, i - map.get(sum));
            else
                map.put(sum, i);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Has zero-sum subarray? " + hasZeroSumSubarray(arr));
        System.out.println("Longest zero-sum subarray length: " + longestZeroSum(arr));

        sc.close();
    }
}
