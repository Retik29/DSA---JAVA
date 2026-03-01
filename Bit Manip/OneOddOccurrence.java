
/**
 * Find the only element occurring an odd number of times
 * 
 * Problem: Given an array where all elements appear an even number of times 
 * except one element that appears an odd number of times, find that element.
 * 
 * Logic: XOR (Exclusive OR) Properties
 * 1. x ^ x = 0 (Any number XORed with itself is 0).
 * 2. x ^ 0 = x (Any number XORed with 0 is that number).
 * 3. Commutative and Associative: The order doesn't matter.
 * 
 * Therefore, when we XOR all elements in the array:
 * - All elements that appear an even number of times cancel each other out (result 0).
 * - The single element that appears an odd number of times remains.
 * 
 * Time Complexity : O(n) - Single traversal of the array.
 * Space Complexity: O(1) - Constant space used for the XOR variable.
 */
import java.util.*;

public class OneOddOccurrence {

    /**
     * Finds the element that appears an odd number of times in the array.
     * 
     * @param arr Input array
     * @return The element occurring odd times
     */
    static int findOdd(int[] arr) {
        int xorSum = 0;
        for (int num : arr) {
            // XOR all elements together
            xorSum ^= num;
        }
        return xorSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter number of elements in array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        // Output results
        System.out.println("--- Odd Occurrence Analysis ---");
        System.out.println("Array: " + Arrays.toString(arr));

        int result = findOdd(arr);
        System.out.println("The element that appears an odd number of times is: " + result);

        sc.close();
    }
}
