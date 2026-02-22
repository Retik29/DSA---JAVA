
// GCD using Euclid's Algorithm — iterative and recursive
// Time: O(log(min(a,b))) | Space: O(1) iterative
import java.util.*;

public class GCDEuclid {

    // Iterative Euclidean
    static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    // Recursive Euclidean
    static int gcdRecursive(int a, int b) {
        return (b == 0) ? Math.abs(a) : gcdRecursive(b, a % b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter two numbers (a b): ");
        int a = sc.nextInt(), b = sc.nextInt();

        System.out.println("GCD(" + a + ", " + b + ") = " + gcd(a, b));

        sc.close();
    }
}
