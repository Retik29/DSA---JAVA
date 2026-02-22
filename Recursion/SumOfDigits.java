
// Sum of Digits using Recursion
// Time: O(log n) | Space: O(log n) recursion stack
import java.util.*;

public class SumOfDigits {

    // Recursive: add last digit + sum of remaining
    static int sumOfDigits(int n) {
        n = Math.abs(n);
        return (n == 0) ? 0 : (n % 10) + sumOfDigits(n / 10);
    }

    // Digital root: keep summing until single digit
    static int digitalRoot(int n) {
        return (n == 0) ? 0 : (n < 10) ? n : digitalRoot(sumOfDigits(n));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        System.out.println("Sum of digits of " + n + ": " + sumOfDigits(n));
        System.out.println("Digital root: " + digitalRoot(Math.abs(n)));

        sc.close();
    }
}
