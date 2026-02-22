
// Binary Exponentiation — compute a^n in O(log n)
// Time: O(log n) | Space: O(1) iterative, O(log n) recursive
import java.util.*;

public class BinaryExponentiation {

    // Iterative: square base and halve exponent
    static long power(long base, long exp) {
        long result = 1;
        base = Math.abs(base);
        while (exp > 0) {
            if ((exp & 1) == 1)
                result *= base; // odd exponent
            base *= base;
            exp >>= 1;
        }
        return result;
    }

    // Modular exponentiation: (base^exp) % mod
    static long powerMod(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter base and exponent: ");
        long base = sc.nextLong(), exp = sc.nextLong();

        System.out.println(base + "^" + exp + " = " + power(base, exp));

        System.out.print("Enter mod (0 to skip): ");
        long mod = sc.nextLong();
        if (mod > 0)
            System.out.println(base + "^" + exp + " mod " + mod + " = " + powerMod(base, exp, mod));

        sc.close();
    }
}
