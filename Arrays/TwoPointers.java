
// Two Pointers — pair with given sum in sorted array
// Time: O(n) | Space: O(1)
import java.util.*;

public class TwoPointers {

    // Find pair in sorted array that sums to target
    static int[] twoSum(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;// Start with two pointers at the ends of the array.lo and hi will move towards each other until they meet.
        while (lo < hi) {
            int sum = arr[lo] + arr[hi];
            if (sum == target)
                return new int[] { lo, hi };
            else if (sum < target)
                lo++;
            else
                hi--;
        }
        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter sorted array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " sorted elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.print("Enter target sum: ");
        int target = sc.nextInt();

        int[] res = twoSum(arr, target);
        System.out.println("Array: " + Arrays.toString(arr));
        if (res[0] == -1)
            System.out.println("No pair found");
        else
            System.out.println("Pair: (" + arr[res[0]] + ", " + arr[res[1]] + ") at indices " + Arrays.toString(res));

        sc.close();
    }
}
