
/**
 * Square Root (Floor)
 * 
 * Problem: Find the floor value of the square root of a non-negative integer 'n'.
 * Example: n = 15 -> returns 3 (since 3^2 <= 15 and 4^2 > 15).
 * 
 * Algorithm: Binary Search on Range
 * 1. For n < 2, the square root is n itself.
 * 2. Define search range: [1, n/2]. No number's square root (for n > 1) 
 *    is greater than its half.
 * 3. Calculate mid. If mid*mid == n, then mid is the answer.
 * 4. If mid*mid < n, then mid could be the floor; store it in 'res' and 
 *    search the right half for a potentially larger floor value.
 * 5. If mid*mid > n, search the left half.
 * 
 * Note: Use 'long' for mid * mid to prevent integer overflow.
 * 
 * Time Complexity : O(log n)
 * Space Complexity: O(1)
 */
import java.util.*;

public class SquareRoot {

    /**
     * Calculates the floor of the square root of n.
     * 
     * @param n Input number
     * @return Floor root
     */
    static int sqrt(int n) {
        // Base cases
        if (n < 2)
            return n;

        int lo = 1;
        int hi = n / 2;
        int res = 1;

        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            long square = mid * mid;

            if (square == n) {
                return (int) mid; // Exact square root found
            } else if (square < n) {
                // Potential floor value found, search for a larger one
                res = (int) mid;
                lo = (int) mid + 1;
            } else {
                // mid*mid is too large, search in the smaller half
                hi = (int) mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a non-negative integer: ");
        int n = sc.nextInt();

        // Calculate root
        int s = sqrt(n);

        // Output results
        System.out.println("--- Square Root (Floor) ---");
        System.out.println("Floor(√" + n + ") = " + s);
        System.out.println("Verification: " + s + "² = " + (s * s) + " (must be <= " + n + ")");
        System.out.println("Next integer: " + (s + 1) + "² = " + ((long) (s + 1) * (s + 1)) + " (must be > " + n + ")");

        sc.close();
    }
}
