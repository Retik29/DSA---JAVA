
/**
 * Bitwise Operators in Java
 * 
 * This program demonstrates fundamental bitwise operations and common 
 * bit manipulation tricks.
 * 
 * Operators:
 * 1. & (AND): Result is 1 if both corresponding bits are 1.
 * 2. | (OR): Result is 1 if at least one corresponding bit is 1.
 * 3. ^ (XOR): Result is 1 if corresponding bits are different (exclusive OR).
 * 4. ~ (NOT): Inverts all bits (0 becomes 1 and 1 becomes 0).
 * 5. << (Left Shift): Shifts bits to the left, fills 0 on right (Multiply by 2^k).
 * 6. >> (Signed Right Shift): Shifts bits right, fills sign bit on left (Divide by 2^k).
 * 7. >>> (Unsigned Right Shift): Shifts bits right, fills 0 on left.
 * 
 * Time Complexity : O(1)
 * Space Complexity: O(1)
 */
import java.util.*;

public class BitwiseOperators {

    /**
     * Helper to show binary representation.
     * 
     * @param n    The number
     * @param bits Precision bits
     * @return Formatted string
     */
    static String toBinary(int n, int bits) {
        // Handle masking for 2's complement display
        String binary = Integer.toBinaryString(n);
        if (binary.length() > bits) {
            binary = binary.substring(binary.length() - bits);
        }
        return String.format("%" + bits + "s", binary).replace(' ', '0');
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter two integers (a and b): ");
        int a = sc.nextInt();
        int b = sc.nextInt();

        // Calculate max bit length for display
        int bits = Math.max(8, Integer.toBinaryString(Math.max(Math.abs(a), Math.abs(b))).length());

        System.out.println("\n--- Binary representations ---");
        System.out.println("A: " + a + " (" + toBinary(a, bits) + ")");
        System.out.println("B: " + b + " (" + toBinary(b, bits) + ")");

        // Basic Bitwise Logic
        System.out.println("\n--- Basic Bitwise Operations ---");
        System.out.println("AND (a & b): " + (a & b) + " (" + toBinary(a & b, bits) + ")");
        System.out.println("OR  (a | b): " + (a | b) + " (" + toBinary(a | b, bits) + ")");
        System.out.println("XOR (a ^ b): " + (a ^ b) + " (" + toBinary(a ^ b, bits) + ")");
        System.out.println("NOT (~a):    " + (~a) + " (" + toBinary(~a, 32) + ")");

        // Shifts
        System.out.print("\nEnter shift amount (k): ");
        int k = sc.nextInt();

        System.out.println("\n--- Bitwise Shifts ---");
        // Left Shift: a * 2^k
        System.out.println("Left Shift (a << k):    " + (a << k) + " [Equivalent to a * 2^" + k + "]");

        // Arithmetic Right Shift: a / 2^k (preserves sign)
        System.out.println("Signed Right (a >> k):  " + (a >> k) + " [Equivalent to a / 2^" + k + "]");

        // Logical Right Shift: fills 0s regardless of sign
        System.out.println("Unsigned Right (a >>> k): " + (a >>> k) + " [Always positive filling]");

        // Bitwise Tricks
        System.out.println("\n--- Bit Manipulation Tricks ---");

        // Parity check
        System.out.println("Is " + a + " odd?  " + ((a & 1) != 0 ? "YES" : "NO"));//even odd check via. bitwise AND with 1 
        // ,even no has last bit 0 and odd no has last bit 1

        // Swap using XOR (no temporary variable needed)
        System.out.println("XOR Swap A=" + a + ", B=" + b);
        int x = a;
        int y = b;
        x = x ^ y; // x holds XOR sum
        y = x ^ y; // y = (a ^ b) ^ b = a
        x = x ^ y; // x = (a ^ b) ^ a = b
        System.out.println("Result Swap: A=" + x + ", B=" + y);

        sc.close();
    }
}
