
// Trailing Zeroes in n! — count factors of 5
// Time: O(log₅ n) | Space: O(1)
import java.util.*;

public class TrailingZeroes {

    static int trailingZeros(int n) {
        int res = 0;
        for (int i = 5; i <= n; i *= 5)
            res += n / i;
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        System.out.println("Trailing zeroes in " + n + "! = " + trailingZeros(n));

        sc.close();
    }
}