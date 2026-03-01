
/**
 * All Divisors of a Number
 * 
 * Problem: Find all integers that divide a given number 'n' exactly.
 * 
 * Logic: Divisors occur in pairs.
 * - If 'i' is a divisor of 'n', then (n / i) is also a divisor.
 * - One of the divisors in a pair must be <= sqrt(n) and the other >= sqrt(n).
 * - Therefore, we only need to iterate from 1 up to sqrt(n).
 * 
 * Example: n = 12
 * - i=1: (1, 12)
 * - i=2: (2, 6)
 * - i=3: (3, 4)
 * - sqrt(12) is approx 3.46, so we stop at 3.
 * 
 * Time Complexity : O(sqrt(n) + D log D) where D is number of divisors.
 * Space Complexity: O(D).
 */
import java.util.*;

public class AllDivisors {

    /**
     * Efficiently finds all divisors of 'n'.
     * 
     * @param n Input number
     * @return Sorted list of all divisors
     */
    static List<Integer> getDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();

        // Iterate only up to the square root of n
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                // If i divides n, add i
                divisors.add(i);

                // Also add the quotient n/i if it is distinct from i
                if (i != n / i) {
                    divisors.add(n / i);
                }
            }
        }

        // Sorting is optional but usually preferred for display
        Collections.sort(divisors);
        return divisors;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter an integer: ");
        int n = sc.nextInt();

        // Perform calculation
        List<Integer> divs = getDivisors(n);

        // Output results
        System.out.println("--- Divisor Analysis ---");
        System.out.println("Number: " + n);
        System.out.println("Total Divisors: " + divs.size());
        System.out.println("All Divisors:   " + divs);

        sc.close();
    }
}
