
// Longest Consecutive Subsequence — HashSet approach
// Time: O(n) | Space: O(n)
import java.util.*;

public class LongestConsecutiveSubsequence {

    static int longestConsecutive(int[] arr) {
        if (arr.length == 0)
            return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : arr)
            set.add(num);

        int maxLen = 1;
        for (int num : set) {
            // Only start counting from sequence beginning
            if (!set.contains(num - 1)) {
                int len = 1;
                while (set.contains(num + len))
                    len++;
                maxLen = Math.max(maxLen, len);
            }
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
        System.out.println("Longest consecutive subsequence length: " + longestConsecutive(arr));

        sc.close();
    }
}
