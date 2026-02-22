
// Print 1 to N and N to 1 using Recursion
// Time: O(n) | Space: O(n) recursion stack
import java.util.*;

public class PrintOneToN {

    // Print 1 to N (tail recursion)
    static void print1ToN(int n) {
        if (n == 0)
            return;
        print1ToN(n - 1);
        System.out.print(n + " ");
    }

    // Print N to 1 (head recursion)
    static void printNTo1(int n) {
        if (n == 0)
            return;
        System.out.print(n + " ");
        printNTo1(n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter N: ");
        int n = sc.nextInt();

        System.out.print("1 to " + n + ": ");
        print1ToN(n);
        System.out.print("\n" + n + " to 1: ");
        printNTo1(n);
        System.out.println();

        sc.close();
    }
}
