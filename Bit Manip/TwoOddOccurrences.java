
/**
 * Find Two Elements That Occur an Odd Number of Times
 * Uses XOR + rightmost-set-bit partitioning.
 * Time: O(n) | Space: O(1)
 */
import java.util.*;

public class TwoOddOccurrences {

    static int[] findTwoOdd(int[] arr) {
        // XOR all to get x ^ y (the two odd-occurring elements)
        int xor = 0;
        for (int num : arr)
            xor ^= num;

        // Rightmost set bit differentiates x and y
        int bit = xor & (-xor);

        // Partition elements by that bit and XOR each group
        int x = 0, y = 0;
        for (int num : arr) {
            if ((num & bit) != 0)
                x ^= num;
            else
                y ^= num;
        }
        return new int[] { x, y };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        int[] res = findTwoOdd(arr);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Two odd-occurring elements: " + res[0] + " and " + res[1]);

        sc.close();
    }
}
