
// Check if Number is Power of Two — n & (n-1) trick
// Time: O(1) | Space: O(1)
import java.util.*;

public class PowerOfTwo {

    // Power of 2 has exactly one set bit: n & (n-1) == 0
    static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        System.out.println(n + " is power of 2? " + isPowerOfTwo(n));
        if (isPowerOfTwo(n))
            System.out.println(n + " = 2^" + Integer.numberOfTrailingZeros(n));

        sc.close();
    }
}
