
/**
 * Print 1 to N and N to 1 using Recursion
 * 
 * Problem: Use recursion to print numbers in ascending and descending order.
 * 
 * Key Concept: Head vs Tail Recursion
 * 1. N to 1 (Tail-ish Recursion):
 *    We print the current number FIRST, then recurse for n-1. 
 *    This resulted in Descending Order.
 * 2. 1 to N (Head-ish Recursion):
 *    We recurse for n-1 FIRST, then print the current number.
 *    The printing happens when the recursion stack starts unfolding.
 *    This resulted in Ascending Order.
 * 
 * Time Complexity : O(n) - One call for each number from n down to 1.
 * Space Complexity: O(n) - Due to the recursion stack of depth n.
 */
import java.util.*;

public class PrintOneToN {

    /**
     * Prints numbers from 1 up to N.
     * Logic: Recurse first, then print (Head Recursion style).
     * 
     * @param n The upper limit
     */
    static void print1ToN(int n) {
        // Base case: stop when n reaches 0
        if (n == 0)
            return;

        // Recurse for the smaller number first
        print1ToN(n - 1);

        // Print after returning from recursion (Ascending)
        System.out.print(n + " ");
    }

    /**
     * Prints numbers from N down to 1.
     * Logic: Print first, then recurse (Tail Recursion style).
     * 
     * @param n The starting number
     */
    static void printNTo1(int n) {
        // Base case
        if (n == 0)
            return;

        // Print the current number first (Descending)
        System.out.print(n + " ");

        // Recurse for the smaller number
        printNTo1(n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input gathering
        System.out.print("Enter value of N: ");
        int n = sc.nextInt();

        // Demonstrate both orders
        System.out.println("--- Recursive Printing ---");

        System.out.print("1 to " + n + " (Ascending):  ");
        print1ToN(n);

        System.out.print("\n" + n + " to 1 (Descending): ");
        printNTo1(n);
        System.out.println();

        sc.close();
    }
}
