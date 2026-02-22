
// Bitwise Operators in Java — AND, OR, XOR, NOT, Shifts
// Time: O(1) | Space: O(1)
import java.util.*;

public class BitwiseOperators {

    // Print binary representation with padding
    static String toBinary(int n, int bits) {
        return String.format("%" + bits + "s", Integer.toBinaryString(n & ((1 << bits) - 1))).replace(' ', '0');
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter two integers (a b): ");
        int a = sc.nextInt(), b = sc.nextInt();
        int bits = Integer.toBinaryString(Math.max(a, b)).length();

        System.out.println("\nOperand A: " + a + " (" + toBinary(a, bits) + ")");
        System.out.println("Operand B: " + b + " (" + toBinary(b, bits) + ")");

        // AND — both bits must be 1
        System.out.println("\na & b  = " + (a & b) + " (" + toBinary(a & b, bits) + ")  [AND]");
        // OR — either bit is 1
        System.out.println("a | b  = " + (a | b) + " (" + toBinary(a | b, bits) + ")  [OR]");
        // XOR — bits are different
        System.out.println("a ^ b  = " + (a ^ b) + " (" + toBinary(a ^ b, bits) + ")  [XOR]");
        // NOT — flips all bits
        System.out.println("~a     = " + (~a) + " (" + toBinary(~a, 32) + ")  [NOT]");

        // Left shift — multiply by 2^k
        System.out.print("\nEnter shift amount k: ");
        int k = sc.nextInt();
        System.out.println("a << " + k + " = " + (a << k) + "  (a × 2^" + k + ")");
        // Right shift — divide by 2^k (preserves sign)
        System.out.println("a >> " + k + " = " + (a >> k) + "  (a / 2^" + k + ")");
        // Unsigned right shift — fills with 0
        System.out.println("a >>> " + k + " = " + (a >>> k) + "  (unsigned)");

        // Common tricks
        System.out.println("\n--- Common Tricks ---");
        System.out.println("Check " + a + " is even? " + ((a & 1) == 0));
        System.out.println("Swap without temp: a=" + a + ", b=" + b);
        int x = a ^ b; // XOR-swap
        int y = x ^ b; // gives a
        x = x ^ y; // gives b
        System.out.println("After swap: a=" + y + ", b=" + x);

        sc.close();
    }
}
