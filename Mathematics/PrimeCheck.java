
// Prime Check — O(√n) using 6k±1 optimization
// Time: O(√n) | Space: O(1)
import java.util.*;

public class PrimeCheck {

    // 6k±1 rule: all primes > 3 are of the form 6k ± 1
    static boolean isPrime(int n) {
        if (n < 2)
            return false;
        if (n < 4)
            return true; // 2, 3 are prime
        if (n % 2 == 0 || n % 3 == 0)
            return false; // multiples of 2, 3
        for (int i = 5; i * i <= n; i += 6) // check 6k-1 and 6k+1
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();
        System.out.println(n + " is prime? " + isPrime(n));

        // Print primes up to n
        System.out.print("Primes up to " + n + ": ");
        for (int i = 2; i <= n; i++)
            if (isPrime(i))
                System.out.print(i + " ");
        System.out.println();

        sc.close();
    }
}
