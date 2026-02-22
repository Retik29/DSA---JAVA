
// Count Set Bits — Naive, Brian Kernighan's, and Built-in
// Time: O(set bits) for Kernighan | Space: O(1)
import java.util.*;

public class CountSetBits {

    // Naive: check each bit — O(log n)
    static int countNaive(int n) {
        int c = 0;
        while (n > 0) {
            c += (n & 1);
            n >>= 1;
        }
        return c;
    }

    // Kernighan: n & (n-1) drops rightmost set bit — O(k)
    static int countKernighan(int n) {
        int c = 0;
        while (n > 0) {
            n &= (n - 1);
            c++;
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        System.out.println("\nn = " + n + " (" + Integer.toBinaryString(n) + ")");
        System.out.println("Set bits (naive):      " + countNaive(n));
        System.out.println("Set bits (Kernighan):  " + countKernighan(n));
        System.out.println("Set bits (built-in):   " + Integer.bitCount(n));

        sc.close();
    }
}
