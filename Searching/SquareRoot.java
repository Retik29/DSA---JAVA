
// Square Root using Binary Search — floor value
// Time: O(log n) | Space: O(1)
import java.util.*;

public class SquareRoot {

    static int sqrt(int n) {
        if (n < 2)
            return n;
        int lo = 1, hi = n / 2, res = 1;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (mid * mid == n)
                return (int) mid;
            else if (mid * mid < n) {
                res = (int) mid;
                lo = (int) mid + 1;
            } else
                hi = (int) mid - 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        int s = sqrt(n);
        System.out.println("Floor(√" + n + ") = " + s);
        System.out.println("Verify: " + s + "² = " + (s * s) + ", " + (s + 1) + "² = " + ((s + 1) * (s + 1)));

        sc.close();
    }
}
