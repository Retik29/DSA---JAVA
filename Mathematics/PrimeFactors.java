
/**
 * Prime Factorization — all factors, unique factors, factorization with exponents,
 * smallest and largest prime factor.
 * Time: O(√n) | Space: O(log n)
 */
import java.util.*;

public class PrimeFactors {

    // All prime factors with repetition
    static List<Integer> primeFactors(int n) {
        List<Integer> f = new ArrayList<>();
        if (n <= 1)
            return f;
        while (n % 2 == 0) {
            f.add(2);
            n /= 2;
        }
        for (int i = 3; i * i <= n; i += 2)
            while (n % i == 0) {
                f.add(i);
                n /= i;
            }
        if (n > 1)
            f.add(n);
        return f;
    }

    // Unique prime factors only
    static List<Integer> uniqueFactors(int n) {
        List<Integer> f = new ArrayList<>();
        if (n <= 1)
            return f;
        if (n % 2 == 0) {
            f.add(2);
            while (n % 2 == 0)
                n /= 2;
        }
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0) {
                f.add(i);
                while (n % i == 0)
                    n /= i;
            }
        if (n > 1)
            f.add(n);
        return f;
    }

    // Factorization as map: prime → exponent
    static Map<Integer, Integer> factorization(int n) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        if (n <= 1)
            return map;
        int cnt = 0;
        while (n % 2 == 0) {
            cnt++;
            n /= 2;
        }
        if (cnt > 0)
            map.put(2, cnt);
        for (int i = 3; i * i <= n; i += 2) {
            cnt = 0;
            while (n % i == 0) {
                cnt++;
                n /= i;
            }
            if (cnt > 0)
                map.put(i, cnt);
        }
        if (n > 1)
            map.put(n, 1);
        return map;
    }

    // Smallest prime factor
    static int smallestPF(int n) {
        if (n <= 1)
            return -1;
        if (n % 2 == 0)
            return 2;
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return i;
        return n; // n itself is prime
    }

    // Largest prime factor
    static long largestPF(long n) {
        if (n <= 1)
            return -1;
        long largest = -1;
        while (n % 2 == 0) {
            largest = 2;
            n /= 2;
        }
        for (long i = 3; i * i <= n; i += 2)
            while (n % i == 0) {
                largest = i;
                n /= i;
            }
        if (n > 1)
            largest = n;
        return largest;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        System.out.println("Prime factors:        " + primeFactors(n));
        System.out.println("Unique prime factors: " + uniqueFactors(n));

        // Display factorization in "p^e × ..." format
        Map<Integer, Integer> fm = factorization(n);
        StringJoiner sj = new StringJoiner(" × ");
        fm.forEach((p, e) -> sj.add(e == 1 ? "" + p : p + "^" + e));
        System.out.println("Factorization:        " + sj);

        System.out.println("Smallest prime factor: " + smallestPF(n));
        System.out.println("Largest prime factor:  " + largestPF(n));

        sc.close();
    }
}
