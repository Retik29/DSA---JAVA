
// Largest and Second Largest in Array — single pass O(n)
// Time: O(n) | Space: O(1)
import java.util.*;

public class LargestSecondLargest {

    // Returns {largest, secondLargest}
    static int[] findBothLargest(int[] arr) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second && num != first)
                second = num;
        }
        return new int[] { first, second == Integer.MIN_VALUE ? -1 : second };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        int[] res = findBothLargest(arr);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Largest: " + res[0] + ", Second largest: " + res[1]);

        sc.close();
    }
}
