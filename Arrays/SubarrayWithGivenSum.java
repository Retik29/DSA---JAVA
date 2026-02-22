
// Subarray with Given Sum — sliding window (non-negative)
// Time: O(n) | Space: O(1)
import java.util.*;

public class SubarrayWithGivenSum {

    // Returns {start, end} or {-1,-1} if not found
    static int[] findSubarray(int[] arr, int target) {
        int sum = 0, start = 0;
        for (int end = 0; end < arr.length; end++) {
            sum += arr[end];
            while (sum > target && start < end)
                sum -= arr[start++];
            if (sum == target)
                return new int[] { start, end };
        }
        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " non-negative elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.print("Enter target sum: ");
        int target = sc.nextInt();

        int[] res = findSubarray(arr, target);
        System.out.println("Array: " + Arrays.toString(arr));
        if (res[0] == -1)
            System.out.println("No subarray found");
        else
            System.out.println("Subarray [" + res[0] + ".." + res[1] + "]: " +
                    Arrays.toString(Arrays.copyOfRange(arr, res[0], res[1] + 1)));

        sc.close();
    }
}
