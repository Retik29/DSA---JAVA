
// Check if Kth Bit is Set — Left-shift & Right-shift methods
// Time: O(1) | Space: O(1)
import java.util.*;

public class CheckKthBit {

    // Method 1: create mask (1 << k), AND with n
    static boolean checkLeftShift(int n, int k) {
        return (n & (1 << k)) != 0;
    }

    // Method 2: shift n right by k, check last bit
    static boolean checkRightShift(int n, int k) {
        return ((n >> k) & 1) == 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number n: ");
        int n = sc.nextInt();
        System.out.print("Enter bit position k (0-indexed from right): ");
        int k = sc.nextInt();

        System.out.println("\nn  = " + n + " (" + Integer.toBinaryString(n) + ")");
        System.out.println("Bit " + k + " set (left-shift)?  " + checkLeftShift(n, k));
        System.out.println("Bit " + k + " set (right-shift)? " + checkRightShift(n, k));

        // Show all bits
        System.out.println("\nAll bits of " + n + ":");
        int bits = Integer.toBinaryString(n).length();
        for (int i = bits - 1; i >= 0; i--)
            System.out.print("Bit " + i + "=" + ((n >> i) & 1) + "  ");
        System.out.println();

        sc.close();
    }
}
