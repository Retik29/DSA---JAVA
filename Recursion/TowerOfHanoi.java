
/**
 * Tower of Hanoi
 * 
 * Problem: Move 'n' disks from a Source rod to a Destination rod using an 
 * Auxiliary rod, following these rules:
 * 1. Only one disk can be moved at a time.
 * 2. Each move consists of taking the upper disk from one of the stacks and 
 *    placing it on top of another stack.
 * 3. No disk may be placed on top of a smaller disk.
 * 
 * Algorithm: Recursion
 * 1. Base Case: If n == 0, no move is needed.
 * 2. Step 1: Move n-1 disks from Source to Auxiliary (using Destination as temp).
 * 3. Step 2: Move the nth (largest) disk directly from Source to Destination.
 * 4. Step 3: Move the n-1 disks from Auxiliary to Destination (using Source as temp).
 * 
 * Time Complexity : O(2^n) - Number of moves is 2^n - 1.
 * Space Complexity: O(n)   - Recursive stack depth is n.
 */
import java.util.*;

public class TowerOfHanoi {

    static int moveCount = 0; // Tracking total moves

    /**
     * Recursive function to solve Tower of Hanoi.
     * 
     * @param n   Number of disks
     * @param src Source rod
     * @param aux Auxiliary rod
     * @param dst Destination rod
     */
    static void solve(int n, char src, char aux, char dst) {
        // Base case: No disks to move
        if (n == 0)
            return;

        // Step 1: Move n-1 disks from source to auxiliary rod
        solve(n - 1, src, dst, aux);

        // Step 2: Move the nth disk from source to destination rod
        System.out.println("Move disk " + n + ": " + src + " → " + dst);
        moveCount++;

        // Step 3: Move the n-1 disks from auxiliary rod to destination rod
        solve(n - 1, aux, src, dst);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input gathering
        System.out.print("Enter number of disks: ");
        int n = sc.nextInt();

        // Solve and output
        System.out.println("--- Tower of Hanoi Solution ---");
        solve(n, 'A', 'B', 'C');

        // Final Summary
        System.out.println("\nSummary:");
        System.out.println("Total moves made: " + moveCount);
        System.out.println("Mathematical check (2^n - 1): " + ((1 << n) - 1));

        sc.close();
    }
}
