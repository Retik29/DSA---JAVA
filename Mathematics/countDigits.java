
// Count Digits in a Number — Iterative, Logarithmic, String
// Time: O(d) iterative, O(1) log | Space: O(1)
import java.util.*;

public class countDigits {

    static int countIterative(long n) {
        n = Math.abs(n);
        if (n == 0)
            return 1;
        int c = 0;
        while (n > 0) {
            c++;
            n /= 10;
        }
        return c;
    }

    static int countLog(long n) {
        n = Math.abs(n);
        return (n == 0) ? 1 : (int) Math.log10(n) + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        long n = sc.nextLong();

        System.out.println("Digit count (iterative): " + countIterative(n));
        System.out.println("Digit count (log):       " + countLog(n));
        System.out.println("Digit count (string):    " + String.valueOf(Math.abs(n)).length());

        sc.close();
    }
}
