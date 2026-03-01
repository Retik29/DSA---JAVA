
/**
 * Primality Test (Prime Check)
 * 
 * Problem: Check if a given integer 'n' is a prime number.
 * 
 * Algorithm: Optimized Trial Division
 * 1. Base cases: Numbers < 2 are not prime. 2 and 3 are prime.
 * 2. divisibility by 2 or 3: Eliminates many cases early.
 * 3. 6k ± 1 Rule: 
 *    - All prime numbers greater than 3 can be written in the form 6k ± 1 
 *      (e.g., 5, 7, 11, 13, 17, 19).
 *    - We iterate from 5 up to sqrt(n) with a step of 6.
 *    - In each step, we check divisibility by 'i' (6k-1) and 'i+2' (6k+1).
 * 
 * Time Complexity : O(sqrt(n)) - Much faster than O(n).
 * Space Complexity: O(1).
 */
import java.util.*;

public class PrimeCheck {

    /**
     * Efficiently checks if n is a prime number using 6k±1 optimization.
     * 
     * @param n The input number
     * @return true if prime, false otherwise
     */
    static boolean isPrime(int n) {
        // Numbers less than 2 are not prime
        if (n < 2)
            return false;

        // 2 and 3 are prime
        if (n <= 3)
            return true;

        // Multiples of 2 or 3 are not prime
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        // All other primes are of form 6k ± 1
        // We start i = 5 (6*1 - 1) and increment by 6
        for (int i = 5; i * i <= n; i += 6) {
            // Check divisibility by i (6k-1) and i+2 (6k+1)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter an integer: ");
        int n = sc.nextInt();

        // Output results
        System.out.println("--- Prime Analysis ---");
        System.out.println("Checking " + n + "...");

        if (isPrime(n)) {
            System.out.println("Result: " + n + " IS a prime number.");
        } else {
            System.out.println("Result: " + n + " is NOT a prime number.");
        }

        sc.close();
    }
}
