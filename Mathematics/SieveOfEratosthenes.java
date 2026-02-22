
/**
 * Sieve of Eratosthenes — find all primes up to n
 * Also includes: Segmented Sieve, Smallest Prime Factor (SPF), SPF-based factorization
 * Time: O(n log log n) | Space: O(n)
 */
import java.util.*;

public class SieveOfEratosthenes {

    // Standard sieve: returns boolean[] where isPrime[i] == true if i is prime
    static boolean[] sieve(int n) {
        boolean[] ip = new boolean[n + 1];
        Arrays.fill(ip, true);
        if (n >= 0)
            ip[0] = false;
        if (n >= 1)
            ip[1] = false;
        for (int p = 2; p * p <= n; p++)
            if (ip[p])
                for (int m = p * p; m <= n; m += p)
                    ip[m] = false;
        return ip;
    }

    // Collect all primes up to n into a list
    static List<Integer> getPrimes(int n) {
        boolean[] ip = sieve(n);
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++)
            if (ip[i])
                primes.add(i);
        return primes;
    }

    // Segmented sieve: primes in range [low, high]
    static List<Integer> segmentedSieve(int low, int high) {
        int lim = (int) Math.sqrt(high) + 1;
        boolean[] base = sieve(lim);

        boolean[] ip = new boolean[high - low + 1];
        Arrays.fill(ip, true);
        if (low <= 0)
            ip[0 - low] = false;
        if (low <= 1 && high >= 1)
            ip[1 - low] = false;

        for (int p = 2; p <= lim; p++)
            if (base[p]) {
                long start = Math.max((long) p * p, ((long) (low + p - 1) / p) * p);
                for (long j = start; j <= high; j += p)
                    ip[(int) (j - low)] = false;
            }

        List<Integer> primes = new ArrayList<>();
        for (int i = Math.max(2, low); i <= high; i++)
            if (ip[i - low])
                primes.add(i);
        return primes;
    }

    // Smallest Prime Factor array via modified sieve
    static int[] computeSPF(int n) {
        int[] spf = new int[n + 1];
        for (int i = 0; i <= n; i++)
            spf[i] = i;
        for (int p = 2; p * p <= n; p++)
            if (spf[p] == p) // p is prime
                for (int m = p * p; m <= n; m += p)
                    if (spf[m] == m)
                        spf[m] = p;
        return spf;
    }

    // Factorize n using precomputed SPF array
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

        // Primes up to n
        System.out.print("Enter n to find primes up to n: ");
        int n = sc.nextInt();
        List<Integer> primes = getPrimes(n);
        System.out.println("Primes up to " + n + ": " + primes);
        System.out.println("Count: " + primes.size());

        // Segmented sieve
        System.out.print("\nEnter low and high for segmented sieve: ");
        int low = sc.nextInt(), high = sc.nextInt();
        System.out.println("Primes in [" + low + ", " + high + "]: " + segmentedSieve(low, high));

        // SPF-based factorization
        System.out.print("\nEnter a number to factorize (using SPF): ");
        int num = sc.nextInt();
        int[] spf = computeSPF(num);
        System.out.println("Prime factors of " + num + ": " + factorize(num, spf));

        sc.close();
    }
}
