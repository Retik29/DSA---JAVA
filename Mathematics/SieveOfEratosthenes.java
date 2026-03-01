
/**
 * Sieve of Eratosthenes and its Variations
 * 
 * Includes implementation of:
 * 1. Standard Sieve: Finding all primes up to N.
 * 2. Segmented Sieve: Finding primes within a range [L, R] efficiently.
 * 3. Smallest Prime Factor (SPF): Precomputing the smallest prime factor 
 *    for every number up to N to enable fast prime factorization.
 * 
 * Complexity Summary:
 * - Standard Sieve: Time O(N log log N), Space O(N).
 * - Segmented Sieve: Time O((R-L+1) log log R), Space O(sqrt(R) + (R-L+1)).
 * - SPF Factorization: Precompute SPF in O(N log log N), then factorize 
 *   each number in O(log N).
 */
import java.util.*;

public class SieveOfEratosthenes {

    /**
     * Standard Sieve of Eratosthenes.
     * Logic: Start from 2, mark all its multiples as composite.
     * Move to the next unmarked number and repeat.
     * 
     * @param n Upper limit
     * @return boolean array where true means index is prime
     */
    static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        // Base cases
        if (n >= 0)
            isPrime[0] = false;
        if (n >= 1)
            isPrime[1] = false;

        // Optimization: Start from p*p because smaller multiples already marked
        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                // Mark all multiples of p starting from p*p
                for (int multiple = p * p; multiple <= n; multiple += p)
                    isPrime[multiple] = false;
            }
        }
        return isPrime;
    }

    /**
     * Collects all prime numbers up to n.
     */
    static List<Integer> getPrimes(int n) {
        boolean[] isPrime = sieve(n);
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i])
                primes.add(i);
        }
        return primes;
    }

    /**
     * Segmented Sieve for finding primes in range [low, high].
     * Useful when 'high' is large but the range size (high-low) is small.
     */
    static List<Integer> segmentedSieve(int low, int high) {
        // Step 1: Find all primes up to sqrt(high) using standard sieve
        int limit = (int) Math.sqrt(high) + 1;
        boolean[] baseIsPrime = sieve(limit);

        // Step 2: Create a boolean array for the range [low, high]
        boolean[] rangeIsPrime = new boolean[high - low + 1];
        Arrays.fill(rangeIsPrime, true);

        // Handle edge cases for 0 and 1
        if (low <= 0)
            rangeIsPrime[0 - low] = false;
        if (low <= 1 && high >= 1)
            rangeIsPrime[1 - low] = false;

        // Step 3: Use primes from base sieve to mark multiples in the range
        for (int p = 2; p <= limit; p++) {
            if (baseIsPrime[p]) {
                // Find first multiple of p in range [low, high]
                // and it must be at least p*p
                long start = Math.max((long) p * p, ((long) (low + p - 1) / p) * p);
                for (long j = start; j <= high; j += p)
                    rangeIsPrime[(int) (j - low)] = false;
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = Math.max(2, low); i <= high; i++) {
            if (rangeIsPrime[i - low])
                primes.add(i);
        }
        return primes;
    }

    /**
     * Computes the Smallest Prime Factor (SPF) for all numbers up to n.
     * This is an O(n log log n) precalculation.
     */
    static int[] computeSPF(int n) {
        int[] spf = new int[n + 1];
        for (int i = 0; i <= n; i++)
            spf[i] = i;

        for (int p = 2; p * p <= n; p++) {
            if (spf[p] == p) { // p is prime
                for (int multiple = p * p; multiple <= n; multiple += p) {
                    // Only update if not already set by a smaller prime
                    if (spf[multiple] == multiple)
                        spf[multiple] = p;
                }
            }
        }
        return spf;
    }

    /**
     * Factorize a number n using a precomputed SPF array in O(log n).
     */
    static List<Integer> factorize(int n, int[] spf) {
        List<Integer> factors = new ArrayList<>();
        while (n > 1) {
            factors.add(spf[n]);
            n /= spf[n];
        }
        return factors;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Demonstration 1: Standard Sieve
        System.out.println("--- 1. Standard Sieve ---");
        System.out.print("Enter n for primes up to n: ");
        int n = sc.nextInt();
        List<Integer> primes = getPrimes(n);
        System.out.println("Found " + primes.size() + " primes: " + primes);

        // Demonstration 2: Segmented Sieve
        System.out.println("\n--- 2. Segmented Sieve ---");
        System.out.print("Enter range [low, high]: ");
        int low = sc.nextInt();
        int high = sc.nextInt();
        System.out.println("Primes in range: " + segmentedSieve(low, high));

        // Demonstration 3: SPF Factorization
        System.out.println("\n--- 3. SPF-based Factorization ---");
        System.out.print("Enter number to factorize: ");
        int num = sc.nextInt();
        int[] spf = computeSPF(num);
        System.out.println("Prime Factors of " + num + ": " + factorize(num, spf));

        sc.close();
    }
}
