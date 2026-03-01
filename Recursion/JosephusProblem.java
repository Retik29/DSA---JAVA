
/**
 * Josephus Problem
 * 
 * Problem: There are 'n' people standing in a circle waiting to be executed. 
 * Counting begins at a specified point in the circle and proceeds around the 
 * circle in a specified direction. After a specified number of people (k) 
 * are skipped, the next person is executed. The procedure is repeated with 
 * the remaining people, starting with the next person, going in the same 
 * direction and skipping the same number of people, until only one person 
 * remains, and is freed.
 * 
 * Logic:
 * Let J(n, k) be the position of the survivor among 'n' people with every 
 * k-th person eliminated.
 * After the first person is killed, we are left with n-1 people. The next 
 * counting starts from the person who was just after the killed person.
 * This shifts the survivor's relative position by 'k'.
 * Recursive Formula: J(n, k) = (J(n-1, k) + k) % n
 * Base Case: J(1, k) = 0 (If only one person, they are the survivor at index 0).
 * 
 * Time Complexity : O(n)
 * Space Complexity: O(n) for recursive, O(1) for iterative.
 */
import java.util.*;

public class JosephusProblem {

    /**
     * Recursive implementation of Josephus Problem (0-indexed).
     * 
     * @param n Total people
     * @param k Step size for elimination
     * @return 0-indexed position of the survivor
     */
    static int josephus(int n, int k) {
        // Base case: Only 1 person left
        if (n == 1)
            return 0;

        // Recursive step: (Relative position in n-1 circle + shift k) % n
        return (josephus(n - 1, k) + k) % n;
    }

    /**
     * Iterative implementation of Josephus Problem (0-indexed).
     * This avoids stack overflow for large n.
     * 
     * @param n Total people
     * @param k Step size for elimination
     * @return 0-indexed position of the survivor
     */
    static int josephusIterative(int n, int k) {
        int res = 0; // J(1, k)
        for (int i = 2; i <= n; i++) {
            res = (res + k) % i;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter number of people (n) and the elimination step (k): ");
        int n = sc.nextInt();
        int k = sc.nextInt();

        // Perform calculation
        int pos = josephusIterative(n, k);

        // Output results
        System.out.println("--- Josephus Survivor ---");
        System.out.println("People: " + n + ", Step: " + k);
        System.out.println("Survivor (0-indexed): " + pos);
        System.out.println("Survivor (1-indexed): " + (pos + 1));

        sc.close();
    }
}
