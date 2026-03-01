
/**
 * Power Set Generation using Bit Manipulation
 * 
 * Problem: Given a set (represented as an array), generate all possible 
 * subsets (the Power Set).
 * 
 * Logic:
 * - A set of size 'n' has 2^n total subsets (including the empty set).
 * - We can map each subset to a binary number (mask) from 0 to (2^n - 1).
 * - In a mask, if the i-th bit is set (1), it means the i-th element of 
 *   the array is included in that particular subset.
 * - Example: n=3, elements={a, b, c}
 *   Mask 5 (binary 101) -> includes elements at index 0 and 2 -> {a, c}.
 * 
 * Time Complexity : O(n * 2^n) - 2^n subsets, each taking O(n) to construct.
 * Space Complexity: O(n * 2^n) - To store all subsets.
 */
import java.util.*;

public class PowerSet {

    /**
     * Generates all subsets of the given array.
     * 
     * @param arr Input array
     * @return List of all subsets (each subset is a list of integers)
     */
    static List<List<Integer>> generatePowerSet(int[] arr) {
        int n = arr.length;
        int totalSubsets = 1 << n; // 2^n
        List<List<Integer>> powerSet = new ArrayList<>();

        // Iterate through all possible binary combinations (masks)
        for (int mask = 0; mask < totalSubsets; mask++) {
            List<Integer> subset = new ArrayList<>();

            // Check each bit of the current mask
            for (int i = 0; i < n; i++) {
                // If the i-th bit is set, include arr[i] in the subset
                if ((mask & (1 << i)) != 0) {
                    subset.add(arr[i]);
                }
            }
            powerSet.add(subset);
        }
        return powerSet;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input gathering
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        // Perform calculation
        List<List<Integer>> ps = generatePowerSet(arr);

        // Output results
        System.out.println("\n--- Power Set Generation ---");
        System.out.println("Input Set: " + Arrays.toString(arr));
        System.out.println("Number of subsets: " + ps.size());

        System.out.println("\nSubsets:");
        for (List<Integer> subset : ps) {
            System.out.println(subset);
        }

        sc.close();
    }
}
