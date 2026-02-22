
// LCM of Two Numbers — LCM(a,b) = (a / GCD(a,b)) * b
// Time: O(log(min(a,b))) | Space: O(1)
import java.util.*;

public class LCMOfTwoNumbers {

    // Euclidean GCD
    static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    // LCM using GCD — divide first to avoid overflow
    static long lcm(long a, long b) {
        return (a == 0 || b == 0) ? 0 : Math.abs(a / gcd(a, b) * b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter two numbers (a b): ");
        long a = sc.nextLong(), b = sc.nextLong();

        System.out.println("GCD(" + a + ", " + b + ") = " + gcd(a, b));
        System.out.println("LCM(" + a + ", " + b + ") = " + lcm(a, b));
        System.out.println("Verify: GCD × LCM = " + (gcd(a, b) * lcm(a, b)) + ", a × b = " + (a * b));

        sc.close();
    }
}
