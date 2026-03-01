
/**
 * Find Two Elements That Occur an Odd Number of Times
 * 
 * Problem: Given an array where all elements appear an even number of times 
 * except for TWO elements which appear an odd number of times, find those two elements.
 * 
 * Logic:
 * 1. Calculate the XOR sum of all elements. Let this be 'xorSum'.
 *    Because only two elements (x and y) occur odd times, xorSum = x ^ y.
 * 2. Find the rightmost set bit in 'xorSum'. Let this be 'rightmostBit'.
 *    Since x and y are different (x ^ y != 0), they must differ in at least 
 *    one bit position. This 'rightmostBit' identifies one such position.
 * 3. Divide all numbers in the array into two groups:
 *    - Group 1: Numbers that have the 'rightmostBit' set.
 *    - Group 2: Numbers that do NOT have the 'rightmostBit' set.
 * 4. XOR all elements in Group 1 to result in 'x'.
 * 5. XOR all elements in Group 2 to result in 'y'.
 *    This works because all even-occurring numbers in each group will cancel out, 
 *    leaving only the odd-occurring ones (x and y) in their respective groups.
 * 
 * Time Complexity : O(n) - Two traversals (one for total XOR, one for partitioning).
 * Space Complexity: O(1) - Constant space used for XOR variables.
 */
import java.util.*;

public class TwoOddOccurrences {

    /**
     * Finds the two elements appearing an odd number of times.
     * 
     * @param arr Input array
     * @return Array containing the two odd-occurring elements
     */
    static int[] findTwoOdd(int[] arr) {
        // Step 1: XOR all elements to get (x ^ y)
        int xorSum = 0;
        for (int num : arr) {
            xorSum ^= num;
        }

        // Step 2: Extract the rightmost set bit
        // Property: (n & -n) isolates the lowest set bit.
        int rightmostBit = xorSum & (-xorSum);

        // Step 3: Partition and XOR each group
        int x = 0;
        int y = 0;
        for (int num : arr) {
            // Check if current number has the bit set
            if ((num & rightmostBit) != 0) {
                // Group with bit set
                x ^= num;
            } else {
                // Group without bit set
                y ^= num;
            }
        }

        return new int[] { x, y };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter number of elements (at least 2): ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        // Perform calculation
        int[] result = findTwoOdd(arr);

        // Output results
        System.out.println("--- Dual Odd Occurrence Analysis ---");
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("The two elements appearing an odd number of times are: " + result[0] + " and " + result[1]);

        sc.close();
    }
}
