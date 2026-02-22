
// Number of bits to flip to convert A to B
// Time Complexity: O(log n) | Space Complexity: O(1)
import java.util.*;

public class CountBitsToConvert {

    static int countFlips(int a, int b) {
        // XOR gives bits that are different
        int xor = a ^ b;
        int count = 0;

        // Count set bits in xor
        while (xor > 0) {
            xor = xor & (xor - 1); // Brian Kernighan's Algorithm
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number (A): ");
        int a = sc.nextInt();
        System.out.print("Enter second number (B): ");
        int b = sc.nextInt();

        System.out.println("Binary A: " + Integer.toBinaryString(a));
        System.out.println("Binary B: " + Integer.toBinaryString(b));
        System.out.println("Number of bits to flip: " + countFlips(a, b));

        sc.close();
    }
}
