
// All Divisors of a Number — O(√n) approach
// Time: O(√n) | Space: O(√n)
import java.util.*;

public class AllDivisors {

    // Find divisors in O(√n) — iterate up to √n, add both i and n/i
    static List<Integer> getDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i != n / i)
                    divisors.add(n / i);
            }
        }
        Collections.sort(divisors);
        return divisors;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        List<Integer> divs = getDivisors(n);
        System.out.println("Divisors of " + n + ": " + divs);
        System.out.println("Count: " + divs.size());

        sc.close();
    }
}
