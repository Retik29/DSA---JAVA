
/**
 * Prime Factorization
 * 
 * Problem: Find the prime numbers that multiply together to give the original 
 * number 'n'.
 * 
 * Algorithm: Trial Division with optimizations
 * 1. Divide by 2 repeatedly until 'n' is odd.
 * 2. Start checking from i = 3 up to sqrt(n), incrementing by 2 (checking only odd divisors).
 * 3. After the loop, if 'n' is still greater than 1, 'n' itself is the last prime factor.
 * 
 * Includes:
 * - All Factors: Lists every prime factor (e.g., 12 -> 2, 2, 3).
 * - Unique Factors: Lists only distinct prime factors (e.g., 12 -> 2, 3).
 * - Compact Factorization: Represents factors with exponents (e.g., 12 -> 2^2 * 3^1).
 * - Smallest/Largest Prime Factor: Identifies extreme prime factors.
 * 
 * Time Complexity : O(sqrt(n)) on average.
 * Space Complexity: O(log n) - To store the list of prime factors.
 */
import java.util.*;

public class PrimeFactors {

    /**
     * Finds all prime factors of a number including repetitions.
     * 
     * @param n Input number
     * @return List of all prime factors
     */
    static List<Integer> primeFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        if (n <= 1)
            return factors;

        // Step 1: Handle factors of 2
        while (n % 2 == 0) {
            factors.add(2);
            n /= 2;
        }

        // Step 2: Handle odd factors from 3 up to sqrt(n)
        for (int i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }

        // Step 3: If n is still > 1, the remaining n is prime
        if (n > 1)
            factors.add(n);

        return factors;
    }

    /**
     * Finds only the unique prime factors of a number.
     * 
     * @param n Input number
     * @return List of unique prime factors
     */
    static List<Integer> uniqueFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        if (n <= 1)
            return factors;

        // Handle 2
        if (n % 2 == 0) {
            factors.add(2);
            while (n % 2 == 0)
                n /= 2;
        }

        // Handle odd factors
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                factors.add(i);
                while (n % i == 0)
                    n /= i;
            }
        }

        if (n > 1)
            factors.add(n);
        return factors;
    }

    /**
     * Generates prime factorization with exponents (compact representation).
     * 
     * @param n Input number
     * @return Map where Key is prime and Value is its exponent
     */
    static Map<Integer, Integer> factorization(int n) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        if (n <= 1)
            return map;

        // Count factors of 2
        int count = 0;
        while (n % 2 == 0) {
            count++;
            n /= 2;
        }
        if (count > 0)
            map.put(2, count);

        // Count odd factors
        for (int i = 3; i * i <= n; i += 2) {
            count = 0;
            while (n % i == 0) {
                count++;
                n /= i;
            }
            if (count > 0)
                map.put(i, count);
        }

        if (n > 1)
            map.put(n, 1);
        return map;
    }

    /**
     * Finds the smallest prime factor of n.
     */
    static int smallestPF(int n) {
        if (n <= 1)
            return -1;
        if (n % 2 == 0)
            return 2;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return i;
        }
        return n;
    }

    /**
     * Finds the largest prime factor of n.
     */
    static long largestPF(long n) {
        if (n <= 1)
            return -1;
        long largest = -1;

        while (n % 2 == 0) {
            largest = 2;
            n /= 2;
        }
        for (long i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                largest = i;
                n /= i;
            }
        }
        if (n > 1)
            largest = n;
        return largest;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter an integer: ");
        int n = sc.nextInt();

        // Output results
        System.out.println("--- Prime Factorization Summary ---");
        System.out.println("Input Number: " + n);

        System.out.println("\nAll Factors (Repeated): " + primeFactors(n));
        System.out.println("Unique Factors:        " + uniqueFactors(n));

        // Format and display factorization map
        Map<Integer, Integer> fm = factorization(n);
        StringJoiner sj = new StringJoiner(" * ");
        fm.forEach((p, e) -> sj.add(e == 1 ? "" + p : p + "^" + e));
        System.out.println("Compact Form:          " + sj.toString());

        System.out.println("\nExtremes:");
        System.out.println("Smallest Prime Factor: " + smallestPF(n));
        System.out.println("Largest Prime Factor:  " + largestPF(n));

        sc.close();
    }
}
