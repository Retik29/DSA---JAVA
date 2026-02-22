
// Factorial — Iterative and Recursive
// Time: O(n) | Space: O(1) iter, O(n) recursive
import java.util.*;

public class Factorial {

    // Iterative
    static long factIterative(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++)
            result *= i;
        return result;
    }

    // Recursive
    static long factRecursive(int n) {
        return (n <= 1) ? 1 : n * factRecursive(n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        System.out.println(n + "! (iterative) = " + factIterative(n));
        System.out.println(n + "! (recursive) = " + factRecursive(n));

        sc.close();
    }
}