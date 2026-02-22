
// Factorial of a Number — iterative, recursive, and large factorial
// Time: O(n) | Space: O(1) iterative
import java.util.*;
import java.math.BigInteger;

public class FactorialOfNumber {

    static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++)
            result *= i;
        return result;
    }

    // BigInteger for very large factorials
    static BigInteger factorialBig(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        if (n <= 20)
            System.out.println(n + "! = " + factorial(n));
        else
            System.out.println(n + "! (long overflow, use BigInteger)");
        System.out.println(n + "! (BigInteger) = " + factorialBig(n));

        sc.close();
    }
}
