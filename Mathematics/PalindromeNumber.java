
// Palindrome Number Check — reverse & half-reverse methods
// Time: O(d) where d = digits | Space: O(1)
import java.util.*;

public class PalindromeNumber {

    // Full reverse approach
    static boolean isPalindromeReverse(int n) {
        if (n < 0)
            return false;
        int orig = n, rev = 0;
        while (n > 0) {
            rev = rev * 10 + n % 10;
            n /= 10;
        }
        return orig == rev;
    }

    // Half reverse approach (avoids overflow)
    static boolean isPalindromeHalf(int n) {
        if (n < 0 || (n % 10 == 0 && n != 0))
            return false;
        int half = 0;
        while (n > half) {
            half = half * 10 + n % 10;
            n /= 10;
        }
        return n == half || n == half / 10; // even or odd digits
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        System.out.println(n + " is palindrome (reverse)?  " + isPalindromeReverse(n));
        System.out.println(n + " is palindrome (half)?     " + isPalindromeHalf(n));

        sc.close();
    }
}
