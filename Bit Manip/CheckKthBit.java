
/**
 * Check if K-th Bit is Set
 * 
 * Problem: Given a number 'n', determine if its 'k-th' bit (0-indexed from right) is 1.
 * 
 * Methods:
 * 1. Left Shift Method: 
 *    Create a mask by shifting 1 left by k positions (1 << k). 
 *    Perform (n & mask). If result is non-zero, the k-th bit is set.
 * 2. Right Shift Method: 
 *    Shift the number 'n' right by k positions (n >> k). 
 *    Perform ((n >> k) & 1). If result is 1, the k-th bit is set.
 * 
 * Time Complexity : O(1)
 * Space Complexity: O(1)
 */
import java.util.*;

public class CheckKthBit {

    /**
     * Method 1: Generate mask (1 << k) and check via bitwise AND.
     * 
     * @param n The input number
     * @param k The bit position (0-indexed)
     * @return true if k-th bit is 1, false otherwise
     */
    static boolean checkLeftShift(int n, int k) {
        // (1 << k) creates a number with only the k-th bit as 1
        return (n & (1 << k)) != 0;
    }

    /**
     * Method 2: Shift the number itself right by k and check the last bit.
     * 
     * @param n The input number
     * @param k The bit position (0-indexed)
     * @return true if k-th bit is 1, false otherwise
     */
    static boolean checkRightShift(int n, int k) {
        // (n >> k) moves the k-th bit to the 0-th position
        return ((n >> k) & 1) == 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input gathering
        System.out.print("Enter number n: ");
        int n = sc.nextInt();
        System.out.print("Enter bit position k (0-indexed from right): ");
        int k = sc.nextInt();

        // Visualization
        System.out.println("--- Bit Analysis ---");
        System.out.println("Number: " + n);
        System.out.println("Binary: " + Integer.toBinaryString(n));

        // Demonstration
        System.out.println("Bit " + k + " set (Left-Shift Masking)?  " + (checkLeftShift(n, k) ? "YES" : "NO"));
        System.out.println("Bit " + k + " set (Right-Shift Logic)?   " + (checkRightShift(n, k) ? "YES" : "NO"));

        // Debugging loop to show active bits
        System.out.println("\nIterating through bits:");
        String binaryStr = Integer.toBinaryString(n);
        for (int i = binaryStr.length() - 1; i >= 0; i--) {
            if (checkRightShift(n, i)) {
                System.out.print("[Bit " + i + ": SET] ");
            }
        }
        System.out.println();

        sc.close();
    }
}
