
/**
 * Count Bits to Flip to Convert A to B
 * 
 * Problem: Given two integers A and B, find the number of bits that need 
 * to be flipped to convert A to B.
 * 
 * Example: A = 10 (1010), B = 20 (10100) -> 4 flips needed.
 * 
 * Logic:
 * 1. XOR Operation: Performing (A ^ B) results in a number where bits are 1 
 *    only if the corresponding bits in A and B are different.
 * 2. Hamming Distance: The problem is essentially finding the "Hamming Distance" 
 *    between A and B, which is the count of set bits in (A ^ B).
 * 3. Bit Counting: We use Brian Kernighan's Algorithm to efficiently count 
 *    the number of set bits in the XOR result.
 * 
 * Time Complexity : O(set bits in XOR) -> O(log2 N) in worst case.
 * Space Complexity: O(1).
 */
import java.util.*;

public class CountBitsToConvert {

    /**
     * Calculates the number of bits that differ between A and B.
     * 
     * @param a First number
     * @param b Second number
     * @return Number of bits to flip
     */
    static int countFlips(int a, int b) {
        // Step 1: XOR gives bits that are different (1 where they differ, 0 where same)
        int xorResult = a ^ b;
        int count = 0;

        // Step 2: Use Brian Kernighan's Algorithm to count set bits in XOR result
        while (xorResult > 0) {
            // Clears the rightmost set bit
            xorResult = xorResult & (xorResult - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter first number (A): ");
        int a = sc.nextInt();
        System.out.print("Enter second number (B): ");
        int b = sc.nextInt();

        // Output results
        System.out.println("--- Conversion Analysis ---");
        System.out.println("Binary A: " + String.format("%8s", Integer.toBinaryString(a)).replace(' ', '0'));
        System.out.println("Binary B: " + String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0'));

        int flips = countFlips(a, b);
        System.out.println("\nNumber of bits that need to be flipped: " + flips);

        sc.close();
    }
}
