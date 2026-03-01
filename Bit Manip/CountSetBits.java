
/* 
 * Algorithms:
 * 1. Naive Method: 
 *    Iterate through all bits of the number and check if the last bit is 1 
 *    using (n & 1), then shift right (n >>= 1).
 *    Complexity: O(log2 n) - proportional to the number of bits.
 * 2. Brian Kernighan's Algorithm: 
 *    Repeatedly flip the rightmost set bit to 0 using (n & (n - 1)).
 *    The number of iterations equals the number of set bits.
 *    Complexity: O(set bits) - very efficient for sparse numbers.
 * 3. Built-in Method:
 *    Java's Integer.bitCount(n) uses a highly optimized bit-parallel approach.
 * 
 * Time Complexity : O(set bits) for Kernighan's.
 * Space Complexity: O(1).
 */
import java.util.*;

public class CountSetBits {

    /**
     * Naive Method: Checks every bit until the number reaches zero.
     * 
     * @param n Input number
     * @return Count of 1s
     */
    static int countNaive(int n) {
        int count = 0;
        while (n > 0) {
            // Check if last bit is 1
            if ((n & 1) == 1) {
                count++;
            }
            // Move to the next bit
            n >>= 1;
        }
        return count;
    }

    /**
     * Brian Kernighan's Algorithm: Clears the rightmost set bit in each step.
     * Property: (n & (n-1)) always sets the rightmost '1' bit to '0'.
     * 
     * @param n Input number
     * @return Count of 1s
     */
    static int countKernighan(int n) {
        int count = 0;
        while (n > 0) {
            // Clears the rightmost set bit
            n &= (n - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter a positive integer: ");
        int n = sc.nextInt();

        // Output results
        System.out.println("--- Set Bit Counting ---");
        System.out.println("Number: " + n);
        System.out.println("Binary representation: " + Integer.toBinaryString(n));

        System.out.println("\nMethod Results:");
        System.out.println("1. Naive count:       " + countNaive(n));
        System.out.println("2. Kernighan count:   " + countKernighan(n));
        System.out.println("3. Integer.bitCount:  " + Integer.bitCount(n));

        sc.close();
    }
}
